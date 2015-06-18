/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mallorcatour.bot.modeller;

import mallorcatour.bot.AdvisorListener;
import mallorcatour.brains.IAdvisor;
import mallorcatour.brains.neural.NeuralAdvisor;
import mallorcatour.brains.neural.cuba.Cuba;
import mallorcatour.brains.neural.france.France;
import mallorcatour.brains.neural.germany.Germany;
import mallorcatour.brains.neural.gusxensen.GusXensen;
import mallorcatour.brains.stats.PokerStatInfo;
import mallorcatour.brains.stats.StatCalculator;
import mallorcatour.core.game.HoleCards;
import mallorcatour.core.game.advice.IAdvice;
import mallorcatour.core.game.interfaces.IPlayerGameInfo;
import mallorcatour.core.game.interfaces.IPokerStats;
import mallorcatour.core.game.situation.LocalSituation;
import mallorcatour.tools.Log;

/**
 * 
 * @author Andrew
 */
public class PlayerStatModel implements IAdvisor, AdvisorListener {

	public static IAdvisor GUSXENSEN_NL_NEURAL;
	public static IAdvisor CUBA_NL_NEURAL;
	public static IAdvisor GERMANY_NL_NEURAL;
	public static IAdvisor FRANCE_NL_NEURAL;
	public static IAdvisor[] neurals;

	static {
		GusXensen player = new GusXensen();
		GUSXENSEN_NL_NEURAL = new NeuralAdvisor(player, player, "Gus Xensen");
		Cuba cuba = new Cuba();
		CUBA_NL_NEURAL = new NeuralAdvisor(cuba, cuba, "Cuba");
		Germany germany = new Germany();
		GERMANY_NL_NEURAL = new NeuralAdvisor(germany, germany, "Germany");
		France france = new France();
		FRANCE_NL_NEURAL = new NeuralAdvisor(france, france, "France");
		neurals = new IAdvisor[] { GUSXENSEN_NL_NEURAL, CUBA_NL_NEURAL, GERMANY_NL_NEURAL, FRANCE_NL_NEURAL };
	}

	private IAdvisor currentNeural = GERMANY_NL_NEURAL;
	private final String DEBUG_PATH;
	private final PokerStatInfo pokerStatInfo = new PokerStatInfo();
	private boolean chooseNeural = true;
	private int situationCount;
	private int handsCount;

	public PlayerStatModel(String debug) {
		this.DEBUG_PATH = debug;
	}

	public PlayerStatModel(IAdvisor currentAdvisor, boolean choose, String debug) {
		this.DEBUG_PATH = debug;
		this.currentNeural = currentAdvisor;
		this.chooseNeural = choose;
	}

	@Override
	public IAdvice getAdvice(LocalSituation situation, HoleCards cards, IPlayerGameInfo gameInfo) {
		IAdvice result = currentNeural.getAdvice(situation, cards, gameInfo);
		return result;
	}

	private void chooseModellingNeural() {
		double minError = Double.MAX_VALUE;
		PokerStatsDistance distance = new PokerStatsDistance();
		for (IAdvisor neural : neurals) {
			double error = distance.getDistance(this.getStats(), neural.getStats());
			if (error < minError) {
				minError = error;
				currentNeural = neural;
			}
		}
		Log.f(DEBUG_PATH, "Modelling by " + currentNeural.getName());
		Log.f(DEBUG_PATH, "Difference in stats: " + minError);
		Log.d("Modelling by " + currentNeural.getName());
		Log.d("Difference in stats: " + minError);
	}

	@Override
	public String getName() {
		return "Player model";
	}

	@Override
	public IPokerStats getStats() {
		return pokerStatInfo;
	}

	@Override
	public void onAdvice(LocalSituation situation, IAdvice advice) {
		situationCount++;
		StatCalculator.changeStat(situation, advice, pokerStatInfo);
		if (situationCount % 10 == 0 && chooseNeural) {
			chooseModellingNeural();
			Log.f(DEBUG_PATH, "Villain stats: " + pokerStatInfo);
			Log.d("Villain stats: " + pokerStatInfo);
		}
	}

	public void addHandPlayed() {
		handsCount++;
	}

	public int getHandsCount() {
		return handsCount;
	}

}
