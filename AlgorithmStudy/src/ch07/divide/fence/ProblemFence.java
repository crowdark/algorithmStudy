package ch07.divide.fence;

import java.util.Arrays;
import java.util.Scanner;

import common.ProblemLoader;

public class ProblemFence {
	static int testCaseNo;
	static int fenceNo;
	static int[] h;

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
				fenceNo = scanner.nextInt();
				h = new int[fenceNo];
				for (int f = 0; f < fenceNo; f++) {
					h[f] = scanner.nextInt();
				}
				// int ret = bruteForce();
				int ret = solve(0, fenceNo - 1);
				System.out.println(ret);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (scanner != null) {
				scanner.close();
			}
		}
	}

	private static int solve(int left, int right) {
		// 1 fence
		if (left == right) {
			return h[left];
		}

		// divide [left, mid] [mid+1, right]
		int mid = (left + right) / 2;

		// solve each case
		int ret = Math.max(solve(left, mid), solve(mid + 1, right));

		// solve mid case
		int lo = mid;
		int hi = mid + 1;
		int height = Math.min(h[lo], h[hi]);

		// consider [mid, mid+1]
		ret = Math.max(ret, height * 2);

		while (left < lo || hi < right) {
			// expend to height is taller.
			if (hi < right && (lo == left || h[lo - 1] < h[hi + 1])) {
				hi++;
				height = Math.min(height, h[hi]);
			} else {
				lo--;
				height = Math.min(height, h[lo]);
			}

			// after expend, calculate
			ret = Math.max(ret, height * (hi - lo + 1));
		}

		return ret;
	}

	public static int bruteForce() {
		int ret = 0;
		for (int left = 0; left < fenceNo; left++) {
			int minHeight = h[left];
			for (int right = left; right < fenceNo; right++) {
				minHeight = Math.min(minHeight, h[right]);
				ret = Math.max(ret, (right - left + 1) * minHeight);
			}
		}
		return ret;
	}
}
