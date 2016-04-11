package ch06.brute.clocksync;

import java.util.Scanner;

import common.ProblemLoader;

public class ProblemClockSync {
	static final int INF = 9999;
	static final int CLOCK_NUM = 16;
	static final int SWITCH_NUM = 10;
	static final int[][] LINK = {
			{ 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 1, 0, 0, 0, 1, 0, 1, 0, 1, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 1 },
			{ 1, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 1, 0, 1, 0, 0, 0 },
			{ 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1 },
			{ 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1 },
			{ 0, 0, 0, 0, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 1, 1 },
			{ 0, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 1, 1, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0 } };
	static int testCaseNo = 0;
	static int[] clock;

	public static void main(String[] args) {
		String problem;
		Scanner scanner = null;
		try {
			problem = ProblemLoader.getProblem(Thread.currentThread()
					.getStackTrace()[1].getClassName());
			System.out.println("================= Problem =================");
			System.out.println(problem);
			System.out.println("======= result =======");

			scanner = new Scanner(problem);

			testCaseNo = scanner.nextInt();

			// Data allocation
			for (int t = 0; t < testCaseNo; t++) {
				clock = new int[CLOCK_NUM];
				for (int cIndex = 0; cIndex < CLOCK_NUM; cIndex++) {
					clock[cIndex] = scanner.nextInt();
				}
			}

			int rst = solve(0);
			System.out.println(rst);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (scanner != null) {
				scanner.close();
			}
		}
	}

	private static int solve(int sw) {
		if (sw == SWITCH_NUM) {
			return isAlign() ? 0 : INF;
		}

		int ret = INF;
		for (int cnt = 0; cnt < 4; cnt++) {
			ret = Math.min(ret, cnt + solve(sw + 1));
			push(sw);
		}
		return ret;
	}

	private static void push(int pushed) {
		for (int cIndex = 0; cIndex < LINK[pushed].length; cIndex++) {
			if (LINK[pushed][cIndex] == 1) {
				clock[cIndex] += 3;
				if (clock[cIndex] == 15) {
					clock[cIndex] = 3;
				}
			}
		}
	}

	public static boolean isAlign() {
		for (int i = 0; i < clock.length; i++) {
			if (clock[i] != 12) {
				return false;
			}
		}
		return true;
	}
}
