import java.util.*;
import java.io.*;

public class Main {

    static int[] values; 
    static int length = 0;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[] arr = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        values = new int[n];
        Arrays.fill(values, Integer.MAX_VALUE);
        for (int i = 0; i < n; i++) {
            int idx = binarySearch(0, length, arr[i]);
            values[idx] = arr[i];
            if (idx == length) length++;
        }

        System.out.print(length);
    }

    public static int binarySearch(int start, int end, int target) {
        int low = start;
        int high = end;

        while (low < high) {
            int mid = (low + high) / 2;

            if (values[mid] < target) {
                low = mid+1;
            } else {
                high = mid;
            }
        }

        return low;
    }
}   