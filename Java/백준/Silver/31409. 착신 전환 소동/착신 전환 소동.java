import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(br.readLine());

        int[] parents = new int[n+1];
        Queue<Integer> isOk = new ArrayDeque<>();
        StringTokenizer st = new StringTokenizer(br.readLine()); 
        for (int i = 1; i <= n; i++) {
            parents[i] = Integer.parseInt(st.nextToken());

            if (parents[i] == i) {
                isOk.add(i);
            }
        }

        sb.append(isOk.size()).append("\n");
        while (!isOk.isEmpty()) {
            int arrival = isOk.poll();
            while (!isOk.isEmpty()) {
                int cur = isOk.poll();
                parents[cur] = arrival;
            }

            parents[arrival] = (arrival % n) + 1;
        }


        for (int i = 1; i <= n; i++) {
            sb.append(parents[i]).append(" ");
        }
        System.out.println(sb);
    }
}   