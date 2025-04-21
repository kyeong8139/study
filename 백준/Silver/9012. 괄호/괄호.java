import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int testCase = Integer.parseInt(br.readLine());

        while (--testCase >= 0) {
            boolean isValid = true;
            char[] input = br.readLine().toCharArray();

            int cnt = 0; // 닫히지 않은 괄호의 갯수
            for (int i = 0; i < input.length; i++) {
                if (input[i] == ')') {
                    if (cnt <= 0) {
                        isValid = false;
                        break;
                    }
                    cnt--;
                } else if (input[i] == '(') {
                    cnt++;
                }
            }

            if (cnt != 0) {
                isValid = false;
            }

            if (isValid) {
                sb.append("YES\n");
            } else {
                sb.append("NO\n");
            }
        }
        System.out.print(sb);
    }

}