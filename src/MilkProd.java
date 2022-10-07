import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class MilkProd {

    static class Interval implements Comparable<Interval>{
        int start;
        int end;
        int val;
        public Interval (int start, int end, int val){
            this.start = start;
            this.end = end;
            this.val = val;
        }

        @Override
        public int compareTo(Interval interval) {
            if (this.end == interval.end){
                return this.start - interval.start;
            }
            return this.end - interval.end;
        }
    }

    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new FileReader("berries.in"));
//        PrintWriter pw = new PrintWriter(new FileWriter("berries.out"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken());
        List<Interval> list = new ArrayList<>(m);
        List<Integer> pos = new ArrayList<>();
        list.add(new Interval(0,0,0));
        for (int i=0; i < m; i++){
            StringTokenizer st1 = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st1.nextToken());
            int end = Integer.parseInt(st1.nextToken());
            int val = Integer.parseInt(st1.nextToken());
            list.add(new Interval (start, end, val));
            if (start-r >= 0){
                pos.add(start-r);
            }
        }
        Collections.sort(list);
        Collections.sort(pos);
//        int[][] val = new int[m+1][n+1];
//        for (int i=1; i <= m; i++){
//            for (int j=1; j <= n; j++){
//                if (list.get(i).end > j){
//                    val[i][j] = val[i-1][j];
//                }
//                else {
//                    if (list.get(i).start - r >= 0){
//                        val[i][j] = Math.max(val[i-1][j], val[i-1][list.get(i).start - r] + list.get(i).val);
//                    }
//                    else {
//                        val[i][j] = Math.max(val[i-1][j], list.get(i).val);
//                    }
//                }
//            }
//        }
//        System.out.println(val[m][n]);
        int[] cur = new int[n+1];
        int[] last = new int[n+1];
        for (int i=1; i <= m; i++){
            for (int j : pos){
                if (list.get(i).end > j){
                    cur[j] = last[j];
                }
                else {
                    if (list.get(i).start - r >= 0){
                        cur[j] = Math.max(last[j], last[list.get(i).start - r] + list.get(i).val);
                    }
                    else {
                        cur[j] = Math.max(last[j], list.get(i).val);
                    }
                }
            }
            for (int j : pos){
                last[j] = cur[j];
            }
        }
        System.out.println(last[pos.get(pos.size()-1)]);
    }
}
