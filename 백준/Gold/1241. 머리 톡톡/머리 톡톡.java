import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] students = new int[n];
        for (int i = 0; i < n; i++) {
            students[i] = Integer.parseInt(br.readLine());
        }

        Map<Integer, Integer> cnts = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int key = students[i];
            cnts.put(key, cnts.getOrDefault(key, 0) + 1);
        }

        Map<Integer, Integer> answers = new HashMap<>();
        for (int key : cnts.keySet()) {
            int answer = 0;
            if (key == 1) {
                answer = cnts.get(key) - 1;
                answers.put(key, answer);
                continue;
            }

            int div = 1;
            while (div <= Math.sqrt(key)) {
                if (key % div == 0) {
                    answer += cnts.getOrDefault(div, 0);
                    if (key/div != div) {
                        answer += cnts.getOrDefault(key/div, 0);
                    }
                }
                div++;
            }
            answers.put(key, answer-1);
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            int answer = answers.get(students[i]);
            sb.append(answer).append("\n");
        }
        System.out.print(sb);
    }
}