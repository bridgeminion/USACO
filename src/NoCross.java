import java.io.*;
import java.util.*;

public class NoCross {

    public static int Sol (int i, int j, int[] top, int[] bot, int n, boolean[][] visited, int[][] val, Map<Integer, List<Integer>> neighbor){
        if (i==n){
            return 0;
        }
        if (visited[i][j]){
            return val[i][j];
        }
        visited[i][j] = true;
        int ans = 0;
        for (int a : neighbor.get(i)){
            if (a >= j){
                if (!visited[i+1][a+1]){
                    val[i+1][a+1] = Sol(i+1, a+1, top, bot, n, visited, val, neighbor);
                    ans = Math.max(ans, 1 + val[i+1][a+1]);
                }
                else {
                    ans = Math.max(ans, 1 + val[i+1][a+1]);
                }
            }
        }
        if (!visited[i+1][j]){
            val[i+1][j] = Sol(i+1, j, top, bot, n, visited, val, neighbor);
        }
        ans = Math.max(ans, val[i+1][j]);
        val[i][j] = ans;
        return ans;
    }

    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new FileReader("berries.in"));
//        PrintWriter pw = new PrintWriter(new FileWriter("berries.out"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int[] top = new int[n];
        int[] bot = new int[n];
        for (int i=0; i < n; i++){
            top[i] = Integer.parseInt(br.readLine());
        }
        for (int i=0; i < n; i++){
            bot[i] = Integer.parseInt(br.readLine());
        }
        Map<Integer, List<Integer>> neighbor = new HashMap<>();
        for (int i=0; i < n; i++){
            for (int j=0; j < n; j++){
                if (Math.abs(top[i]-bot[j]) <= 4){
                    neighbor.putIfAbsent(i, new ArrayList<>());
                    neighbor.get(i).add(j);
                }
            }
        }
        boolean[][] visited = new boolean[n+1][n+1];
        int[][] val = new int[n+1][n+1];
        System.out.println(Sol(0, 0, top, bot, n, visited, val, neighbor));
        System.out.println();
    }

}
