import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class QuickSortStanford {

    public static void QS(int[] arr, int left, int right){
        if (right - left <= 0){
            return;
        }
        int piv = arr[left];
        int i = left+1;
        for (int j=left+1; j <= right; j++){
            if (arr[j] < piv){
                int temp = arr[j];
                arr[j] = arr[i];
                arr[i] = temp;
                i++;
            }
        }
        int temp = arr[left];
        arr[left] = arr[i-1];
        arr[i-1] = temp;
        QS (arr, left, i-1);
        QS (arr, i, right);
    }

    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new FileReader("moobuzz.in"));
//        PrintWriter pw = new PrintWriter(new FileWriter("moobuzz.out"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int[] num = new int[n];
        StringTokenizer st1 = new StringTokenizer(br.readLine());
        for (int i=0; i < n; i++){
            num[i] = Integer.parseInt(st1.nextToken());
        }
        QS (num, 0, n-1);
        System.out.println(Arrays.toString(num));
    }

}
