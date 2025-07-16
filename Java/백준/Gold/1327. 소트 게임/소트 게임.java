import java.io.*;
import java.util.*;

public class Main {

    static class State {
        int[] arr;
        int cnt;

        public State(int[] arr, int count) {
            this.arr = arr;
            this.cnt = count;
        }
    }
    static int N, K;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        int[] init = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            init[i] = Integer.parseInt(st.nextToken());
        }

        int answer = -1;
        int[] target = Arrays.copyOf(init, N);
        Arrays.sort(target);

        Queue<State> queue = new ArrayDeque<>();
        Set<Integer> visitedHash = new HashSet<>();
        queue.add(new State(Arrays.copyOf(init, N), 0));
        visitedHash.add(getHash(init));

        while (!queue.isEmpty()) {
            State cur = queue.poll();

            if (Arrays.equals(cur.arr, target)) {
                answer = cur.cnt;
                break;
            }

            for (int i = 0; i <= N - K; i++) {
                int[] nextArr = reverse(cur.arr, i);
                int hash = getHash(nextArr);
                if (!visitedHash.contains(hash)) {
                    visitedHash.add(hash);
                    queue.add(new State(nextArr, cur.cnt + 1));
                }
            }
        }
        System.out.println(answer);
    }

    public static int[] reverse(int[] cards, int begin) {
        int[] result = Arrays.copyOf(cards, N);
        int left = begin;
        int right = begin + K - 1;

        while (left < right) {
            int temp = result[left];
            result[left] = result[right];
            result[right] = temp;
            left++;
            right--;
        }

        return result;
    }

    public static int getHash(int[] arr) {
        int hash = 31;
        for (int i = 0; i < N; i++) {
            hash = hash * 31 + arr[i];
        }
        return hash;
    }
}