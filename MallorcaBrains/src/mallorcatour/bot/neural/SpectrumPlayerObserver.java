package mallorcatour.bot.neural;

import java.util.HashMap;
import java.util.Map;

import mallorcatour.bot.interfaces.ISpectrumHolder;
import mallorcatour.bot.interfaces.ISpectrumListener;
import mallorcatour.brains.IAdvisor;
import mallorcatour.brains.math.StrengthManager;
import mallorcatour.core.equilator.StreetEquity;
import mallorcatour.core.game.Action;
import mallorcatour.core.game.Card;
import mallorcatour.core.game.HoleCards;
import mallorcatour.core.game.LimitType;
import mallorcatour.core.game.PokerStreet;
import mallorcatour.core.game.advice.IAdvice;
import mallorcatour.core.game.interfaces.IGameInfo;
import mallorcatour.core.game.interfaces.IGameObserver;
import mallorcatour.core.game.interfaces.IPlayerGameInfo;
import mallorcatour.core.game.situation.LocalSituation;
import mallorcatour.core.game.situation.NoStrengthSituationHandler;
import mallorcatour.core.spectrum.Spectrum;
import mallorcatour.util.Log;

public class SpectrumPlayerObserver implements IGameObserver<IGameInfo>, ISpectrumHolder, IHoleCardsObserver {
	private Spectrum spectrum;
	private Spectrum randomSpectrum;
	private IAdvisor model;
	private IGameInfo gameInfo;
	private StrengthManager strengthManager;
	private ISpectrumListener spectrumListener;
	private final String hero;
	private NoStrengthSituationHandler situationHandler;
	private LocalSituation situation;

	public SpectrumPlayerObserver(IAdvisor model, StrengthManager strengthManager, ISpectrumListener spectrumListener,
			String hero) {
		this.hero = hero;
		this.model = model;
		situationHandler = new NoStrengthSituationHandler(LimitType.NO_LIMIT, hero);
		this.strengthManager = strengthManager;
		this.spectrumListener = spectrumListener;
	}

	private Map<HoleCards, IAdvice> calculateAdvices(LocalSituation situation, Action action) {
		Map<HoleCards, IAdvice> advices = new HashMap<HoleCards, IAdvice>();
		for (HoleCards cards : spectrum) {
			StreetEquity equity;
			if (gameInfo.isPreFlop()) {
				equity = strengthManager.preflop.get(cards);
			} else if (gameInfo.isFlop()) {
				equity = strengthManager.flop.get(cards);
				if (equity == null) {
					Log.d("Cards: " + cards);
				}
			} else if (gameInfo.isTurn()) {
				equity = strengthManager.turn.get(cards);
			} else if (gameInfo.isRiver()) {
				equity = strengthManager.river.get(cards);
			} else {
				throw new IllegalStateException("Incorrect street: " + gameInfo.getStage());
			}
			situation.setStrength(equity.strength);
			situation.setPositivePotential(equity.positivePotential);
			situation.setNegativePotential(equity.negativePotential);
			IAdvice advice = null;
			advice = model.getAdvice(situation, cards, null);
			advices.put(cards, advice);
		}
		return advices;
	}

	private void modifySpectrum(LocalSituation situation, Action action) {
		Map<HoleCards, IAdvice> advices = calculateAdvices(situation, action);
		for (HoleCards cards : advices.keySet()) {
			IAdvice advice = advices.get(cards);
			double percent;
			if (action.isFold()) {
				percent = advice.getFold();
			} else if (action.isAggressive()) {
				percent = advice.getAggressive();
			} else {
				percent = advice.getPassive();
			}
			double oldPercent = spectrum.getWeight(cards);
			if (percent == 0) {
				spectrum.remove(cards);
			} else {
				spectrum.add(cards, percent * oldPercent);
			}
		}
		if (spectrum.isEmpty()) {
			spectrum = randomSpectrum;
		}
		spectrumListener.onSpectrumChanged(spectrum, "");
	}

	@Override
	public void onStageEvent(PokerStreet street) {
		situationHandler.onStageEvent(street);
		if (street == PokerStreet.FLOP) {
			spectrum.remove(gameInfo.getFlop().toArray());
		} else if (street == PokerStreet.TURN) {
			spectrum.remove(gameInfo.getTurn());
			randomSpectrum.remove(gameInfo.getTurn());
		} else if (street == PokerStreet.RIVER) {
			spectrum.remove(gameInfo.getRiver());
			randomSpectrum.remove(gameInfo.getRiver());
		}
	}

	@Override
	public void onHandStarted(IGameInfo gameInfo) {
		this.gameInfo = gameInfo;
		situationHandler.onHandStarted(gameInfo);
		spectrum = Spectrum.random();
		randomSpectrum = Spectrum.random();
	}

	@Override
	public void onHandEnded() {
		situationHandler.onHandEnded();
	}

	@Override
	public Spectrum getSpectrum() {
		return spectrum;
	}

	@Override
	public void onHoleCards(Card c1, Card c2) {
		spectrum.remove(c1, c2);
		randomSpectrum.remove(c1, c2);
	}

	@Override
	public void onActed(Action action, double toCall, String name) {
		situationHandler.onActed(action, toCall, name);
		if (name.equals(hero)) {
			modifySpectrum(situation, action);
		}else{
			situation = situationHandler.getSituation();
		}
	}

}