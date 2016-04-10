package ch06.brute.boardcover;

import java.util.Arrays;
import java.util.Scanner;

import common.ProblemLoader;

public class ProblemBoardCover {
	static int testCaseNo = 0;
	static int w = 0;
	static int h = 0;
	static int[][][] coverType = { { { 0, 0 }, { 1, 0 }, { 0, 1 } },
			{ { 0, 0 }, { 0, 1 }, { 1, 1 } }, { { 0, 0 }, { 1, 0 }, { 1, 1 } },
			{ { 0, 0 }, { 1, 0 }, { 1, -1 } } };

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

			for (int t = 0; t < testCaseNo; t++) {
				h = scanner.nextInt();
				w = scanner.nextInt();
				int[][] board = new int[h][w];
				int whiteCount = 0;
				for (int hIndex = 0; hIndex < h; hIndex++) {
					String line = scanner.next();
					for (int wIndex = 0; wIndex < w; wIndex++) {
						if ('#' == line.charAt(wIndex)) {
							board[hIndex][wIndex] = 1;
						} else {
							board[hIndex][wIndex] = 0;
							whiteCount++;
						}
					}
				}
				if (whiteCount % 3 != 0) {
					System.out.println("0");
				} else {
					int count = cover(board);
					System.out.println(count);
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

	private static int cover(int[][] board) {
		int y = -1, x = -1;
		// 채워야 할 녀석을 찾는다
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				if (board[i][j] == 0) {
					y = i;
					x = j;
					break;
				}
			}
			if (y != -1)
				break;
		}

		if (y == -1)
			return 1;
		int ret = 0;
		for (int type = 0; type < 4; type++) {
			if (set(board, y, x, type, 1)) {
				ret += cover(board);
			}
			set(board, y, x, type, -1);
		}
		return ret;
	}

	private static boolean set(int[][] board, int y, int x, int type, int action) {
		// TODO Auto-generated method stub
		boolean ok = true;
		for (int i = 0; i < 3; i++) {
			int ny = y + coverType[type][i][0];
			int nx = x + coverType[type][i][1];

			if (ny < 0 || nx < 0 || ny >= board.length || nx >= board[0].length) {
				ok = false;
			} else if ((board[ny][nx] += action) > 1) {
				ok = false;
			}
		}
		return ok;
	}
}
