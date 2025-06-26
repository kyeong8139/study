import java.util.*;
import java.io.*;

public class Main {

    static class Edge implements Comparable<Edge>{
        int to;
        int weight;

        public Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }

        public int compareTo (Edge o) {
            return o.weight - this.weight;
        }
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int p = Integer.parseInt(st.nextToken());
        int w = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        ArrayList<Edge>[] graph = new ArrayList[p];
        for (int i = 0; i < p; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < w; i++) {
            st = new StringTokenizer(br.readLine());
            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            graph[v1].add(new Edge(v2, weight));
            graph[v2].add(new Edge(v1, weight));
        }

        int[] width = new int[p];
        Arrays.fill(width, -1);

        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.add(new Edge(start, Integer.MAX_VALUE));
        while (!pq.isEmpty()) {
            Edge cur = pq.poll();
            if (width[cur.to] >= cur.weight) continue;

            width[cur.to] = cur.weight;
            if (cur.to == end) break;

            for (Edge next : graph[cur.to]) {
                int nextWeight = Math.min(cur.weight, next.weight);
                if (nextWeight <= width[next.to]) continue;

                pq.add(new Edge(next.to, nextWeight));
            }
        }

        System.out.println(width[end]);
    }
}   