package mallorcatour.bot.math;

import java.util.Map;

import mallorcatour.bot.actionpreprocessor.NLActionPreprocessor;
import mallorcatour.bot.interfaces.IDecisionListener;
import mallorcatour.bot.interfaces.IPlayer;
import mallorcatour.bot.interfaces.IPokerNN;
import mallorcatour.bot.interfaces.ISpectrumListener;
import mallorcatour.bot.interfaces.IVillainModeller;
import mallorcatour.bot.modeller.IProfitCalculator;
import mallorcatour.bot.modeller.NLProfitCalculator;
import mallorcatour.bot.modeller.SpectrumSituationHandler;
import mallorcatour.core.game.Action;
import mallorcatour.core.game.Card;
import mallorcatour.core.game.HoleCards;
import mallorcatour.core.game.LimitType;
import mallorcatour.core.game.PokerStreet;
import mallorcatour.core.game.advice.Advice;
import mallorcatour.core.game.interfaces.IActionPreprocessor;
import mallorcatour.core.game.interfaces.IGameInfo;
import mallorcatour.core.game.situation.LocalSituation;
import mallorcatour.util.Log;

/** 
 * This bot currently does not use global parameters.
 * 
 * @author Andrew
 */
public class MixBot implements IPlayer {

    private IGameInfo gameInfo;  // general game information
	private String heroName, villainName;
    private Card heroCard1, heroCard2;
    private final IActionPreprocessor actionPreprocessor;
    private final BaseAdviceCreatorFromMap adviceCreator;
    private final String DEBUG_PATH;
    private final IPokerNN postflopNN;
    private final SpectrumSituationHandler situationHandler;
    private final StrengthManager strengthManager;
    private final IProfitCalculator profitCalculator;

    public MixBot(IPokerNN postflopNN, IVillainModeller villainModeller,
            ISpectrumListener listener,
            IDecisionListener decisionListener, String debug) {
        this.postflopNN = postflopNN;
		adviceCreator = new AdviceCreatorFromMap();
		strengthManager = new StrengthManager();
		profitCalculator = new NLProfitCalculator(villainModeller);
		situationHandler = new SpectrumSituationHandler(villainModeller, LimitType.NO_LIMIT, true, false, listener,
				decisionListener, strengthManager, debug);
        actionPreprocessor = new NLActionPreprocessor();
        this.DEBUG_PATH = debug;
    }

    /**
     * An event called to tell us our hole cards and seat number
     * @param c1 your first hole card
     * @param c2 your second hole card
     * @param seat your seat number at the table
     */
    public void onHoleCards(Card c1, Card c2, String heroName, String villainName) {
        situationHandler.onHoleCards(c1, c2, heroName, villainName);
        this.heroCard1 = c1;
        this.heroCard2 = c2;
        this.heroName = heroName;
        this.villainName = villainName;
    }

    /**
     * Requests an Action from the player
     * Called when it is the Player's turn to act.
     */
    public Action getAction() {
        Advice advice;
        Action action = null;
        Log.f(DEBUG_PATH, "=========  Decision-making  =========");
        LocalSituation situation = situationHandler.onHeroSituation();
        if (gameInfo.getBankRoll(villainName) == IGameInfo.SITTING_OUT) {
            Log.f(DEBUG_PATH, "Villain is sitting out");
            double percent = 0.5;
            action = Action.createRaiseAction(percent
                    * (gameInfo.getPotSize() + gameInfo.getHeroAmountToCall()), percent);
        } else if (gameInfo.isPostFlop()) {
            advice = postflopNN.getAdvice(situation, new HoleCards(heroCard1, heroCard2));
            action = advice.getAction();
            Log.f(DEBUG_PATH, "Advice: " + advice.toString());
            action = actionPreprocessor.preprocessAction(action, gameInfo, villainName);
        } else {
			// preflop
			Map<Action, Double> map = profitCalculator.getProfitMap(gameInfo, heroName, situation, heroCard1,
					heroCard2, situationHandler.getVillainSpectrum(), strengthManager);
			Log.f(DEBUG_PATH, "Map<Action, Profit>: " + map.toString());
            advice = adviceCreator.create(map);
            action = advice.getAction();
            Log.f(DEBUG_PATH, "Advice: " + advice.toString());
            action = actionPreprocessor.preprocessAction(action, gameInfo, villainName);
        }
        Log.f(DEBUG_PATH, "Action: " + action.toString());
        Log.f(DEBUG_PATH, "=========  End  =========");
        situationHandler.onHeroActed(action);
        return action;
    }

    /**
     * A new betting round has started.
     */
    public void onStageEvent(PokerStreet street) {
    	strengthManager.onStageEvent(street);
        situationHandler.onStageEvent(street);
    }

    /**
     * A new game has been started.
     * @param gi the game stat information
     */
    @Override
    public void onHandStarted(IGameInfo gameInfo) {
        this.gameInfo = gameInfo;
        strengthManager.onHandStarted(gameInfo);
        situationHandler.onHandStarted(gameInfo);
    }

    /**
     * An villain action has been observed.
     */
    public void onVillainActed(Action action, double toCall) {
    	strengthManager.onVillainActed(action, toCall);
        situationHandler.onVillainActed(action, toCall);
    }

	@Override
	public void onHandEnded() {
		strengthManager.onHandEnded();
		situationHandler.onHandEnded();
	}

}
