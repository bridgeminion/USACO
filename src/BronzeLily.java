import java.io.*;
import java.util.*;

public class BronzeLily {

    public static void main(String[] args) throws IOException {

//        BufferedReader br = new BufferedReader(new FileReader("measurement.in"));
//        PrintWriter pw = new PrintWriter(new FileWriter("measurement.out"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int rows = Integer.parseInt(st.nextToken());
        int cols = Integer.parseInt(st.nextToken());
        int m1 = Integer.parseInt(st.nextToken());
        int m2 = Integer.parseInt(st.nextToken());
        boolean[][] grid = new boolean[rows][cols];
        boolean[][] visited = new boolean[rows][cols];
        int startrow = 0;
        int startcol = 0;
        int endrow = 0;
        int endcol = 0;
        for (int i=0; i < rows; i++){
            StringTokenizer st1 = new StringTokenizer(br.readLine());
            for (int j=0; j < cols; j++){
                int temp = Integer.parseInt(st1.nextToken());
                if (temp == 1){
                    grid[i][j] = true;
                }
                else if (temp == 3){
                    grid[i][j] = true;
                    startrow = i;
                    startcol = j;
                }
                else if (temp == 4){
                    grid[i][j] = true;
                    endrow = i;
                    endcol = j;
                }
            }
        }
        int[] dr = {-m1, -m1, -m2, -m2, m1, m1, m2, m2};
        int[] dc = {-m2, m2, -m1, m1, -m2, m2, -m1, m1};
        Queue<Integer> row = new LinkedList<>();
        Queue<Integer> col = new LinkedList<>();
        row.add(startrow);
        col.add(startcol);
        visited[startrow][startcol] = true;
        int counter = 0;
        while(!row.isEmpty()){
            counter++;
            int size = row.size();
            for (int i=0; i < size; i++){
                int temp1 = row.remove();
                int temp2 = col.remove();
                for (int j=0; j < 8; j++){
                    int nrow = temp1 + dr[j];
                    int ncol = temp2 + dc[j];
                    if (nrow == endrow && ncol == endcol){
                        System.out.println(counter);
                        return;
                    }
                    if (nrow >= 0 && nrow < rows && ncol >= 0 && ncol < cols && grid[nrow][ncol] && !visited[nrow][ncol]){
                        row.add(nrow);
                        col.add(ncol);
                        visited[nrow][ncol] = true;
                    }
                }
            }
        }
        pw.close();
    }
}

