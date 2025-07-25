import java.util.*;
import java.io.*;

public class Main {

    static class Computer {
        int id;
        int endTime;
        int cnt;

        public Computer (int id) {
            this.id = id;
        }
    }

    static class Person implements Comparable<Person> {
        int startTime;
        int endTime;

        public Person(int startTime, int endTime) {
            this.startTime = startTime;
            this.endTime = endTime;
        }

        @Override
        public int compareTo(Person o) {
            if (this.startTime == o.endTime) {
                return this.endTime - o.endTime;
            }
            return this.startTime - o.startTime;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        PriorityQueue<Person> people = new PriorityQueue<>();
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            people.add(new Person(start, end));
        }
        
        PriorityQueue<Computer> emptyComputer = new PriorityQueue<>((x, y) -> x.id - y.id);
        PriorityQueue<Computer> usingComputer = new PriorityQueue<>((x, y) -> x.endTime - y.endTime);

        int computerCnt = 0;
        while (!people.isEmpty()) {
            Person cur = people.poll();

            while (!usingComputer.isEmpty() && usingComputer.peek().endTime <= cur.startTime) {
                emptyComputer.add(usingComputer.poll());
            }

            if (emptyComputer.isEmpty()) {
                Computer newComputer = new Computer(computerCnt++);
                emptyComputer.add(newComputer);
            }

            Computer target = emptyComputer.poll();
            target.endTime = cur.endTime;
            target.cnt++;
            usingComputer.add(target);
        }

        int[] cnts = new int[computerCnt];
        while(!usingComputer.isEmpty()) {
            Computer computer = usingComputer.poll();
            cnts[computer.id] = computer.cnt;
        }
        while(!emptyComputer.isEmpty()) {
            Computer computer = emptyComputer.poll();
            cnts[computer.id] = computer.cnt;
        }

        StringBuilder sb = new StringBuilder();
        sb.append(computerCnt).append("\n");
        for (int cnt : cnts) {
            sb.append(cnt).append(" ");
        }
        System.out.println(sb);
    }
}