package ch07.divide.example;

import java.util.Arrays;

public class Karatsuba {
	public static void main(String[] args) {
		int[] a = { 1, 0, 1, 9, 9 };
		int[] b = { 2, 0, 9 };
		// int[] c = multiply(a, b);
		int[] c = add(a, b);
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

	public static int[] karatsuba(final int[] a, final int[] b) {
		int an = a.length;
		int bn = b.length;

		if (an < bn) {
			return karatsuba(b, a);
		}

		if (an == 0 || bn == 0) {
			return new int[0];
		}

		if (an <= 50) {
			return multiply(a, b);
		}

		int half = an / 2;

		int[] a0 = new int[half];
		int[] a1 = new int[an - half];
		System.arraycopy(a, 0, a0, 0, half);
		System.arraycopy(a, half + 1, a1, 0, an);

		int bhalf = Math.min(half, bn);
		int[] b0 = new int[bhalf];
		int[] b1 = new int[bn - bhalf];
		System.arraycopy(b, 0, b0, 0, bhalf);
		System.arraycopy(b, bhalf + 1, b1, 0, bn);

		int[] z2 = karatsuba(a1, b1);
		int[] z0 = karatsuba(a0, b0);
		a0 = add(a0, a1);

		return null;
	}

	private static int[] add(int[] a0, int[] a1) {
		int size = 0;
		int a[], b[];
		if (a0.length > a1.length) {
			size = a0.length + 1;
			a = a0;
			b = new int[a0.length];
			System.arraycopy(a1, 0, b, 0, a1.length);
		} else {
			size = a1.length + 1;
			a = a1;
			b = new int[a1.length];
			System.arraycopy(a0, 0, b, 0, a0.length);
		}

		int[] c = new int[size];
		for (int i = 0; i < a.length; i++) {
			c[i] = a[i] + b[i];
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
