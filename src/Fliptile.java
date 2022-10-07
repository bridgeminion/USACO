import java.io.*;
import java.util.StringTokenizer;

public class Fliptile {

    static int dr[] = {-1, 1, 0, 0, 0};
    static int dc[] = {0, 0, -1, 1, 0};
    static int[][] ans = new int[15][15];
    static int min_flips = 225;
    static boolean works = false;

    public static void toggle (int row, int col, boolean[][] grid, int num_rows, int num_cols){
        for (int i=0; i < 5 ; i++){
            int nr = row + dr[i];
            int nc = col + dc[i];
            if (nr >= 0 && nr < num_rows && nc >= 0 && nc < num_cols){
                grid[nr][nc] = !grid[nr][nc];
            }
        }
    }

    public static void generate1strow (boolean[][] grid, int num_rows, int num_cols, int[][] cur, int num_flips){
        for (int i=0; i < 1 << num_cols; i++){
            for (int j=0; j < num_cols; j++){
                if (((i >> j) & 1) == 1){
//                    System.out.print(1 + " ");
                    cur[0][j]++;
                    num_flips++;
                    toggle(0, j, grid, num_rows, num_cols);
                }
                else{
//                    System.out.print(0 + " ");
                }
            }
            restOfGrid(grid, num_rows, num_cols, cur, num_flips);
            for (int j=0; j < num_cols; j++) {
                if (((i >> j) & 1) == 1) {
                    toggle(0, j, grid, num_rows, num_cols);
                    cur[0][j]--;
                    num_flips--;
                }
            }
//            System.out.println();
        }
//        System.out.println();
    }

    public static void restOfGrid (boolean[][] grid1, int num_rows, int num_cols, int[][] cur1, int num_flips){
        boolean[][] grid = new boolean[num_rows][num_cols];
        for (int i=0; i < num_rows; i++){
            if (num_cols >= 0) System.arraycopy(grid1[i], 0, grid[i], 0, num_cols);
        }
        int[][] cur = new int[num_rows][num_cols];
        for (int i=0; i < num_rows; i++){
            if (num_cols >= 0) System.arraycopy(cur1[i], 0, cur[i], 0, num_cols);
        }
        for (int i=1; i < num_rows; i++){
            for (int j=0; j < num_cols; j++){
                if (grid[i-1][j]){
                    toggle(i, j, grid, num_rows, num_cols);
                    cur[i][j]++;
                    num_flips++;
                }
            }
        }
        for (int i=0; i < num_rows; i++){
            for (int j=0; j < num_cols; j++){
                if (grid[i][j]){
                    return;
                }
            }
        }
        works = true;
//        System.out.println("Num flips: " + num_flips);
        if (num_flips > min_flips){
            return;
        }
        if (num_flips < min_flips){
            min_flips = num_flips;
            for (int i=0; i < num_rows; i++){
                System.arraycopy(cur[i], 0, ans[i], 0, num_cols);
            }
            return;
        }
        boolean done = false;
        for (int i=0; i < num_rows; i++){
            if (done){
                break;
            }
            for (int j=0; j < num_cols; j++){
                if (ans[i][j] < cur[i][j]){
                    return;
                }
                if (cur[i][j] < ans[i][j]){
                    done = true;
                    break;
                }
            }
        }
        for (int i=0; i < num_rows; i++){
            System.arraycopy(cur[i], 0, ans[i], 0, num_cols);
        }
    }

    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new FileReader("herding.in"));
//        PrintWriter pw = new PrintWriter(new FileWriter("herding.out"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int num_rows = Integer.parseInt(st.nextToken());
        int num_cols = Integer.parseInt(st.nextToken());
        boolean[][] grid = new boolean[num_rows][num_cols];
        for (int i=0; i < num_rows; i++){
            StringTokenizer st1 = new StringTokenizer(br.readLine());
            for (int j=0; j < num_cols; j++){
                grid[i][j] = Integer.parseInt(st1.nextToken()) == 1;
            }
        }
        for (int i=0; i < num_rows; i++){
            for (int j=0; j < num_cols; j++){
                ans[i][j] = 2;
            }
        }
        generate1strow(grid, num_rows, num_cols, new int[num_rows][num_cols], 0);
        if (works){
            for (int i=0; i < num_rows; i++){
                for (int j=0; j < num_cols; j++){
                    System.out.print(ans[i][j]);
                    System.out.print(" ");
                }
                System.out.println();
            }
        }
        else{
            System.out.println("IMPOSSIBLE");
        }
//        System.out.println(min_flips);
        pw.close();
    }
}
