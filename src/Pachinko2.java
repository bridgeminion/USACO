import java.io.*;
import java.util.*;

public class Pachinko2 {

    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new FileReader("paintbarn.in"));
//        PrintWriter pw = new PrintWriter(new FileWriter("paintbarn.out"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int[][] val = new int[n+1][n+1];
        for (int i=0; i < n; i++){
            StringTokenizer st1 = new StringTokenizer(br.readLine());
            for (int j=0; j <= i; j++){
                val[i][j] = Integer.parseInt(st1.nextToken());
            }
        }
        for (int i=n-1; i >= 0; i--){
            for (int j=0; j <= i; j++){
                val[i][j] += Math.max(val[i+1][j], val[i+1][j+1]);
            }
        }
        System.out.println(val[0][0]);
    }
}
