import java.io.*;
import java.util.*;

public class Main {

    static class Information {
        int row;
        int col;
        int dist;

        public Information (int row, int col, int dist) {
            this.row = row;
            this.col = col;
            this.dist = dist;
        }
    }
    static Information[] infos;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        infos = new Information[n+m-1];
        int idx = 0;
        int row = 1;
        int col = 1;
        for (; row < n; row++) {
            int dist = Integer.parseInt(br.readLine());
            infos[idx++] = new Information(row, col, dist);
        }

        st = new StringTokenizer(br.readLine());
        for (; col <= m; col++) {
            int dist = Integer.parseInt(st.nextToken());
            infos[idx++] = new Information(row, col, dist);
        }

        int[] answer = new int[2];
        outer: for (int r = 1; r <= n; r++) {
            for (int c = 1; c <= m; c++) {
                if (isValidate(r, c)) {
                    answer[0] = r;
                    answer[1] = c;
                    break outer;
                }
            }
        }

        System.out.print(answer[0] + " " + answer[1]);
    }

    public static boolean isValidate (int row, int col) {
        for (Information info : infos) {
            int curDist = Math.abs(info.row - row) + Math.abs(info.col - col);
            if (info.dist != curDist) {
                return false;
            }
        }

        return true;
    }
}