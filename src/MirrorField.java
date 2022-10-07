import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class MirrorField {

    static int ans = 0;
    static boolean done = false;

    public static void sim (int row, int col, char dir, char[][] grid, int counter, int num_rows, int num_cols){
        if (row < 0 || row >= num_rows || col < 0 || col >= num_cols){
            if (counter > ans){
                ans = counter;
                return;
            }
        }
        if (counter >= num_rows * num_cols){
            System.out.println("-1");
            done = true;
            return;
        }
        if (grid[row][col] == '/'){
            if (dir == 'E'){
                sim(row+1, col-1, 'N', grid, counter + 1, num_rows, num_cols);
            }
            if (dir == 'N'){
                sim(row+1, col-1, 'E', grid, counter + 1, num_rows, num_cols);
            }
            if (dir == 'W'){
                sim(row-1, col+1, 'S', grid, counter + 1, num_rows, num_cols);
            }
            if (dir == 'S'){
                sim(row-1, col+1, 'W', grid, counter + 1, num_rows, num_cols);
            }
        }
        else {
            if (dir == 'E'){
                sim(row-1, col-1, 'N', grid, counter + 1, num_rows, num_cols);
            }
            if (dir == 'N'){
                sim(row-1, col-1, 'E', grid, counter + 1, num_rows, num_cols);
            }
            if (dir == 'W'){
                sim(row+1, col+1, 'S', grid, counter + 1, num_rows, num_cols);
            }
            if (dir == 'S'){
                sim(row+1, col+1, 'W', grid, counter + 1, num_rows, num_cols);
            }
        }
    }

    public static void main(String[] args) throws IOException {

//        BufferedReader br = new BufferedReader(new FileReader("citystate.in"));
//        PrintWriter pw = new PrintWriter(new FileWriter("citystate.out"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int num_rows = Integer.parseInt(st.nextToken());
        int num_cols = Integer.parseInt(st.nextToken());
        char[][] grid = new char[num_rows][num_cols];
        for (int i=0; i < num_rows; i++){
            StringTokenizer st1 = new StringTokenizer(br.readLine());
            String temp = st1.nextToken();
            for (int j=0; j < num_cols; j++){
                grid[i][j] = temp.charAt(j);
            }
        }
        for (int i=0; i < num_cols; i++){
            sim (0, i, 'N', grid, 0, num_rows, num_cols);
            sim (num_rows - 1, i, 'S', grid, 0, num_rows, num_cols);
        }
        for (int i=0; i < num_rows; i++){
            sim (i, 0, 'W', grid, 0, num_rows, num_cols);
            sim (i, num_cols - 1, 'E', grid, 0, num_rows, num_cols);
        }
        if (!done){
            System.out.println(ans);
        }
    }

}
