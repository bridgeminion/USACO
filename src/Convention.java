import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Convention {

    static int ans = 1000000001;

    public static void binarySearch (int low, int high, int[] arrive, int num_cow, int bus, int cap){
        while (low <= high) {
            int mid = (high+low)/2;
            if (check(mid, arrive, num_cow, bus, cap)){
                if (mid < ans){
                    ans = mid;
                }
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
    }

    public static boolean check (int time, int[] arrive, int num_cow, int bus, int cap){
        int cur;
        int j = 0;
        int num = 0;
        for (int i=0; i < bus; i++){
            cur = arrive[j]+time;
            while (num < cap && j < num_cow && arrive[j] <= cur){
                j++;
                num++;
            }
            if (j >= num_cow){
                return true;
            }
            num = 0;
        }
        return false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("convention.in"));
        PrintWriter pw = new PrintWriter(new FileWriter("convention.out"));
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int num_cow = Integer.parseInt(st.nextToken());
        int num_bus = Integer.parseInt(st.nextToken());
        int cap = Integer.parseInt(st.nextToken());
        int[] arrive = new int[num_cow];
        StringTokenizer st1 = new StringTokenizer(br.readLine());
        for (int i=0; i < num_cow; i++){
            arrive[i] = Integer.parseInt(st1.nextToken());
        }
        Arrays.sort(arrive);
        binarySearch(0, (int)Math.pow(10, 9), arrive, num_cow, num_bus, cap);
//        check(4, arrive, num_cow, num_bus, cap);
//        for (int i=1; i < 14; i++){
//            System.out.println(i);
//            System.out.println(check(i, arrive, num_cow, num_bus, cap));
//        }
        pw.println(ans);
        pw.close();
    }
}
