package ch08.dynamic.wildcard;

import java.util.Scanner;

import common.ProblemLoader;

public class ProblemWildCard {
	static int testCaseNo;
	static int cache[][];
	static char[] patternChar;
	static char[] textChar;

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
				String pattern = scanner.next();
				patternChar = pattern.toCharArray();
				
				int n = scanner.nextInt();
				String[] text = new String[n];
				for(int j=0;j<n;j++) {
					cache = new int[101][101];
					text[j] = scanner.next();
					textChar = text[j].toCharArray();
					
					if (match2(0, 0) == 1) {
						System.out.println(String.valueOf(textChar));
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (scanner != null) {
				scanner.close();
			}
		}
	}

	private static int match(int w, int s) {
		int ret = cache[w][s];
		if (ret != 0) return ret;

		while(s < textChar.length && w < patternChar.length && (patternChar[w] == '?' || patternChar[w] == textChar[s])) {
			w++;
			s++;
		}
		
		if (w == patternChar.length ) {
			if (s == textChar.length) {
				return 1; 
			} else {
				return -1;
			}
		}
		
		if(patternChar[w] == '*') {
			for(int skip=0; skip+s <= textChar.length; skip++) {
				if(match(w+1, s+skip) == 1) {
					return 1;
				}
			}
		}
		return -1;
	}
	
	private static int match2(int w, int s) {
		int ret = cache[w][s];
		if (ret != 0) return ret;

		while(s < textChar.length && w < patternChar.length && (patternChar[w] == '?' || patternChar[w] == textChar[s])) {
			return match2(w+1, s+1);
		}
		
		if (w == patternChar.length ) {
			if (s == textChar.length) {
				return 1; 
			} else {
				return -1;
			}
		}
		
		if(patternChar[w] == '*') {
			if (match2(w+1, s) == 1 || (s < textChar.length && match2(w, s+1)== 1)) {
				return 1;
			}
		}
		return -1;
	}
}
