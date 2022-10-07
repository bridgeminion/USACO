import java.io.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class TestTaking {

    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new FileReader("clocktree.in"));
//        PrintWriter pw = new PrintWriter(new FileWriter("clocktree.out"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[] val = new int[m];
        for (int i=0; i < m; i++){
            int temp = Integer.parseInt(br.readLine());
            val[i] = temp;
        }
        Arrays.sort(val);
        int ans = 0;
        for (int i=0; i <= n; i++){
            int index = Arrays.binarySearch(val, n-i);
            if (index < 0){
                index = (index+1)*-1;
                if (index == 0){
                    ans = Math.max(ans, Math.max(i+val[index]-n, n-i-val[index]));
                }
                else if (index == m){
                    ans = Math.max(ans, Math.max(i+val[index-1]-n, n-i-val[index-1]));
                }
                else {
                    ans = Math.max(ans, Math.min(Math.max(i+val[index-1]-n, n-i-val[index-1]), Math.max(i+val[index]-n, n-i-val[index])));
                }
            }
        }
        System.out.println(ans);
    }
}
