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
        int pos;
        Node curNode;
        int visited;
        Word prevWord;

        public Word(int pos, int visited, Word prev, Node cur) {
            this.pos = pos;
            this.visited = visited;
            this.prevWord = prev;
            this.curNode = cur;
        }
    }

    static int[] rightDirs = {1, -3, 5};
    static int[] leftDirs = {-1, 3, -5};
    static int[] colDirs = {4, -4};
    static char[] board = new char[16];

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
            int idx = 0;
            for (int i = 0; i < 4; i++) {
                char[] input = br.readLine().toCharArray();
                for (char c : input) {
                    board[idx++] = c;
                }
            }

            Queue<Word> queue = new ArrayDeque<>();
            for (int pos = 0; pos < board.length; pos++) {
                idx = board[pos] - 'A';
                Node node = head.nextNodes[idx];
                if (node == null) continue;

                Word word = new Word(pos, 1 << pos, null, node);
                queue.add(word);
            }

            Set<String> set = new HashSet<>();
            while (!queue.isEmpty()) {
                Word word = queue.poll();
                if (word.curNode.isEnd) {
                    StringBuilder sb = new StringBuilder();
                    Word curWord = word;
                    while (true) {
                        char alphabet = board[curWord.pos];
                        sb.append(alphabet);

                        if (curWord.prevWord == null) break;
                        curWord = curWord.prevWord;
                    }
                    set.add(sb.reverse().toString());
                }

                if (Integer.bitCount(word.visited) == 8) continue;

                for (int dir : colDirs) {
                    Word nextWord = getNextWord(word, dir);
                        if (nextWord != null) {
                            queue.add(nextWord);
                        }
                }

                if (word.pos % 4 != 3) {
                    for (int dir : rightDirs) {
                        Word nextWord = getNextWord(word, dir);
                        if (nextWord != null) {
                            queue.add(nextWord);
                        }
                    }
                }

                if (word.pos % 4 != 0) {
                    for (int dir : leftDirs) {
                        Word nextWord = getNextWord(word, dir);
                        if (nextWord != null) {
                            queue.add(nextWord);
                        }
                    }
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

    public static Word getNextWord(Word word, int dir) {
        int nextPos = word.pos + dir;
        if (nextPos < 0 || nextPos >= 16 || ((word.visited & (1 << nextPos)) != 0)) return null;
                        
        int nextIdx = board[nextPos] - 'A';
        Node nextNode = word.curNode.nextNodes[nextIdx];
        if (nextNode == null) return null;
                        
        int nextVisited = word.visited | (1 << nextPos);
        return new Word(nextPos, nextVisited, word, nextNode);
    }
}