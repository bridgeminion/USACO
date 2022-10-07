import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Danger {

    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new FileReader("paintbarn.in"));
//        PrintWriter pw = new PrintWriter(new FileWriter("paintbarn.out"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        List<Integer> map = new ArrayList<>();
        for (int i=0; i < m; i++){
            map.add(Integer.parseInt(br.readLine()));
        }
        int[][] dist = new int[n][n];
        for (int i=0; i < n; i++){
            StringTokenizer st1 = new StringTokenizer(br.readLine());
            for (int j=0; j < n; j++){
                dist[i][j] = Integer.parseInt(st1.nextToken());
            }
        }
        for (int k=0; k < n; k++){
            for (int i=0; i < n; i++){
                for (int j=0; j < n; j++){
                    if (i != k && k != j){
                        if (dist[i][j] > dist[i][k] + dist[k][j]){
                            dist[i][j] = dist[i][k] + dist[k][j];
                        }
                    }
                }
            }
        }
        int ans = 0;
        for (int i=0; i < map.size()-1; i++){
            ans += dist[map.get(i)-1][map.get(i+1)-1];
        }
        System.out.println(ans);
    }
}
