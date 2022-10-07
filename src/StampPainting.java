import java.io.*;
import java.util.StringTokenizer;

public class StampPainting {

    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new FileReader("haybales.in"));
//        PrintWriter pw = new PrintWriter(new FileWriter("haybales.out"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        long[] s = new long[n+1];
        long mod = (long)Math.pow(10, 9)+7;
        for (int i=1; i < k; i++){
            s[i] = (m*s[i-1] + m)%mod;
        }
        for (int i=k; i <= n; i++){
            s[i] = (m*s[i-1]+mod)%mod;
            s[i] -= (mod+(m-1)*s[i-k]%mod);
            s[i] = (s[i] + mod)%mod;
        }
        long total = 1;
        for (int i=0; i < n; i++){
            total = (total*m)%mod;
        }
        System.out.println((total + mod - s[n] + s[n-1])%mod);
    }
}
