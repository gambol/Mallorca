/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mallorcatour.grandtorino.mathbot;

import java.util.HashMap;
import java.util.Map;
import mallorcatour.core.bot.LimitType;
import mallorcatour.core.equilator13.PokerEquilatorBrecher;
import mallorcatour.core.equilator13.StreetEquity;
import mallorcatour.game.advice.Advice;
import mallorcatour.game.core.Action;
import mallorcatour.game.core.Card;
import mallorcatour.game.core.Flop;
import mallorcatour.game.core.HoleCards;
import mallorcatour.game.core.PokerStreet;
import mallorcatour.game.core.Spectrum;
import mallorcatour.game.situation.LocalSituation;
import mallorcatour.grandtorino.nn.modeller.BaseVillainModeller;
import mallorcatour.util.CollectionUtils;
import mallorcatour.util.Log;

/**
 *
 * @author Andrew
 */
public class NLGameSolver {

    private final static double NEARLY_ZERO = 0.0001;
    private final static double DEFAULT_POSITIVE_POTENTIAL = 0.1;
    private final static double DEFAULT_NEGATIVE_POTENTIAL = 0.1;
    private final static double TURN_POT_COEFF = 10.3;
    private final static double RIVER_POT_COEFF = 8.65;
    private final static double IP_ADDITIONAL_PROFIT_BB = 0.056;
    private final BaseVillainModeller villainModeller;

    public NLGameSolver(BaseVillainModeller villainModeller) {
        this.villainModeller = villainModeller;
    }

    public Map<Action, Double> onSecondActionPreflop(int heroActions, int heroAggressiveActions,
            int villainActions, int villainAggressiveActions,
            double effectiveStack, double pot, double toCall,
            Spectrum villainSpectrum, HoleCards heroCards,
            Map<HoleCards, StreetEquity> strengthMap, boolean isHeroOnButton,
            double bigBlind) {
        return onSecondActionRecursive(heroActions, heroAggressiveActions,
                villainActions, villainAggressiveActions, effectiveStack, pot,
                toCall, 0, villainSpectrum, new Card[]{}, heroCards,
                strengthMap, isHeroOnButton, PokerStreet.PREFLOP, bigBlind);
    }

    public Map<Action, Double> onSecondActionFlop(int heroActions, int heroAggressiveActions,
            int villainActions, int villainAggressiveActions,
            double effectiveStack, double pot, double toCall,
            Spectrum villainSpectrum, Flop flop, HoleCards heroCards,
            Map<HoleCards, StreetEquity> strengthMap, boolean isHeroOnButton, double bigBlind) {
        return onSecondActionRecursive(heroActions, heroAggressiveActions,
                villainActions, villainAggressiveActions, effectiveStack, pot,
                toCall, 0, villainSpectrum, flop.toArray(), heroCards,
                strengthMap, isHeroOnButton, PokerStreet.FLOP, bigBlind);
    }

    public Map<Action, Double> onSecondActionTurn(int heroActions, int heroAggressiveActions,
            int villainActions, int villainAggressiveActions,
            double effectiveStack, double pot, double toCall,
            Spectrum villainSpectrum, Flop flop, Card turn, HoleCards heroCards,
            Map<HoleCards, StreetEquity> strengthMap, boolean isHeroOnButton, double bigBlind) {
        return onSecondActionRecursive(heroActions, heroAggressiveActions,
                villainActions, villainAggressiveActions, effectiveStack, pot,
                toCall, 0, villainSpectrum,
                new Card[]{flop.first, flop.second, flop.third, turn},
                heroCards, strengthMap, isHeroOnButton, PokerStreet.TURN, bigBlind);
    }

    public Map<Action, Double> onSecondActionRiver(int heroActions, int heroAggressiveActions,
            int villainActions, int villainAggressiveActions,
            double effectiveStack, double pot, double toCall,
            Spectrum villainSpectrum, Flop flop, Card turn, Card river,
            HoleCards heroCards, Map<HoleCards, StreetEquity> strengthMap,
            boolean isHeroOnButton, double bigBlind) {
        return onSecondActionRecursive(heroActions, heroAggressiveActions,
                villainActions, villainAggressiveActions, effectiveStack, pot,
                toCall, 0, villainSpectrum,
                new Card[]{flop.first, flop.second, flop.third, turn, river},
                heroCards, strengthMap, isHeroOnButton, PokerStreet.RIVER, bigBlind);
    }

