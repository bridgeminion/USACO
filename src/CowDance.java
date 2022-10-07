import java.io.*;
import java.util.*;

public class CowDance {

    static int ans = 10001;
    static boolean[] visited = new boolean[10001];

    public static void binarySearch (int low, int high, int n, int[] time, int max){
        while (low <= high){
            int mid = (low + high)/2;
            if (check(mid, n, time, max)){
                ans = Math.min(ans, mid);
                high = mid-1;
            }
            else {
                low = mid+1;
            }
        }
//        int mid = (low + high)/2;
//        if (visited[mid]){
//            return;
//        }
//        visited[mid] = true;
//        if (check(mid, n, time, max)){
//            if (ans > mid){
//                ans = mid;
//                binarySearch(low, mid-1, n, time, max);
//            }
//        }
//        else {
//            if (ans > (mid+1+high)/2)
//            binarySearch(mid+1, high, n, time, max);
//        }
    }

    public static boolean check(int k, int n, int[] time, int max){
        PriorityQueue<Integer> q = new PriorityQueue<>();
        if (k >= n){
            return true;
        }
        for (int i=0; i < k; i++){
            q.add(time[i]);
        }
        int index = k;
        int cur;
        while (!q.isEmpty()){
            cur = q.poll();
            if (cur > max){
                return false;
            }
            if (index <= n-1){
                q.add(time[index] + cur);
                index++;
            }
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("cowdance.in"));
        PrintWriter pw = new PrintWriter(new FileWriter("cowdance.out"));
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int max = Integer.parseInt(st.nextToken());
        int[] time = new int[n];
        for (int i=0; i < n; i++){
            time[i] = Integer.parseInt(br.readLine());
        }
        check(5, n, time, max);
        binarySearch(0, 10000, n, time, max);
        pw.println(ans);
        pw.close();
    }
}
