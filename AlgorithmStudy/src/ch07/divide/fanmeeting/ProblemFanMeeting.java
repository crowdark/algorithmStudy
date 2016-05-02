package ch07.divide.fanmeeting;

import java.util.Scanner;

import common.ProblemLoader;

public class ProblemFanMeeting {
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
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (scanner != null) {
				scanner.close();
			}
		}
	}
}
