import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Scales {

    static int ans = 0;

    public static void dfs (int index, int[] weight, int sum, int max){
        if (index < 0){
            ans = Math.max(sum, ans);
            return;
        }
        if (index == 0){
            if (sum + weight[0] <= max){
                ans = Math.max(ans, sum + weight[0]);
            }
            else{
                ans = Math.max(sum, ans);
            }
            return;
        }
        if (weight[index] + weight[index-1] + sum <= max){
            dfs (index-1, weight, sum + weight[index], max);
        }
        if (weight[index] + sum <= max && weight[index] + weight[index-1] + sum > max){
            dfs (index-2, weight, sum + weight[index], max);
            dfs (index-2, weight, sum + weight[index-1], max);
        }
        if (weight[index] + sum > max){
            dfs (index-1, weight, sum, max);
        }
    }

    public static void main(String[] args) throws IOException {

//        BufferedReader br = new BufferedReader(new FileReader("measurement.in"));
//        PrintWriter pw = new PrintWriter(new FileWriter("measurement.out"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int max = Integer.parseInt(st.nextToken());
        int[] weight = new int[n];
        for (int i=0; i < n; i++){
            weight[i] = Integer.parseInt(br.readLine());
        }
        dfs (n-1, weight, 0, max);
        System.out.println(ans);
    }
}