    private Map<Action, Double> onSecondActionRecursive(int heroActions, int heroAggressiveActions,
            int villainActions, int villainAggressiveActions,
            double effectiveStack, double pot, double toCall,
            double heroInvestment, Spectrum villainSpectrum,
            Card[] board, HoleCards heroCards, Map<HoleCards, StreetEquity> villainStrengthMap,
            boolean isHeroOnButton, PokerStreet street, double bigBlind) {
        Map<Action, Double> result = new HashMap<Action, Double>();
        //if hero folds
        double foldProfit = -heroInvestment;
        result.put(Action.foldAction(), foldProfit);
        //if hero calls
        double strength = PokerEquilatorBrecher.strengthVsSpectrum(heroCards,
                board, villainSpectrum);
        double passiveProfit = strength * (pot - heroInvestment)
                - (1 - strength) * (toCall + heroInvestment);
        if (street == PokerStreet.PREFLOP && passiveProfit > 0) {
            int add = isHeroOnButton ? 1 : -1;
            passiveProfit += add * IP_ADDITIONAL_PROFIT_BB * bigBlind;
        } else if (street == PokerStreet.FLOP || street == PokerStreet.TURN) {
            StreetEquity equity = PokerEquilatorBrecher.equityVsSpectrum(heroCards,
                    board, villainSpectrum);
            double realEquity = StreetEquity.calculateRealEquity(equity);
            double nextStreetPot;
            if (street == PokerStreet.FLOP) {
                nextStreetPot = TURN_POT_COEFF * bigBlind;
            } else {
                nextStreetPot = RIVER_POT_COEFF * bigBlind;
            }
            double additionalPassiveProfit = realEquity * nextStreetPot - nextStreetPot / 2;
            if (toCall != 0) {
                passiveProfit += additionalPassiveProfit;
            }
        }
        result.put(Action.callAction(toCall), passiveProfit);
        //if hero cannot raise
        if (effectiveStack == 0) {
            return result;
        }
        //if hero raises
        Action heroReraiseAction = Action.createRaiseAction(toCall, pot, effectiveStack);
        double amount;
        if (heroReraiseAction.isAllin()) {
            amount = effectiveStack;
        } else {
            amount = heroReraiseAction.getAmount();
        }

        double aggressiveProfit = calculateHeroActionProfit(amount,
                heroActions, heroAggressiveActions,
                villainActions, villainAggressiveActions, effectiveStack, pot,
                toCall, heroInvestment, villainSpectrum, board, heroCards,
                toCall != 0, true, isHeroOnButton, street, villainStrengthMap, bigBlind);

        result.put(heroReraiseAction, aggressiveProfit);
        return result;
    }

    public Map<Action, Double> onFirstActionPreFlop(int heroActions, int heroAggressiveActions,
            int villainActions, int villainAggressiveActions,
            double effectiveStack, double pot, Spectrum villainSpectrum,
            HoleCards heroCards, Map<HoleCards, StreetEquity> strengthMap,
            boolean wasVillainPreviousAggressive, boolean isHeroOnButton, double bigBlind) {
        Map<Action, Double> result = new HashMap<Action, Double>();

        //if hero folds
        double foldProfit = 0;
        result.put(Action.foldAction(), foldProfit);
        //if hero checks
        double callProfit = calculateHeroActionProfit(0, heroActions,
                heroAggressiveActions, villainActions, villainAggressiveActions,
                effectiveStack, pot, /*hero toCall*/ bigBlind / 2, /*hero investment*/ 0,
                villainSpectrum, new Card[]{}, heroCards, wasVillainPreviousAggressive,
                false, isHeroOnButton, PokerStreet.PREFLOP, strengthMap, bigBlind);
        result.put(Action.callAction(bigBlind / 2), callProfit);

        //if hero raises
        double raiseAmount = bigBlind;
        Action heroRaiseAction = Action.createRaiseAction(raiseAmount, -1);

        double aggressiveProfit = calculateHeroActionProfit(raiseAmount, heroActions,
                heroAggressiveActions, villainActions, villainAggressiveActions,
                effectiveStack, pot, bigBlind / 2, 0, villainSpectrum, new Card[]{}, heroCards,
                wasVillainPreviousAggressive, true, isHeroOnButton, PokerStreet.PREFLOP,
                strengthMap, bigBlind);
        result.put(heroRaiseAction, aggressiveProfit);
        return result;
    }

