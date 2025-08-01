import java.util.*;
import java.io.*;

public class Main {

    static class SegmentTree {
        int length = 0;
        int n;
        long[] tree;

        public SegmentTree(int n) {
            int height = ((int) Math.ceil(Math.log(n) / Math.log(2))) + 1;
            int size = (int) Math.pow(2, height);
            this.tree = new long[size];
            this.n = n;
        }

        public void add(long data) {
            updateData(1, 0, n-1, length, data);
            length++;
        }

        public void update(int targetOrder, long newData) {
            updateData(1, 0, n-1, targetOrder-1, newData);
        }

        private long updateData(int curPos, int curStartIdx, int curEndIdx, int targetIdx, long newData) {
            if (curStartIdx > targetIdx || curEndIdx < targetIdx) {
                return tree[curPos];
            } else if (curStartIdx == curEndIdx) {
                tree[curPos] = newData;
                return tree[curPos];
            }

            int mid = (curStartIdx + curEndIdx) / 2;
            long left = updateData(curPos*2, curStartIdx, mid, targetIdx, newData);
            long right = updateData(curPos*2 + 1, mid + 1, curEndIdx, targetIdx, newData);
            tree[curPos] = left + right;
            return tree[curPos];
        }
    
        public long getSum(int targetStartOrder, int targetEndOrder) {
            return getSum(1, 0, n-1, targetStartOrder - 1, targetEndOrder - 1);
        }

        private long getSum (int curPos, int curStartIdx, int curEndIndex, int targetStartIdx, int targetEndIdx) {
            if (curStartIdx > targetEndIdx || curEndIndex < targetStartIdx) {
                return 0;
            } else if (curStartIdx >= targetStartIdx && curEndIndex <= targetEndIdx) {
                return tree[curPos];
            }

            int mid = (curStartIdx + curEndIndex) / 2;
            long left = getSum(curPos*2, curStartIdx, mid, targetStartIdx, targetEndIdx);
            long right = getSum(curPos*2+1, mid+1, curEndIndex, targetStartIdx, targetEndIdx);
            return left + right;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        SegmentTree tree = new SegmentTree(n);
        for (int i = 0; i < n; i++) {
            long num = Long.parseLong(br.readLine());
            tree.add(num);
        }

        for (int i = 0; i < m+k; i++) {
            st = new StringTokenizer(br.readLine());
            int command = Integer.parseInt(st.nextToken());

            if (command == 1) {
                int order = Integer.parseInt(st.nextToken());
                long data = Long.parseLong(st.nextToken());
                tree.update(order, data);
            } else if (command == 2) {
                int startOrder = Integer.parseInt(st.nextToken());
                int endOrder = Integer.parseInt(st.nextToken());
                long result = tree.getSum(startOrder, endOrder);

                bw.write(String.valueOf(result));
                bw.newLine();
            }
        }
        bw.flush();
        bw.close();
    }
}
