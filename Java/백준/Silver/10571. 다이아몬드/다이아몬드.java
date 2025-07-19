import java.util.*;
import java.io.*;

public class Main {

    static class Diamond{
        double weight;
        double value;

        public Diamond (double weight, double value) {
            this.weight = weight;
            this.value = value;
        }

        public boolean isMoreValuable(Diamond target) {
            if (target.weight > this.weight && target.value < this.value) {
                return true;
            }
            return false;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int testCase = Integer.parseInt(br.readLine());

        for (int tc = 0; tc < testCase; tc++) {
            int n = Integer.parseInt(br.readLine());
            Diamond[] diamonds = new Diamond[n];
            for (int i = 0; i < n; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                double weight = Double.parseDouble(st.nextToken());
                double value = Double.parseDouble(st.nextToken());

                diamonds[i] = new Diamond(weight, value);
            }

            int[] dp = new int[n];  // i번째 다이아몬드를 마지막으로 하는 가장 긴 수열의 길이
            Arrays.fill(dp, 1);

            for (int i = 0; i < n; i++) {
                Diamond cur = diamonds[i];
                for (int j = i+1; j < n; j++) {
                    if (cur.isMoreValuable(diamonds[j])) {
                        dp[j] = Math.max(dp[j], dp[i]+1);
                    }
                }
            }

            int answer = 1;
            for (int length : dp) {
                answer = Math.max(answer, length);
            }
            sb.append(answer).append("\n");
            
        }
        System.out.print(sb);
    }
}