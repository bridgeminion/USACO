import java.io.*;
import java.util.StringTokenizer;

public class MaxCross {

    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new FileReader("haybales.in"));
//        PrintWriter pw = new PrintWriter(new FileWriter("haybales.out"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        boolean[] signal = new boolean[n];
        int[] num_broken = new int[n];
        for (int i=0; i < b; i++){
            StringTokenizer st1 = new StringTokenizer(br.readLine());
            int temp = Integer.parseInt(st1.nextToken());
            signal[temp-1] = true;
        }
        int counter = 0;
        for (int i=0; i < n; i++){
            if (signal[i]){
                counter++;
            }
            num_broken[i] = counter;
        }
        int ans = n;
        for (int i=0; i < n-k; i++){
            int temp = num_broken[i+k] - num_broken[i];
            if (temp < ans){
                ans = temp;
            }
        }
        pw.println(ans);
        pw.close();
    }
}
