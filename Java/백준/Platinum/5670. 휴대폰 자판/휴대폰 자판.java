import java.io.*;
import java.util.*;

public class Main {

    static long initHash = 31L;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        Map<Long, List<String>>[] maps = new HashMap[2];

        while (true) {
            String input = br.readLine();
            if (input == null) break;
            
            int n = Integer.parseInt(input);
            maps[0] = new HashMap<>();
            maps[1] = new HashMap<>();

            int mapIdx = 0;
            long initKey = initHash;
            Map<Long, List<String>> map = maps[0];
            Map<Long, List<String>> next = maps[1];
            map.put(initKey, new ArrayList<>());
            for (int i = 0; i < n; i++) {
                String word = br.readLine();
                map.get(initKey).add(word);
            }
            
            int sum = 0;
            int keyLength = 0;
            while (!map.isEmpty()) {
                for (long key : map.keySet()) {
                    List<String> value = map.get(key);
                    
                    int visited = 0;
                    int cnt = 0;
                    boolean needInput = (key == initKey);
                    for (String word : value) {
                        if (word.length() == keyLength) {
                            needInput = true;
                            continue;
                        }
                        
                        cnt++;
                        char nextChar = word.charAt(keyLength);
                        int nextIdx = nextChar -'a';
                        visited |= 1 << nextIdx;

                        long nextKey = getHash(key, nextChar); 
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
                keyLength++;
            }

            double answer = sum / (double) n;
            bw.write(String.format("%.2f", answer));
            bw.newLine();
        }
        bw.flush();
        bw.close();
    }

    public static long getHash(long hash, char c) {
        return (hash * 26) + (c - 'a');
    } 
}
