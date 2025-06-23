import java.util.*;
import java.io.*;

public class Main {

    static class Information implements Comparable<Information> {
        int value;

        public Information(int value) {
            this.value = value;
        }

        public int compareTo(Information o) {
            return o.value - this.value;
        }
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        long sum = 0;
        HashMap<String, PriorityQueue<Information>> map = new HashMap<>();

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int command = Integer.parseInt(st.nextToken());

            switch (command) {
                case 1:
                    String name = st.nextToken();
                    if (!map.containsKey(name)) {
                        map.put(name, new PriorityQueue<>());
                    }

                    int cnt = Integer.parseInt(st.nextToken());
                    for (int j = 0; j < cnt; j++) {
                        int value = Integer.parseInt(st.nextToken());
                        map.get(name).add(new Information(value));
                    }

                    break;
            
                case 2:
                    name = st.nextToken();
                    cnt = Integer.parseInt(st.nextToken());

                    if (!map.containsKey(name)) break;

                    for (int j = 0; j < cnt; j++) {
                        if (map.get(name).isEmpty()) break;
                        sum += map.get(name).poll().value;
                    }
                
                    break;
            }
        }

        System.out.println(sum);
    }
}   