import java.io.*;
import java.util.*;

public class Homework {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("homework.in"));
        PrintWriter pw = new PrintWriter(new FileWriter("homework.out"));
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        List<Integer> list = new ArrayList<>();
        StringTokenizer st1 = new StringTokenizer(br.readLine());
        int sum = 0;
        for (int i=0; i < n; i++){
            int temp = Integer.parseInt(st1.nextToken());
            list.add(temp);
            sum += temp;
        }
        int[] min = new int[n];
        int curmin = list.get(n-1);
        for (int i=n-1; i >=0; i--){
            if (list.get(i) < curmin){
                curmin = list.get(i);
            }
            min[i] = curmin;
        }
        double max = 0;
        int cur = 0;
        List<Integer> ans = new ArrayList<>();
        for (int i=0; i < n-2; i++){
            cur+=list.get(i);
            double temp = (double)(sum - cur - min[i])/(n - i - 2);
            if (temp > max){
                max = temp;
                ans.clear();
                ans.add(i+1);
            }
            else if (temp == max){
                ans.add(i+1);
            }
        }
        Collections.sort(ans);
        for (int i : ans){
            pw.println(i);
        }
        pw.close();
    }
}
