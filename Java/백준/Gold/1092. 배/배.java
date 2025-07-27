import java.util.*;
import java.io.*;

public class Main {

    static class Crane {
        int id;
        int weight;
        int cnt;

        public Crane(int id, int weight) {
            this.id = id;
            this.weight = weight;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        TreeSet<Crane> waitingCrane = new TreeSet<>((x, y) -> x.weight == y.weight ? x.id - y.id : x.weight - y.weight);
        PriorityQueue<Crane> usingCrane = new PriorityQueue<>((x, y) -> x.cnt - y.cnt);
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            int weight = Integer.parseInt(st.nextToken());
            waitingCrane.add(new Crane(i, weight));
        }

        int m = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        Integer[] boxes = new Integer[m];
        for (int i = 0; i < m; i++) {
            boxes[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(boxes, Comparator.reverseOrder());

        int answer = 0;
        outer: for (int i = 0; i < m; i++) {
            Crane dummy = new Crane(-1, boxes[i]);
            Crane target = waitingCrane.ceiling(dummy);
            while (target == null) {
                if (usingCrane.isEmpty())  {
                    answer = -1;
                    break outer;
                }
                
                int cnt = usingCrane.peek().cnt;
                while (!usingCrane.isEmpty() && usingCrane.peek().cnt == cnt) {
                    waitingCrane.add(usingCrane.poll());
                }
                target = waitingCrane.ceiling(dummy);
            }
            
            waitingCrane.remove(target);
            answer = Math.max(++target.cnt, answer);
            usingCrane.add(target);
        }

        System.out.print(answer);
    }
}