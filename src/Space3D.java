import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Space3D {

    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new FileReader("cowqueue.in"));
//        PrintWriter pw = new PrintWriter(new FileWriter("cowqueue.out"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        boolean[][][] grid = new boolean[n][n][n];
        boolean[][][] visited = new boolean[n][n][n];
        for (int i=0; i < n; i++){
            for (int j=0; j < n; j++){
                StringTokenizer st1 = new StringTokenizer(br.readLine());
                String temp = st1.nextToken();
                for (int k=0; k < n; k++){
                    if (temp.charAt(k) == '*'){
                        grid[i][j][k] = true;
                    }
                }
            }
        }
        int[] dr = {-1, 1, 0, 0, 0, 0};
        int[] dc = {0, 0, 1, -1, 0, 0};
        int[] dh = {0, 0, 0, 0, 1, -1};
        int counter = 0;
        for (int i=0; i < n; i++){
            for (int j=0; j < n; j++){
                for (int k=0; k < n; k++){
                    if (grid[i][j][k] && !visited[i][j][k]){
                        counter++;
                        visited[i][j][k] = true;
                        Queue<Integer> row = new LinkedList<>();
                        Queue<Integer> col = new LinkedList<>();
                        Queue<Integer> height = new LinkedList<>();
                        row.add(i);
                        col.add(j);
                        height.add(k);
                        while (!row.isEmpty()){
                            int temp = row.size();
                            for (int a=0; a < temp; a++){
                                int r = row.remove();
                                int c = col.remove();
                                int h = height.remove();
                                for (int m=0; m < 6; m++){
                                    int nr = r + dr[m];
                                    int nc = c + dc[m];
                                    int nh = h + dh[m];
                                    if (nr >= 0 && nr < n && nc >= 0 && nc < n && nh >= 0 && nh < n && grid[nr][nc][nh] && !visited[nr][nc][nh]){
                                        visited[nr][nc][nh] = true;
                                        row.add(nr);
                                        col.add(nc);
                                        height.add(nh);
                                    }
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
