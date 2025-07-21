import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[] next = new int[100_0001];
        int[] prev = new int[100_0001];

        st = new StringTokenizer(br.readLine());
        int headId = Integer.parseInt(st.nextToken());
        next[headId] = headId;
        prev[headId] = headId;

        int prevId = headId;
        for (int i = 1; i < n; i++) {
            int curId = Integer.parseInt(st.nextToken());
            prev[curId] = prevId; 
            next[prevId] = curId;
            prevId = curId;
        }
        prev[headId] = prevId;
        next[prevId] = headId;

        StringBuilder sb = new StringBuilder();
        for (int cmd = 0; cmd < m; cmd++) {
            st = new StringTokenizer(br.readLine());
            String command = st.nextToken();
            int curId = Integer.parseInt(st.nextToken());

            switch(command) {
                case "BN":
                    sb.append(next[curId]);
                    int newId = Integer.parseInt(st.nextToken());

                    prev[newId] = curId;
                    next[newId] = next[curId];

                    prev[next[curId]] = newId;
                    next[curId] = newId;
                    break;
                case "BP":
                    sb.append(prev[curId]);
                    newId = Integer.parseInt(st.nextToken());

                    prev[newId] = prev[curId];
                    next[newId] = curId;

                    next[prev[curId]] = newId;
                    prev[curId] = newId; 
                    break;
                case "CN":
                    sb.append(next[curId]);

                    next[curId] = next[next[curId]];
                    prev[next[curId]] = curId;
                    break;
                case "CP":
                    sb.append(prev[curId]);

                    prev[curId] = prev[prev[curId]];
                    next[prev[curId]] = curId;
                    break;
            }
            sb.append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}