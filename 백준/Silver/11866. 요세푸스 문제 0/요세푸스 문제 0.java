import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        sb.append("<");
        Queue<Integer> queue = new ArrayDeque<>();
        for (int i = 1; i <= n; i++) {
            queue.offer(i);
        }

        int cnt = 0;
        while (!queue.isEmpty()) {
            int person = queue.poll();
            if (++cnt == k) {
                sb.append(person).append(", ");
                cnt = 0;
            } else {
                queue.offer(person);
            }
        }

        sb.delete(sb.length()-2, sb.length());
        sb.append(">");
        System.out.print(sb);
    }

}