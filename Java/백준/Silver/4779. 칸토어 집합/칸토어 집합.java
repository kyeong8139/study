import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String result = getResult(12);
        
        String input;
        StringBuilder sb = new StringBuilder();
        while (true) {
            input = br.readLine();
            if (input == null) break;

            int endIdx = (int) Math.pow(3, Integer.parseInt(input));
            sb.append(result.substring(0, endIdx)).append("\n");
        }

        System.out.print(sb);
    }

    public static String getResult(int cnt) {
        if (cnt == 0) {
            return "-";
        }

        String token = getResult(cnt-1);
        String space = " ".repeat(token.length());
        return token + space + token;
    }
}