import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class HighCard {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("highcard.in"));
        PrintWriter pw = new PrintWriter(new FileWriter("highcard.out"));
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        boolean[] who_els = new boolean[2*n+1];
        for (int i=0; i < n; i++){
            int temp = Integer.parseInt(br.readLine());
            who_els[temp] = true;
        }
        int num_waiting = 0;
        int ans = 0;
        for (int i=2*n; i >= 0; i--){
            if (who_els[i]){
                if (num_waiting > 0) {
                    num_waiting--;
                    ans++;
                }
            }
            else {
                num_waiting++;
            }
        }
        pw.println(ans);
        pw.close();
    }
}
