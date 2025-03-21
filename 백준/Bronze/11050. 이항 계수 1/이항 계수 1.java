import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int result = getCombination(n, k);
        System.out.print(result);
    }

    private static int getCombination(int n, int k) {
        if (k == 0 || n == k) {
            return 1;
        }
        
        return getCombination(n-1, k) + getCombination(n-1, k-1);
    }
}