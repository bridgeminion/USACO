import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class ChocolateEating {

    public static boolean check (List<Integer> list, int n, int d, Long ans){
        int day = 0;
        Long cur = 0L;
        int index = 0;
        while (index < n){
            int i = list.get(index);
            int temp = cur.compareTo(ans);
            if (temp < 0){
                cur += i;
                index++;
            }
            else {
                cur /= 2;
                day++;
            }
            if (day >= d){
                return true;
            }
        }
        while (cur >= ans){
            day++;
            cur/=2;
        }
        return day >= d;
    }

    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new FileReader("haybales.in"));
//        PrintWriter pw = new PrintWriter(new FileWriter("haybales.out"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());
        List<Integer> list = new ArrayList<>(n);
        for (int i=0; i < n; i++){
            list.add(Integer.parseInt(br.readLine()));
        }
        long low = 0;
        long high = Long.MAX_VALUE;
        long ans = 0;
        while (low <= high) {
            long mid = (low+high)/2;
            if (check (list, n, d, mid)){
                ans = Math.max(ans, mid);
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        System.out.println(ans);
    }
}
