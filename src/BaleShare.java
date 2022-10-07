import java.io.*;
import java.util.StringTokenizer;

public class BaleShare {

    static int ans = 20000;

    public static void split(int bale1, int bale2, int bale3, int[] num, int index, int cur_min){
        if (index >= num.length){
            if (cur_min < ans){
                ans = cur_min;
            }
            return;
        }
        if (cur_min > ans){
            return;
        }
        int temp1 = Math.max(bale1 + num[index], bale2);
        int min1 = Math.max(temp1, bale3);
        split(bale1 + num[index], bale2, bale3, num, index+1, min1);
        int temp2 = Math.max(bale1, bale2 + num[index]);
        int min2 = Math.max(temp2, bale3);
        split(bale1, bale2 + num[index], bale3, num, index+1, min2);
        int temp3 = Math.max(bale1, bale2);
        int min3 = Math.max(temp3, bale3 + num[index]);
        split(bale1, bale2, bale3 + num[index], num, index+1, min3);

    }

    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new FileReader("moobuzz.in"));
//        PrintWriter pw = new PrintWriter(new FileWriter("moobuzz.out"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int[] num = new int[n];
        for (int i=0; i < n; i++){
            StringTokenizer st1 = new StringTokenizer(br.readLine());
            num[i] = Integer.parseInt(st1.nextToken());
        }
        split(0, 0, 0, num, 0, 0);
        System.out.println(ans);
    }
}
