import java.io.*;
import java.util.StringTokenizer;

public class CowIDs {

    static class Pair {
        int a;
        long b;
        public Pair (int a, long b){
            this.a = a;
            this.b = b;
        }
    }

    public static long choose (int a, int b){
        if (b==0){
            return 1;
        }
        long ans = 1;
        for (int i=a-b+1; i <= a; i++){
            ans*=i;
        }
        for (int i=1; i <= b; i++){
            ans/=i;
        }
        return ans;
    }

    public static Pair f (long n, int num_ones){
        long cur = 0;
        int num_zeros = 0;
        while (cur < n){
            cur += choose(num_ones + num_zeros, num_ones);
            num_zeros++;
        }
        num_zeros--;
        cur -= choose(num_ones + num_zeros, num_ones);
        return new Pair (num_zeros, cur);
    }

    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new FileReader("mountains.in"));
//        PrintWriter pw = new PrintWriter(new FileWriter("mountains.out"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        StringBuilder ans = new StringBuilder();
        ans.append("1");
        int num_ones = k-1;
        Pair res = f (n, num_ones);
        int last = res.a;
        long cur = res.b;
        while (num_ones > 0){
            n -= cur;
            num_ones--;
            res = f (n, num_ones);
            int num_zeros = res.a;
            cur = res.b;
            for (int i=0; i < last - num_zeros; i++){
                ans.append("0");
            }
            ans.append("1");
            last = num_zeros;
        }
        for (int i=0; i < last; i++){
            ans.append("0");
        }
        System.out.println(ans);
    }
}
