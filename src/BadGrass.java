import java.io.*;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BadGrass {

    static int[] dr = {-1, 1, 0, 0, -1, 1, -1, 1};
    static int[] dc = {0, 0, 1, -1, -1, 1, 1, -1};

    public static void main(String[] args) throws IOException {

//        BufferedReader br = new BufferedReader(new FileReader("perimeter.in"));
//        PrintWriter pw = new PrintWriter(new FileWriter("perimeter.out"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int rows = Integer.parseInt(st.nextToken());
        int cols = Integer.parseInt(st.nextToken());
        boolean[][] grid = new boolean[rows][cols];
        boolean[][] visited = new boolean[rows][cols];
        for (int i=0; i < rows; i++){
            StringTokenizer st1 = new StringTokenizer(br.readLine());
            for (int j=0; j < cols; j++){
                int a = Integer.parseInt(st1.nextToken());
                grid[i][j] = a > 0;
            }
        }
        int counter = 0;
        for (int i=0; i < rows; i++){
            for (int j=0; j < cols; j++){
                if (grid[i][j] && !visited[i][j]){
                    counter++;
                    Queue<Integer> row = new LinkedList<>();
                    Queue<Integer> col = new LinkedList<>();
                    row.add(i);
                    col.add(j);
                    visited[i][j] = true;
                    while (!row.isEmpty()){
                        int size = row.size();
                        for (int k=0; k < size; k++){
                            int r = row.remove();
                            int c = col.remove();
                            for (int a=0; a < 8; a++){
                                int nr = r+dr[a];
                                int nc = c+dc[a];
                                if (nr >= 0 && nr < rows && nc >= 0 && nc < cols && grid[nr][nc] && !visited[nr][nc]){
                                    visited[nr][nc] = true;
                                    row.add(nr);
                                    col.add(nc);
                                }
                            }
                        }
                    }
                }
            }
        }
        pw.println(counter);
        pw.close();
    }
}
