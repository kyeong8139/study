import java.io.*;
import java.util.*;

public class Main {

    static class Alias {
        HashMap<Character, Alias> next = new HashMap<>();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(br.readLine());

        HashMap<Character, Alias> head = new HashMap<>();
        HashMap<String, Integer> cnts = new HashMap<>();
        for (int user = 0; user < n; user++) {
            String name = br.readLine();
            cnts.put(name, cnts.getOrDefault(name, 0) + 1);

            String result = null;
            HashMap<Character, Alias> cur = head; 

            int i = 0;
            for (; i < name.length(); i++) {
                char key = name.charAt(i);
                if (!cur.containsKey(key)) {
                    result = name.substring(0, i+1);
                    break;
                }

                cur = cur.get(key).next;
            }

            for (; i < name.length(); i++) {
                char key = name.charAt(i);
                if (!cur.containsKey(key)) {
                    cur.put(key, new Alias());
                }

                cur = cur.get(key).next;
            }

            if (result == null) {
                int cnt = cnts.get(name);
                if (cnt == 1) {
                    sb.append(name).append("\n");
                } else {
                    sb.append(name).append(cnt).append("\n");
                }
            } else {
                sb.append(result).append("\n");
            }
        }

        System.out.print(sb);
    }
}