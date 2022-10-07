import java.io.*;
import java.util.*;

public class Hurdles1 {

    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new FileReader("paintbarn.in"));
//        PrintWriter pw = new PrintWriter(new FileWriter("paintbarn.out"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int num_stations = Integer.parseInt(st.nextToken());
        int num_paths = Integer.parseInt(st.nextToken());
        int num_tasks = Integer.parseInt(st.nextToken());
        int[][] dist = new int[num_stations+1][num_stations+1];
        for (int i=0; i < num_paths; i++){
            StringTokenizer st1 = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st1.nextToken());
            int b = Integer.parseInt(st1.nextToken());
            int h = Integer.parseInt(st1.nextToken());
            dist[a][b] = h;
        }
        for (int k=1; k <= num_stations; k++){
            for (int i=1; i <= num_stations; i++){
                for (int j=1; j <= num_stations; j++){
                    if (dist[i][k] != 0 && dist[k][j] != 0){
                        if ((dist[i][j] > dist[i][k] && dist[i][j] > dist[k][j]) || dist[i][j] == 0){
                            dist[i][j] = Math.max(dist[i][k], dist[k][j]);
                        }
                    }
                }
            }
        }
        for (int i=0; i < num_tasks; i++){
            StringTokenizer st1 = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st1.nextToken());
            int b = Integer.parseInt(st1.nextToken());
            if (dist[a][b] == 0){
                System.out.println(-1);
            }
            else {
                System.out.println(dist[a][b]);
            }
        }
    }
}
