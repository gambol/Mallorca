package mallorcatour.bot.math;

import java.util.Map;

import mallorcatour.brains.IAdvisor;
import mallorcatour.brains.math.StrengthManager;
import mallorcatour.core.game.Action;
import mallorcatour.core.game.Card;
import mallorcatour.core.game.HoleCards;
import mallorcatour.core.game.interfaces.IAggressionInfo;
import mallorcatour.core.game.interfaces.IGameInfo;
import mallorcatour.core.spectrum.Spectrum;
import mallorcatour.util.CollectionUtils;
import mallorcatour.util.Log;

/**
 * @deprecated remove fl from bot project.
 * @author andriipanasiuk
 *
 */
@Deprecated
public class FLProfitCalculator implements IProfitCalculator {

	private final FLGameSolver gameSolver;
	private StrengthManager strengthManager;

	public FLProfitCalculator(IAdvisor villainModeller) {
		gameSolver = new FLGameSolver(villainModeller);
	}

	@Override
	public Map<Action, Double> getProfitMap(IGameInfo gameInfo, IAggressionInfo situation,
			Card holeCard1, Card holeCard2, Spectrum villainSpectrum) {
		Log.d("getFLProfitMap. Pot: " + gameInfo.getPotSize());
		int heroActionCount  =situation.getHeroActionCount();
		int heroAggressionCount = situation.getHeroAggresionActionCount();
		int villainAggressionCount = situation.getVillainAggresionActionCount();
		int villainActionCount = situation.getVillainActionCount();
		boolean wasVillainPreviousAggressive = situation.wasVillainPreviousAggresive();
		//TODO add real count of raises on street
		int raisesOnStreetCount = 0;
		if (gameInfo.isPreFlop()) {
			if (gameInfo.onButton()
					&& gameInfo.getHeroAmountToCall() == gameInfo.getBigBlindSize() / 2) {
				Map<Action, Double> map = gameSolver.onFirstActionPreFlop(heroActionCount, heroAggressionCount,
						villainActionCount, villainAggressionCount, gameInfo.getPotSize(), villainSpectrum,
						new HoleCards(holeCard1, holeCard2), strengthManager.preflop, /*
																				 * was
																				 * villain
																				 * aggressive
																				 */false,
						/* is Hero on button */true, gameInfo.getBigBlindSize());
				double profit = CollectionUtils.maxValue(map);
				Log.d("Hero's second action on preflop " + map.toString());
				Log.d("\nHero profit: " + profit);
				return map;
			} else {
				Map<Action, Double> map = gameSolver.onSecondActionPreflop(heroActionCount, heroAggressionCount,
						villainActionCount, villainAggressionCount, gameInfo.getPotSize(),
						gameInfo.getHeroAmountToCall(), villainSpectrum, new HoleCards(holeCard1, holeCard2),
						strengthManager.preflop, gameInfo.onButton(), raisesOnStreetCount,
						gameInfo.getBigBlindSize());
				double profit = CollectionUtils.maxValue(map);
				Log.d("Hero's second action on preflop " + map.toString());
				Log.d("\nHero profit: " + profit);
				return map;
			}
		} else if (gameInfo.isFlop()) {
			if (gameInfo.getHeroAmountToCall() == 0 && !gameInfo.onButton()) {
				Map<Action, Double> map = gameSolver.onFirstActionFlop(heroActionCount, heroAggressionCount,
						villainActionCount, villainAggressionCount, gameInfo.getPotSize(), villainSpectrum, gameInfo.getFlop(),
						new HoleCards(holeCard1, holeCard2), strengthManager.flop,
						wasVillainPreviousAggressive, gameInfo.onButton(), gameInfo
								.getBigBlindSize());
				double profit = CollectionUtils.maxValue(map);
				Log.d("Hero's first action on flop " + map.toString());
				Log.d("\nHero profit: " + profit);
				return map;
			} else {
				Map<Action, Double> map = gameSolver.onSecondActionFlop(heroActionCount, heroAggressionCount,
						villainActionCount, villainAggressionCount, gameInfo.getPotSize(),
						gameInfo.getHeroAmountToCall(), villainSpectrum, gameInfo.getFlop(), new HoleCards(
								holeCard1, holeCard2), strengthManager.flop, gameInfo.onButton(),
						raisesOnStreetCount, gameInfo.getBigBlindSize());
				double profit = CollectionUtils.maxValue(map);
				Log.d("Flop. Second action. " + map.toString());
				Log.d("\nHero profit: " + profit);
				return map;
			}
		} else if (gameInfo.isTurn()) {
			if (gameInfo.getHeroAmountToCall() == 0 && !gameInfo.onButton()) {
				Map<Action, Double> map = gameSolver.onFirstActionTurn(heroActionCount, heroAggressionCount,
						villainActionCount, villainAggressionCount, gameInfo.getPotSize(), villainSpectrum,
						gameInfo.getFlop(), gameInfo.getTurn(), new HoleCards(holeCard1, holeCard2),
						strengthManager.turn, wasVillainPreviousAggressive, gameInfo.onButton(),
						gameInfo.getBigBlindSize());
				double profit = CollectionUtils.maxValue(map);
				Log.d("Hero's first action on turn " + map.toString());
				Log.d("\nHero profit: " + profit);
				return map;
			} else {
				Map<Action, Double> map = gameSolver.onSecondActionTurn(heroActionCount, heroAggressionCount,
						villainActionCount, villainAggressionCount, gameInfo.getPotSize(),
						gameInfo.getHeroAmountToCall(), villainSpectrum, gameInfo.getFlop(),gameInfo.getTurn(),
						new HoleCards(holeCard1, holeCard2), strengthManager.turn,
						gameInfo.onButton(), raisesOnStreetCount, gameInfo.getBigBlindSize());
				double profit = CollectionUtils.maxValue(map);
				Log.d("Turn. Second action. " + map.toString());
				Log.d("\nHero profit: " + profit);
				return map;
			}
		} else if (gameInfo.isRiver()) {
			if (gameInfo.getHeroAmountToCall() == 0 && !gameInfo.onButton()) {
				Map<Action, Double> map = gameSolver.onFirstActionRiver(heroActionCount, heroAggressionCount,
						villainActionCount, villainAggressionCount, gameInfo.getPotSize(), villainSpectrum, gameInfo.getFlop(),
						gameInfo.getTurn(), gameInfo.getRiver(), new HoleCards(holeCard1, holeCard2),
						strengthManager.river, wasVillainPreviousAggressive, gameInfo.onButton(),
						gameInfo.getBigBlindSize());
				double profit = CollectionUtils.maxValue(map);
				Log.d("Hero's first action on river: " + map.toString());
				Log.d("\nHero profit: " + profit);
				return map;
			} else {
				Map<Action, Double> map = gameSolver.onSecondActionRiver(heroActionCount, heroAggressionCount,
						villainActionCount, villainAggressionCount, gameInfo.getPotSize(),
						gameInfo.getHeroAmountToCall(), villainSpectrum, gameInfo.getFlop(),
						gameInfo.getTurn(), gameInfo.getRiver(),
						new HoleCards(holeCard1, holeCard2), strengthManager.river, gameInfo.onButton(),
						raisesOnStreetCount, gameInfo.getBigBlindSize());
				double profit = CollectionUtils.maxValue(map);
				Log.d("River. Second action: " + map.toString());
				Log.d("\nHero profit: " + profit);
				return map;
			}
		} else {
			// no reachable
			throw new RuntimeException();
		}
	}

}