import java.io.*;
import java.util.*;

public class Main {

    public static final long MOD = 1234567891;
    public static final long MULTIPLY = 31;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int length = Integer.parseInt(br.readLine());
        String input = br.readLine();

        long answer = 0;
        long r = 1;
        for (int i = 0; i < length; i++) {
            int num = input.charAt(i) - 'a' + 1;
            answer = (answer + ((num * r) % MOD)) % MOD;

            r = (r * MULTIPLY) % MOD;
        }
        System.out.println(answer);
    }
}