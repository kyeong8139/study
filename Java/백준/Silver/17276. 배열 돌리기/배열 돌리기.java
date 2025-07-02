import java.io.*;
import java.util.*;

public class Main {

    static int[][] dirs = {{0, -1}, {-1, -1}, {-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int testCaseCnt = Integer.parseInt(br.readLine());

        while (--testCaseCnt >= 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());

            int[][] arr = new int[n][n];
            for (int row = 0; row < n; row++) {
                st = new StringTokenizer(br.readLine());
                for (int col = 0; col < n; col++) {
                    arr[row][col] = Integer.parseInt(st.nextToken());
                }
            }

            int[][] result = rotate(arr, d / 45);
            for (int row = 0; row < n; row++) {
                for (int col = 0; col < n; col++) {
                    sb.append(result[row][col]).append(" ");
                }
                sb.append("\n");
            }
        }

        System.out.print(sb);
    }

    public static int[][] rotate (int[][] origin, int step) {
        int[][] changed = new int[origin.length][origin[0].length];
        for (int row = 0; row < changed.length; row++) {
            for (int col = 0; col < changed[row].length; col++) {
                changed[row][col] = origin[row][col];
            }
        }  

        int mid = origin.length / 2;
        for (int depth = 1; depth <= origin.length / 2; depth++) {
            for (int d = 0; d < dirs.length; d++) {
                int beforeRow = mid + (dirs[d][0] * depth);
                int beforeCol = mid + (dirs[d][1] * depth);

                int afterDir = (dirs.length + d +step) % dirs.length;
                int afterRow = mid + (dirs[afterDir][0] * depth);
                int afterCol = mid + (dirs[afterDir][1] * depth);

                changed[afterRow][afterCol] = origin[beforeRow][beforeCol];
            }
        }

        return changed;
    }
}