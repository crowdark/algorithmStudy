package ch06.brute.picnic;

import java.util.Scanner;

import common.ProblemLoader;

public class ProblemPicnic {
	static int testCaseNo = 0;
	static int studentNo = 0;
	static int friendNo = 0;
	static boolean isFriends[][];

	public static void main(String[] args) {
		try {
			String problem = ProblemLoader.getProblem(Thread.currentThread()
					.getStackTrace()[1].getClassName());
			System.out.println("================= Problem =================");
			System.out.println(problem);
			System.out.println("======= result =======");

			Scanner scanner = new Scanner(problem);
			testCaseNo = scanner.nextInt();

			for (int i = 0; i < testCaseNo; i++) {
				studentNo = scanner.nextInt();
				friendNo = scanner.nextInt();
				isFriends = new boolean[studentNo][studentNo];
				boolean[] taken = new boolean[studentNo];

				for (int f = 0; f < friendNo; f++) {
					int a = scanner.nextInt();
					int b = scanner.nextInt();
					isFriends[a][b] = true;
					isFriends[b][a] = true;
				}

				int result = countPairing(taken);
				System.out.println(result);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static int countPairing(boolean[] taken) {
		int firstFree = -1;

		for (int i = 0; i < studentNo; i++) {
			if (!taken[i]) {
				firstFree = i;
				break;
			}
		}

		if (firstFree == -1)
			return 1;
		int ret = 0;

		for (int pairWith = firstFree + 1; pairWith < studentNo; pairWith++) {
			if (!taken[pairWith] && isFriends[firstFree][pairWith]) {
				taken[firstFree] = taken[pairWith] = true;
				ret += countPairing(taken);
				taken[firstFree] = taken[pairWith] = false;
			}
		}

		return ret;
	}
}