    public Map<Action, Double> onFirstActionFlop(int heroActions, int heroAggressiveActions,
            int villainActions, int villainAggressiveActions,
            double effectiveStack, double pot, Spectrum villainSpectrum,
            Flop flop, HoleCards heroCards, Map<HoleCards, StreetEquity> strengthMap,
            boolean wasVillainPreviousAggressive, boolean isHeroOnButton, double bigBlind) {
        return onFirstAction(heroActions, heroAggressiveActions, villainActions,
                villainAggressiveActions, effectiveStack, pot, villainSpectrum,
                flop.toArray(), heroCards, strengthMap, wasVillainPreviousAggressive,
                isHeroOnButton, PokerStreet.FLOP, bigBlind);
    }

    public Map<Action, Double> onFirstActionTurn(int heroActions, int heroAggressiveActions,
            int villainActions, int villainAggressiveActions,
            double effectiveStack, double pot, Spectrum villainSpectrum,
            Flop flop, Card turn, HoleCards heroCards, Map<HoleCards, StreetEquity> strengthMap,
            boolean wasVillainPreviousAggressive, boolean isHeroOnButton, double bigBlind) {
        return onFirstAction(heroActions, heroAggressiveActions, villainActions,
                villainAggressiveActions, effectiveStack, pot, villainSpectrum,
                new Card[]{flop.first, flop.second, flop.third, turn}, heroCards,
                strengthMap, wasVillainPreviousAggressive,
                isHeroOnButton, PokerStreet.TURN, bigBlind);
    }

    public Map<Action, Double> onFirstActionRiver(int heroActions, int heroAggressiveActions,
            int villainActions, int villainAggressiveActions,
            double effectiveStack, double pot, Spectrum villainSpectrum,
            Flop flop, Card turn, Card river, HoleCards heroCards,
            Map<HoleCards, StreetEquity> strengthMap,
            boolean wasVillainPreviousAggressive, boolean isHeroOnButton, double bigBlind) {
        return onFirstAction(heroActions, heroAggressiveActions, villainActions,
                villainAggressiveActions, effectiveStack, pot, villainSpectrum,
                new Card[]{flop.first, flop.second, flop.third, turn, river},
                heroCards, strengthMap, wasVillainPreviousAggressive, isHeroOnButton,
                PokerStreet.RIVER, bigBlind);
    }

    //effective stack by Andrew
    private Map<Action, Double> onFirstAction(int heroActions, int heroAggressiveActions,
            int villainActions, int villainAggressiveActions,
            double effectiveStack, double pot, Spectrum villainSpectrum,
            Card[] board, HoleCards heroCards,
            Map<HoleCards, StreetEquity> villainStrengthMap,
            boolean wasVillainPreviousAggressive,
            boolean isHeroOnButton, PokerStreet street, double bigBlind) {
        Map<Action, Double> result = new HashMap<Action, Double>();

        //if hero folds
        double foldProfit = 0;
        result.put(Action.foldAction(), foldProfit);
        //if hero checks
        double checkProfit = calculateHeroActionProfit(0, heroActions,
                heroAggressiveActions, villainActions, villainAggressiveActions,
                effectiveStack, pot, 0, 0,
                villainSpectrum, board, heroCards, wasVillainPreviousAggressive,
                false, isHeroOnButton, street, villainStrengthMap, bigBlind);
        result.put(Action.checkAction(), checkProfit);

        //if hero bets
        Action heroBetAction = Action.createBetAction(pot, effectiveStack);
        double betAmount;
        if (heroBetAction.isAllin()) {
            betAmount = effectiveStack;
        } else {
            betAmount = heroBetAction.getAmount();
        }

        double aggressiveProfit = calculateHeroActionProfit(betAmount, heroActions,
                heroAggressiveActions, villainActions, villainAggressiveActions,
                effectiveStack, pot, 0, 0, villainSpectrum, board, heroCards,
                wasVillainPreviousAggressive, true, isHeroOnButton, street,
                villainStrengthMap, bigBlind);

        result.put(heroBetAction, aggressiveProfit);
        return result;
    }

