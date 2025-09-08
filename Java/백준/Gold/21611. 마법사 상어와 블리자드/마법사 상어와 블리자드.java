
import java.io.*;
import java.util.*;

public class Main {

    static int[][] dirs = {{0, -1}, {1, 0}, {0, 1}, {-1, 0}};
    static int[][] magicDir = {{0, 0}, {-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        // id값 할당 
        int[][] ids = new int[n+1][n+1];
        int row = (n+1) / 2;
        int col = (n+1) / 2;
        int id = 1;
        int dir = 0;
        int length = 1;
        outer : while (true) {
            for (int i = 0; i < 2; i++) {
                for (int cnt = 0; cnt < length; cnt++) {
                    row += dirs[dir][0];
                    col += dirs[dir][1];            
                    ids[row][col] = id++;
                    if (row == 1 && col == 1) break outer;
                }
                dir = (dir + 1) % dirs.length;
            }
            length++;
        }

        // 구슬 할당
        int[] marbles = new int[n*n+1];
        for (int r = 1; r <= n; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 1; c <= n; c++) {
                marbles[ids[r][c]] = Integer.parseInt(st.nextToken());
            }
        }

        // 블리자드
        int[] count = new int[4];
        for (int command = 0; command < m; command++) {
            st = new StringTokenizer(br.readLine());
            int d = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());

            // 얼음 파편
            row = (n+1) / 2;
            col = (n+1) / 2;
            for (int i = 0; i < s; i++) {
                row += magicDir[d][0];
                col += magicDir[d][1];

                id = ids[row][col];
                marbles[id] = 0;
            }

            // 폭발 
            while (true) {  
                boolean hasBoom = false;
                int prev = -1;
                int streak = 0;
                for (int i = 1; i < marbles.length; i++) {
                    if (marbles[i] == 0) continue;

                    if (marbles[i] == prev) {
                        streak++;
                    } else {
                        if (streak >= 4) {
                            count[prev] += streak;
                            for (int j = i-1; streak > 0; j--) {
                                if (marbles[j] == prev) {
                                    marbles[j] = 0;
                                    streak--;
                                }
                            }
                            hasBoom = true;
                        }

                        prev = marbles[i];
                        streak = 1;
                    }
                }
                if (streak >= 4) {
                    count[prev] += streak;
                    for (int j = n*n; streak > 0; j--) {
                        if (marbles[j] == prev) {
                            marbles[j] = 0;
                            streak--;
                        }
                    }
                    hasBoom = true;
                }

                if (!hasBoom) break;
            }

            int[] nextMarbles = new int[marbles.length];
            id = -1;
            int prev = -1;

            for (int i = 1; i < marbles.length; i++) {
                int cur = marbles[i];
                if (cur == 0) continue;  

                if (cur == prev) {
                    nextMarbles[id]++;
                } else {
                    id += 2;
                    if (id + 1 >= nextMarbles.length) break;
                    nextMarbles[id] = 1; 
                    nextMarbles[id + 1] = cur; 
                    prev = cur;           
                }
            }
            marbles = nextMarbles;
        }

        int answer = 0;
        for (int i = 1; i <= 3; i++) {
            answer += i * count[i];
        }
        System.out.println(answer);
    }
}