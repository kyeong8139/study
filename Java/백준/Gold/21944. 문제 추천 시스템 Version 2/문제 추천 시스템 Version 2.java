import java.io.*;
import java.util.*;

public class Main {

    static class Problem implements Comparable<Problem>{
        int id;
        int level;
        int category;

        public Problem (int id, int level, int category) {
            this.id = id;
            this.level = level;
            this.category = category;
        }

        @Override
        public int compareTo (Problem o) {
            if (o.level == this.level) {
                return o.id - this.id;
            }

            return o.level - this.level;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        HashMap<Integer, Problem> problemMap = new HashMap<>();
        int n = Integer.parseInt(br.readLine());
        TreeSet<Problem>[] problems = new TreeSet[101]; 
        for (int i = 1; i <= 100; i++) {
            problems[i] = new TreeSet<>();
        }

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int id = Integer.parseInt(st.nextToken());
            int level = Integer.parseInt(st.nextToken());
            int category = Integer.parseInt(st.nextToken());

            Problem problem = new Problem(id, level, category);
            problems[category].add(problem);
            problemMap.put(id, problem);
        }

        int m = Integer.parseInt(br.readLine());
        for (int cmd = 0; cmd < m; cmd++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String command = st.nextToken();

            switch (command) {
                case "recommend":
                    int category = Integer.parseInt(st.nextToken());
                    int order = Integer.parseInt(st.nextToken());

                    Problem target = null;
                    if (order == 1) {
                        target = problems[category].first();
                    } else {
                        target = problems[category].last();
                    }
                    sb.append(target.id).append("\n");
                    break;
                case "recommend2":
                    order = Integer.parseInt(st.nextToken());
                    int id = (order == 1) ? -1 : 10_0001;
                    int level = (order == 1) ? -1 : 101;
                    target = new Problem(id, level, -1);

                    if (order == 1) {
                        for (int i = 1; i <= 100; i++) {
                            if (problems[i].isEmpty()) continue;
                            Problem cur = problems[i].first();

                            if ((cur.level > target.level) || (cur.level == target.level && cur.id > target.id)) {
                                target = cur;
                            }
                        }
                    } else {
                        for (int i = 1; i <= 100; i++) {
                            if (problems[i].isEmpty()) continue;
                            Problem cur = problems[i].last();

                            if ((cur.level < target.level) || (cur.level == target.level && cur.id < target.id)) {
                                target = cur;
                            }
                        }
                    }
                    sb.append(target.id).append("\n");
                    break;
                case "recommend3":
                    order = Integer.parseInt(st.nextToken());
                    level = Integer.parseInt(st.nextToken());
                    target = new Problem(-1, level, -1);
                    Problem answer = null;

                    if (order == 1) {
                        for (int i = 1; i <= 100; i++) {
                            if (problems[i].isEmpty()) continue;
                            Problem cur = problems[i].floor(target);

                            if (cur != null && ((answer == null) || (cur.level < answer.level) || (cur.level == answer.level && cur.id < answer.id))) {
                                answer = cur;
                            }
                        }
                    } else {
                        for (int i = 1; i <= 100; i++) {
                            if (problems[i].isEmpty()) continue;
                            Problem cur = problems[i].ceiling(target);

                            if (cur != null && ((answer == null) || (cur.level > answer.level) || (cur.level == answer.level && cur.id > answer.id))) {
                                answer = cur;
                            }
                        }
                    }
                    sb.append(answer == null ? -1 : answer.id).append("\n");
                    break;
                case "add":
                    id = Integer.parseInt(st.nextToken());
                    level = Integer.parseInt(st.nextToken());
                    category = Integer.parseInt(st.nextToken());

                    Problem problem = new Problem(id, level, category);
                    problems[category].add(problem);
                    problemMap.put(id, problem);
                    break;
                case "solved":
                    id = Integer.parseInt(st.nextToken());
                    target = problemMap.get(id);

                    problems[target.category].remove(target);
                    problemMap.remove(id);
                    break;
            }
        }

        System.out.print(sb);
    }
}