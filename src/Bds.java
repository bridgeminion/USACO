import java.io.*;
import java.util.*;

public class Bds {

    static boolean done = false;

    public static long choose (int a, int b){
        long ans = 1;
        for (int i=a-b+1; i <= a; i++){
            ans*=i;
        }
        for (int i=1; i <= b; i++){
            ans/=i;
        }
        return ans;
    }

    public static boolean check (int[] cur, int n, int target){
        int sum = 0;
        for (int i=0; i < n; i++){
            sum += cur[i]*choose(n-1, i);
        }
        return sum == target;
    }

    public static void generate (int n, int[] cur, boolean[] visited, int num, int target){
        if (done){
            return;
        }
        if (num == n){
            if (check(cur, n, target)){
                for (int i : cur){
                    System.out.print(i + " ");
                }
                done = true;
            }
            return;
        }
        for (int i=1; i <= n; i++){
            if (!visited[i]){
                cur[num] = i;
                visited[i] = true;
                generate(n, cur, visited, num+1, target);
                cur[num] = 0;
                visited[i] = false;
            }
        }
    }

    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new FileReader("paintbarn.in"));
//        PrintWriter pw = new PrintWriter(new FileWriter("paintbarn.out"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int sum = Integer.parseInt(st.nextToken());
        generate(n, new int[n], new boolean[n+1], 0, sum);
    }
}
