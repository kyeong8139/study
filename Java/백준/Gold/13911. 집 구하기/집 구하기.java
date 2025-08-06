import java.util.*;
import java.io.*;

public class Main {

    static class Edge implements Comparable<Edge> {
        int to;
        long weight;
        
        public Edge(int to, long weight) {
            this.to = to;
            this.weight = weight;
        }
        
        @Override
        public int compareTo (Edge o) {
            return Long.compare(this.weight, o.weight);
        }
    }
    
    static final long MAX_DIST = 300_0000_0000L;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int v = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());

        List<Edge>[] graph = new List[v+1];
        for (int i = 1; i <= v; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            long weight = Long.parseLong(st.nextToken());

            graph[from].add(new Edge(to, weight));
            graph[to].add(new Edge(from, weight));
        }

        long[][] dist = new long[2][v+1];
        Arrays.fill(dist[0], MAX_DIST);
        Arrays.fill(dist[1], MAX_DIST);
        for (int i = 0; i < 2; i++) {
            st = new StringTokenizer(br.readLine());
            int cnt = Integer.parseInt(st.nextToken());
            long limitDist = Long.parseLong(st.nextToken());

            PriorityQueue<Edge> pq = new PriorityQueue<>();
            st = new StringTokenizer(br.readLine());
            for (int store = 0; store < cnt; store++) {
                int targetV = Integer.parseInt(st.nextToken());
                dist[i][targetV] = 0;
                pq.add(new Edge(targetV, 0));
            }

            while(!pq.isEmpty()) {
                Edge cur = pq.poll();

                for (Edge next : graph[cur.to]) {
                    long nextWeight = cur.weight + next.weight;
                    if (dist[i][next.to] <= nextWeight || nextWeight > limitDist) continue;

                    pq.add(new Edge(next.to, nextWeight));
                    dist[i][next.to] = nextWeight;
                }
            }
        }

        long minDist = MAX_DIST;
        for (int i = 1; i <= v; i++) {
            if (dist[0][i] == 0 || dist[1][i] == 0) continue;
            minDist = Math.min(minDist, dist[0][i] + dist[1][i]);
        }

        long answer = (minDist == MAX_DIST) ? -1 : minDist;
        bw.write(String.valueOf(answer));
        bw.flush();
        bw.close();
    }
}