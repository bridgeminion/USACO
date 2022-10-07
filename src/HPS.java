import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class HPS {

    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new FileReader("haybales.in"));
//        PrintWriter pw = new PrintWriter(new FileWriter("haybales.out"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int[] h = new int[n];
        int[] p = new int[n];
        int[] s = new int [n];
        StringTokenizer st2 = new StringTokenizer(br.readLine());
        String input = st2.nextToken();
        if (input.equals("H")){
            h[0] = 1;
        }
        else if (input.equals("P")){
            p[0] = 1;
        }
        else{
            s[0] = 1;
        }
        for (int i=1; i < n; i++){
            StringTokenizer st1 = new StringTokenizer(br.readLine());
            String cur = st1.nextToken();
            if (cur.equals("H")){
                h[i] = h[i-1] +  1;
                p[i] = p[i-1];
                s[i] = s[i-1];
            }
            else if (cur.equals("P")){
                p[i] = p[i-1] + 1;
                h[i] = h[i-1];
                s[i] = s[i-1];
            }
            else{
                s[i] = s[i-1] + 1;
                h[i] = h[i-1];
                p[i] = p[i-1];
            }
        }
        int[] temp = new int[6];
        int max = 0;
        int all_h = h[n-1];
        int all_s = s[n-1];
        int all_p = p[n-1];
        for (int i=1; i < n; i++){
            temp[0] = h[i-1] + s[n-1] - s[i-1];
            temp[1] = h[i-1] + p[n-1] - p[i-1];
            temp[2] = s[i-1] + h[n-1] - h[i-1];
            temp[3] = s[i-1] + p[n-1] - p[i-1];
            temp[4] = p[i-1] + h[n-1] - h[i-1];
            temp[5] = p[i-1] + s[n-1] - s[i-1];
            for (int j=0; j < 6; j++){
                if(temp[j] > max){
                    max = temp[j];
                }
            }
        }
        if (all_h > max){
            max = all_h;
        }
        if (all_p > max){
            max = all_p;
        }
        if (all_s > max){
            max = all_s;
        }
        pw.print(max);
        pw.close();
    }
}
