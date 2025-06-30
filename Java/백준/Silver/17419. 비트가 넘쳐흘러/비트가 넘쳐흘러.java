import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        String k = br.readLine();

        int cnt = 0;
        String num = k;
        do {
            num = calcurate(num);
            cnt++;
        } while (!num.equals("0"));

        System.out.print(cnt);
    }

    public static String calcurate (String num) {
    
        char[] origin = num.toCharArray();
        char[] result = num.toCharArray();
        for (int i = 0; i < result.length; i++) {
            result[i] = (result[i] == '0') ? '1' : '0';
        }

        for (int i = result.length - 1; i >= 0; i--) {
            if (result[i] == '0') {
                result[i] = '1';
                break;
            } else {
                result[i] = '0';
            }
        }

        for (int i = 0; i < result.length; i++) {
            if (origin[i] != result[i]) {
                result[i] = '0';
            }
        }

        BigInteger answer = new BigInteger(new String(origin), 2).subtract(new BigInteger(new String(result), 2));
        return answer.toString(2);
    }
}