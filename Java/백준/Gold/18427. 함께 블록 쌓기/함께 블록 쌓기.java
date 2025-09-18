import java.io.*;
import java.util.*;

public class Main {

    static final int MOD = 10007;
    static int answer = 0;
    static int n, m, h;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());

        List<Integer>[] blocks = new ArrayList[n]; 
        for (int student = 0; student < n; student++) {
            blocks[student] = new ArrayList<>();
            blocks[student].add(0);

            st = new StringTokenizer(br.readLine());
            while (st.hasMoreTokens()) {
                blocks[student].add(Integer.parseInt(st.nextToken()));
            }
        }

        Map<Integer, Integer> prev = new HashMap<>();
        prev.put(0, 1);
        for (int student = 0; student < n; student++) {
            Map<Integer, Integer> cur = new HashMap<>();
            for (int prevKey : prev.keySet()) {
                for (int block : blocks[student]) {
                    int key = prevKey + block;
                    if (key > h) continue;

                    int value = (prev.get(prevKey) % MOD) + (cur.getOrDefault(key, 0) % MOD);
                    cur.put(key, value);
                }
            }
            prev = cur;
            prev.put(0, 1);
        }

        System.out.print(prev.getOrDefault(h, 0) % MOD);
    }
}