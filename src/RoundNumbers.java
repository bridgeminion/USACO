import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class RoundNumbers {

    public static long choose (int a, int b){
        long ans = 1;
        for (int i=1; i <= a; i++){
            ans *= i;
        }
        for (int i=1; i <= b; i++){
            ans /= i;
        }
        for (int i=1;  i <= a-b; i++){
            ans /= i;
        }
        return ans;
    }

    static int countBits(int n)
    {
        int count = 0;
        while (n > 0) {
            count += n & 1;
            n >>= 1;
        }
        return count;
    }

    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new FileReader("mooyomooyo.in"));
//        PrintWriter pw = new PrintWriter(new FileWriter("mooyomooyo.out"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken())-1;
        int end = Integer.parseInt(st.nextToken());
        long ans_end = 0;
        long ans_start = 0;
        boolean first = true;
        int end_ones = 0;
        int end_start = 0;
        for (int i=32; i >= 0; i--){
            if (((end >> (i-1)) & 1) == 1){
                end_ones++;
                if (first){
                    end_start = i;
                    first = false;
                    for (int j=i-1; j >= 1; j--){
                        for (int k=j-1; k >= (j+1)/2; k--){
                            ans_end += choose(j-1, k);
                        }
                    }
                }
                else {
                    for (int j=(i+1)/2; j <= i; j++){
                        ans_end += choose (i, j);
                    }
                }
            }
        }
        first = true;
        int start_ones = 0;
        int start_start = 0;
        for (int i=32; i >= 0; i--){
            if (((start >> (i-1)) & 1) == 1){
                start_ones++;
                if (first){
                    start_start = i;
                    first = false;
                    for (int j=i-1; j >= 1; j--){
                        for (int k=j-1; k >= (j+1)/2; k--){
                            ans_start += choose(j-1, k);
                        }
                    }
                }
                else {
                    for (int j=(i+1)/2; j <= i; j++){
                        ans_end += choose (i, j);
                    }
                }
            }
        }
        if (end_ones <= (end_start-1)/2){
            ans_end++;
        }
        if (start_ones <= (start_start-1)/2){
            ans_start++;
        }
        System.out.println(ans_end-ans_start);
    }
}
