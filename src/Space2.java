import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Space2 {

    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new FileReader("cowqueue.in"));
//        PrintWriter pw = new PrintWriter(new FileWriter("cowqueue.out"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        boolean[][] grid = new boolean[n][n];
        boolean[][] visited = new boolean[n][n];
        for (int i=0; i < n; i++){
            StringTokenizer st1 = new StringTokenizer(br.readLine());
            String temp = st1.nextToken();
            for (int j=0; j < n; j++){
                if (temp.charAt(j) == '*'){
                    grid[i][j] = true;
                }
            }
        }
        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, 1, -1};
        int counter = 0;
        for (int i=0; i < n; i++){
            for (int j=0; j < n; j++){
                if (grid[i][j] && !visited[i][j]){
                    counter++;
                    visited[i][j] = true;
                    Queue<Integer> row = new LinkedList<>();
                    Queue<Integer> col = new LinkedList<>();
                    row.add(i);
                    col.add(j);
                    while (!row.isEmpty()){
                        int temp = row.size();
                        for (int a=0; a < temp; a++){
                            int r = row.remove();
                            int c = col.remove();
                            for (int k=0; k < 4; k++){
                                int nr = r + dr[k];
                                int nc = c + dc[k];
                                if (nr >= 0 && nr < n && nc >= 0 && nc < n && grid[nr][nc] && !visited[nr][nc]){
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
        System.out.println(counter);
        pw.close();
    }
}
