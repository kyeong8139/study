import java.io.*;
import java.util.*;

public class Main {

    static class Factory implements Comparable<Factory>{
        int usedTime;

        @Override
        public int compareTo(Factory o) {
            return this.usedTime - o.usedTime;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());

        int[] items = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            items[i] = Integer.parseInt(st.nextToken());
        }

        int answer = binarySearch(1, n, x, items);
        bw.write(String.valueOf(answer));
        bw.flush();
        bw.close();
    }

    public static int binarySearch(int start, int end, int limitTime, int[] items) {
        int left = start;
        int right = end;
        int factoryCnt = end;
        while (left <= right) {
            int mid = (left + right) / 2;
            boolean result = isPossible(mid, limitTime, items);

            if (result) {
                right = mid - 1;
                factoryCnt = Math.min(factoryCnt, mid);
            } else {
                left = mid + 1;
            }
        }

        return factoryCnt;
    }

    public static boolean isPossible(int factoryCnt, int limitTime, int[] items) {
        PriorityQueue<Factory> pq = new PriorityQueue<>();
        for (int i = 0; i < factoryCnt; i++) {
            pq.add(new Factory());
        }

        for (int i = 0; i < items.length; i++) {
            Factory cur = pq.poll();
            cur.usedTime += items[i];
            pq.add(cur);
        }

        int time = 0;
        while (!pq.isEmpty()) {
            time = Math.max(time, pq.poll().usedTime);
        }

        if (time > limitTime) {
            return false;
        }
        return true;
    }
}