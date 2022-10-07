import java.io.*;
import java.util.*;

public class Pageant2 {


    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new FileReader("cowqueue.in"));
//        PrintWriter pw = new PrintWriter(new FileWriter("cowqueue.out"));
        int[] dr = {1, 0, -1, 0};
        int[] dc = {0, 1, 0, -1};
        Scanner br = new Scanner(System.in);
        StringTokenizer st = new StringTokenizer(br.nextLine());
        int num_rows = Integer.parseInt(st.nextToken());
        int num_cols = Integer.parseInt(st.nextToken());
        int[][] grid = new int[num_rows][num_cols];
        boolean[][] visited = new boolean[num_rows][num_cols];
        int startX = 0;
        int startY = 0;
        for (int i=0; i < num_rows; i++){
            StringTokenizer st1 = new StringTokenizer(br.nextLine());
            String cur = st1.nextToken();
            for (int j=0; j < num_cols; j++){
                char temp = cur.charAt(j);
                if (temp == 'X'){
                    startX = i;
                    startY = j;
                    grid[i][j] = 2;
                }
            }
        }
        Queue<Integer> x = new LinkedList<>();
        Queue<Integer> y = new LinkedList<>();
        x.add(startX);
        y.add(startY);
        visited[startX][startY] = true;
        grid[startX][startY] = 1;
        List<Integer> first_x = new ArrayList<>();
        List<Integer> first_y = new ArrayList<>();
        List<Integer> second_x = new ArrayList<>();
        List<Integer> second_y = new ArrayList<>();
        first_x.add(startX);
        first_y.add(startY);
        while (!x.isEmpty()){
            int temp = x.size();
            for (int i=0; i < temp; i++){
                int curX = x.remove();
                int curY = y.remove();
                for (int j=0; j < 4; j++){
                    int row = curX + dr[j];
                    int col = curY + dc[j];
                    if (row >= 0 && row < num_rows && col >= 0 && col < num_cols && !visited[row][col] && grid[row][col] == 2){
                        x.add(row);
                        y.add(col);
                        visited[row][col] = true;
                        grid[row][col] = 1;
                        first_x.add(row);
                        first_y.add(col);
                    }
                }
            }
        }
//        for (int i=0; i < num_rows; i++){
//            for (int j=0; j < num_cols; j++){
//                pw.print(grid[i][j]);
//            }
//            pw.println();
//        }
        for (int i=0; i < num_rows; i++){
            for (int j = 0; j < num_cols; j++){
                if (grid[i][j] == 2){
                    second_x.add(i);
                    second_y.add(j);
                }
            }
        }
        int ans = 999999;
        for (int i=0; i < first_x.size(); i++){
            for (int j=0; j < second_x.size(); j++){
                int dist = Math.abs(second_x.get(j) - first_x.get(i)) + Math.abs(second_y.get(j) - first_y.get(i));
                if (dist < ans){
                    ans = dist;
                }
            }
        }
        System.out.println(ans-1);
    }
}
