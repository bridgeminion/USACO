import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Bowls {

    static int ans = 20;

    public static void solve(int[] bowls, int pos, int total, boolean done){
        if (pos >= 20){
            boolean works = true;
            for (int i=0; i < 20; i++){
                if (bowls[i] == 1){
                    works = false;
                }
            }
            if (works && total < ans){
                ans = total;
            }
            return;
        }
        if (total > ans){
            return;
        }
        if (!done){
            bowls[pos] = 1 - bowls[pos];
            bowls[pos+1] = 1 - bowls[pos+1];
            solve(bowls, pos, total+1, true);
            bowls[pos] = 1 - bowls[pos];
            bowls[pos+1] = 1 - bowls[pos+1];
            solve(bowls, pos, total, true);
        }
        else if (bowls[pos] == 1){
            if (pos == 18){
                bowls[pos] = 1 - bowls[pos];
                bowls[pos+1] = 1 - bowls[pos+1];
                solve(bowls, pos+1, total+1, done);
                bowls[pos] = 1 - bowls[pos];
                bowls[pos+1] = 1 - bowls[pos+1];
            }
            else if (pos == 19){
                solve(bowls, pos+1, total, done);
            }
            else{
                bowls[pos] = 1 - bowls[pos];
                bowls[pos+1] = 1 - bowls[pos+1];
                bowls[pos+2] = 1 - bowls[pos+2];
                solve(bowls, pos+1, total+1,done);
                bowls[pos] = 1 - bowls[pos];
                bowls[pos+1] = 1 - bowls[pos+1];
                bowls[pos+2] = 1 - bowls[pos+2];
            }
        }
        else{
            solve(bowls, pos+1, total, done);
        }

    }

    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new FileReader("berries.in"));
//        PrintWriter pw = new PrintWriter(new FileWriter("berries.out"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] bowls = new int[20];
        for (int i=0; i < 20; i++){
            bowls[i] = Integer.parseInt(st.nextToken());
        }
        solve(bowls, 0, 0, false);
        System.out.println(ans);
    }
}
