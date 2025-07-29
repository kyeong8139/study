import java.io.*;
import java.util.*;

public class Main {

    public static int[] scores;
    static {
        scores = new int[9];
        scores[3] = 1;
        scores[4] = 1;
        scores[5] = 2;
        scores[6] = 3;
        scores[7] = 5;
        scores[8] = 11;
    }

    static class Node {
        boolean isEnd = false;
        Node[] nextNodes;

        public Node() {
            nextNodes = new Node[26];
        }
    }

    static class Word {
        int prevPos;
        int pos;
        int visited;
        Word prevWord;
        Node curNode;

        public Word(int startPos, int pos, int visited, Word prev, Node cur) {
            this.prevPos = startPos;
            this.pos = pos;
            this.visited = visited;
            this.prevWord = prev;
            this.curNode = cur;
        }
    }

    static int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}, {1, 1}, {1, -1}, {-1, 1}, {-1, -1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        Node head = new Node();
        int n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            String word = br.readLine();
            Node cur = head;
            for (char c : word.toCharArray()) {
                int idx = c - 'A';
                if (cur.nextNodes[idx] == null) {
                    cur.nextNodes[idx] = new Node();
                }
                cur = cur.nextNodes[idx];
            }
            cur.isEnd = true;
        }

        br.readLine();
        int m = Integer.parseInt(br.readLine());
        for (int round = 0; round < m; round++) {
            char[][] board = new char[4][4];
            for (int row = 0; row < board.length; row++) {
                board[row] = br.readLine().toCharArray();
            }

            Queue<Word> queue = new ArrayDeque<>();
            for (int row = 0; row < board.length; row++) {
                for (int col = 0; col < board[row].length; col++) {
                    int idx = board[row][col] - 'A';
                    Node cur = head.nextNodes[idx];
                    if (cur == null) continue;

                    int pos = getPos(row, col);
                    Word word = new Word(pos, pos, 1 << pos, null, cur);
                    queue.add(word);
                }
            }

            Set<String> set = new HashSet<>();
            while (!queue.isEmpty()) {
                Word word = queue.poll();
                if (word.curNode.isEnd) {
                    StringBuilder sb = new StringBuilder();
                    Word curWord = word;
                    int curRow;
                    int curCol;
                    while (true) {
                        int curPos = curWord.pos;
                        curRow = curPos / 4;
                        curCol = curPos % 4;
                        sb.append(board[curRow][curCol]);

                        if (curWord.prevWord == null) break;
                        curWord = curWord.prevWord;
                    }
                    set.add(sb.reverse().toString());
                }

                if (Integer.bitCount(word.visited) == 8) continue;

                for (int[] dir : dirs) {
                    int nextRow = word.pos / 4 + dir[0];
                    int nextCol = word.pos % 4 + dir[1];
                    int nextPos = getPos(nextRow, nextCol);
                    if (nextRow < 0 || nextCol < 0 || nextRow >= board.length || nextCol >= board[nextRow].length || (word.visited & (1 << nextPos)) != 0) continue;
                    
                    int nextIdx = board[nextRow][nextCol] - 'A';
                    Node nextNode = word.curNode.nextNodes[nextIdx];
                    if (nextNode == null) continue;
                    int nextVisited = word.visited | (1 << nextPos);
                    queue.add(new Word(word.pos, nextPos, nextVisited, word, nextNode));
                }
            }

            String max = null;
            int score = 0;
            for (String str : set){
                score += scores[str.length()];
                if (max == null || (str.length() > max.length()) || (str.length() == max.length() && max.compareTo(str) > 0)) {
                    max = str;
                } 
            }
            bw.write(score + " " + max + " " + set.size());
            bw.newLine();
            br.readLine();
        }
        bw.flush();
        bw.close();
    }

    public static int getPos(int row, int col) {
        return (row * 4) + col;
    }
}