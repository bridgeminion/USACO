import java.io.*;
import java.util.*;

public class Acowdemia {

    public static class Interval {
        int start;
        int end;
        int numAvailable;

        public Interval (int start, int end, int numAvailable){
            this.start = start;
            this.end = end;
            this.numAvailable = numAvailable;
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int[] c = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i=0; i < n; i++){
            c[i] = Integer.parseInt(st.nextToken());
        }
        List<Interval> l = new ArrayList<>();
        Collections.sort(l, new Comparator<Interval>() {
            @Override
            public int compare(Interval o1, Interval o2) {
                return 0;
            }
        });
        System.out.println("TEST");
    }
}
