import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Typo {

    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new FileReader("cowqueue.in"));
//        PrintWriter pw = new PrintWriter(new FileWriter("cowqueue.out"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        String input = st.nextToken();
        int moreLeft = 0;
        int numRight = 0;
        int ans = 0;
        int last = 0;
        if (input.length() % 2 == 1){
            System.out.println(0);
            return;
        }
        for (int i=0; i < input.length(); i++){
            if (input.charAt(i) == '('){
                moreLeft++;
            }
            else{
                moreLeft--;
                numRight++;
            }
            if (moreLeft == -2){
                ans += numRight - 1;
                moreLeft = 0;
            }
            if (moreLeft <= 1){
                last = 0;
            }
            else if (input.charAt(i) == '('){
                last++;
            }
        }
        if (moreLeft > 0){
            ans = last;
        }
        System.out.println(ans);
        pw.close();
    }
}
