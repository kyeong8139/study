import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        
        boolean[] answer = new boolean[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            String cur = st.nextToken();
            if (cur.equals("1")) {
                answer[i] = true;
            }
        }

        int cnt = 0;
        boolean[] lights = new boolean[n];
        for (int i = 0; i < n; i++) {
            if (answer[i] != lights[i]) {
                lights[i] = !lights[i];
                if (i+1 < n) lights[i+1] = !lights[i+1];
                if (i+2 < n) lights[i+2] = !lights[i+2];

                cnt++;
            }
        }

        System.out.print(cnt);
    }
}   