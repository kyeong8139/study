import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int length = Integer.parseInt(st.nextToken());
        int target = Integer.parseInt(st.nextToken());
        int[] nums = new int[length];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < length; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        } 

        int left = 0;
        int right = 0;
        int sum = nums[left];
        int cnt = 0;
        while (right < length) {
            if (sum == target) {
                cnt++;
            }

            if (sum >= target) {
                sum -= nums[left++];
            } else {
                if (++right < length) {
                    sum += nums[right];
                }
            }
        }

        while (left < length) {
            if (sum < target) {
                break;
            } else if (sum == target) {
                cnt++;
            } else {
                sum -= nums[left++];
            }
        }

        System.out.print(cnt);
    }
}   