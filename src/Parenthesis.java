import java.io.*;
import java.util.StringTokenizer;

public class Parenthesis {

    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new FileReader("mountains.in"));
//        PrintWriter pw = new PrintWriter(new FileWriter("mountains.out"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        boolean[] open = new boolean[n];
        for (int i=0; i < n; i++){
            if (br.readLine().equals("0")){
                open[i] = true;
            }
        }
        long[] val = new long[n];
        long mod = 12345678910L;
        int level = 0;
        for (int i=0; i < n; i++){
            if (open[i]){
                level++;
            }
            else {
                if (open[i-1]){
                    val[level-1]++;
                }
                else {
                    val[level-1] += val[level]*2;
                    val[level-1] = val[level-1] % mod;
                    val[level] = 0;
                }
                level--;
            }
        }
        System.out.println(val[0]);
    }
}
