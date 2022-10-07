import java.io.*;
import java.util.*;


public class Munching {

    static int[] dr = {0, 1, 0, -1};
    static int[] dc = {1, 0, -1, 0};

    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new FileReader("cowqueue.in"));
//        PrintWriter pw = new PrintWriter(new FileWriter("cowqueue.out"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int num_rows = Integer.parseInt(st.nextToken());
        int num_cols = Integer.parseInt(st.nextToken());
        char[][] grid = new char[num_rows][num_cols];
        boolean[][] visited = new boolean[num_rows][num_cols];
        int barn_x = 0;
        int barn_y = 0;
        int start_x = 0;
        int start_y = 0;
        for (int i=0; i < num_rows; i++){
            StringTokenizer st1 = new StringTokenizer(br.readLine());
            String temp = st1.nextToken();
            for (int j=0; j < num_cols; j++){
                grid[i][j] = temp.charAt(j);
                if (grid[i][j] == 'B'){
                    barn_x = i;
                    barn_y = j;
                }
                if (grid[i][j] == 'C'){
                    start_x = i;
                    start_y = j;
                }
            }
        }
        Queue<Integer> x = new LinkedList<>();
        Queue<Integer> y = new LinkedList<>();
        x.add(start_x);
        y.add(start_y);
        visited[start_x][start_y] = true;
        int ans = 0;
        while(!x.isEmpty() && !y.isEmpty()){
            int temp = x.size();
            for (int l=0; l < temp; l++){
                int cur_x = x.remove();
                int cur_y = y.remove();
                for (int k=0; k < 4; k++){
                    int row = cur_x + dr[k];
                    int col = cur_y + dc[k];
                    if (row >= 0 && row < num_rows && col >=0 && col < num_cols && grid[row][col] == 'B'){
                        ans++;
                        System.out.println(ans);
                        return;
                    }
                    if (row >= 0 && row < num_rows && col >=0 && col < num_cols && !visited[row][col] && grid[row][col] != '*'){
                        x.add(row);
                        y.add(col);
                        visited[row][col] = true;
                    }
                }
            }
            ans++;
        }
        System.out.println(ans);

    }
}
