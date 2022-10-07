import java.io.*;
import java.util.StringTokenizer;

public class NQueen {

    static int ans = 0;

    public static boolean open(boolean[][] grid, int row, int col, int n){
        for (int i=0; i < n; i++){
            if (grid[row][i]){
                return false;
            }
        }
        for (int i=0; i < n; i++){
            if (grid[i][col]){
                return false;
            }
        }
        int c = col - row;
        for (int i=0; i < n; i++){
            if (i+c >= 0 && i+c < n && grid[i][i+c]){
                return false;
            }
        }
        int sum = row+col;
        for (int i=0; i < n; i++){
            int other = sum-i;
            if (other >= 0 && other < n && grid[i][other]){
                return false;
            }
        }
        return true;
    }

    public static void recurse(boolean[][] grid, int num, boolean[][] visited, int col, int n){
        if (num == n){
            ans++;
            System.out.println("answer:" + ans);
            return;
        }
        for (int i=0; i < n; i++){
            if (open(grid, i, col, n)){
                grid[i][col] = true;
                System.out.println("i=" + i + " j=" + col);
                recurse(grid, num+1, visited, col+1, n);
                grid[i][col] = false;
            }
        }
    }

    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new FileReader("moobuzz.in"));
//        PrintWriter pw = new PrintWriter(new FileWriter("moobuzz.out"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        boolean[][] grid = new boolean[n][n];
        boolean[][] visited = new boolean[n][n];
        recurse(grid, 0, visited, 0, n);
        System.out.println(ans);
    }
}
