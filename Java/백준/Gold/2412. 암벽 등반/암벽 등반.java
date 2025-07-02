import java.util.*;
import java.io.*;

public class Main {

    static class Point{
        int id;
        int x;
        int y;
        int dist;

        public Point(int id, int x, int y, int dist) {
            this.id = id;
            this.x = x;
            this.y = y;
            this.dist = dist;
        }
    } 

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int goal = Integer.parseInt(st.nextToken());

        HashMap<Integer, List<Point>> points = new HashMap<>();
        points.put(0, new ArrayList<>());
        points.get(0).add(new Point(0, 0, 0, 0));
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            Point cur = new Point(i, x, y, 0);

            if (!points.containsKey(y)) {
                points.put(y, new ArrayList<>());
            }
            points.get(y).add(cur);
        }

        int[] dist = new int[n+1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        PriorityQueue<Point> pq = new PriorityQueue<>((x, y) -> x.dist - y.dist);
        
        List<Point> goals = points.get(goal);
        if (goals != null) {
            for (Point g : goals) {
                dist[g.id] = 0;
                pq.add(g);
            }
        }

        while(!pq.isEmpty()) {
            Point cur = pq.poll();
            if (dist[cur.id] < cur.dist) continue;
            
            if (cur.x == 0 && cur.y == 0) break;

            for (int i = cur.y - 2; i <= cur.y + 2; i++) {
                List<Point> nexts = points.get(i);
                if (nexts == null) continue;

                for (Point next : nexts) {
                    if ((Math.abs(cur.x-next.x) <= 2) && (dist[next.id] > cur.dist + 1)) {
                        dist[next.id] = cur.dist + 1;
                        pq.add(new Point(next.id, next.x, next.y, cur.dist + 1));
                    }
                }
            }
        }

        int answer = dist[0] == Integer.MAX_VALUE ? -1 : dist[0];
        System.out.print(answer);
    }
}   