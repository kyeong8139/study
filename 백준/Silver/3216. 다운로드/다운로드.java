import java.io.*;
import java.util.*;

public class Main {

    static class Music {
        int length;
        int downloadTime;

        public Music (int length, int downloadTime) {
            this.length = length;
            this.downloadTime = downloadTime;
        }
    }

    static Music[] musics;
    static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        musics = new Music[n];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int length = Integer.parseInt(st.nextToken());
            int time = Integer.parseInt(st.nextToken());
            musics[i] = new Music(length, time);
        }

        for (int i = 1; i < n; i++) {
            musics[i].downloadTime += musics[i-1].downloadTime;
        }

        binarySearch(musics[0].downloadTime, musics[n-1].downloadTime);
        System.out.print(answer);
    }

    public static void binarySearch(int min, int max) {
        if (min > max) return;

        int mid = (min + max) / 2;

        int time = mid;
        int downloadIdx = 0;
        int playIdx = 0;
        boolean isOk = true;
        while (downloadIdx < musics.length) {
            if (time >= musics[downloadIdx].downloadTime) {
                downloadIdx++;
            } else {
                if (playIdx >= downloadIdx) {
                    isOk = false;
                    break;
                }

                time += musics[playIdx].length;
                playIdx++;
            }
        }

        if (isOk) {
            answer = Math.min(answer, mid);
            binarySearch(min, mid - 1);
        } else {
            binarySearch(mid + 1, max);
        }
    }
}