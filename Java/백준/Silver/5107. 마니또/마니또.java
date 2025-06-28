import java.util.*;
import java.io.*;

public class Main {

    static Map<String, String> parents = new HashMap<>(); 
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int testCase = 1;
        while (true) {
            int n = Integer.parseInt(br.readLine());
            if (n == 0) break;

            parents.clear();
            
            for (int i = 0; i < n; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                String giver = st.nextToken();
                String taker = st.nextToken();

                union(giver, taker);
            }

            HashSet<String> boss = new HashSet<>();
            for (String child : parents.keySet()) {
                boss.add(getParent(child));
            }

            sb.append(testCase++).append(" ").append(boss.size()).append("\n");
        }

        System.out.print(sb.toString());
    }

    public static String getParent(String child) {
        if (!parents.containsKey(child)) {
            parents.put(child, child);
        }

        String parent = parents.get(child);
        if (parent.equals(child)) {
            return parent;
        }

        return getParent(parent);
    }

    public static void union(String giver, String taker) {
        String giverParent = getParent(giver);
        String takerParent = getParent(taker);

        if (!giverParent.equals(takerParent)) {
            parents.put(takerParent, giverParent);
        }
    }
}   