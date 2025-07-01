import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] jewels = new int[1001];
        StringBuilder sb = new StringBuilder();
        long sum = 0;
        for (int t = 0; t < n; t++) {
            int length = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());

            for (int i = 1; i <= length; i++) {
                jewels[i] = Integer.parseInt(st.nextToken());
            }

            int left = 1;
            int cur = 0;
            int max = Integer.MIN_VALUE;
            int maxLeft = 1;
            int maxRight = 1;
            for (int i = 1; i <= length; i++) {
                if (cur <= 0) {
                    cur = jewels[i];
                    left = i;
                } else {
                    cur += jewels[i];
                }

                if (max < cur || (max == cur && i-left < maxRight - maxLeft)) {
                    max = cur;
                    maxLeft = left;
                    maxRight = i;
                }
            }

            sum += max;
            sb.append(maxLeft + " " + maxRight + "\n");
        }

        System.out.println(sum);
        System.out.print(sb);
    }
}