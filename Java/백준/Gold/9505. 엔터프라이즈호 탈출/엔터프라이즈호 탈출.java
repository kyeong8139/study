import java.io.*;
import java.util.*;

public class Main {

    static int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    static class Pos implements Comparable<Pos>{
        int row;
        int col;
        int time;

        public Pos (int row, int col, int time) {
            this.row = row;
            this.col = col;
            this.time = time;
        }

        @Override
        public int compareTo (Pos o) {
            return this.time - o.time;
        }
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int testCase = Integer.parseInt(br.readLine());

        while (--testCase >= 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int k = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            int h = Integer.parseInt(st.nextToken());

            Map<Character, Integer> ships = new HashMap<>();
            for (int i = 0; i < k; i++) {
                st = new StringTokenizer(br.readLine());
                char name = st.nextToken().charAt(0);
                int time = Integer.parseInt(st.nextToken());

                ships.put(name, time);
            }
            
            int[][] dist = new int[h+2][w+2];
            for (int i = 0; i < dist.length; i++) {
                Arrays.fill(dist[i], Integer.MAX_VALUE);
            }
            PriorityQueue<Pos> pq = new PriorityQueue<>();

            char[][] board = new char[h+2][w+2];
            for (int row = 1; row <= h; row++) {
                String input = br.readLine();
                for (int col = 1; col <= w; col++) {
                    board[row][col] = input.charAt(col-1);

                    if (board[row][col] == 'E') {
                        pq.add(new Pos(row, col, 0));
                        dist[row][col] = 0;
                    }
                }
            }

            int answer = -1;
            while (!pq.isEmpty()) {
                Pos cur = pq.poll();
                if (cur.row == 0 || cur.row == h+1 || cur.col == 0 || cur.col == w+1) {
                    answer = cur.time;
                    break;
                }

                for (int[] dir : dirs) {
                    int nextRow = cur.row + dir[0];
                    int nextCol = cur.col + dir[1];
                    if (nextRow < 0 || nextRow > h+1 || nextCol < 0 || nextCol > w+1) continue;

                    int nextTime = cur.time + ships.getOrDefault(board[nextRow][nextCol], 0);
                    if (dist[nextRow][nextCol] > nextTime) {
                        dist[nextRow][nextCol] = nextTime;
                        pq.add(new Pos(nextRow, nextCol, nextTime));
                    }
                }
            }
            bw.write(String.valueOf(answer));
            bw.newLine();
        }
        bw.flush();
        bw.close();
    }
}
