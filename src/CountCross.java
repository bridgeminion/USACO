import java.io.*;
import java.util.*;

public class CountCross {

    static class Coordinate{
        int x;
        int y;
        public Coordinate(int x, int y){
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Coordinate that = (Coordinate) o;
            return x == that.x && y == that.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }

    static int[] dr = {2, -2, 0, 0};
    static int[] dc = {0, 0, 2, -2};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("countcross.in"));
        PrintWriter pw = new PrintWriter(new FileWriter("countcross.out"));
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken());
        boolean[][] grid = new boolean[2*n-1][2*n-1];
        for (int i=0; i < r; i++){
            StringTokenizer st1 = new StringTokenizer(br.readLine());
            int row1 = Integer.parseInt(st1.nextToken());
            int col1 = Integer.parseInt(st1.nextToken());
            int row2 = Integer.parseInt(st1.nextToken());
            int col2 = Integer.parseInt(st1.nextToken());
            grid[row1+row2-2][col1+col2-2] = true;
        }
        Set<Coordinate> cow = new HashSet<>(k);
        for (int i=0; i < k; i++){
            StringTokenizer st1 = new StringTokenizer(br.readLine());
            int row = Integer.parseInt(st1.nextToken());
            int col = Integer.parseInt(st1.nextToken());
            cow.add(new Coordinate(2*row-2, 2*col-2));
        }
        boolean[][] visited = new boolean[2*n-1][2*n-1];
        Queue<Integer> row = new LinkedList<>();
        Queue<Integer> col = new LinkedList<>();
        int[] index = new int[k+1];
        int counter = 0;
        for (Coordinate i : cow){
            if (!visited[i.x][i.y]){
                visited[i.x][i.y] = true;
                row.add(i.x);
                col.add(i.y);
                while (!row.isEmpty()){
                    int curr = row.remove();
                    int curc = col.remove();
                    if (cow.contains(new Coordinate(curr, curc))){
                        index[counter]++;
                    }
                    for (int j=0; j < 4; j++){
                        int newr = curr + dr[j];
                        int newc = curc + dc[j];
                        if (newr >= 0 && newr <= 2*n-2 && newc >= 0 && newc <= 2*n-2 && !visited[newr][newc]){
                            if (dr[j] == 2 && !grid[newr-1][newc]){
                                visited[newr][newc] = true;
                                row.add(newr);
                                col.add(newc);
                            }
                            else if (dr[j] == -2 && !grid[newr+1][newc]){
                                visited[newr][newc] = true;
                                row.add(newr);
                                col.add(newc);
                            }
                            else if (dc[j] == 2 && !grid[newr][newc-1]){
                                visited[newr][newc] = true;
                                row.add(newr);
                                col.add(newc);
                            }
                            else if (dc[j] == -2 && !grid[newr][newc+1]){
                                visited[newr][newc] = true;
                                row.add(newr);
                                col.add(newc);
                            }
                        }
                    }
                }
                counter++;
            }
        }
        long ans = 0;
        for (int i=0; i <= counter; i++){
            ans += index[i] * (k - index[i]);
        }
        pw.println(ans/2);
        pw.close();
    }
}
