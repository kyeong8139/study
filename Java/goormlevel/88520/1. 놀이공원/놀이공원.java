import java.io.*;
import java.util.*;

class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int testCase = Integer.parseInt(br.readLine());
		while (--testCase >= 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int k = Integer.parseInt(st.nextToken());
			
			int[][] board = new int[n+1][n+1];
			for (int row = 1; row <= n; row++) {
				st = new StringTokenizer(br.readLine());
				for (int col = 1; col <= n; col++) {
					board[row][col] = Integer.parseInt(st.nextToken());
				}
			}

			for (int row = 1; row <= n; row++) {
				for (int col = 1; col <= n; col++) {
					board[row][col] += board[row-1][col] + board[row][col-1] - board[row-1][col-1];
				}
			}

			int min = n*n;
			for (int row = k; row <= n; row++) {
				for (int col = k; col <= n; col++) {
					int result = board[row][col] - board[row-k][col] - board[row][col-k] + board[row-k][col-k];
					min = Math.min(min, result);
				}
			}
			bw.write(String.valueOf(min));
			bw.newLine();
		}
		bw.flush();
		bw.close();
	}
}