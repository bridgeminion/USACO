import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

public class SelfishGrazing {

    static class Range implements Comparable<Range>{
        int start;
        int end;
        public Range(int start, int end){
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Range range) {
            if (this.end == range.end){
                return range.start-start;
            }
            return this.end - range.end;
        }
    }

//    // dp[k] = the largest number of the first k cows that can graze without any overlapping ranges.
//    public static int dp (int n, boolean[] visited, int[] val, int[] end, List<Range> list){
//        if (n<=0){
//            return 0;
//        }
//        if (visited[n]){
//            return val[n];
//        }
//        visited[n] = true;
//        int temp = Arrays.binarySearch(end, list.get(n-1).start);
//        if (temp < 0){
//            temp = (temp+1)*-1;
//        }
//        int no = dp (n-1, visited, val, end, list);
//        int yes = 1 + dp (temp, visited, val, end, list);
////        System.out.println(n + " " + temp);
//        val[n] = Math.max(no, yes);
//        return val[n];
//    }

    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new FileReader("berries.in"));
//        PrintWriter pw = new PrintWriter(new FileWriter("berries.out"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        List<Range> list = new ArrayList<>(n+1);
        int[] end = new int[n+1];
        for (int i=0; i < n; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            end[i] = e;
            list.add(new Range(s,e));
        }
        Collections.sort(list);
        Arrays.sort(end);
        int[] val = new int[n+1];
//        System.out.println(dp (n, new boolean[n+1], val, end, list));
        val[0] = 0;
        for (int i=0; i < n; i++){
            int temp = Arrays.binarySearch(end, list.get(i).start);
            if (temp < 0){
                temp = (temp+1)*-1;
            }
            val[i+1] = Math.max(val[i], 1 + val[temp]);
        }
        System.out.println(val[n]);
    }
}
