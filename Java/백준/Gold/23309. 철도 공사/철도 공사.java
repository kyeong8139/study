import java.util.*;
import java.io.*;

public class Main {

    static class Station {
        int id;
        Station prev;
        Station next;

        public Station(int id, Station prev, Station next) {
            this.id = id;
            this.prev = prev;
            this.next = next;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        Station[] stations = new Station[100_0001];
        st = new StringTokenizer(br.readLine());

        int id = Integer.parseInt(st.nextToken());
        Station head = new Station(id, null, null);
        head.prev = head;
        head.next = head;
        stations[id] = head;

        Station prev = head;
        for (int i = 1; i < n; i++) {
            id = Integer.parseInt(st.nextToken());
            Station cur = new Station(id, prev, head);
            stations[id] = cur;

            prev.next = cur;
            prev = cur;
        }
        head.prev = prev;

        for (int cmd = 0; cmd < m; cmd++) {
            st = new StringTokenizer(br.readLine());
            String command = st.nextToken();
            int i = Integer.parseInt(st.nextToken());
            Station cur = stations[i];

            switch(command) {
                case "BN":
                    bw.write(String.valueOf(cur.next.id));
                    id = Integer.parseInt(st.nextToken());
                    if (stations[id] != null) continue;

                    Station newStation = new Station(id, cur, cur.next);
                    cur.next.prev = newStation;
                    cur.next = newStation;
                    stations[id] = newStation;
                    break;
                case "BP":
                    bw.write(String.valueOf(cur.prev.id));
                    id = Integer.parseInt(st.nextToken());
                    if (stations[id] != null) continue;

                    newStation = new Station(id, cur.prev, cur);
                    cur.prev.next = newStation;
                    cur.prev = newStation;
                    stations[id] = newStation;
                    break;
                case "CN":
                    int targetId = cur.next.id;
                    bw.write(String.valueOf(targetId));

                    cur.next = cur.next.next;
                    cur.next.prev = cur;
                    stations[targetId] = null;
                    break;
                case "CP":
                    targetId = cur.prev.id;
                    bw.write(String.valueOf(targetId));

                    cur.prev = cur.prev.prev;
                    cur.prev.next = cur;
                    stations[targetId] = null;
                    break;
            }
            bw.newLine();
        }
        bw.flush();
        bw.close();
    }
}