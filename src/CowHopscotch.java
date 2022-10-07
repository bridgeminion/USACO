import java.io.*;
import java.util.StringTokenizer;

public class CowHopscotch {


    public static long dp (long[][] grid, boolean[][] visited, long[][] val, int cur_row, int cur_col, int num_rows, int num_cols, int mod){
        if (cur_row == num_rows-1 && cur_col == num_cols-1){
            return 1;
        }
        if (cur_row == num_rows-1){
            return 0;
        }
        if (visited[cur_row][cur_col]){
            return val[cur_row][cur_col];
        }
        visited[cur_row][cur_col] = true;
        for (int i=cur_row+1; i < num_rows; i++){
            for (int j=cur_col+1; j < num_cols; j++){
                if (grid[cur_row][cur_col] != grid[i][j]){
                    val[cur_row][cur_col] += dp (grid, visited, val, i, j, num_rows, num_cols, mod);
                    val[cur_row][cur_col] = val[cur_row][cur_col] % mod;
                }
            }
        }
        return val[cur_row][cur_col];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("hopscotch.in"));
        PrintWriter pw = new PrintWriter(new FileWriter("hopscotch.out"));
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        long[][] grid = new long[r][c];
        for (int i=0; i < r; i++){
            StringTokenizer st1 = new StringTokenizer(br.readLine());
            for (int j=0; j < c; j++){
                grid[i][j] = Long.parseLong(st1.nextToken());
            }
        }
        boolean[][] visited = new boolean[r][c];
        long[][] val = new long[r][c];
        dp (grid, visited, val, 0, 0, r, c, 1000000007);
        pw.println(val[0][0]);
        pw.close();
    }
}
