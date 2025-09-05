import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());

        List<Integer> list = new ArrayList<>();
        for (int i = 0 ; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String command = st.nextToken();
            switch (command) {
                case "push_back":
                    int num = Integer.parseInt(st.nextToken());
                    list.add(num);
                    break;
                case "pop_back":
                    list.remove(list.size() - 1);
                    break;
                case "size":
                    bw.write(String.valueOf(list.size()));
                    bw.newLine();
                    break;
                case "get" :
                    int idx = Integer.parseInt(st.nextToken()) - 1;
                    bw.write(String.valueOf(list.get(idx)));
                    bw.newLine();
                    break;
            }
        }
        bw.flush();
        bw.close();
    }
}