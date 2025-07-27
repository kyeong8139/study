import java.io.*;
import java.util.*;

class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());

		long[] money = new long[n+1];
		st = new StringTokenizer(br.readLine());
		for (int day = 1; day <= n; day++) {
			String input = st.nextToken();
			
			boolean isMinus = false;
			if (input.charAt(0) == '-') {
				isMinus = true;
			}
			money[day] = Integer.parseInt(input.substring(1)) * ((isMinus) ? -1 : 1);
			money[day] += money[day - 1];
		}

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());

			long result = money[end] - money[start-1];
			if (result > 0) {
				sb.append("+");
			}
			sb.append(result).append("\n");
		}

		System.out.print(sb);
	}
}