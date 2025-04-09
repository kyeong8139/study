import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();

        int testCase = Integer.parseInt(br.readLine());
        while(--testCase >= 0) {
            long target = Long.parseLong(br.readLine());
            if (target == 0L) {
                answer.append("0").append("\n");
                continue;
            }

            StringBuilder cur = new StringBuilder();
            while (target != 0) {
                switch ((int) (target % 3)) {
                    case 0:
                    case 1:
                        cur.append(target % 3);
                        break;
                    case -1:
                        cur.append("-");
                        break;
                    case 2:
                        cur.append("-");
                        target++;
                        break;
                    case -2:
                        cur.append("1");
                        target--;
                        break;
                }
                target /= 3;
            }
            answer.append(cur.reverse()).append("\n");
        }

        System.out.print(answer);
    }

}