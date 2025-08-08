import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int[] lines =  new int[501];
        Arrays.fill(lines, -1);

        int n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            lines[a] = b;
        }

        int[] dp = new int[n+2];
        Arrays.fill(dp, Integer.MAX_VALUE);        
        
        for (int i = 1; i < lines.length; i++) {
            if (lines[i] == -1) continue;
            int j;
            for (j = 1; j <= n; j++) {
                if (dp[j] > lines[i]) break;
            }
            dp[j] = lines[i];
        }

        int answer = n;
        for (int i = 1; i <= n+1; i++) {
            if (dp[i] == Integer.MAX_VALUE) {
                answer -= (i-1);
                break;
            }
        }

        bw.write(String.valueOf(answer));
        bw.flush();
        bw.close();
    }
}