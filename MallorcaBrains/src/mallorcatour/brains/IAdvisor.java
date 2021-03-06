/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mallorcatour.brains;

import mallorcatour.bot.interfaces.HasName;
import mallorcatour.brains.stats.IPokerStats;
import mallorcatour.core.game.HoleCards;
import mallorcatour.core.game.advice.IAdvice;
import mallorcatour.core.game.interfaces.IPlayerGameInfo;
import mallorcatour.core.game.situation.LocalSituation;

/**
 * 
 * @author Andrew
 */
public interface IAdvisor extends HavingStats, HasName {

	/**
	 * Return advice for action in any situation with hole cards.
	 * 
	 * @param gameInfo
	 *            TODO
	 */
	IAdvice getAdvice(LocalSituation situation, HoleCards cards, IPlayerGameInfo gameInfo);

	public static final IAdvisor UNSUPPORTED = new IAdvisor() {

		@Override
		public IPokerStats getStats() {
			throw new UnsupportedOperationException();
		}

		@Override
		public String getName() {
			throw new UnsupportedOperationException();
		}

		@Override
		public IAdvice getAdvice(LocalSituation situation, HoleCards cards, IPlayerGameInfo gameInfo) {
			return null;
		}
	};
}
