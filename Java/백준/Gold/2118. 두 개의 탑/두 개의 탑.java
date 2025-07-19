import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        // idx-1 ~ idx까지의 거리
        int[] dist = new int[n+1]; 
        for (int i = 1; i < dist.length; i++) {
            dist[i] = Integer.parseInt(br.readLine());
        }

        // 시계 방향의 1 ~ idx까지의 거리
        int[] circleSum = new int[n+1]; 
        for (int i = 1; i < circleSum.length; i++) {
            circleSum[i] = circleSum[i-1] + dist[i];
        }
        
        int total = circleSum[circleSum.length - 1];
        int answer = 0;
        for (int left = 1; left < n; left++) {
            int right = left+1;
            while (right <= n) {    
                int circleDist = circleSum[right] - circleSum[left];
                int antiCircleDist = total - circleDist;
                int curDist = Math.min(circleDist, antiCircleDist);
                answer = Math.max(answer, curDist);
                
                if (circleDist >= antiCircleDist) break;
                right++;
            }

        }

        System.out.print(answer);
    }
}