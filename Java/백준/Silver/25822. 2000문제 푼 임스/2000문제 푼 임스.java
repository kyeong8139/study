import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        double coin = Double.parseDouble(br.readLine());
        int n = Integer.parseInt(br.readLine());

        int[] problems = new int[n+1];
        int maxProblem = -1;
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            problems[i] = Integer.parseInt(st.nextToken());
            maxProblem = Math.max(maxProblem, problems[i]);
        }

        int[][] streaks = new int[n+1][3]; 
        for (int day = 1; day <= n; day++) {
            if (problems[day] == 0) {
                streaks[day][0] = 0;
                streaks[day][1] = streaks[day-1][0] + 1;
                streaks[day][2] = streaks[day-1][1] + 1;
            } else {
                for (int item = 0; item <= 2; item++) {
                    streaks[day][item] = streaks[day-1][item] + 1;
                }
            }
        }

        int maxDay = 0;
        int maxItemCnt = Math.min(2, (int)(coin / 0.99));
        for (int day = 1; day <= n; day++) {
            for (int item = 0; item <= maxItemCnt; item++) {
                int cur = streaks[day][item];
                maxDay = Math.max(maxDay, cur);
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(maxDay).append("\n").append(maxProblem);
        System.out.print(sb);
    }
}