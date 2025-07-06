import java.util.*;
import java.io.*;

public class Main {

    static class Edge {
        int v1;
        int v2; 
        boolean isUsed;

        public Edge (int v1, int v2, boolean isUsed) {
            this.v1 = v1;
            this.v2 = v2;
            this.isUsed = isUsed;
        }
    }

    static List<Edge>[] graph;
    static int[] dist;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        graph = new List[n+1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken()); 

            Edge edge = new Edge(v1, v2, false);

            graph[v1].add(edge);
            graph[v2].add(edge);
        }

        // 사이클 구하기
        dist = new int[n+1];
        Arrays.fill(dist, -1);

        for (int start = 1; start <= n; start++) {
            dfs(start, start, new boolean[n+1]);
        }

        // 사이클까지의 거리 구하기
        int start = 1;
        for (; start <= n; start++) {
            if (dist[start] == 0) break;
        }
    
        boolean[] isVisited = new boolean[n+1];
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{start, 0});
        
        while(!queue.isEmpty()) {
            int[] cur = queue.poll();
            if (isVisited[cur[0]]) continue;

            isVisited[cur[0]] = true;
            dist[cur[0]] = cur[1];
            
            for (Edge edge : graph[cur[0]]) {
                int next = cur[0] ^ (edge.v1 ^ edge.v2);

                if (isVisited[next]) continue;

                int nextDist = dist[next] == 0 ? 0 : cur[1] + 1;
                queue.add(new int[]{next, nextDist});
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            sb.append(dist[i]).append(" ");
        }
        System.out.println(sb);
    }

    public static void dfs(int start, int cur, boolean[] isVisited) {
        if (dist[start] == 0) return;

        isVisited[cur] = true;
        for (Edge edge : graph[cur]) {
            if (edge.isUsed) continue;

            int next = cur ^ (edge.v1 ^ edge.v2);

            if (next == start) {
                for (int i = 0; i < isVisited.length; i++) {
                    if (isVisited[i]) dist[i] = 0;
                }
                return;
            }
            
            edge.isUsed = true;
            dfs(start, next, isVisited);
            edge.isUsed = false;
        }
        isVisited[cur] = false;
    }
}   