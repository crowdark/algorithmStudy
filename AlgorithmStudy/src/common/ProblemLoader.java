package common;

import java.io.File;
import java.util.Scanner;

public class ProblemLoader {

	public static String getProblem(String className) throws Exception {
		File f = new File("problems/" + className + "/problem.txt");

		StringBuilder sb = new StringBuilder();
		Scanner scanner = null;
		try {
			scanner = new Scanner(f);
			if (f.exists()) {
				while (scanner.hasNext()) {
					sb.append(scanner.nextLine()).append("\n");
				}
				int newlineIndex = sb.lastIndexOf("\n");

				if (newlineIndex != -1) {
					sb.deleteCharAt(newlineIndex);
				}

				scanner.close();
				return sb.toString();
			} else {
				throw new Exception("File not exist");
			}

		} catch (Exception e) {
			throw e;
		} finally {
			if (scanner != null) {
				scanner.close();
			}
		}

	}

}