    private double calculateHeroActionProfit(double heroActionAmount,
            int heroActions, int heroAggressiveActions,
            int villainActions, int villainAggressiveActions,
            double effectiveStack, double pot, double heroToCall,
            double heroInvestment, Spectrum villainSpectrum,
            Card[] board, HoleCards heroCards, boolean wasVillainPreviousAggressive,
            boolean wasHeroPreviousAggressive, boolean isHeroOnButton,
            PokerStreet street, Map<HoleCards, StreetEquity> villainStrengthMap, double bigBlind) {

        double villainToCall = heroActionAmount;
        double villainPot = pot + heroToCall + villainToCall;
        double villainEffectiveStack = effectiveStack - villainToCall;
        LocalSituation villainSituation = getVillainSituationWithoutStrength(
                street, !isHeroOnButton, villainToCall, villainPot,
                villainEffectiveStack, heroActions + 1,
                heroAggressiveActions + (wasHeroPreviousAggressive ? 1 : 0),
                villainActions, villainAggressiveActions, wasVillainPreviousAggressive,
                wasHeroPreviousAggressive);
        Log.d("Villain situation: " + villainSituation.toString());
        Spectrum foldSpectrum = new Spectrum();
        Spectrum passiveSpectrum = new Spectrum();
        Spectrum aggressiveSpectrum = new Spectrum();
        //
        processVillainSpectrums(villainSpectrum, foldSpectrum, passiveSpectrum,
                aggressiveSpectrum, villainSituation, villainStrengthMap);
        //
        double villainSpectrumWeight = villainSpectrum.summaryWeight();
        double foldEquity = foldSpectrum.summaryWeight();
        double passiveEquity = passiveSpectrum.summaryWeight();
        double aggressiveEquity = aggressiveSpectrum.summaryWeight();

        if (Math.abs(foldEquity + passiveEquity + aggressiveEquity - villainSpectrumWeight) > NEARLY_ZERO) {
            throw new RuntimeException("Equities must be equals in summary. "
                    + "Fold equity: " + foldEquity + " call equity: " + passiveEquity
                    + " raise equity: " + aggressiveEquity + ". Summary: " + villainSpectrumWeight);
        }
        double ifVillainFoldProfit = (pot - heroInvestment);

        //if villain calls
        double strength = PokerEquilatorBrecher.strengthVsSpectrum(heroCards,
                board, passiveSpectrum);
        double ifVillainPassiveProfit = strength * (pot - heroInvestment + villainToCall)
                - (1 - strength) * (heroInvestment + heroToCall + villainToCall);
        if (street == PokerStreet.PREFLOP && ifVillainPassiveProfit > 0) {
            int add = isHeroOnButton ? 1 : -1;
            ifVillainPassiveProfit += add * IP_ADDITIONAL_PROFIT_BB * bigBlind;
        } else if (street == PokerStreet.FLOP || street == PokerStreet.TURN) {
            StreetEquity equity = PokerEquilatorBrecher.equityVsSpectrum(heroCards,
                    board, passiveSpectrum);
            double realEquity = StreetEquity.calculateRealEquity(equity);
            double nextStreetPot;
            if (street == PokerStreet.FLOP) {
                nextStreetPot = TURN_POT_COEFF * bigBlind;
            } else {
                nextStreetPot = RIVER_POT_COEFF * bigBlind;
            }
            double additionalVillainPassiveProfit = realEquity * nextStreetPot - nextStreetPot / 2;
            if (heroActionAmount != 0) {
                ifVillainPassiveProfit += additionalVillainPassiveProfit;
            }
        }
        if (passiveSpectrum.isEmpty()) {
            ifVillainPassiveProfit = 0;
        }
        //if villain aggressives
        Action villainAggressiveAction = Action.createRaiseAction(villainToCall,
                pot + heroToCall + villainToCall, villainEffectiveStack);

        double villainReraiseAmount;
        if (villainAggressiveAction.isAllin()) {
            villainReraiseAmount = effectiveStack - villainToCall;
        } else {
            villainReraiseAmount = villainAggressiveAction.getAmount();
        }
        double effectiveStackAfterVillainAggressive = effectiveStack - villainToCall - villainReraiseAmount;
        double potAfterVillainAggressive = pot + heroToCall + 2 * villainToCall + villainReraiseAmount;

        double ifVillainAggressiveProfit;
        if (aggressiveEquity == 0) {
            ifVillainAggressiveProfit = 0;
        } else {
            Map<Action, Double> map = onSecondActionRecursive(heroActions + 1,
                    heroAggressiveActions + (wasHeroPreviousAggressive ? 1 : 0),
                    villainActions + 1, villainAggressiveActions + 1,
                    effectiveStackAfterVillainAggressive, potAfterVillainAggressive,
                    villainReraiseAmount, heroInvestment + heroToCall + villainToCall,
                    aggressiveSpectrum, board, heroCards, villainStrengthMap,
                    isHeroOnButton, street, bigBlind);
            ifVillainAggressiveProfit = CollectionUtils.maxValue(map);
        }
        double aggressiveProfit = (foldEquity / villainSpectrumWeight) * ifVillainFoldProfit
                + (passiveEquity / villainSpectrumWeight) * ifVillainPassiveProfit
                + (aggressiveEquity / villainSpectrumWeight) * ifVillainAggressiveProfit;
        Log.d("Villain will fold in " + foldEquity / villainSpectrumWeight + " percents");
        Log.d("Villain will call in " + passiveEquity / villainSpectrumWeight + " percents");
        Log.d("Villain will reraise in " + aggressiveEquity / villainSpectrumWeight + " percents");
        return aggressiveProfit;
    }

