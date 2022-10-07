import java.io.*;
import java.util.LinkedList;
import java.util.Queue;

public class Gates {

    static int[] dr = {-2, 2, 0, 0};
    static int[] dc = {0, 0, -2, 2};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("gates.in"));
        PrintWriter pw = new PrintWriter(new FileWriter("gates.out"));
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        boolean[][] grid = new boolean[4005][4005];
        String path = br.readLine();
        int curx = 2000;
        int cury = 2000;
        grid[curx][cury] = true;
        for (int i=0; i < n; i++){
            char temp = path.charAt(i);
            if (temp == 'N') {
                grid[curx][cury+1] = true;
                cury += 2;
            }
            else if (temp == 'S') {
                grid[curx][cury-1] = true;
                cury -= 2;
            }
            else if (temp == 'W') {
                grid[curx-1][cury] = true;
                curx -= 2;
            }
            else {
                grid[curx+1][cury] = true;
                curx += 2;
            }
            grid[curx][cury] = true;
        }
        boolean[][] visited = new boolean[4005][4005];
        int ans = 0;
        for (int i=1; i <= 4004; i+=2){
            for (int j=1; j <= 4004; j+=2){
                if (!visited[i][j] && !grid[i][j]){
                    visited[i][j] = true;
                    ans++;
                    Queue<Integer> row = new LinkedList<>();
                    Queue<Integer> col = new LinkedList<>();
                    row.add(i);
                    col.add(j);
                    while (!row.isEmpty()){
                        int curr = row.remove();
                        int curc = col.remove();
                        for (int k=0; k < 4; k++){
                            int nr = curr + dr[k];
                            int nc = curc + dc[k];
                            if (nr >= 0 && nr <= 4004 && nc >= 0 && nc <= 4004 && !visited[nr][nc] && !grid[nr][nc]){
                                if (k == 0){
                                    if (!grid[nr+1][nc]){
                                        visited[nr][nc] = true;
                                        row.add(nr);
                                        col.add(nc);
                                    }
                                }
                                if (k == 1){
                                    if (!grid[nr-1][nc]){
                                        visited[nr][nc] = true;
                                        row.add(nr);
                                        col.add(nc);
                                    }
                                }
                                if (k == 2){
                                    if (!grid[nr][nc+1]){
                                        visited[nr][nc] = true;
                                        row.add(nr);
                                        col.add(nc);
                                    }
                                }
                                if (k == 3){
                                    if (!grid[nr][nc-1]){
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
        }
        pw.println(ans-1);
        pw.close();
    }
}
