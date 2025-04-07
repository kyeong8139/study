import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int[] input = new int[3];
            input[0] = Integer.parseInt(st.nextToken());
            input[1] = Integer.parseInt(st.nextToken());
            input[2] = Integer.parseInt(st.nextToken());
            if (input[0] == 0 && input[1] == 0 && input[2] == 0) {
                break;
            }

            Arrays.sort(input);

            int max = input[2];
            int mid = input[1];
            int min = input[0];

            if (max * max == (mid * mid) + (min * min)) {
                sb.append("right").append("\n");
            } else {
                sb.append("wrong").append("\n");
            }
        }

        System.out.print(sb);
    }
}