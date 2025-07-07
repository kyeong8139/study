import java.io.*;
import java.util.*;

public class Main {
    static int[][] dirs = {{0, -1}, {0, 1}, {1, 0}, {-1, 0}, {1, 1}, {1, -1}, {-1, 1}, {-1, -1}}; 

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        char[][] arr = new char[n][m];
        for (int row = 0; row < n; row++) {
            String input = br.readLine();
            for (int col = 0; col < m; col++) {
                arr[row][col] = input.charAt(col);
            }
        }

        Map<String, Integer>[][][] words = new Map[n][m][6];
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < m; col++) {
                for (int length = 0; length <= 5; length++) {
                    words[row][col][length] = new HashMap<>();
                }
                
                String key = String.valueOf(arr[row][col]);
                words[row][col][1].put(key, 1);
            }
        }
        
        for (int length = 1; length < 5; length++) {
            for (int row = 0; row < n; row++) {
                for (int col = 0; col < m; col++) {
                    for (int[] dir : dirs) {
                        int prevRow = (n + row + dir[0]) % n;
                        int prevCol = (m + col + dir[1]) % m;

                        for (String prefix : words[prevRow][prevCol][length].keySet()) {
                            String next = prefix + arr[row][col];
                            int nextCnt = words[row][col][length+1].getOrDefault(next, 0) + words[prevRow][prevCol][length].get(prefix);
                            words[row][col][length+1].put(next, nextCnt);
                        }
                    }
                }
            }
        }

        HashMap<String, Integer> cnts = new HashMap<>();
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < m; col++) {
                for (int length = 1; length <= 5; length++) {
                    for (String key : words[row][col][length].keySet()) {
                        int cnt = cnts.getOrDefault(key, 0) + words[row][col][length].get(key);
                        cnts.put(key, cnt);
                    }
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < k; i++) {
            String key = br.readLine();
            sb.append(cnts.getOrDefault(key, 0)).append("\n");
        }

        System.out.print(sb);
    }
}