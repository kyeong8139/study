import java.io.*;
import java.util.*;

public class Main {

    static final int MOD = 10_0000_0007;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int testCase = Integer.parseInt(br.readLine());

        while (--testCase >= 0) {
            int n = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());
    
            PriorityQueue<Long> slimes = new PriorityQueue<>((x, y) -> x < y ? -1 : 1);
            for (int i = 0; i < n; i++) {
                long slime = Long.parseLong(st.nextToken()); 
                slimes.add(slime);
            }

            long answer = 1L;
            while (slimes.size() > 1) {
                long slimeA = slimes.poll();
                long slimeB = slimes.poll();

                long energy = (slimeA % MOD) * (slimeB % MOD);
                slimes.add(energy);
                answer = (answer * (energy % MOD)) % MOD; 
            }
            bw.write(String.valueOf(answer));
            bw.newLine();
        }
        bw.flush();
        bw.close();
    }
}