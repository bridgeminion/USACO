import java.io.*;
import java.util.*;

class Blob implements Comparable<Blob>{
    int perimeter;
    int area;
    public Blob (int perimeter, int area){
        this.perimeter = perimeter;
        this.area = area;
    }
    public int compareTo(Blob x){
        if (x.area == area){
            return perimeter-x.perimeter;
        }
        return x.area-area;
    }
}

public class Perimeter {

    static List<Blob> ans = new ArrayList<>();
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new FileReader("perimeter.in"));
        PrintWriter pw = new PrintWriter(new FileWriter("perimeter.out"));
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        boolean[][] grid = new boolean[n][n];
        boolean[][] visited = new boolean[n][n];
        for (int i=0; i < n; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            String temp = st.nextToken();
            for (int j=0; j < n; j++){
                char a = temp.charAt(j);
                grid[i][j] = a == '#';
            }
        }
        for (int i=0; i < n; i++){
            for (int j=0; j < n; j++){
                if (grid[i][j] && !visited[i][j]){
                    Blob temp = new Blob (0,1);
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
                            for (int a=0; a < 4; a++){
                                int nr = r+dr[a];
                                int nc = c+dc[a];
                                if (nr >= 0 && nr < n && nc >= 0 && nc < n && grid[nr][nc] && !visited[nr][nc]){
                                    visited[nr][nc] = true;
                                    row.add(nr);
                                    col.add(nc);
                                    temp.area++;
                                }
                                if ((nr < 0 || nr == n || nc < 0 || nc == n) || !grid[nr][nc]){
                                    temp.perimeter++;
                                }
                            }
                        }
                    }
                    ans.add(temp);
                }
            }
        }
        Collections.sort(ans);
        pw.print(ans.get(0).area + " " + ans.get(0).perimeter);
        pw.close();
    }
}
