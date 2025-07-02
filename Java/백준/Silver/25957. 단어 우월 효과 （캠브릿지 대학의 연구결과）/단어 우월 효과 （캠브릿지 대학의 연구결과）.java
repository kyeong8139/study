import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        HashMap<Long, String>[][] words = new HashMap[27][27];
        for (int start = 0; start < words.length; start++) {
            for (int end = 0; end < words[0].length; end++) {
                words[start][end] = new HashMap<>();
            }
        }

        for (int i = 0; i < n; i++) {
            String word = br.readLine();
            int start = getIdx(word.charAt(0));
            int end = word.length() == 1 ? 0 : getIdx(word.charAt(word.length() - 1));
            long hash = getHash(word);

            words[start][end].put(hash, word);
        }

        int m = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++) {
            String word = st.nextToken();
            int start = getIdx(word.charAt(0));
            int end = word.length() == 1 ? 0 : getIdx(word.charAt(word.length() - 1));
            long hash = getHash(word);

            String origin = words[start][end].get(hash);
            sb.append(origin).append(" ");
        }
        System.out.print(sb);
    }

    public static int getIdx(char alphabet) {
        return alphabet - 'a' + 1;
    }

    public static long getHash(String word) {
        int[] frequency = new int[27];
        for (char c : word.toCharArray()) {
            int idx = getIdx(c);
            frequency[idx]++;
        }

        long hash = 0;
        long MOD = 1_000_000_007;
        int base = 31;
        for (int cnt : frequency) {
            hash = (hash * base + cnt) % MOD;
        }

        return hash;
    }
}