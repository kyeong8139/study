import java.io.*;
import java.util.*;

public class Main {

    static class SegmentTree{
        long[] tree;
        int dataSize;
        
        public SegmentTree(long[] arr) {
            int leafSize = 1;
            this.dataSize = arr.length - 1;
            while (leafSize < dataSize) {
                leafSize *= 2;
            }
            tree = new long[leafSize * 2];

            init(arr, 1, 1, dataSize);
        }

        private long init(long[] arr, int cur, int start, int end) {
            if (start == end) {
                return tree[cur] = arr[start];
            }

            int mid = (start + end) / 2;
            long left = init(arr, cur*2, start, mid);
            long right = init(arr, cur*2+1, mid+1, end);
            return tree[cur] = left + right;
        }

        public void update(int idx, long diff) {
            update(1, 1, dataSize, idx, diff);
        }

        private void update(int cur, int start, int end, int idx, long diff) {
            if (idx < start || idx > end) return;

            tree[cur] += diff;
            if (start != end) {
                int mid = (start + end) / 2;
                update(cur*2, start, mid, idx, diff);
                update(cur*2+1, mid+1, end, idx, diff);
            }
        }

        public long getSum(int left, int right) {
            return getSum(1, 1, dataSize, left, right);
        }

        private long getSum(int cur, int start, int end, int left, int right) {
            if (right < start || end < left) return 0;

            if (left <= start && end <= right) {
                return tree[cur];
            }

            int mid = (start + end) / 2;
            long leftSum = getSum(cur*2, start, mid, left, right);
            long rightSum = getSum(cur*2+1, mid+1, end, left, right);
            return leftSum + rightSum;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int q = Integer.parseInt(st.nextToken());

        long[] arr = new long[n+1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            arr[i] = Long.parseLong(st.nextToken());
        }
        
        SegmentTree tree = new SegmentTree(arr);
        for (int query = 0; query < q; query++) {
            st = new StringTokenizer(br.readLine());
            int left = Integer.parseInt(st.nextToken());
            int right = Integer.parseInt(st.nextToken());
            if (left > right) {
                int temp = left;
                left = right;
                right = temp;
            }

            long sum = tree.getSum(left, right);
            sb.append(sum).append("\n");

            int idx = Integer.parseInt(st.nextToken());
            long value = Long.parseLong(st.nextToken());
            tree.update(idx, value-arr[idx]);
            arr[idx] = value;
        }
        System.out.print(sb);
    }
}