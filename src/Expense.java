import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Expense {

    public static boolean test (int cap, int m, List<Integer> list){
        int cur = cap;
        int num = 1;
        for (int i : list){
            if (i <= cur){
                cur -= i;
            }
            else if (i > cap){
                return false;
            }
            else {
                cur = cap-i;
                num++;
                if (num > m) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new FileReader("moop.in"));
//        PrintWriter pw = new PrintWriter(new FileWriter("moop.out"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        List<Integer> list = new ArrayList<>();
        for (int i=0; i < n; i++){
            list.add(Integer.parseInt(br.readLine()));
        }
        int low = 0;
        int high = 1000000000;
        while (low <= high) {
            int mid = (high+low)/2;
            if (test (mid, m, list)){
                high = mid - 1;
            }
            else {
                low = mid + 1;
            }
        }
        System.out.println(low);
    }
}
