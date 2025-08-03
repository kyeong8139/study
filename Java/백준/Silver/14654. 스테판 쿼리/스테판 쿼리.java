import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[][] rounds = new int[n][2];
        
        for (int team = 0; team < 2; team++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int round = 0; round < n; round++) {
                rounds[round][team] = Integer.parseInt(st.nextToken());
            }
        }

        int maxStreak = 0;
        int[] streaks = new int[2];

        for (int round = 0; round < n; round++) {
            int teamZero = rounds[round][0];
            int teamOne = rounds[round][1];
            int step = teamOne - teamZero;
            int winner;
            if (step == 0) {
                winner = -1;
            } else if ((teamOne != 1 && step == 1) || (teamOne == 1 && step == -2)) {
                winner = 1;
            } else {
                winner = 0;
            }

            if (winner == -1) {
                if (streaks[0] == 0) {
                    winner = 0;
                } else {
                    winner = 1;
                }
            }

            int loser = winner ^ 1;
            maxStreak = Math.max(maxStreak, ++streaks[winner]);
            streaks[loser] = 0;
        }
        System.out.println(maxStreak); 
    }
}
