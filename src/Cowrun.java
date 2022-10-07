import java.io.*;
import java.util.StringTokenizer;

public class Cowrun {

    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new FileReader("cowrun.in"));
//        PrintWriter pw = new PrintWriter(new FileWriter("cowrun.out"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[] dist = new int[n+1];
        for (int i=1; i <= n; i++){
            dist[i] = Integer.parseInt(br.readLine());
        }
//        int[][][] val = new int[n+1][m+1][2];
//        for (int i=1; i <= n; i++){
//            for (int j=0; j <= m; j++){
//                for (int k=0; k < 2; k++){
//                    if (k==0){
//                        if (j==0){
//                            val[i][j][0] = Math.max(Math.max(val[i-1][j][0], val[i-1][j][1]), Math.max(val[i-1][j+1][0], val[i-1][j+1][1]));
//                        }
//                        else if (j==m){
//                            val[i][j][0] = val[i-1][j-1][1];
//                        }
//                        else {
//                            val[i][j][0] = Math.max(val[i-1][j+1][0], val[i-1][j+1][1]);
//                        }
//                    }
//                    else {
//                        if (j==0){
//                            val[i][j][1] = Math.max(Math.max(val[i-1][j][0], val[i-1][j][1]), Math.max(val[i-1][j+1][0], val[i-1][j+1][1]));
//                        }
//                        else if (j==m){
//                            val[i][j][1] = val[i-1][j-1][1] + dist[i];
//                        }
//                        else {
//                            val[i][j][1] = val[i-1][j-1][1] + dist[i];
//                        }
//                    }
//                }
//            }
//        }
        int[][] cur = new int[m+1][2];
        int[][] last = new int[m+1][2];
        for (int i=1; i <= n; i++){
            for (int j=0; j <= m; j++){
                for (int k=0; k < 2; k++){
                    if (k==0){
                        if (j==0){
                            cur[j][0] = Math.max(Math.max(last[j][0], last[j][1]), Math.max(last[j+1][0], last[j+1][1]));
                        }
                        else if (j==m){
                            cur[j][0] = last[j-1][1];
                        }
                        else {
                            cur[j][0] = Math.max(last[j+1][0], last[j+1][1]);
                        }
                    }
                    else {
                        if (j==0){
                            cur[j][1] = Math.max(Math.max(last[j][0], last[j][1]), Math.max(last[j+1][0], last[j+1][1]));
                        }
                        else if (j==m){
                            cur[j][1] = last[j-1][1] + dist[i];
                        }
                        else {
                            cur[j][1] = last[j-1][1] + dist[i];
                        }
                    }
                }
            }
            for (int j=0; j <= m; j++){
                for (int k=0; k < 2; k++){
                    last[j][k] = cur[j][k];
                }
            }
        }
        System.out.println(Math.max(last[0][0], last[0][1]));
    }
}
