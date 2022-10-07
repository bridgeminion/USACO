import java.io.*;
import java.util.StringTokenizer;

public class BCount {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("bcount.in"));
        PrintWriter pw = new PrintWriter(new FileWriter("bcount.out"));
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int q = Integer.parseInt(st.nextToken());
        int[] b1 = new int[n+1];
        int[] b2 = new int[n+1];
        int[] b3 = new int[n+1];
        for (int i=1; i <= n; i++){
            int temp = Integer.parseInt(br.readLine());
            b1[i] = b1[i-1];
            b2[i] = b2[i-1];
            b3[i] = b3[i-1];
            if (temp == 1) b1[i]++;
            else if (temp == 2) b2[i]++;
            else b3[i]++;
        }
        for (int i=0; i < q; i++){
            StringTokenizer st1 = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st1.nextToken());
            int b = Integer.parseInt(st1.nextToken());
            pw.println((b1[b] - b1[a-1]) + " " +  (b2[b] - b2[a-1]) + " " + (b3[b] - b3[a-1]));
        }
        pw.close();
    }
}
