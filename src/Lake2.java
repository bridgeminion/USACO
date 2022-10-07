import java.io.*;
import java.util.*;

public class Lake2 {

    static int[] dr = {0, 1, 0, -1};
    static int[] dc = {1, 0, -1, 0};

    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new FileReader("cowqueue.in"));
//        PrintWriter pw = new PrintWriter(new FileWriter("cowqueue.out"));
        Scanner in = new Scanner(System.in);
        StringTokenizer st = new StringTokenizer(in.nextLine());
        int num_rows = Integer.parseInt(st.nextToken());
        int num_cols = Integer.parseInt(st.nextToken());
        int num_wet = Integer.parseInt(st.nextToken());
        boolean[][] visited = new boolean[num_rows][num_cols];
        boolean[][] lake = new boolean[num_rows][num_cols];
        for (int i=0; i < num_wet; i++){
            StringTokenizer st1 = new StringTokenizer(in.nextLine());
            int row = Integer.parseInt(st1.nextToken());
            int col = Integer.parseInt(st1.nextToken());
            lake[row-1][col-1] = true;
        }
        int ans = 0;
        Queue<Integer> x = new LinkedList<>();
        Queue<Integer> y = new LinkedList<>();
        for (int i=0; i < num_rows; i++){
            for (int j=0; j < num_cols; j++){
                if (!visited[i][j] && lake[i][j]){
                    x.add(i);
                    y.add(j);
                    visited[i][j] = true;
                    int size = 1;
                    while(!x.isEmpty() && !y.isEmpty()){
                        int temp = x.size();
                        for (int l=0; l < temp; l++){
                            int cur_x = x.remove();
                            int cur_y = y.remove();
                            for (int k=0; k < 4; k++){
                                int row = cur_x + dr[k];
                                int col = cur_y + dc[k];
                                if (row >= 0 && row < num_rows && col >=0 && col < num_cols && !visited[row][col] && lake[row][col]){
                                    x.add(row);
                                    y.add(col);
                                    size++;
                                    visited[row][col] = true;
                                }
                            }
                        }
                    }
                    if (size > ans){
                        ans = size;
                    }
                }
            }
        }
        System.out.println(ans);
    }
}
