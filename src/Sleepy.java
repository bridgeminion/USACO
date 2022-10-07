import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Sleepy {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("sleepy.in"));
        PrintWriter pw = new PrintWriter(new FileWriter("sleepy.out"));
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int[] line = new int[n];
        StringTokenizer st1 = new StringTokenizer(br.readLine());
        for (int i=0; i < n; i++){
            line[i] = Integer.parseInt(st1.nextToken());
        }
        int ans = n-1;
        for (int i=n-2; i >= 0; i--){
            if (line[i] < line[i+1]){
                ans--;
            }
            else{
                break;
            }
        }
        pw.println(ans);
        pw.close();
    }
}
