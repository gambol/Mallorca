package mallorcatour.bot.math;

import java.util.HashMap;

import mallorcatour.core.game.Action;
import mallorcatour.tools.FileUtils;

public class ActionDistribution extends HashMap<Action, RandomVariable> {

	public static class ActionProfit extends SimpleEntry<Action, RandomVariable> {

		public ActionProfit(Action arg0, RandomVariable arg1) {
			super(arg0, arg1);
		}

		/**
		 * 
		 */
		private static final long serialVersionUID = -7986077498025705358L;

	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 3270697821017287382L;

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("{ ");
		for (java.util.Map.Entry<Action, RandomVariable> entry : entrySet()) {
			Action action = entry.getKey();
			builder.append(action);
			builder.append(" = ");
			RandomVariable profit = entry.getValue();
			builder.append(profit.printProfitability(action));
			builder.append(FileUtils.LINE_SEPARATOR);
		}
		builder.append("}");
		return builder.toString();
	}
}