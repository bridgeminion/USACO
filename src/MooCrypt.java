import java.io.*;
import java.util.*;

public class MooCrypt {
    int[] dr = {-1, 0, 1, 0, 1, -1, 1, -1};
    int[] dc = {0, 1, 0, -1, 1, 1, -1, -1};
    boolean[][] visited;
    void recurse (int row, int col, String current, int max_rows, int max_cols, char grid[][], Map<String, Integer> segment, boolean[][] visited, Integer index){
        if (current.length() == 3){
            if (current.charAt(2) == current.charAt(1) && current.charAt(0) != current.charAt(1)){
                segment.putIfAbsent(current, 0);
                segment.put(current, segment.get(current) + 1);
            }
            return;
        }
        visited[row][col] = true;
        if (current.length() == 2){
            int r = row + dr[index];
            int c = col + dc[index];
            if (r < 0 || r >= max_rows || c < 0 || c >= max_cols || visited[r][c]) {
                visited[row][col] = false;
                return;
            }
            recurse(r, c, current + grid[r][c], max_rows, max_cols, grid, segment, visited, index);
        }
        else {
            for (int dir = 0; dir < 8; ++dir) {
                int r = row + dr[dir];
                int c = col + dc[dir];
                if (r < 0 || r >= max_rows || c < 0 || c >= max_cols || visited[r][c]) {
                    continue;
                }
                recurse(r, c, current + grid[r][c], max_rows, max_cols, grid, segment, visited, dir);
            }
        }
        visited[row][col] = false;
    }

    public static void main(String[] args) throws IOException {
        MooCrypt instance = new MooCrypt();
//        BufferedReader br = new BufferedReader(new FileReader("moocrypt.in"));
//        PrintWriter pw = new PrintWriter(new FileWriter("moocrypt.out"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        int row, col;
        StringTokenizer st = new StringTokenizer(br.readLine());
        row = Integer.parseInt(st.nextToken());
        col = Integer.parseInt(st.nextToken());
        char[][] grid = new char[row][col];
        boolean[][] visited = new boolean[row][col];
        Map<String, Integer> segment = new HashMap<>();
        for (int i=0; i < row; i++){
            StringTokenizer st1 = new StringTokenizer(br.readLine());
            String temp = st1.nextToken();
            for (int j=0; j < col; j++){
                grid[i][j] = temp.charAt(j);
            }
        }
        for (int i=0; i < row; i++){
            for (int j=0; j < col; j++){
                instance.recurse(i, j, "" + grid[i][j], row, col, grid, segment, visited, -1);
            }
        }
        int ans = 0;
        for (Map.Entry<String, Integer> entry: segment.entrySet()) {
            if (entry.getValue() > ans){
                if (!(entry.getKey().charAt(0) == 'M' && entry.getKey().charAt(1) != 'O')){
                    if (!(entry.getKey().charAt(0) != 'M' && entry.getKey().charAt(1) == 'O')){
                        ans = entry.getValue();
                    }
                }
            }
        }
        pw.println(ans);
        pw.close();
    }
}
