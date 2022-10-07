import java.io.*;
import java.util.HashSet;
import java.util.Set;

public class Superbull {

    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new FileReader("moop.in"));
//        PrintWriter pw = new PrintWriter(new FileWriter("moop.out"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        int[] id = new int[n];
        for (int i=0; i < n; i++){
            id[i] = Integer.parseInt(br.readLine());
        }
        int[][] cost = new int[n][n];
        for (int i=0; i < n; i++){
            for (int j=0; j < n; j++){
                cost[i][j] = -(id[i]^id[j]);
            }
        }
        Set<Integer> visited = new HashSet<>();
        int[] key = new int[n];
        for (int i=0; i < n; i++){
            key[i] = Integer.MAX_VALUE;
        }
        key[0] = 0;
        long ans = 0;
        for (int i=0; i < n; i++){
            int min = Integer.MAX_VALUE;
            int index = -1;
            for (int j=0; j < n; j++){
                if (!visited.contains(j) && min > key[j]){
                    index = j;
                    min = key[j];
                }
            }
            ans += min;
            visited.add(index);
            for (int j=0; j < n; j++){
                if (!visited.contains(j)){
                    key[j] = Math.min(key[j], cost[index][j]);
                }
            }
        }
        System.out.println(-ans);
    }
}
