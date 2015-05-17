/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mallorcatour.game.situation;

import mallorcatour.interfaces.ISituationHandler;
import mallorcatour.game.core.Card;
import mallorcatour.game.core.Action;
import mallorcatour.game.core.PokerStreet;
import mallorcatour.core.bot.IGameInfo;
import mallorcatour.core.bot.LimitType;
import mallorcatour.core.equilator13.EquilatorPreflop;
import mallorcatour.core.equilator13.PokerEquilatorBrecher;
import mallorcatour.game.core.PlayerInfo;
import mallorcatour.util.Log;

/**
 *
 * @author Andrew
 */
public class ProfitSituationHandler implements ISituationHandler {

    protected IGameInfo gameInfo;  // general game information
    protected String heroName, villainName;
    private Card heroCard1, heroCard2;
    protected LimitType limitType;
    private double profit;
    private double expectedProfit;
    private double heroToCall;
    private boolean hasFlop;

    public ProfitSituationHandler() {
    }

    /**
     * An event called to tell us our hole cards and seat number
     * @param c1 your first hole card
     * @param c2 your second hole card
     * @param seat your seat number at the table
     */
    public void onHoleCards(Card c1, Card c2, String heroName, String villainName) {
        heroCard1 = c1;
        heroCard2 = c2;
        profit = -1;
        this.villainName = villainName;
        if (gameInfo.getButtonName().equals(heroName)) {
            heroToCall = gameInfo.getBigBlindSize() / 2;
        } else {
            heroToCall = 0;
        }
        hasFlop = false;
    }

    public LocalSituation onHeroSituation() {
        return null;
    }

    /**
     * A new betting round has started.
     */
    public void onStageEvent(PokerStreet street) {
        if (street == PokerStreet.FLOP) {
            hasFlop = true;
            double pot = gameInfo.getPotSize();
            PlayerInfo villainInfo = getVillainInfo();
            double heroPreflopStrength = EquilatorPreflop.strengthByFormula(heroCard1, heroCard2);
            double villainPreflopStrength = EquilatorPreflop.strengthByFormula(
                    villainInfo.getHoleCards().first, villainInfo.getHoleCards().second);
            expectedProfit = pot * heroPreflopStrength / (heroPreflopStrength + villainPreflopStrength) - (pot / 2);
        }
    }

    public boolean hasFlop() {
        return hasFlop;
    }

    /**
     * A new game has been started.
     * @param gi the game stat information
     */
    public void onHandStarted(IGameInfo gameInfo) {
        this.gameInfo = gameInfo;
    }

    /**
     * An villain action has been observed.
     */
    public void onVillainActed(Action action, double toCall) {
        if (action.isFold()) {
            if (!gameInfo.isPreFlop()) {
                Log.d("Hero wins uncontested");
                profit = (gameInfo.getPotSize() - toCall) / 2;
                Log.d("Profit: " + profit);
            }
        } else if (action.isAggressive()) {
            heroToCall = action.getAmount();
        }
    }

    public void beforeHeroAction() {
        //do nothing
    }

    public void onHeroActed(Action action) {
        if (action.isFold()) {
            if (!gameInfo.isPreFlop()) {
                profit = -(gameInfo.getPotSize() - heroToCall) / 2;
                Log.d("Profit: " + profit);
            }
        } else if (action.isPassive()) {
            heroToCall = 0;
        }
    }

    public double getProfit() {
        return profit;
    }

    public double getExpectedProfit() {
        return expectedProfit;
    }

    private PlayerInfo getVillainInfo() {
        PlayerInfo villainInfo = null;
        for (PlayerInfo player : gameInfo.getPlayers()) {
            if (player.getName().equals(villainName)) {
                villainInfo = player;
            }
        }
        return villainInfo;
    }

    public void onHandEnded() {
        if (profit == -1 && hasFlop) {
            double pot = gameInfo.getPotSize();
            int[] heroCards = new int[7];
            int[] villainCards = new int[7];
            heroCards[0] = heroCard1.intValueForBrecher();
            heroCards[1] = heroCard2.intValueForBrecher();

            PlayerInfo villainInfo = getVillainInfo();
            villainCards[0] = villainInfo.getHoleCards().first.intValueForBrecher();
            villainCards[1] = villainInfo.getHoleCards().second.intValueForBrecher();
            for (int i = 2; i < 7; i++) {
                heroCards[i] = gameInfo.getBoard().get(i - 2).intValueForBrecher();
                villainCards[i] = gameInfo.getBoard().get(i - 2).intValueForBrecher();
            }
            int heroCombination = PokerEquilatorBrecher.combination(heroCards);
            int villainCombination = PokerEquilatorBrecher.combination(villainCards);
            if (heroCombination > villainCombination) {
                profit = pot / 2;
            } else if (heroCombination == villainCombination) {
                profit = 0;
            } else {
                profit = -pot / 2;
            }
            Log.d("Profit: " + profit);
        }
    }
}
