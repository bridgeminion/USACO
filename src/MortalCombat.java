import java.io.*;
import java.util.StringTokenizer;

public class MortalCombat {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("cowmbat.in"));
        PrintWriter pw = new PrintWriter(new FileWriter("cowmbat.out"));
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        String s = br.readLine();
        int[][] cost = new int[m][m];
        for (int i=0; i < m; i++){
            StringTokenizer st1 = new StringTokenizer(br.readLine());
            for (int j=0; j < m; j++){
                cost[i][j] = Integer.parseInt(st1.nextToken());
            }
        }
        for (int mid=0; mid < m; mid++){
            for (int i=0; i < m; i++){
                for (int j=0; j < m; j++){
                    cost[i][j] = Math.min(cost[i][j], cost[i][mid] + cost[mid][j]);
                }
            }
        }

    }
}
