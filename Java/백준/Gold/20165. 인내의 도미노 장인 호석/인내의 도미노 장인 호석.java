import java.util.*;
import java.io.*;

public class Main {

    static int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int round = Integer.parseInt(st.nextToken());

        int[][] board = new int[n+1][m+1];
        for (int row = 1; row <= n; row++) {
            st = new StringTokenizer(br.readLine());
            for (int col = 1; col <= m; col++) {
                board[row][col] = Integer.parseInt(st.nextToken());
            }
        }

        int score = 0;
        boolean[][] isUsed = new boolean[n+1][m+1];
        for (int r = 0; r < round; r++) {
            // 공격
            st = new StringTokenizer(br.readLine());
            int row = Integer.parseInt(st.nextToken());
            int col = Integer.parseInt(st.nextToken());
            int dir = -1;
            String dirStr = st.nextToken();
            switch (dirStr) {
                case "E":
                    dir = 0;
                    break;
                case "W":
                    dir = 1;
                    break;
                case "S":
                    dir = 2;
                    break;
                case "N":
                    dir = 3;
                    break;
            }

            int length = isUsed[row][col] ? 0 : board[row][col];
            for (int i = 0; i < length; i++) {
                if (isUsed[row][col]) {
                    row += dirs[dir][0];
                    col += dirs[dir][1];
                    if (row <= 0 || row > n || col <= 0 || col > m) break;
                    continue;
                }

                isUsed[row][col] = true;
                score++;
                length = Math.max(length, board[row][col] + i);
                
                row += dirs[dir][0];
                col += dirs[dir][1];
                if (row <= 0 || row > n || col <= 0 || col > m) break;
            }

            // 수비
            st = new StringTokenizer(br.readLine());
            row = Integer.parseInt(st.nextToken());
            col = Integer.parseInt(st.nextToken());
            isUsed[row][col] = false;
        }

        bw.write(String.valueOf(score));
        bw.newLine();
        for (int row = 1; row <= n; row++) {
            for (int col = 1; col <= m; col++) {
                bw.write(isUsed[row][col] ? "F " : "S ");
            }
            bw.newLine();
        }

        bw.flush();
        bw.close();
    }
}