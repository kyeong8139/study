import java.util.*;
import java.io.*;

public class Main {

    static int[] segmentTree;
    static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        while (true) {
            String input = br.readLine();
            if (input == null) break;

            StringTokenizer st = new StringTokenizer(input);
            n = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            int[] rootRange = { 0, n - 1 };
            int size = getSize(n);
            segmentTree = new int[size * 2];
            Arrays.fill(segmentTree, 1);

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                int num = Integer.parseInt(st.nextToken());
                update(1, rootRange, i, getSign(num));
            }

            for (int i = 0; i < k; i++) {
                st = new StringTokenizer(br.readLine());
                String command = st.nextToken();

                switch (command) {
                    case "C":
                        int idx = Integer.parseInt(st.nextToken());
                        int data = Integer.parseInt(st.nextToken());
                        update(1, rootRange, idx - 1, getSign(data));
                        break;

                    case "P":
                        int start = Integer.parseInt(st.nextToken());
                        int end = Integer.parseInt(st.nextToken());
                        int[] targetRange = { start - 1, end - 1 };
                        int result = query(1, rootRange, targetRange);

                        String answer;
                        if (result > 0)
                            answer = "+";
                        else if (result < 0)
                            answer = "-";
                        else
                            answer = "0";

                        bw.write(answer);
                        break;
                }
            }
            bw.newLine();
        }
        bw.flush();
        bw.close();
    }

    public static int getSize(int n) {
        int size = 1;
        while (size < n) {
            size *= 2;
        }
        return size;
    }

    public static void update(int cur, int[] curRange, int target, int val) {
        if (curRange[0] == curRange[1]) {
            segmentTree[cur] = val;
            return;
        }

        int mid = (curRange[0] + curRange[1]) / 2;
        if (target <= mid) {
            int[] leftRange = { curRange[0], mid };
            update(cur * 2, leftRange, target, val);
        } else {
            int[] rightRange = { mid + 1, curRange[1] };
            update(cur * 2 + 1, rightRange, target, val);
        }
        segmentTree[cur] = segmentTree[cur * 2] * segmentTree[cur * 2 + 1];
    }

    public static int query(int cur, int[] curRange, int[] targetRange) {
        if (targetRange[1] < curRange[0] || targetRange[0] > curRange[1]) {
            return 1;
        }
        if (targetRange[0] <= curRange[0] && targetRange[1] >= curRange[1]) {
            return segmentTree[cur];
        }

        int mid = (curRange[0] + curRange[1]) / 2;

        int[] leftRange = { curRange[0], mid };
        int leftResult = query(cur * 2, leftRange, targetRange);
        
        int[] rightRange = { mid + 1, curRange[1] };
        int rightResult = query(cur * 2 + 1, rightRange, targetRange);

        return leftResult * rightResult;
    }

    private static int getSign(int data) {
        if (data == 0) {
            return 0;
        } else if (data < 0) {
            return -1;
        } else {
            return 1;
        }
    }
}