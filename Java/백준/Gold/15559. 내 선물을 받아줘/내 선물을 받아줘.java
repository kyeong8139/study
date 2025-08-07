import java.io.*;
import java.util.*;

public class Main {

    static Map<Integer, Integer> parents;
    static int width, height;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Map<Character, int[]> dirs = new HashMap<>();
        dirs.put('N', new int[]{-1, 0});
        dirs.put('S', new int[]{1, 0});
        dirs.put('W', new int[]{0, -1});
        dirs.put('E', new int[]{0, 1});

        StringTokenizer st = new StringTokenizer(br.readLine());
        height = Integer.parseInt(st.nextToken());
        width = Integer.parseInt(st.nextToken());
        char[][] board = new char[height][width];

        for (int r = 0; r < height; r++) {
            String input = br.readLine();
            for (int c = 0; c < width; c++) {
                board[r][c] = input.charAt(c);
            }
        }
        
        parents = new HashMap<>();
        for (int i = 0; i < width*height; i++) {
            parents.put(i, i);
        }

        for (int r = 0; r < height; r++) {
            for (int c = 0; c < width; c++) {
                int id = getId(r, c);
                int curParent = getParent(id);
                if (curParent != id) continue;

                Queue<int[]> queue = new ArrayDeque<>();
                queue.offer(new int[]{r, c});

                while(!queue.isEmpty()) {
                    int[] cur = queue.poll();
                    int row = cur[0];
                    int col = cur[1];

                    int[] dir = dirs.get(board[row][col]);
                    int nextRow = row + dir[0];
                    int nextCol = col + dir[1];

                    if (nextRow < 0 || nextRow >= height || nextCol < 0 || nextCol >= width) continue;
                    int nextId = getId(nextRow, nextCol);
                    int nextParent = getParent(nextId);

                    if (curParent == nextParent) continue;

                    parents.put(nextParent, curParent);
                    if (nextParent == nextId) {
                        queue.add(new int[]{nextRow, nextCol});
                    }
                }
            }
        }

        int cnt = 0;
        for (int id : parents.keySet()) {
            if (id == parents.get(id)) cnt++;
        }

        System.out.println(cnt);
    }

    public static int getParent(int id) {
        int parent = parents.get(id);
        if (parent == id) {
            return id;
        }

        int grandParent = getParent(parent);
        parents.put(id, grandParent);
        return grandParent;
    }

    public static int getId(int row, int col) {
        return row * width + col;
    }
}