    private void processVillainSpectrums(Spectrum villainSpectrum,
            Spectrum foldSpectrum, Spectrum passiveSpectrum,
            Spectrum aggressiveSpectrum, LocalSituation villainSituation,
            Map<HoleCards, StreetEquity> strengthMap) {
        for (HoleCards villainCards : villainSpectrum) {
            StreetEquity equity = strengthMap.get(villainCards);
            double strength = equity.strength;
            villainSituation.setStrength(strength);
            if (villainSituation.getStreet() == LocalSituation.FLOP
                    || villainSituation.getStreet() == LocalSituation.TURN) {
                villainSituation.setPositivePotential(equity.positivePotential);
                villainSituation.setNegativePotential(equity.negativePotential);
            }
            Advice advice = villainModeller.getAdvice(villainSituation, villainCards);
            double oldWeight = villainSpectrum.getWeight(villainCards);
            foldSpectrum.add(villainCards, oldWeight * advice.getFold());
            passiveSpectrum.add(villainCards, oldWeight * advice.getPassive());
            aggressiveSpectrum.add(villainCards, oldWeight * advice.getAggressive());
        }
    }

    private LocalSituation getVillainSituationWithoutStrength(PokerStreet street,
            boolean villainOnButton, double toCall, double pot, double effectiveStack,
            int heroActions, int heroAggressiveActions,
            int villainActions, int villainAggressiveActions,
            boolean wasVillainPreviousAggressive, boolean wasHeroPreviousAggressive) {
        LocalSituation result = null;
        //potOdds
        double potOdds = toCall / (toCall + pot);
        if (street == PokerStreet.PREFLOP) {
            result = new LocalSituation(LocalSituation.PREFLOP, LimitType.NO_LIMIT);
        } else if (street == PokerStreet.FLOP) {
            //equities
            result = new LocalSituation(LocalSituation.FLOP, LimitType.NO_LIMIT);
            result.setPositivePotential(DEFAULT_POSITIVE_POTENTIAL);
            result.setNegativePotential(DEFAULT_NEGATIVE_POTENTIAL);
        } else if (street == PokerStreet.TURN) {
            //equities
            result = new LocalSituation(LocalSituation.TURN, LimitType.NO_LIMIT);
            result.setPositivePotential(DEFAULT_POSITIVE_POTENTIAL);
            result.setNegativePotential(DEFAULT_NEGATIVE_POTENTIAL);
        } else if (street == PokerStreet.RIVER) {
            //equities
            result = new LocalSituation(LocalSituation.RIVER, LimitType.NO_LIMIT);
        }
        result.setLocalOpponentAggresion(heroActions != 0 ? (double) heroAggressiveActions / heroActions : 0);
        result.setLocalAggresion(villainActions != 0 ? (double) villainAggressiveActions / villainActions : 0);
        result.wasOpponentPreviousAggresive(wasHeroPreviousAggressive);
        result.wasHeroPreviousAggresive(wasVillainPreviousAggressive);
        result.setPotOdds(potOdds);
        result.isOnButton(villainOnButton);
        Log.d("Pot: " + pot + " toCall: " + toCall
                + " effectiveStack: " + effectiveStack);
        result.setPotToStackOdds((pot + toCall) / (pot + toCall + effectiveStack));
        result.canRaise(effectiveStack != 0);
        return result;
    }
}
