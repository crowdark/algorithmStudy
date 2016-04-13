package ch07.divide.quadtree;

import java.text.StringCharacterIterator;
import java.util.Scanner;

import common.ProblemLoader;

public class ProblemQuadTree {
	static int testCaseNo;

	public static void main(String[] args) {
		Scanner scanner = null;
		try {
			String problem = ProblemLoader.getProblem(Thread.currentThread()
					.getStackTrace()[1].getClassName());
			System.out.println("================= Problem =================");
			System.out.println(problem);
			System.out.println("======= result =======");

			scanner = new Scanner(problem);
			testCaseNo = scanner.nextInt();

			for (int i = 0; i < testCaseNo; i++) {
				String compressed = scanner.next();
				String reversed = "";
				if (compressed.length() == 1
						&& (compressed.charAt(0) == 'w' || compressed.charAt(0) == 'b')) {
					reversed = compressed;
				} else {
					StringCharacterIterator iter = new StringCharacterIterator(
							compressed);
					reversed = reverse(iter);
				}

				System.out.println(reversed);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (scanner != null) {
				scanner.close();
			}
		}
	}

	private static String reverse(StringCharacterIterator iter) {
		String leftUpper = getNext(iter);
		String rightUpper = getNext(iter);
		String leftLower = getNext(iter);
		String rightLower = getNext(iter);

		return leftLower + rightLower + leftUpper + rightUpper;
	}

	private static String getNext(StringCharacterIterator iter) {
		char currnetChar = iter.current();
		iter.next();
		if (currnetChar == StringCharacterIterator.DONE) {
			return "";
		} else {
			if (currnetChar == 'x') {
				return Character.toString(currnetChar) + reverse(iter);
			} else {
				return Character.toString(currnetChar);
			}
		}
	}
}
