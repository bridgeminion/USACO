import java.io.*;
import java.util.StringTokenizer;

public class EGroup {

    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new FileReader("censor.in"));
//        PrintWriter pw = new PrintWriter(new FileWriter("censor.out"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int[] cur = new int[n+1];
        for (int i=0; i < n; i++){
            cur[i+1] = Integer.parseInt(br.readLine());
        }
        int[][] val1 = new int[n+1][4];
        int[][] val2 = new int[n+1][4];
        if (cur[1] == 1){
            val1[1][1] = 0;
            val1[1][2] = 1;
            val1[1][3] = 1;
        }
        else if (cur[1] == 2){
            val1[1][1] = 1;
            val1[1][2] = 0;
            val1[1][3] = 1;
        }
        else {
            val1[1][1] = 1;
            val1[1][2] = 1;
            val1[1][3] = 0;
        }
        // increasing
        for (int i=1; i <= n; i++){
            if (cur[i]==1){
                val1[i][1] = val1[i-1][1];
                val1[i][2] = Math.min(val1[i-1][1], val1[i-1][2]) + 1;
                int temp = Math.min(val1[i-1][1], val1[i-1][2]);
                val1[i][3] = Math.min(val1[i-1][3], temp) + 1;
            }
            else if (cur[i]==2){
                val1[i][1] = val1[i-1][1] + 1;
                val1[i][2] = Math.min(val1[i-1][1], val1[i-1][2]);
                int temp = Math.min(val1[i-1][1], val1[i-1][2]);
                val1[i][3] = Math.min(val1[i-1][3], temp) + 1;
            }
            else {
                val1[i][1] = val1[i-1][1] + 1;
                val1[i][2] = Math.min(val1[i-1][1], val1[i-1][2]) + 1;
                int temp = Math.min(val1[i-1][1], val1[i-1][2]);
                val1[i][3] = Math.min(val1[i-1][3], temp);
            }
        }
        int temp1 = Math.min(val1[n][1], val1[n][2]);
        int ans1 = Math.min(temp1, val1[n][3]);
        // decreasing
        for (int i=1; i <= n; i++){
            if (cur[i]==1){
                int temp = Math.min(val2[i-1][3], val2[i-1][2]);
                val2[i][1] = Math.min(temp, val2[i-1][1]);
                val2[i][2] = Math.min(val2[i-1][3], val2[i-1][2]) + 1;
                val2[i][3] = val2[i-1][3] + 1;
            }
            else if (cur[i]==2){
                int temp = Math.min(val2[i-1][3], val2[i-1][2]);
                val2[i][1] = Math.min(temp, val2[i-1][1]) + 1;
                val2[i][2] = Math.min(val2[i-1][3], val2[i-1][2]);
                val2[i][3] = val2[i-1][3] + 1;
            }
            else {
                int temp = Math.min(val2[i-1][3], val2[i-1][2]);
                val2[i][1] = Math.min(temp, val2[i-1][1]) + 1;
                val2[i][2] = Math.min(val2[i-1][3], val2[i-1][2]) + 1;
                val2[i][3] = val2[i-1][3];
            }
        }
        int temp2 = Math.min(val2[n][1], val2[n][2]);
        int ans2 = Math.min(temp2, val2[n][3]);
        System.out.println(Math.min(ans1, ans2));
    }
}
