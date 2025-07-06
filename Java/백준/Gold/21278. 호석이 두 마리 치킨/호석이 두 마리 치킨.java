import java.util.*;
import java.io.*;

public class Main {

    static int MAX = 987654321;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        List<Integer>[] graph = new List[n+1];
        Arrays.fill(graph, new ArrayList<>());

        int[][] dist = new int[n+1][n+1];
        for (int from = 1; from <= n; from++) {
            for (int to = 1; to <= n; to++) {
                if (from == to) {
                    dist[from][to] = 0;
                } else {
                    dist[from][to] = MAX;
                }
            }
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());

            dist[v1][v2] = 2;
            dist[v2][v1] = 2;
        }

        for (int via = 1; via <= n; via++) {
            for (int from = 1; from <= n; from++) {
                for (int to = 1; to <= n; to++) {
                    dist[from][to] = Math.min(dist[from][to], dist[from][via] + dist[via][to]);
                }
            }   
        }

        long min = MAX;
        int storeA = -1;
        int storeB = -1;
        for (int i = 1; i <= n; i++) {
            for (int j = i+1; j <= n; j++) {
                long sum = 0;
                for (int to = 1; to <= n; to++) {
                    sum += Math.min(dist[i][to], dist[j][to]);
                }

                if (sum < min) {
                    min = sum;
                    storeA = i;
                    storeB = j;
                }
            }
        }

        System.out.println(storeA + " " + storeB + " " + min);
    }
}   