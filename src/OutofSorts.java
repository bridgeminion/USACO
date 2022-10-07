import java.io.*;
import java.util.*;

public class OutofSorts {

    static class Num implements Comparable<Num>{
        int val;
        int index;

        public Num (int val, int index){
            this.val = val;
            this.index = index;
        }

        @Override
        public int compareTo(Num num) {
            if (this.val == num.val){
                return this.index-num.index;
            }
            return this.val-num.val;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("sort.in"));
        PrintWriter pw = new PrintWriter(new FileWriter("sort.out"));
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        List<Num> a = new ArrayList<>();
        List<Num> sorted = new ArrayList<>(n);
        for (int i=0; i < n; i++){
            int temp = Integer.parseInt(br.readLine());
            a.add(new Num(temp, i));
            sorted.add(new Num(temp, i));
        }
        Collections.sort(sorted);
//        boolean sorted = false;
//        int ans = 0;
//        while (!sorted){
//            sorted = true;
//            ans++;
//            for (int i=0; i < n-1; i++){
//                if (a[i+1] < a[i]){
//                    int temp = a[i+1];
//                    a[i+1] = a[i];
//                    a[i] = temp;
//                    sorted = false;
//                }
//            }
//        }
        int ans = 0;
        for (int i=0; i < n; i++){
            int temp = Collections.binarySearch(sorted, a.get(i));
            ans = Math.max(ans, i-temp+1);
        }
        pw.println(ans);
        pw.close();
    }
}
