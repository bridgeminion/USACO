import java.io.*;
import java.util.*;

public class MooyoMooyo {

    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("mooyomooyo.in"));
        PrintWriter pw = new PrintWriter(new FileWriter("mooyomooyo.out"));
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int[][] grid = new int[n][10];
        for (int i=0; i < n; i++){
            String temp = br.readLine();
            for (int j=0; j < 10; j++){
                grid[i][j] = temp.charAt(j) - '0';
            }
        }
//        pw.println("NEW ONE");
//        for (int i=0; i < n; i++){
//            for (int j=0; j < 10; j++){
//                pw.print(grid[i][j]);
//            }
//            pw.println();
//        }
//        pw.println();
        boolean done = false;
        while (!done) {
            done = true;
            boolean[][] visited = new boolean[n][10];
            for (int i=0; i < n; i++){
                for (int j=0; j < 10; j++){
                    if (!visited[i][j] && grid[i][j] != 0){
                        List<Integer> totalr = new ArrayList<>();
                        List<Integer> totalc = new ArrayList<>();
                        Queue<Integer> row = new LinkedList<>();
                        Queue<Integer> col = new LinkedList<>();
                        row.add(i);
                        col.add(j);
                        visited[i][j] = true;
                        while (!row.isEmpty()){
                            int cr = row.remove();
                            int cc = col.remove();
                            totalr.add(cr);
                            totalc.add(cc);
                            for (int a=0; a < 4; a++){
                                int nr = cr + dr[a];
                                int nc = cc + dc[a];
                                if (nr >= 0 && nr < n && nc >= 0 && nc < 10 && grid[nr][nc] == grid[cr][cc] && !visited[nr][nc]){
                                    visited[nr][nc] = true;
                                    row.add(nr);
                                    col.add(nc);
                                }
                            }
                        }
                        if (totalr.size() >= k){
                            done = false;
                            for (int a=0; a < totalr.size(); a++){
                                grid[totalr.get(a)][totalc.get(a)] = 0;
                            }
                        }
                    }
                }
            }
//            pw.println("NEW ONE");
//            for (int i=0; i < n; i++){
//                for (int j=0; j < 10; j++){
//                    pw.print(grid[i][j]);
//                }
//                pw.println();
//            }
//            pw.println();
            for (int j=0; j < 10; j++){
                int realrow = n-1;
                for (int i=n-1; i >= 0; i--){
                    if (grid[i][j] != 0){
                        grid[realrow][j] = grid[i][j];
                        if (realrow != i){
                            grid[i][j] = 0;
                        }
                        realrow--;
                    }
                }
            }
//            pw.println("NEW ONE");
//            for (int i=0; i < n; i++){
//                for (int j=0; j < 10; j++){
//                    pw.print(grid[i][j]);
//                }
//                pw.println();
//            }
//            pw.println();
        }
        for (int i=0; i < n; i++){
            for (int j=0; j < 10; j++){
                pw.print(grid[i][j]);
            }
            pw.println();
        }
        pw.close();
    }
}
