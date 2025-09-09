import java.util.*;
import java.io.*;

public class Main {

    static class Pos implements Comparable<Pos> {
        int row;
        int col;
        int time;

        public Pos(int row, int col, int time) {
            this.row = row;
            this.col = col;
            this.time = time;
        }

        public int compareTo (Pos o) {
            return this.time - o.time;
        }
    }

    static int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int h = Integer.parseInt(st.nextToken());
        int w = Integer.parseInt(st.nextToken());
        char[][] board = new char[h][w];
        for (int r = 0; r < h; r++) {
            board[r] = br.readLine().toCharArray();
        }

        PriorityQueue<Pos> pq = new PriorityQueue<>();
        for (int r = 0; r < h; r++) {
            for (int c = 0; c < w; c++) {
                if (board[r][c] == 'S') {
                    pq.add(new Pos(r, c, 0));
                }
            }
        }

        int[][] times = new int[h][w];
        boolean[][] wallIsNear = new boolean[h][w];
        for (int r = 0; r < h; r++) {
            Arrays.fill(times[r], Integer.MAX_VALUE);

            for (int c = 0; c < w; c++) {
                if (board[r][c] == '#') {
                    for (int[] dir : dirs) {
                       int nextRow = r + dir[0];
                        int nextCol = c + dir[1];
                        if (nextRow < 0 || nextRow >= h || nextCol < 0 || nextCol >= w) continue;
                        wallIsNear[nextRow][nextCol] = true;
                    }
                }
                
            }
        }

        int answer = 0;
        while (!pq.isEmpty()) {
            Pos cur = pq.poll();
            if (times[cur.row][cur.col] <= cur.time) continue;

            times[cur.row][cur.col] = cur.time;
            if (board[cur.row][cur.col] == 'E') {
                answer = cur.time;
                break;
            }
            
            for (int[] dir : dirs) {
                int nextRow = cur.row + dir[0];
                int nextCol = cur.col + dir[1];

                if (nextRow < 0 || nextRow >= h || nextCol < 0 || nextCol >= w || (board[nextRow][nextCol] == '#')) continue;

                int nextTime = cur.time;
                if (!wallIsNear[cur.row][cur.col] || !wallIsNear[nextRow][nextCol]) {
                    nextTime++;
                }
                pq.add(new Pos(nextRow, nextCol, nextTime));
            }
        }

        System.out.println(answer);
    }
}