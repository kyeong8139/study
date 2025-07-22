import java.io.*;
import java.util.*;

public class Main {

    static long n, p, q, x, y;
    static Map<Long, Long> nums = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Long.parseLong(st.nextToken());
        p = Long.parseLong(st.nextToken());
        q = Long.parseLong(st.nextToken());
        x = Long.parseLong(st.nextToken());
        y = Long.parseLong(st.nextToken());

        System.out.println(getNum(n));
    }

    public static long getNum(long idx) {
        if (idx <= 0) {
            return 1;
        } else if (nums.containsKey(idx)) {
            return nums.get(idx);
        }

        long left = idx/p - x;
        long right = idx/q - y;
        long result = getNum(left) + getNum(right);
        nums.put(idx, result);

        return result;
    }
}