import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        Map<String, List<String>>[] maps = new HashMap[2];

        while (true) {
            String input = br.readLine();
            if (input == null) break;
            
            int n = Integer.parseInt(input);
            maps[0] = new HashMap<>();
            maps[1] = new HashMap<>();

            int mapIdx = 0;
            String initKey = "";
            Map<String, List<String>> map = maps[0];
            Map<String, List<String>> next = maps[1];
            map.put(initKey, new ArrayList<>());
            for (int i = 0; i < n; i++) {
                String word = br.readLine();
                map.get(initKey).add(word);
            }
            
            int sum = 0;
            while (!map.isEmpty()) {
                for (String key : map.keySet()) {
                    List<String> value = map.get(key);
                    
                    int visited = 0;
                    int cnt = 0;
                    boolean needInput = (key.length() == 0);
                    for (String word : value) {
                        if (word.length() == key.length()) {
                            needInput = true;
                            continue;
                        }
                        
                        cnt++;
                        char nextChar = word.charAt(key.length());
                        int nextIdx = nextChar -'a';
                        visited |= 1 << nextIdx;

                        String nextKey = word.substring(0, key.length()+1);
                        if (!next.containsKey(nextKey)) next.put(nextKey, new ArrayList<>());
                        next.get(nextKey).add(word);
                    }

                    if (Integer.bitCount(visited) != 1) needInput = true;
                    if (needInput) {
                        sum += cnt;
                    }
                }

                map.clear();
                mapIdx ^= 1;
                map = maps[mapIdx];
                next = maps[mapIdx^1];
            }

            double answer = sum / (double) n;
            bw.write(String.format("%.2f", answer));
            bw.newLine();
        }
        bw.flush();
        bw.close();
    }
}
