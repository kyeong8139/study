import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        ArrayList<Integer> names = new ArrayList<>();
        int num = 0;
        while (names.size() < n) {
            int cur = num;
            int maxStreak = 0;
            int streak = 0;
            while (cur > 0) {
                if (cur % 10 == 6) {
                    maxStreak = Math.max(maxStreak, ++streak);
                } else {
                    streak = 0;
                }
                cur /= 10;
            }

            if (maxStreak >= 3) {
                names.add(num);
            }
            num++;
        }

        names.sort(Comparator.naturalOrder());
        System.out.print(names.get(n-1));
    }
}