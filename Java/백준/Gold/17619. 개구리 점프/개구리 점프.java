import java.util.*;
import java.io.*;

public class Main {

    static class Block implements Comparable<Block>{
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

        @Override
        public int compareTo(Block o) {
            if (this.xStart == o.xStart) {
                return this.xEnd - o.xEnd;
            }
            return this.xStart - o.xStart;
        }
    }

    static int[] parents;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int q = Integer.parseInt(st.nextToken());
        PriorityQueue<Block> blocks = new PriorityQueue<>();
        for (int id = 1; id <= n; id++) {
            st = new StringTokenizer(br.readLine());
            int xStart = Integer.parseInt(st.nextToken());
            int xEnd = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            
            Block block = new Block(id, xStart, xEnd, y);
            blocks.add(block);
        }
        
        parents = new int[n+1];
        for (int i = 1; i <= n; i++) {
            parents[i] = i;
        }
        
        Block prev = blocks.poll();
        while (!blocks.isEmpty()) {
            Block cur = blocks.poll();

            boolean movable = getMovable(prev, cur);
            if (movable) {
                parents[cur.id] = parents[prev.id];
            }

            prev = cur;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < q; i++) {
            st = new StringTokenizer(br.readLine());
            int blockA = Integer.parseInt(st.nextToken());
            int blockB = Integer.parseInt(st.nextToken());

            int result = 1;
            int parentA = getParent(blockA);
            int parentB = getParent(blockB);
            if (parentA != parentB) {
                result = 0;
            }

            sb.append(result).append("\n");
        }
        System.out.print(sb);
    }

    public static boolean getMovable(Block prev, Block cur) {
        if (prev.xEnd >= cur.xStart) {
            return true;
        }

        return false; 
    }

    public static int getParent(int child) {
        if (parents[child] == child) {
            return child;
        }

        return parents[child] = getParent(parents[child]);
    }
}