import java.io.*;
import java.util.*;

public class Main {

    static class Matrix {
        long row;
        long col;

        public Matrix (long row, long col) {
            this.row = row;
            this.col = col;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        
        Stack<Matrix> stack = new Stack<>();
        for (int i = 0; i < n; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            stack.add(new Matrix(r, c));
        }

        Matrix cur = stack.pop();
        long answer = 0L;
        while (!stack.isEmpty()) {
            Matrix prev = stack.pop();
            answer += prev.row * prev.col * cur.col;
            cur.row = prev.col;
        }

        System.out.print(answer);
    }
}