import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Knapsack {

    static class Thing{
        int size;
        int val;
        public Thing (int size, int val){
            this.size = size;
            this.val = val;
        }
    }

    public static int dp (int n, int c, List<Thing> list, boolean[][] visited, int[][] val){
        if (n==0){
            return 0;
        }
        if (visited[n][c]){
            return val[n][c];
        }
        visited[n][c] = true;
        if (list.get(n).size > c){
            val[n][c] = dp (n-1, c, list, visited, val);
            return val[n][c];
        }
        val[n][c] = Math.max(dp (n-1, c-list.get(n).size, list, visited, val) + list.get(n).val, dp (n-1, c, list, visited, val));
        return val[n][c];
    }

    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new FileReader("berries.in"));
//        PrintWriter pw = new PrintWriter(new FileWriter("berries.out"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        List<Thing> list = new ArrayList<>(n);
        list.add(new Thing(0, 0));
        for (int i=0; i < n; i++){
            StringTokenizer st1 = new StringTokenizer(br.readLine());
            int size = Integer.parseInt(st1.nextToken());
            int val = Integer.parseInt(st1.nextToken());
            list.add(new Thing (size, val));
        }
        System.out.println(dp(n, c, list, new boolean[n+1][c+1], new int[n+1][c+1]));
    }
}
