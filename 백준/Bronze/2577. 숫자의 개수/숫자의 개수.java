import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int num = 1;
        for (int i = 0; i < 3; i++) {
            num *= Integer.parseInt(br.readLine());
        }

        char[] value = String.valueOf(num).toCharArray();
        int[] cnt = new int[10];
        for (char cur : value) {
            int idx = cur - '0';
            cnt[idx]++;
        }

        for (int i = 0; i <= 9; i++) {
            System.out.println(cnt[i]);
        }
    }
}