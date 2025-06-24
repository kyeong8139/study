import java.io.*;
import java.util.*;

public class Main {
    
    static int[] parents;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        parents = new int[n+1];
        for (int i = 1; i <= n; i++) {
            parents[i] = i;
        }

        for (int i = 0; i < n-2; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            union(a, b);
        }

        int prev = getParent(1);
        for (int i = 2; i <= n; i++) {
            int cur = getParent(i);
            if (prev != cur) {
                System.out.println((i-1) + " " + i);
                break;
            }

            prev = cur;
        }
    }

    public static void union(int a, int b) {
        int parent_A = getParent(a);
        int parent_B = getParent(b);

        if (parent_A != parent_B) {
            parents[parent_A] = parent_B;
        }
    }

    public static int getParent(int a) {
        if (parents[a] == a) {
            return a;
        }

        return parents[a] = getParent(parents[a]);
    }
}