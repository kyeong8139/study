import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int money = 100 * Integer.parseInt(st.nextToken());
        int price = Integer.parseInt(st.nextToken());

        String answer;
        if (money < price) {
            answer = "No";
        } else {
            answer = "Yes";
        }
        System.out.println(answer);
    }
}