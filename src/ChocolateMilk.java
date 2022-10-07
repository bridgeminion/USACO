import java.io.*;
import java.util.StringTokenizer;

public class ChocolateMilk {

    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new FileReader("moop.in"));
//        PrintWriter pw = new PrintWriter(new FileWriter("moop.out"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        int[] in = new int[n+1];
        int[] out = new int[n+1];
        for (int i=0; i < n-1; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            out[start]++;
            in[end]++;
        }
        int start = 2;
        int end = n;
        for (int i=1; i <= n; i++){
            if (out[i] >= 2){
                end = i;
                break;
            }
        }
        for (int i=n; i >= 1; i--){
            if (in[i] >= 2){
                start = i;
                break;
            }
        }
        for (int i=start; i <= end; i++){
            pw.println(i);
        }
        pw.close();
    }
}
