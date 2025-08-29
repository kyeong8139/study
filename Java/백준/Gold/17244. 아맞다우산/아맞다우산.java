
import java.io.*;
import java.util.*;

public class Main {

    static class Pos{
        int row;
        int col;
        int dist;
        int item;

        public Pos(int row, int col, int dist, int item) {
            this.row = row;
            this.col = col;
            this.dist = dist;
            this.item = item;
        }
    }

    static int MAX_DIST = 2500;
    static int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}}; 
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int col = Integer.parseInt(st.nextToken());
        int row = Integer.parseInt(st.nextToken());
        char[][] house = new char[row][col];
        for (int r = 0; r < row; r++) {
            house[r] = br.readLine().toCharArray();
        }

        int[][][] dist = new int[row][col][1<<6];
        Queue<Pos> queue = new ArrayDeque<>();
        int itemCnt = 0;
        for (int r = 0; r < row; r++) {
            for (int c = 0; c < col; c++) {
                if (house[r][c] != '#') {
                    for (int i = 0; i < (1 << 6); i++) {
                        dist[r][c][i] = MAX_DIST;
                    }
                    for (int i = 0; i <= 5; i++) {
                    }
                }

                if (house[r][c] == 'S') {
                    queue.add(new Pos(r, c, 0, 0));   
                } else if (house[r][c] == 'X') {
                    house[r][c] = (char) (itemCnt + '0');
                    itemCnt++;
                }
            }
        }

        while (!queue.isEmpty()) {
            Pos cur = queue.poll();
            if (house[cur.row][cur.col] >= '0' && house[cur.row][cur.col] < '5') {
                int idx = house[cur.row][cur.col] - '0';
                if ((cur.item & (1 << idx)) == 0) {
                    cur.item |= 1 << idx;
                }
            }

            if (dist[cur.row][cur.col][cur.item] <= cur.dist) continue;
            
            dist[cur.row][cur.col][cur.item] = cur.dist;
            if (house[cur.row][cur.col] == 'E') continue;

            for (int[] dir : dirs) {
                int nextRow = cur.row + dir[0];
                int nextCol = cur.col + dir[1];
                int nextDist = cur.dist + 1;

                if (nextRow < 0 || nextRow == row || nextCol < 0 || nextCol == col) continue;

                queue.add(new Pos(nextRow, nextCol, nextDist, cur.item));
            }
        }

        int answer = (1 << itemCnt) - 1;
        for (int r = 0; r < row; r++) {
            for (int c = 0; c < col; c++) {
                if (house[r][c] == 'E') {
                    System.out.println(dist[r][c][answer]);
                } 
            }
        }
    }
}