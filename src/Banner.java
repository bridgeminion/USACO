import java.io.*;
import java.util.StringTokenizer;

public class Banner {

    public static long gcd (long a, long b){
        if (b==0){
            return a;
        }
        return gcd (b, a%b);
    }

    public static boolean valid (long a, long b, int l1, int l2){
        if (gcd(a, b) != 1){
            return false;
        }
        return a*a+b*b >= Math.min(l1*l1, l2*l2) && a*a+b*b <= Math.max(l1*l1, l2*l2);
    }

    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new FileReader("mountains.in"));
//        PrintWriter pw = new PrintWriter(new FileWriter("mountains.out"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int w = Integer.parseInt(st.nextToken());
        int h = Integer.parseInt(st.nextToken());
        int l1 = Integer.parseInt(st.nextToken());
        int l2 = Integer.parseInt(st.nextToken());
        long ans = 0;
        if (l1==1){
            ans += w*(h+1) + h*(w+1);
        }
        for (int i = 1; i <= w; i++) {
            for (int j = 1; j <= h; j++) {
                if (valid(i, j, l1, l2)){
                    ans += 2*(w-i+1)*(h-j+1);
                }
            }
        }
        System.out.println(ans);
    }
}