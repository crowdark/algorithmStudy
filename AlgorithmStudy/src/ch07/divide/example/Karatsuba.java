package ch07.divide.example;

import java.util.Arrays;

public class Karatsuba {
	public static void main(String[] args) {
		int[] a = {1, 0, 1};
		int[] b = {2, 0 ,2};
		int[] c = multiply(a, b);
		System.out.println(Arrays.toString(c));
	}

	public static int[] multiply(final int[] a, final int[] b) {
		int[] c = new int[a.length + b.length + 1];
		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < b.length; j++) {
				c[i + j] += a[i] * b[j];
			}
		}
		normalize(c);
		return c;
	}

	private static void normalize(int[] num) {
		// TODO Auto-generated method stub
		for (int i = 0; i < num.length - 1; i++) {
			if (num[i] < 0) {
				int borrow = (Math.abs(num[i]) + 9) / 10;
				num[i + 1] -= borrow;
				num[i] += borrow * 10;
			} else {
				num[i + 1] += num[i] / 10;
				num[i] %= 10;
			}
		}
	}
}
