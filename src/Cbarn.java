import java.io.*;
import java.util.StringTokenizer;

public class Cbarn {

    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new FileReader("berries.in"));
//        PrintWriter pw = new PrintWriter(new FileWriter("berries.out"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        int[] num = new int[n];
        for (int i=0; i < n; i++){
            num[i] = Integer.parseInt(br.readLine());
        }
        int min = 0;
        int start = -1;
        int sum = 0;
        for (int i=0; i < n; i++){
            sum += num[i];
            int temp = sum-i-1;
            if (temp < min){
                start = i;
                min = temp;
            }
        }
        if (start == -1){
            System.out.println(0);
            return;
        }
        long ans = 0;
        int last = 0;
        for (int i=0; i < n; i++){
            int index = (start+n-i)%n;
            if (num[index] == 0){
                last++;
            }
            else {
                int add = last;
                int sub = last - num[index];
                ans += (add*(add+1)*(2*add+1))/6 - (sub*(sub+1)*(2*sub+1))/6;
                last -= num[index] - 1;
            }
        }
        System.out.println(ans);
    }
}
