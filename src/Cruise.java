import java.io.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.StringTokenizer;

public class Cruise {

    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new FileReader("moop.in"));
//        PrintWriter pw = new PrintWriter(new FileWriter("moop.out"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int[] left = new int[n+1];
        int[] right = new int[n+1];
        int[] end = new int[n+1];
        for (int i=0; i < n; i++){
            StringTokenizer st1 = new StringTokenizer(br.readLine());
            left[i+1] = Integer.parseInt(st1.nextToken());
            right[i+1] = Integer.parseInt(st1.nextToken());
        }
        String s = br.readLine();
        for (int i=1; i <= n; i++){
            int cur = i;
            StringTokenizer st1 = new StringTokenizer(s);
            for (int j=0; j < m; j++){
                char temp = st1.nextToken().charAt(0);
                if (temp=='L'){
                    cur = left[cur];
                }
                else {
                    cur = right[cur];
                }
            }
            end[i] = cur;
        }
        int[] last = new int[n+1];
        int counter = 1;
        int cur = 1;
        while (last[cur] == 0){
            last[cur] = counter;
            cur = end[cur];
            counter++;
        }
        int c = counter - last[cur];
        int num = n + (k-n)%c;
        int ans = 1;
        for (int i=0; i < num; i++){
            ans = end[ans];
        }
        System.out.println(ans);
    }
}
