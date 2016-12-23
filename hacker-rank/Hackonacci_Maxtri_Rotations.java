// Hackonacci Matrix Rotations
// @link https://www.hackerrank.com/contests/w27/challenges/hackonacci-matrix-rotations

import java.util.*;

public class Main {
	int[] repeatResult = new int[]{1, 1, 0, 1, 0, 0, 1};
	int hackonacci(long n) {
		return repeatResult[ (int) (n % 7)];
	}

	class Matrix {
		int[][] array;
		int n;
		Matrix(int n) {
			this.n = n;
			array = new int[n+1][n+1];
		}
	}

	class Position {
		int row;
		int column;

		Position(int h, int c) {
			this.row = h;
			this.column =c;
		}
	}

	Matrix calculateMatrix(int n) {
		Matrix result = new Matrix(n);
		for (int i = 1; i <= n; i++)
			for (int j = 1; j<= n; j++) {
				long tmp = (i * j);
				result.array[i][j] = hackonacci(tmp * tmp);
			}
		return result;
	}


	interface Rotateable {
		Position rotate(Position vt, int n);
	}

	class Rotate90 implements Rotateable {
		@Override
		public Position rotate(Position vt, int n) {
			return new Position(vt.column, n + 1 - vt.row);
		}
	}

	class Rotate180 implements Rotateable {
		@Override
		public Position rotate(Position vt, int n) {
			return new Position(n + 1 - vt.row, n + 1 - vt.column);
		}
	}

	class Rotate270 implements Rotateable {
		@Override
		public Position rotate(Position vt, int n) {
			return new Position(n + 1 - vt.column, vt.row);
		}
	}

	int calculateDifferentAfterRotate(Matrix m, Rotateable xoay) {
		int n = m.n;
		int result = 0;
		for (int i = 1; i <= n; i++)
			for (int j = 1; j <= n; j++){
				Position cu = new Position(i, j);
				Position moi = xoay.rotate(cu, n);
				if (m.array[cu.row][cu.column] != m.array[moi.row][moi.column])
					result++;
			}
		return result;
	}

	public int[] process(int n){
		int[] a =  new int[4];
		Matrix m = this.calculateMatrix(n);
		a[0] = 0;
		a[1] = this.calculateDifferentAfterRotate(m, new Rotate90());
		a[2] = this.calculateDifferentAfterRotate(m, new Rotate180());
		a[3] = this.calculateDifferentAfterRotate(m, new Rotate270());

		return a;
	}

	void run() {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int q = in.nextInt();
		int[] kq = this.process(n);

		for(int i = 0; i < q; i++){
			int angle = in.nextInt();
			int tmp = (angle / 90) % 4;
			System.out.println(kq[tmp]);
		}
	}

	public static void main(String[] args) {
		new Main().run();
	}
}
