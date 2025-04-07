import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();

        int answer = 0;
        for (int i = 0; i < 5; i++) {
            int num = input.charAt(i) - '0';
            answer += (int) Math.pow(num, 5);
        }
        System.out.print(answer);
    }
}