import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[] scores = new int[2];
        scores[0] = 100;
        scores[1] = 100;

        int[] dices = new int[2];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            dices[0] = Integer.parseInt(st.nextToken());
            dices[1] = Integer.parseInt(st.nextToken());

            if (dices[0] > dices[1]) {
                scores[1] -= dices[0];
            } else if (dices[1] > dices[0]) {
                scores[0] -= dices[1];
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(scores[0]).append("\n").append(scores[1]);
        System.out.print(sb);
    }
}   