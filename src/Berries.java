import java.io.*;
import java.util.*;

public class Berries {

    public static int test (int[] berry, int num_tree, int num_basket, int k, int[] temp){
        for (int i=0; i < num_tree; i++){
            temp[i] = berry[i];
        }
        int num_full = 0;
        for (int i=0; i < num_tree; i++){
            num_full += temp[i]/k;
            temp[i] = temp[i]%k;
        }
        if (num_full >= num_basket){
            return num_basket/2 * k;
        }
        Arrays.sort(temp);
        int ans = (num_full-num_basket/2)*k;
        for (int i=0; i < num_basket-num_full; i++){
            if (num_tree-1-i < 0){
                break;
            }
            ans += temp[num_tree-1-i];
        }
        return ans;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("berries.in"));
        PrintWriter pw = new PrintWriter(new FileWriter("berries.out"));
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int num_tree = Integer.parseInt(st.nextToken());
        int num_basket = Integer.parseInt(st.nextToken());
        int[] berry = new int[num_tree];
        int max = 0;
        StringTokenizer st1 = new StringTokenizer(br.readLine());
        for (int i=0; i < num_tree; i++){
            berry[i] = Integer.parseInt(st1.nextToken());
            max = Math.max(max, berry[i]);
        }
        int ans = 0;
        for (int i=1; i <= max; i++){
            ans = Math.max(ans, test(berry, num_tree, num_basket, i, new int[num_tree]));
        }
        pw.println(ans);
        pw.close();
    }
}
