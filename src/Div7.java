import java.io.*;
import java.util.StringTokenizer;

public class Div7 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("div7.in"));
        PrintWriter pw = new PrintWriter(new FileWriter("div7.out"));
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int[] mod = new int[n];
        int[] prefix = new int[n];
        for (int i=0; i < n; i++){
            StringTokenizer st1 = new StringTokenizer(br.readLine());
            mod[i] = Integer.parseInt(st1.nextToken()) % 7;
        }
        int counter = 0;
        for (int i=0; i < n; i++){
            counter += mod[i];
            counter = counter % 7;
            prefix[i] = counter;
        }
        int ans = 0;
        for (int i=0; i < 7; i++){
            int start = -1;
            int end = -1;
            for (int j=0; j < n; j++){
                if (prefix[j] == i){
                    start = j;
                    break;
                }
            }
            for (int j = n-1; j >= 0; j--){
                if (prefix[j] == i){
                    end = j;
                    break;
                }
            }
            int length = end - start;
            if (length > ans){
                ans = length;
            }
        }
        pw.println(ans);
        pw.close();
    }
}
