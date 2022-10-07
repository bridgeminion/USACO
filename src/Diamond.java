import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Diamond {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("diamond.in"));
        PrintWriter pw = new PrintWriter(new FileWriter("diamond.out"));
//       BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//       PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        int[] number = new int[10001];
        int n, k;
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        for (int i=0; i < n; i++){
            StringTokenizer st1 = new StringTokenizer(br.readLine());
            int temp = Integer.parseInt(st1.nextToken());
            number[temp]++;
        }
        int ans = 0;
        int index = 0;
        for (int i=0; i < 10001 - k; i++){
            int cur_num = 0;
            for (int j=i; j <= i + k; j++){
                cur_num += number[j];
            }
            if (cur_num > ans){
                ans = cur_num;
            }
        }
        pw.println(ans);
        pw.close();
    }
}
