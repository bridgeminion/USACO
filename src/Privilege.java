import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Privilege {

    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new FileReader("clocktree.in"));
//        PrintWriter pw = new PrintWriter(new FileWriter("clocktree.out"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int[] cows = new int[n];
        int[] sorted = new int[n];
        for (int i=0; i < n; i++){
            cows[i] = Integer.parseInt(br.readLine());
            sorted[i] = cows[i];
        }
        Arrays.sort(sorted);
        int ans = 0;
        for (int i=0; i < n; i++){
            if (cows[i] != sorted[i]){
                boolean done = false;
                for (int j=i+1; j < n; j++){
                    if (cows[j] != sorted[j] && cows[j] == sorted[i] && cows[i] == sorted[j]){
                        ans++;
                        int temp = cows[i];
                        cows[i] = cows[j];
                        cows[j] = temp;
                        done = true;
                        break;
                    }
                }
                if (!done){
                    for (int j=i+1; j < n; j++){
                        if (cows[j] != sorted[j] && cows[j] == sorted[i]){
                            ans++;
                            int temp = cows[i];
                            cows[i] = cows[j];
                            cows[j] = temp;
                            break;
                        }
                    }
                }
            }
        }
        System.out.println(ans);
    }
}
