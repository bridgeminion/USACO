import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class EightQueen {

    static int ans = 0;

    public static boolean open(boolean[][] grid, int row, int col){
        for (int i=0; i < 8; i++){
            if (grid[row][i]){
                return false;
            }
        }
        for (int i=0; i < 8; i++){
            if (grid[i][col]){
                return false;
            }
        }
        int c = col - row;
        for (int i=0; i < 8; i++){
            if (i+c >= 0 && i+c < 8 && grid[i][i+c]){
                return false;
            }
        }
        int sum = row+col;
        for (int i=0; i < 8; i++){
            int other = sum-i;
            if (other >= 0 && other < 8 && grid[i][other]){
                return false;
            }
        }
        return true;
    }

    public static void recurse(boolean[][] grid, int num, boolean[][] visited, int col){
        if (num == 8){
            ans++;
            System.out.println("answer:" + ans);
            return;
        }
        for (int i=0; i < 8; i++){
            if (open(grid, i, col)){
                grid[i][col] = true;
                System.out.println("i=" + i + " j=" + col);
                recurse(grid, num+1, visited, col+1);
                grid[i][col] = false;
            }
        }
    }
//    public static void recurse(boolean[][] grid, int num, boolean[][] visited, int row, int col){
//        System.out.println("in recurse with i " + row);
//        if (num == 8){
//            ans++;
//            return;
//        }
//        for (int i=row; i < 8; i++){
//            for (int j=col; j < 8; j++){
//                if (!visited[i][j]){
//                    visited[i][j] = true;
//                    if (open(grid, i, j)){
//                        grid[i][j] = true;
//                        recurse(grid, num+1, visited, (i+1)%8, 0);
//                        System.out.println("back track to i=" + i + ", j="+ j);
//                        if (i==0 && j==1) {
//                            System.out.println("aa");
//                        }
//                        grid[i][j] = false;
//                        visited[i][j] = false;
//                        if (j>0){
//
//                        }
//                        recurse(grid, num, visited, i, (j+1)%8);
//                    }
//                }
//            }
//        }
//    }

    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new FileReader("moobuzz.in"));
//        PrintWriter pw = new PrintWriter(new FileWriter("moobuzz.out"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        boolean[][] grid = new boolean[8][8];
        boolean[][] visited = new boolean[8][8];
        recurse(grid, 0, visited, 0);
        System.out.println(ans);
//        grid[2][0] = true;
//        grid[1][3] = true;
//        grid[4][1] = true;
//        for (int i=0; i < 8; i++){
//            for (int j=0; j < 8; j++){
//                System.out.print("row = " + i + " ");
//                System.out.print("col = " + j + " ");
//                System.out.println(open(grid, i , j));
//            }
//        }
    }
}
