import java.io.*;
import java.util.StringTokenizer;

public class LongestIncreasingSubsequence {

    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new FileReader("paintbarn.in"));
//        PrintWriter pw = new PrintWriter(new FileWriter("paintbarn.out"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int[] val = new int[n];
        int[] len = new int[n];
        for (int i=0; i < n; i++){
            String temp = br.readLine().trim();
            if (temp.charAt(0) == '-'){
                val[i] = -Integer.parseInt(temp.substring(1));
            }
            else{
                val[i] = Integer.parseInt(temp);
            }
        }
        int ans = 0;
        len[0] = 1;
        for (int i=1; i < n; i++){
            len[i] = 1;
            for (int j=0; j < i; j++){
                if (val[i] > val[j]){
                    len[i] = Math.max(len[i], 1+len[j]);
                }
            }
            ans = Math.max(ans, len[i]);
        }
        System.out.println(ans);
    }
}
