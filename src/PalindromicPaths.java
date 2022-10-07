import java.io.*;
import java.util.*;

public class PalindromicPaths {

    static Set<String> set1 = new HashSet<>();
    static Set<String> ans = new HashSet<>();

    public static void dfs1(int row, int col, String current, char[][] grid, int n){
        System.out.println("row:" + row + ", col:" + col);
        if (row + col == n-1) {
            current += grid[row][col];
            set1.add(current + row);
            return;
        }
        dfs1(row+1, col, current + Character.toString(grid[row][col]), grid, n);
        dfs1(row, col+1, current + Character.toString(grid[row][col]), grid, n);
    }

    public static void dfs2(int row, int col, String current, char[][] grid, int n){
        System.out.println("row1:" + row + ", col1:" + col);
        if (row + col == n-1){
            current += grid[row][col];
            if (set1.contains(current + row)){
                ans.add(current);
            }
            return;
        }
        dfs2(row-1, col, current + Character.toString(grid[row][col]), grid, n);
        dfs2(row, col-1, current + Character.toString(grid[row][col]), grid, n);
    }

    public static void main(String[] args) throws IOException {

//        BufferedReader br = new BufferedReader(new FileReader("measurement.in"));
//        PrintWriter pw = new PrintWriter(new FileWriter("measurement.out"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        char[][] grid = new char[n][n];
        for (int i=0; i < n; i++){
            StringTokenizer st1 = new StringTokenizer(br.readLine());
            String temp = st1.nextToken();
            for (int j=0; j < n; j++){
                grid[i][j] = temp.charAt(j);
            }
        }
        dfs1(0, 0, "", grid, n);
        dfs2(n-1, n-1, "", grid, n);
        System.out.println(ans.size());
        pw.close();
    }
}
