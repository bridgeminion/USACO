import java.io.*;
import java.util.*;

public class Allowance {

    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new FileReader("haybales.in"));
//        PrintWriter pw = new PrintWriter(new FileWriter("haybales.out"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        Map<Integer, Integer> amt = new HashMap<>();
        List<Integer> amts = new ArrayList<>(n);
        long total = 0;
        for (int i=0; i < n; i++){
            StringTokenizer st1 = new StringTokenizer(br.readLine());
            int val = Integer.parseInt(st1.nextToken());
            int num = Integer.parseInt(st1.nextToken());
            amt.put(val, num);
            amts.add(val);
            total += (long)val*num;
        }
        int ans = 0;
        for (int i : amts){
            if (i >= c){
                ans += amt.get(i);
                total -= (long)amt.get(i)*i;
                amt.put(i, 0);
            }
        }
        amts.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2-o1;
            }
        });
        while (total >= c){
            int temp = c;
            int smallest = Integer.MAX_VALUE;
            for (int i : amts){
                if (i <= temp && amt.get(i) > 0){
                    int num = Math.min(temp/i, amt.get(i));
                    temp -= i*num;
                    amt.put(i, amt.get(i) - num);
                    total -= i*num;
                }
                if (i < smallest && amt.get(i) > 0){
                    smallest = i;
                }
            }
            if (temp > 0){
                amt.put(smallest, amt.get(smallest)-1);
                total -= smallest;
            }
            ans++;
        }
        System.out.println(ans);
    }
}
