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
        
        int cnt = getPathCnt(start, mid) * getPathCnt(mid, end);
        System.out.println(cnt);
    }

    public static int getPathCnt(int start, int end) {
        if (start == end) return 1;

        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(start);
        int cnt = 0;
        while(!queue.isEmpty()) {
            int cur = queue.poll();
            if (cur == end) cnt++;

            int next = cur + 1;
            if (next <= end && ((cur-1) / col == (next-1) / col)) {
                    queue.offer(next);
            }

            next = cur + col;
            if (next <= end) {
                    queue.offer(next);
            }
        }

        return cnt;
    }
}   