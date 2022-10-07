import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SkewedSort {

    static int dist = 0;

    public static int[] ssort (int[] arr, int start, int end){
        if (end-start == 1){
            int[] cur = new int[1];
            cur[0] = arr[start];
            return cur;
        }
        int mid = (start + end + 1)/2;
        int[] a = ssort(arr, start, mid);
        int[] b = ssort(arr, mid, end);
        int[] cur = new int[end-start];
        if (a[0] > b[0]){
            dist += (end-start)*(end-start)/2;
            for (int i=start; i < mid; i++){
                cur[i-start] = b[i-start];
                cur[i-start + (end-start)/2] = a[i-start];
            }
        }
        else {
            for (int i=start; i < mid; i++){
                cur[i-start] = a[i-start];
                cur[i-start + (end-start)/2] = b[i-start];
            }
        }
        return cur;
    }

    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new FileReader("moobuzz.in"));
//        PrintWriter pw = new PrintWriter(new FileWriter("moobuzz.out"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int[] num = new int[(int)Math.pow(2, n)];
        for (int i=0; i < Math.pow(2, n); i++){
            num[i] = Integer.parseInt(br.readLine());
        }
        int[] ans = ssort(num, 0, num.length);
        System.out.println(dist);
        for (int i : ans){
            System.out.println(i);
        }
    }
}
