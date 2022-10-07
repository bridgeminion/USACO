import java.io.*;
import java.util.StringTokenizer;

public class Stamps2 {

    public static void dp (int x, boolean[] valid, int[] stamps, int index, int cur, int k){
        if (cur==k){
            return;
        }
        for (int i=index; i < stamps.length; i++){
            valid[x+stamps[i]] = true;
            dp (x+stamps[i], valid, stamps, i, cur+1, k);
        }
    }

    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new FileReader("censor.in"));
//        PrintWriter pw = new PrintWriter(new FileWriter("censor.out"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int k = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());
        int[] stamps = new int[n];
        StringTokenizer st1 = new StringTokenizer(br.readLine());
        for (int i=0; i < n; i++){
            int temp = Integer.parseInt(st1.nextToken());
            stamps[i] = temp;
        }
//        boolean[] valid =  new boolean[2000*k + 1];
//        dp (0, valid, stamps, 0, 0, k);
//        int max = 0;
//        int cur = 0;
//        for (int i=1; i <= 2000*k; i++){
//            if (valid[i]){
//                cur++;
//            }
//            else {
//                cur = 0;
//            }
//            max = Math.max(max, cur);
//        }
        int big = 2000*k+2;
        int[] min = new int[big];
        for (int i=0; i < big; i++){
            min[i] = big;
        }
        for (int i : stamps){
            min[i] = 1;
        }
        for (int i=0; i < n; i++){
            for (int j=0; j < big; j++){
                if (j+stamps[i] < big){
                    min[j+stamps[i]] = Math.min(min[j+stamps[i]], min[j] + 1);
                }
            }
        }
        int counter = 0;
        for (int i=1; i < big; i++){
            if (min[i] > k){
                break;
            }
            counter++;
        }
        System.out.println(counter);
    }
}
