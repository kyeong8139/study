import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        BigInteger num = BigInteger.ONE;
        for (int i = 2; i <= n; i++) {
            num = num.multiply(BigInteger.valueOf(i));
        }

        int answer = 0;
        while (num.mod(BigInteger.TEN).equals(BigInteger.ZERO)) {
            answer++;
            num = num.divide(BigInteger.TEN);
        }

        System.out.print(answer);
    }
}