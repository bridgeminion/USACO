import java.io.*;
import java.util.*;

public class Where {

    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static boolean isPCL (int[][] grid, int minr, int maxr, int minc, int maxc, int n){
        boolean[][] visited = new boolean[n][n];
        Set<Integer> colors = new HashSet<>();
        int dup = -1;
        for (int i=minr; i <= maxr; i++){
            for (int j=minc; j <= maxc; j++){
                if (!visited[i][j]){
                    Queue<Integer> row = new LinkedList<>();
                    Queue<Integer> col = new LinkedList<>();
                    row.add(i);
                    col.add(j);
                    int temp = grid[i][j];
                    if (colors.contains(temp)){
                        if (dup == -1){
                            dup = temp;
                        }
                        else if (dup != temp){
                            return false;
                        }
                    }
                    else if (colors.size() > 1){
                        return false;
                    }
                    colors.add(temp);
                    visited[minr][minc] = true;
                    while (!row.isEmpty()){
                        int curr = row.remove();
                        int curc = col.remove();
                        for (int k=0; k < 4; k++){
                            int nr = curr+dr[k];
                            int nc = curc+dc[k];
                            if (nr >= minr && nr <= maxr && nc >= minc && nc <= maxc && !visited[nr][nc] && grid[nr][nc] == temp){
                                visited[nr][nc] = true;
                                row.add(nr);
                                col.add(nc);
                            }
                        }
                    }
                }
            }
        }
        return colors.size() == 2 && dup != -1;
    }

    static class Rect {
        int minr;
        int maxr;
        int minc;
        int maxc;

        public Rect(int minr, int maxr, int minc, int maxc) {
            this.minr = minr;
            this.maxr = maxr;
            this.minc = minc;
            this.maxc = maxc;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Rect rect = (Rect) o;
            return minr == rect.minr &&
                    maxr == rect.maxr &&
                    minc == rect.minc &&
                    maxc == rect.maxc;
        }

        @Override
        public int hashCode() {
            return Objects.hash(minr, maxr, minc, maxc);
        }
    }

    public static boolean valid (Rect cur, Rect other){
        if (other.minr == cur.minr && other.maxr == cur.maxr && other.minc == cur.minc && other.maxc == cur.maxc){
            return true;
        }
        return !(other.minr <= cur.minr && other.maxr >= cur.maxr && other.minc <= cur.minc && other.maxc >= cur.maxc);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("where.in"));
        PrintWriter pw = new PrintWriter(new FileWriter("where.out"));
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        int[][] grid = new int[n][n];
        for (int i=0; i < n; i++){
            String temp = br.readLine();
            for (int j=0; j < n; j++){
                grid[i][j] = temp.charAt(j) - 'A';
            }
        }
        int ans = 0;
        Set<Rect> set = new HashSet<>();
        Set<Rect> set1 = new HashSet<>();
        for (int a=0; a < n; a++){
            for (int b=a; b < n; b++){
                for (int c=0; c < n; c++){
                    for (int d=c; d < n; d++){
                        if (isPCL(grid, a, b, c, d, n)){
                            ans++;
                            set.add(new Rect (a, b, c, d));
                            set1.add(new Rect (a, b, c, d));
                        }
                    }
                }
            }
        }
        for (Rect i : set){
            boolean invalid = false;
            for (Rect j : set){
                if (!valid(i, j)){
                    invalid = true;
                    break;
                }
            }
            if (invalid){
                ans--;
                set1.remove(i);
            }
        }
        pw.println(ans);
        pw.close();
    }
}
