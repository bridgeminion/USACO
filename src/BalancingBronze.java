import java.io.*;
import java.util.StringTokenizer;

public class BalancingBronze {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("balancing.in"));
        PrintWriter pw = new PrintWriter(new FileWriter("balancing.out"));
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        int n, max;
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        max = Integer.parseInt(st.nextToken());
        int[] x = new int[n];
        int[] y = new int[n];
        int max_x = 0;
        int max_y = 0;
        int ans = 2147483647;
        for (int i=0; i < n; i++){
            StringTokenizer st1 = new StringTokenizer(br.readLine());
            x[i] = Integer.parseInt(st1.nextToken());
            y[i] = Integer.parseInt(st1.nextToken());
            if (x[i] > max_x){
                max_x = x[i];
            }
            if (y[i] > max_y){
                max_y = y[i];
            }
        }
        for (int i=0; i < n; i++){
            for (int j=0; j < n; j ++){
                int[] temp = new int[4];
                int xDiv = x[i]+1;
                int yDiv = y[j] + 1;
                for (int k=0; k < n; k++){
                    if (xDiv > x[k] && yDiv > y[k]){
                        temp[0]++;
                    }
                    if (xDiv > x[k] && yDiv < y[k]){
                        temp[1]++;
                    }
                    if (xDiv < x[k] && yDiv > y[k]){
                        temp[2]++;
                    }
                    if (xDiv < x[k] && yDiv < y[k]){
                        temp[3]++;
                    }
                }
                int bigtemp = 0;
                for (int a=0; a < 4; a++){
                    if (temp[a] > bigtemp){
                        bigtemp = temp[a];
                    }
                }
                if (bigtemp < ans){
                    ans = bigtemp;
                }
            }
        }
        pw.println(ans);
        pw.close();
    }
}
