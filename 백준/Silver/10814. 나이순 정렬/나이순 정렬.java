import java.io.*;
import java.util.*;

public class Main {

    static class Person implements Comparable<Person> {
        int order;
        int age;
        String name;

        public Person (int order, int age, String name) {
            this.order = order;
            this.age = age;
            this.name = name;
        }

        public int compareTo (Person o) {
            if (this.age == o.age) {
                return this.order - o.order;
            }
            return this.age - o.age;
        }

        public String toString() {
            return this.age + " " + this.name;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        PriorityQueue<Person> pq = new PriorityQueue<>();
        for (int order = 1; order <= n; order++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int age = Integer.parseInt(st.nextToken());
            String name = st.nextToken();

            pq.add(new Person(order, age, name));
        }

        StringBuilder answer = new StringBuilder();
        while (!pq.isEmpty()) {
            answer.append(pq.poll().toString()).append("\n");
        }
        System.out.print(answer);
    }
}