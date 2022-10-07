import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class BuyHay {

    static class Hay implements Comparable<Hay> {
        int amt;
        int price;
        public Hay (int amt, int price){
            this.amt = amt;
            this.price = price;
        }

        @Override
        public int compareTo(Hay hay) {
            double temp = ((double)this.amt/this.price - (double)hay.price/hay.price);
            if (temp==0){
                return (this.amt - hay.amt);
            }
            if (temp > 0){
                return 1;
            }
            return -1;
        }
    }

    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new FileReader("cowqueue.in"));
//        PrintWriter pw = new PrintWriter(new FileWriter("cowqueue.out"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int h = Integer.parseInt(st.nextToken());
        List<Hay> list = new ArrayList<>();
        for (int i=0; i < n; i++){
            StringTokenizer st1 = new StringTokenizer(br.readLine());
            list.add(new Hay(Integer.parseInt(st1.nextToken()), Integer.parseInt(st1.nextToken())));
        }
        int[] dp = new int[h+1];
        for (int i=0; i <= h; i++){
            dp[i] = 50000*5000;
        }
        dp[0] = 0;
        for (int i=0; i <= h; i++){
            int temp = i;
            for (Hay j : list){
                if (j.amt + i > h){
                    dp[h] = Math.min(dp[i] + j.price, dp[h]);
                }
                else {
                    dp[j.amt + i] = Math.min(dp[j.amt + i], dp[i] + j.price);
                }
            }
        }
        System.out.println(dp[h]);
    }
}
