import java.io.*;
import java.util.*;

public class Hexagon {

    static class Coordinate{
        int x;
        int y;
        public Coordinate(int x, int y){
            this.x = x;
            this.y = y;
        }
    }


    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new FileReader("cowqueue.in"));
//        PrintWriter pw = new PrintWriter(new FileWriter("cowqueue.out"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int start = Integer.parseInt(st.nextToken());
        int distance = Integer.parseInt(st.nextToken());
        int[][] grid = new int[2 * n - 1][4 * n - 3];
        boolean[] visited = new boolean[1000000];
        List<Coordinate> index = new ArrayList<>();
        Queue<Integer> q = new LinkedList<>();
        int counter = 1;
        for (int i = 0; i < n; i++) {
            for (int j = n - i - 1; j < 3 * n + i - 2; j += 2) {
                grid[i][j] = counter;
                counter++;
                index.add(new Coordinate(i, j));
            }
        }
        for (int i = n; i < 2 * n - 1; i++) {
            for (int j = i - n + 1; j < 5 * n - i - 4; j += 2) {
                grid[i][j] = counter;
                counter++;
                index.add(new Coordinate(i, j));
            }
        }
//        for (int i = 0; i < 2 * n - 1; i++) {
//            for (int j = 0; j < 4 * n - 3; j++) {
//                pw.print(grid[i][j]);
//                pw.print(" ");
//            }
//            pw.println();
//        }
//        pw.println();
//        for (Coordinate i : index) {
//            pw.print(i.x);
//            pw.print(" ");
//            pw.print(i.y);
//            pw.println();
//        }
        q.add(start);
        int cur_dist = 0;
        while (!q.isEmpty() && cur_dist < distance){
            cur_dist++;
            int temp = q.size();
            for (int i=0; i < temp; i++){
                int cur = q.remove();
                visited[cur] = true;
                q.addAll(getNeighbor(cur, index, grid, visited));
            }
        }
        Set<Integer> temp = new HashSet<>();
        List<Integer> ans = new ArrayList<>();
        temp.addAll(q);
        ans.addAll(temp);
        Collections.sort(ans);
        pw.println(ans.size());
        for (int i : ans){
            pw.println(i);
        }
        pw.close();
    }

    private static List<Integer> getNeighbor(int cur, List<Coordinate> index, int[][] grid, boolean[] visited) {
        int[] dr = {-1, -1, 0, 0, 1, 1};
        int[] dc = {-1, 1, -2, 2, -1, 1};
        int row = index.get(cur - 1).x;
        int col = index.get(cur - 1).y;
        List<Integer> result = new ArrayList<>();
        for (int i=0; i < 6; i++){
            int r = row + dr[i];
            int c = col + dc[i];
            if (r >= 0 && r < grid.length && c >= 0 && c < grid[0].length && !visited[grid[r][c]] && grid[r][c] != 0){
                visited[grid[r][c]] = true;
                result.add(grid[r][c]);
            }
        }
        return result;
    }
}
