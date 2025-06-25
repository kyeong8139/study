import java.io.*;
import java.util.*;

public class Main {

    static class Knight {
        int x;
        int y;

        public Knight(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static int[][] dirs = {{-2, -1}, {-2, 1}, {-1, -2}, {-1, 2}, {1, -2}, {1, 2}, {2, -1}, {2, 1}};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[][] board = new int[n+1][n+1];
        for (int row = 1; row <= n; row++) {
            for (int col = 1; col <= n; col++) {
                board[row][col] = -1;
            }
        }

        st = new StringTokenizer(br.readLine());
        int initX = Integer.parseInt(st.nextToken());
        int initY = Integer.parseInt(st.nextToken());
        
        Queue<Knight> queue = new ArrayDeque<>();
        queue.offer(new Knight(initX, initY));

        int cnt = 0;
        while(!queue.isEmpty()) {
            int size = queue.size();
            while (--size >= 0) {
                Knight cur = queue.poll();
                if (board[cur.x][cur.y] != -1) {
                    continue;
                }

                board[cur.x][cur.y] = cnt;
                for (int[] dir : dirs) {
                    int nextX = cur.x + dir[0];
                    int nextY = cur.y + dir[1];

                    if (nextX <= 0 || nextX > n || nextY <= 0 || nextY > n || board[nextX][nextY] != -1) continue;
                    queue.offer(new Knight(nextX, nextY));
                }
            } 

            cnt++;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            sb.append(board[x][y]).append(" ");
        }

        System.out.println(sb.toString());
    }
}