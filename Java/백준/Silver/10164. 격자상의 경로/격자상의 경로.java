import java.util.*;
import java.io.*;

public class Main {

    static int[] dirs;
    static int col;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        dirs = new int[]{1, m};
        col = m;

        int start = 1;
        int mid = k == 0 ? start : k;
        int end = n * m;
        
        long cnt = getPathCnt(start, mid) * getPathCnt(mid, end);
        System.out.println(cnt);
    }

    public static long getPathCnt(int start, int end) {
        if (start == end) return 1L;

        int r1 = (start - 1) / col;
        int r2 = (end - 1) / col;
        int down = r2 - r1;

        int c1 = (start - 1) % col;
        int c2 = (end - 1) % col;
        int right = c2 - c1;

        return getComb(down + right, Math.min(down, right)); 
    }

    public static long getComb(int n, int k) {
        long comb = 1;

        for (int i = 1; i <= k; i++) {
            comb = comb * (n - i + 1) / i;
        }

        return comb;
    }
}   