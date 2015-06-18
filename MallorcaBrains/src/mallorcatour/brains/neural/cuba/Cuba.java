package mallorcatour.brains.neural.cuba;

import mallorcatour.brains.HavingStats;
import mallorcatour.brains.neural.IPokerNeurals;
import mallorcatour.brains.stats.PokerStats;
import mallorcatour.core.game.interfaces.IPokerStats;
import mallorcatour.neural.core.NeuralCreator;

import org.neuroph.core.NeuralNetwork;

public class Cuba implements IPokerNeurals, HavingStats {

	private NeuralNetwork<?> preflop, flop, turn, river;

	public Cuba() {
		preflop = NeuralCreator.createPerceptron(new PreflopNeuralInfo());
		flop = NeuralCreator.createPerceptron(new FlopNeuralInfo());
		turn = NeuralCreator.createPerceptron(new TurnNeuralInfo());
		river = NeuralCreator.createPerceptron(new RiverNeuralInfo());
	}

	@Override
	public NeuralNetwork<?> getRiver() {
		return river;
	}

	@Override
	public NeuralNetwork<?> getTurn() {
		return turn;
	}

	@Override
	public NeuralNetwork<?> getFlop() {
		return flop;
	}

	@Override
	public NeuralNetwork<?> getPreflop() {
		return preflop;
	}

	@Override
	public IPokerStats getStats() {
		return new PokerStats(0.92, 0.42, 0.91, 0.29);
	}

}
