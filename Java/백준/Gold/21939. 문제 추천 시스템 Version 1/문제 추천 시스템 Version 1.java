import java.io.*;
import java.util.*;

public class Main {

    static class Problem implements Comparable<Problem> {
        int id;
        int level;

        public Problem(int id, int level) {
            this.id = id;
            this.level = level;
        }

        public int compareTo(Problem o) {
            if (this.level == o.level) {
                return o.id - this.id;
            }

            return o.level - this.level;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        TreeSet<Problem> set = new TreeSet<>();
        HashMap<Integer, Integer> levels = new HashMap<>();

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int id = Integer.parseInt(st.nextToken());
            int level = Integer.parseInt(st.nextToken());

            set.add(new Problem(id, level));
            levels.put(id, level);
        }

        int m = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < m; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String command = st.nextToken();

            switch(command) {
                case "recommend":
                    int order = Integer.parseInt(st.nextToken());
                    Problem problem = null;
                    if (order > 0) {
                        problem = set.first();
                    } else {
                        problem = set.last();
                    }
                    sb.append(problem.id).append("\n");
                    break;
                case "add":
                    int id = Integer.parseInt(st.nextToken());
                    int level = Integer.parseInt(st.nextToken());
                    
                    set.add(new Problem(id, level));
                    levels.put(id, level);
                    break;
                case "solved":
                    id = Integer.parseInt(st.nextToken());
                    level = levels.get(id);

                    Problem target = set.ceiling(new Problem(id, level));
                    set.remove(target);
                    break;
            }
        }

        System.out.print(sb);
    }
}