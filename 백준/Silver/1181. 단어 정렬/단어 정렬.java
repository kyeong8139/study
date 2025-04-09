import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        PriorityQueue<String> pq = new PriorityQueue<>((x, y) -> {
            if (x.length() == y.length()) {
                return x.compareTo(y);
            }
            return x.length() - y.length();
        });

        for (int i = 0; i < n; i++) {
            pq.offer(br.readLine());
        }

        String prev = "";
        StringBuilder answer = new StringBuilder();
        while (!pq.isEmpty()) {
            String cur = pq.poll();
            if (cur.equals(prev)) continue;

            answer.append(cur).append("\n");
            prev = cur;
        }
        System.out.print(answer);
    }
}