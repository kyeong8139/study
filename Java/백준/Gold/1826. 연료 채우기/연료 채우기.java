import java.io.*;
import java.util.*;

public class Main {

    static class Station {
        int pos;
        int fuel;

        public Station (int pos, int fuel) {
            this.pos = pos;
            this.fuel = fuel;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        PriorityQueue<Station> stations = new PriorityQueue<>((x, y) -> x.pos - y.pos);
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int pos = Integer.parseInt(st.nextToken());
            int fuel = Integer.parseInt(st.nextToken());

            stations.add(new Station(pos, fuel));
        }
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        int endPos = Integer.parseInt(st.nextToken());
        stations.add(new Station(endPos, 0));

        int curFule = Integer.parseInt(st.nextToken());
        int curPos = 0;

        boolean isOk = true;
        List<Station> visited = new ArrayList<>();
        PriorityQueue<Station> notVisited = new PriorityQueue<>((x,  y) -> y.fuel - x.fuel);
        while (!stations.isEmpty()) {
            Station station = stations.poll();
            curFule -= (station.pos - curPos); 
            if (curFule < 0) {
                while (!notVisited.isEmpty()){
                    Station prev = notVisited.poll();
                    visited.add(prev);
                    curFule += prev.fuel;
                    
                    if (curFule >= 0) {
                        break;
                    }
                }
                
                if (curFule < 0) {
                    isOk = false;
                    break;
                }
            } 
            
            notVisited.add(station);
            curPos = station.pos;
        }

        int answer = -1;
        if (isOk) {
            answer = visited.size();
        }

        System.out.print(answer);
    }
}