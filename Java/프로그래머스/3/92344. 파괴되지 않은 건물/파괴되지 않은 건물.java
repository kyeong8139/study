import java.util.*;

class Solution {
    
    public int solution(int[][] board, int[][] skills) {
        int row = board.length;
        int col = board[0].length;
        int[][] deltas = new int[row + 1][col + 1];
        
        for (int[] skill : skills) {
            int startRow = skill[1];
            int endRow = skill[3];
            int startCol = skill[2];
            int endCol = skill[4];
            int value = skill[5] * ((skill[0] == 1) ? -1 : 1);
            
            deltas[startRow][startCol] += value;
            deltas[startRow][endCol + 1] -= value;
            deltas[endRow + 1][startCol] -= value;
            deltas[endRow + 1][endCol + 1] += value;
        }
        
        for (int r = 0; r < row; r++) {
            for (int c = 1; c < col; c++) {
                deltas[r][c] += deltas[r][c-1];   
            } 
        }
        
        for (int r = 1; r < row; r++) {
            for (int c = 0; c < col; c++) {
                deltas[r][c] += deltas[r-1][c];   
            } 
        }
        
        int cnt = 0;
        for (int r = 0; r < row; r++) {
            for (int c = 0; c < col; c++) {
                int result = deltas[r][c] + board[r][c];
                if (result > 0) cnt++;
            } 
        }
        
        return cnt;
    }
}