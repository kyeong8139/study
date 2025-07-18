import java.util.*;
import java.io.*;

public class Main {

    static class Block{
        int id;
        int xStart;
        int xEnd;
        int y;

        public Block(int id, int xStart, int xEnd, int y) {
            this.id = id;
            this.xStart = xStart;
            this.xEnd = xEnd;
            this.y = y;
        }
    }

    static int[] parents;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int q = Integer.parseInt(st.nextToken());
        Block[] blocks = new Block[n];
        for (int idx = 0; idx < n; idx++) {
            st = new StringTokenizer(br.readLine());
            int id = idx+1;
            int xStart = Integer.parseInt(st.nextToken());
            int xEnd = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            
            Block block = new Block(id, xStart, xEnd, y);
            blocks[idx] = block;
        }
        Arrays.sort(blocks, (x, y) -> x.xStart - y.xStart);
        
        parents = new int[n+1];
        for (int i = 1; i <= n; i++) {
            parents[i] = i;
        }
        
        PriorityQueue<Block> pq = new PriorityQueue<>((x, y) -> x.xEnd - y.xEnd);
        for (int i = 0; i < n; i++) {
            Block cur = blocks[i];
            
            // 가지치기
            while(!pq.isEmpty() && pq.peek().xEnd < cur.xStart) {
                pq.poll();
            }
            
            Block result = new Block(cur.id, cur.xStart, cur.xEnd, cur.y);
            /**
             * Block near = pq.poll()일 때, near은 항상 아래 조건을 만족함
             *      - near.xStart <= cur.xStart <= near.xEnd
             */
            while(!pq.isEmpty()) { 
                Block near = pq.poll();
                
                int curParent = getParent(cur.id);
                int nearParent = getParent(near.id);

                if (curParent != nearParent) {
                    parents[nearParent] = curParent;
                    result.xStart = Math.min(result.xStart, near.xStart);
                    result.xEnd = Math.max(result.xEnd, near.xEnd);
                }
            }
            pq.add(result);
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < q; i++) {
            st = new StringTokenizer(br.readLine());
            int blockA = Integer.parseInt(st.nextToken());
            int blockB = Integer.parseInt(st.nextToken());

            boolean isMovable = false;
            int parentA = getParent(blockA);
            int parentB = getParent(blockB);
            if (parentA == parentB) {
                isMovable = true;
            }

            sb.append(isMovable ? 1 : 0).append("\n");
        }
        System.out.print(sb);
    }

    public static int getParent(int child) {
        if (parents[child] == child) {
            return child;
        }

        return parents[child] = getParent(parents[child]);
    }
}