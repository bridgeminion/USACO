import java.io.*;
import java.util.StringTokenizer;

public class Pie1 {

    static boolean[][] visited = new boolean[101][101];
    static int[][] val = new int[101][101];

    public static int dp (int[][] grid, int cur_row, int cur_col, int num_rows, int num_cols){
        if (cur_row < 0 || cur_row >= num_rows || cur_col < 0 || cur_col >= num_cols){
            return Integer.MIN_VALUE;
        }
        if (cur_col == 0){
            if (cur_row == 0){
                return grid[0][0];
            }
            else {
                return Integer.MIN_VALUE;
            }
        }
        if (visited[cur_row][cur_col]){
            return val[cur_row][cur_col];
        }
        visited[cur_row][cur_col] = true;
        val[cur_row][cur_col] = grid[cur_row][cur_col] + Math.max(dp (grid, cur_row-1, cur_col-1, num_rows, num_cols), Math.max(dp (grid, cur_row, cur_col-1, num_rows, num_cols), dp (grid, cur_row+1, cur_col-1, num_rows, num_cols)));
        return val[cur_row][cur_col];
    }


    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new FileReader("moobuzz.in"));
//        PrintWriter pw = new PrintWriter(new FileWriter("moobuzz.out"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int[][] grid = new int[r][c];
        for (int i=0; i < r; i++){
            StringTokenizer st1 = new StringTokenizer(br.readLine());
            for (int j=0; j < c; j++){
                grid[i][j] = Integer.parseInt(st1.nextToken());
            }
        }
        System.out.println(dp(grid, r-1, c-1, r, c));
    }
}
