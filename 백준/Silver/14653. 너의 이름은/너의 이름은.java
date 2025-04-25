import java.io.*;
import java.util.*;

public class Main {

    static class Message {
        int cnt;
        char sender;

        public Message (int cnt, char sender) {
            this.cnt = cnt;
            this.sender = sender;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int q = Integer.parseInt(st.nextToken());

        Message[] messages = new Message[k+1];
        for (int i = 1; i <= k; i++) {
            st = new StringTokenizer(br.readLine());
            int cnt = Integer.parseInt(st.nextToken());
            char sender = st.nextToken().charAt(0);

            messages[i] = new Message(cnt, sender);
        }

        StringBuilder sb = new StringBuilder();
        if (messages[q].cnt == 0) {
            sb.append(-1);
        } else {
            HashSet<Character> set = new HashSet<>();
            for (int i = 1; i < n; i++) {
                set.add((char) ('A' + i));
            }

            int cnt = messages[q].cnt;
            int idx = q-1;
            while (idx > 0) {
                int prev = messages[idx].cnt;
                if (prev != cnt) break;

                set.remove(messages[idx].sender);
                idx--;
            }

            for (int i = q; i <= k; i++) {
                set.remove(messages[i].sender);
            }

            for (char person : set) {
                sb.append(person).append(" ");
            }
        }
        System.out.println(sb);
    }
}