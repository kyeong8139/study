import java.io.*;
import java.util.*;

public class Main {

    static class Edge implements Comparable<Edge> {
        Planet to;
        int dist;

        public Edge (Planet to, int dist) {
            this.to = to;
            this.dist = dist;
        }

        @Override
        public int compareTo(Edge o) {
            return this.dist - o.dist;
        }
    }

    static class Planet {
        int id;
        int[] pos;

        public Planet(int id, int[] pos) {
            this.id = id;
            this.pos = pos;
        }
    }

    static TreeSet<Planet>[] planets;
    static int[] parents;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        planets = new TreeSet[3];
        planets[0] = new TreeSet<>((x, y) -> (x.pos[0] == y.pos[0]) ? x.id - y.id : x.pos[0] - y.pos[0]);
        planets[1] = new TreeSet<>((x, y) -> (x.pos[1] == y.pos[1]) ? x.id - y.id : x.pos[1] - y.pos[1]);
        planets[2] = new TreeSet<>((x, y) -> (x.pos[2] == y.pos[2]) ? x.id - y.id : x.pos[2] - y.pos[2]);

        for (int id = 1; id <= n; id++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int z = Integer.parseInt(st.nextToken());

            Planet planet = new Planet(id, new int[]{x, y, z});
            planets[0].add(planet);
            planets[1].add(planet);
            planets[2].add(planet);
        }

        parents = new int[n+1];
        for (int i = 1; i <= n; i++) {
            parents[i] = i;
        }

        Planet cur = planets[0].first();
        parents[cur.id] = 0;

        PriorityQueue<Edge> pq = new PriorityQueue<>();
        addEdge(cur, pq);

        int cnt = 1;
        long distSum = 0;
        while (!pq.isEmpty()) {
            Edge edge = pq.poll();
            if (parents[edge.to.id] == 0) continue;

            distSum += edge.dist;
            parents[edge.to.id] = 0;
            addEdge(edge.to, pq);
            
            if (++cnt == n) break;
        }

        System.out.print(distSum);
    }

    public static void addEdge(Planet planet, PriorityQueue<Edge> pq) {
        for (int i = 0; i < 3; i++) {
            TreeSet<Planet> set = planets[i];
            set.remove(planet);
            
            Planet cur = new Planet(0, planet.pos);
            Planet near = set.ceiling(cur);
            while (near != null) {
                if (parents[near.id] != 0) {
                    int nearDist = Math.abs(cur.pos[i] - near.pos[i]);
                    pq.add(new Edge(near, nearDist));
                    break;
                }

                set.remove(near);
                cur.pos = near.pos;
                near = set.ceiling(cur);
            }

            cur.id = 10_0001;
            cur.pos = planet.pos;
            near = set.floor(cur);
            while (near != null) {
                if (parents[near.id] != 0) {
                    int nearDist = Math.abs(cur.pos[i] - near.pos[i]);
                    pq.add(new Edge(near, nearDist));
                    break;
                }

                set.remove(near);
                cur.pos = near.pos;
                near = set.floor(cur);
            }
        }
    }
}