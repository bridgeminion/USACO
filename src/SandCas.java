import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SandCas {

    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new FileReader("mountains.in"));
//        PrintWriter pw = new PrintWriter(new FileWriter("mountains.out"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int add = Integer.parseInt(st.nextToken());
        int sub = Integer.parseInt(st.nextToken());
        int[] s = new int[n];
        int[] e = new int[n];
        for (int i=0; i < n; i++){
            StringTokenizer st1 = new StringTokenizer(br.readLine());
            s[i] = Integer.parseInt(st1.nextToken());
            e[i] = Integer.parseInt(st1.nextToken());
        }
        Arrays.sort(s);
        Arrays.sort(e);
        long ans = 0;
        for (int i=0; i < n; i++){
            if (s[i] > e[i]){
                ans += sub*(s[i]-e[i]);
            }
            else {
                ans += add*(e[i]-s[i]);
            }
        }
        System.out.println(ans);
    }
}
