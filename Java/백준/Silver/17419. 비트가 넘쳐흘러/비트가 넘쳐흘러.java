import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        String k = br.readLine();

        int answer = 0;
        for (int i = 0; i < k.length(); i++) {
            if (k.charAt(i) == '1') {
                answer++;
            }
        }

        System.out.println(answer);
    }
}