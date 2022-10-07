import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Leprechuan {

    public static int LC (int[] input, int start, int n){
        int max = input[start];
        int cur;
        int last = 0;
        for (int i=0; i < input.length; i++){
            cur = Math.max(last+input[(i+start)%n], input[(i+start)%n]);
            max = Math.max(cur, max);
            last = cur;
        }
        return max;
    }

    public static void main(String[] args) throws IOException {

//        BufferedReader br = new BufferedReader(new FileReader("citystate.in"));
//        PrintWriter pw = new PrintWriter(new FileWriter("citystate.out"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        int[][] input = new int[n][n];
        for (int i = 0; i < n; i++) {
            StringTokenizer st1 = new StringTokenizer(br.readLine());
            for (int j=0; j < n; j++){
                input[i][j] = Integer.parseInt(st1.nextToken());
            }
        }
        // horizontal
        int ans = input[0][0];
        for (int i=0; i < n; i++){
            for (int j=0; j < n; j++){
                ans = Math.max(ans, LC (input[i], j, n));
            }
        }
        // vertical
        int[] temp = new int[n];
        for (int i=0; i < n; i++){
            for (int j=0; j < n; j++){
                temp[j] = input[j][i];
            }
            for (int j=0; j < n; j++){
                ans = Math.max(ans, LC (temp, j, n));
            }
        }
        // diagonal (row++ col++)
        for (int i=0; i < n; i++){
            for (int j=0; j < n; j++){
                temp[j] = input[(i+j)%n][j];
            }
            for (int j=0; j < n; j++){
                ans = Math.max(ans, LC (temp, j, n));
            }
        }
        // other diagonal
        for (int i=0; i < n; i++){
            for (int j=0; j < n; j++){
                temp[j] = input[(i+j)%n][n-j-1];
            }
            for (int j=0; j < n; j++){
                ans = Math.max(ans, LC (temp, j, n));
            }
        }
        System.out.println(ans);
    }
}
