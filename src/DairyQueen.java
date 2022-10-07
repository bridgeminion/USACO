import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class DairyQueen {

    static int ans = 0;

    public static void recurse(int total, int cur, List<Integer> coins, int index){
        if (cur == total){
            ans++;
            return;
        }
        if (cur > total){
            return;
        }
        for (int i=index; i < coins.size(); i++){
            int temp = coins.get(i);
            recurse(total, cur + temp, coins, i);
        }
    }

    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new FileReader("berries.in"));
//        PrintWriter pw = new PrintWriter(new FileWriter("berries.out"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int total = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());
        List<Integer> coins = new ArrayList<>();
        for (int i = 0; i < n; i++){
            StringTokenizer st1 = new StringTokenizer(br.readLine());
            coins.add(Integer.parseInt(st1.nextToken()));
        }
        recurse(total, 0, coins, 0);
        System.out.println(ans);
    }
}
