import java.util.*;
import java.io.*;

public class Main {

    static class Diamond{
        double weight;
        double value;

        public Diamond (double weight, double value) {
            this.weight = weight;
            this.value = value;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int testCase = Integer.parseInt(br.readLine());

        for (int tc = 0; tc < testCase; tc++) {
            int n = Integer.parseInt(br.readLine());
            Diamond[] diamonds = new Diamond[n];
            for (int i = 0; i < n; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                double weight = Double.parseDouble(st.nextToken());
                double value = Double.parseDouble(st.nextToken());

                diamonds[i] = new Diamond(weight, value);
            }

            List<Diamond>[] dp = new List[n];
            for(int i = 0; i < n; i++) {
                dp[i] = new ArrayList<>();
            }
            
            int cnt = 0;
            for (int i = 0; i < n; i++) {
                Diamond cur = diamonds[i];
                
                int curIdx = 0;
                for (List<Diamond> list : dp) {
                    boolean isValuable = false;
                    for (Diamond dia : list) {
                        if (dia.weight < cur.weight && dia.value > cur.value) {
                            isValuable = true;
                            break;
                        }
                    }

                    if (!isValuable) break;
                    curIdx++;
                }
                dp[curIdx].add(cur);
                if (curIdx == cnt) cnt++;
            }
            sb.append(cnt).append("\n");
            
        }
        System.out.print(sb);
    }
}