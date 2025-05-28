import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testCase = Integer.parseInt(br.readLine());
        StringBuilder answer = new StringBuilder();

        while (--testCase >= 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String inputA = st.nextToken();
            String inputB = st.nextToken();
            int length = Math.max(inputA.length(), inputB.length());

            StringBuilder result = new StringBuilder();
            int carry = 0;
            for (int i = 1; i <= length; i++) {
                int idxA = inputA.length() - i;
                int idxB = inputB.length() - i;

                int curA = 0;
                int curB = 0;
                if (idxA >= 0) {
                    curA = inputA.charAt(idxA) - '0';
                }
                if (idxB >= 0) {
                    curB = inputB.charAt(idxB) - '0';
                }

                int cur = curA + curB + carry;
                carry = cur / 2;
                cur = cur % 2;

                result.insert(0, cur);
            }

            if (carry == 1) {
                result.insert(0, 1);
            }

            while (result.length() > 1 && result.charAt(0) == '0') {
                result.deleteCharAt(0);
            }
            answer.append(result).append("\n");

        }

        System.out.print(answer);
    }
}