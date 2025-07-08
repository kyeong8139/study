import java.io.*;

public class Main {

    static class Alias {
        int cnt = 0;
        Alias[] next = new Alias[26];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(br.readLine());

        Alias[] head = new Alias[26];
        for (int user = 0; user < n; user++) {
            String name = br.readLine();
            String result = null;
            Alias[] cur = head; 

            int pos = 0;
            for (; pos < name.length(); pos++) {
                int idx = name.charAt(pos) - 'a';
                if (cur[idx] == null) {
                    if (result == null) {
                        result = name.substring(0, pos + 1);
                    }
                    cur[idx] = new Alias();
                }

                if (pos != name.length()-1) {
                    cur = cur[idx].next;
                }
            }

            pos--;
            int idx = name.charAt(pos) - 'a';
            if (cur[idx] == null) {
                cur[idx] = new Alias();
            }

            Alias last = cur[idx];
            last.cnt = last.cnt + 1;
            if (result == null) {
                if (last.cnt == 1) {
                    sb.append(name).append("\n");
                } else {
                    sb.append(name).append(last.cnt).append("\n");
                }
            } else {
                sb.append(result).append("\n");
            }

        }

        System.out.print(sb);
    }
}