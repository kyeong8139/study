import java.io.*;
import java.util.*;

public class Main {

    static int maxMusic = 0;
    static int guitarCount = Integer.MAX_VALUE;
    static long[] guitars; 

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        guitars = new long[n];
        for (int g = 0; g < n; g++) {
            st = new StringTokenizer(br.readLine());
            String name = st.nextToken();
            String music = st.nextToken();

            long musicBit = 0;
            for (int i = 0; i < m; i++) {
                if (music.charAt(i) == 'Y') {
                    musicBit |= (1L << i);
                }
            }
            guitars[g] = musicBit;
        }

        comb(0, 0, 0);

        int answer = maxMusic == 0 ? -1 : guitarCount;
        System.out.println(answer);
    }

    public static void comb(int depth, int cnt, long result) {
        if (depth == guitars.length) {
            int music = Long.bitCount(result);
            if (maxMusic < music) {
                maxMusic = music;
                guitarCount = cnt;
            } else if (maxMusic == music) {
                guitarCount = Math.min(guitarCount, cnt);
            }
            return;
        }

        comb(depth+1, cnt, result);
        comb(depth+1, cnt+1, result | guitars[depth]);
    }
}