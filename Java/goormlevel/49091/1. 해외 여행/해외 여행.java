import java.io.*;
import java.util.*;

class Main {

	static final int MAX_WEIGHT = 10_0000_0000;
	
	static class Edge implements Comparable<Edge> {
		int to;
		int weight;

		public Edge(int to, int weight) {
			this.to = to;
			this.weight = weight;
		}

		@Override
		public int compareTo (Edge o) {
			return this.weight - o.weight;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());

		List<Edge>[] graph = new List[n+1];
		for (int from = 1; from <= n; from++) {
			graph[from] = new ArrayList<>();
		}

		for (int edge = 0; edge < m; edge++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());

			graph[from].add(new Edge(to, weight));
		}

		
		int[] dist = new int[n+1];
		Arrays.fill(dist, MAX_WEIGHT);
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		pq.offer(new Edge(1, 0));

		while(!pq.isEmpty()) {
			Edge cur = pq.poll();
			if (cur.weight >= dist[cur.to]) continue;
			
			dist[cur.to] = cur.weight;
			if (cur.to == n) break;
			
			for (Edge next : graph[cur.to]) {
				int nextWeight = cur.weight + next.weight;
				if (dist[next.to] <= nextWeight) continue;
				
				pq.offer(new Edge(next.to, nextWeight));
			}
		}

		System.out.println(dist[n] == MAX_WEIGHT ? "go home" : dist[n]);
	}
}