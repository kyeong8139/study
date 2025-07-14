import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        long sum = 0;
        for (int cnt = 0; cnt < 20; cnt++) {
            int bitOne = 0;
            int bitZero = 0;
            for (int i = 0; i < n; i++) {
                if (arr[i] % 2 == 0) {
                    bitZero++;
                } else {
                    bitOne++;
                }
                arr[i] /= 2;
            }

            sum += (long) Math.pow(2, cnt) * bitOne * bitZero;
        }

        System.out.println(sum);
    }
}   