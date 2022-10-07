import java.io.*;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class WateringHole {

    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new FileReader("moop.in"));
//        PrintWriter pw = new PrintWriter(new FileWriter("moop.out"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        int[] key = new int[n+1];
        for (int i=1; i <= n; i++){
            key[i] = Integer.parseInt(br.readLine());
        }
        int[][] cost = new int[n+1][n+1];
        for (int i=1; i <= n; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j=1; j <= n; j++){
                cost[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        Set<Integer> visited = new HashSet<>();
        int ans = 0;
        while (visited.size() < n){
            int min = Integer.MAX_VALUE;
            int index = 0;
            for (int i=1; i <= n; i++){
                if (!visited.contains(i) && key[i] < min){
                    min = key[i];
                    index = i;
                }
            }
            visited.add(index);
            ans += min;
            for (int i=1; i <= n; i++){
                if (!visited.contains(i)){
                    key[i] = Math.min(key[i], cost[i][index]);
                }
            }
        }
        System.out.println(ans);
    }
}
