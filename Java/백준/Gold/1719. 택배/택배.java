import java.io.*;
import java.util.*;

public class Main {

    static final int MAX_DIST = 200_0001;

    static class Path {
        int dist;
        int firstNode;

        public Path(int dist, int firstNode) {
            this.dist = dist;
            this.firstNode = firstNode;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        Path[][] paths = new Path[n+1][n+1];
        for (int from = 1; from <= n; from++) {
            for (int to = 1; to <= n; to++) {
                paths[from][to] = new Path(MAX_DIST, -1);
            }
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());
            int dist = Integer.parseInt(st.nextToken());

            paths[v1][v2].dist = dist;
            paths[v1][v2].firstNode = v2;

            paths[v2][v1].dist = dist;
            paths[v2][v1].firstNode = v1;

        }

        for (int via = 1; via <= n; via++) {
            for (int from = 1; from <= n; from++) {
                for (int to = 1; to <= n; to++) {
                    Path cur = paths[from][to];
                    int viaDist = paths[from][via].dist + paths[via][to].dist;
                    if (cur.dist > viaDist) {
                        cur.dist = viaDist;
                        cur.firstNode = paths[from][via].firstNode;
                    }
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int from = 1; from <= n; from++) {
            for (int to = 1; to <= n; to++) {
                String firstNode;
                if (from == to) {
                    sb.append("-").append(" ");
                } else {
                    sb.append(paths[from][to].firstNode).append(" ");
                }
            }
            sb.append("\n");
        }

        System.out.print(sb);
    }

}