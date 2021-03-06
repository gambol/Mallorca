package mallorcatour.tools;

public class Log {
	public static boolean WRITE_TO_ERR = false;
	public static String DEBUG_PATH = "debug.txt";

	public static void d(String log) {
		if (WRITE_TO_ERR) {
			System.err.println(log);
		} else {
			System.out.println(log);
		}
	}

	public static void f(String path, String log) {
		f(0, path, log);
	}

	public static void f(int indent, String path, String log) {
		String prefix = "";
		for (int i = 0; i < indent; i++) {
			prefix += "	";
		}
		log = prefix + log;
		if (WRITE_TO_ERR) {
			System.err.println(log);
		} else {
			MyFileWriter fileWriter = MyFileWriter.prepareForWriting(path, true);
			fileWriter.addToFile(log, true);
			fileWriter.endWriting();
		}
	}

	public static void f(int indent, String log) {
		f(indent, DEBUG_PATH, log);
	}

	public static void f(String log) {
		f(DEBUG_PATH, log);
	}

}
