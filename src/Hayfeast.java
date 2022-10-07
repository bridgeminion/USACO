import java.io.*;
import java.util.*;

public class Hayfeast {

    static class Hay {
        int f;
        int s;
        int index;
        public Hay (int f, int s, int index){
            this.f = f;
            this.s = s;
            this.index = index;
        }

//        @Override
//        public int compareTo(Hay o) {
//            if (this.s == o.s){
//                if (this.f == o.f) {
//                    if (this.index == o.index) {
//                        return 0;
//                    }
//                    return o.index - this.index;
//                }
//                return o.f - this.f;
//            }
//            return o.s - this.s;
//        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("hayfeast.in"));
        PrintWriter pw = new PrintWriter(new FileWriter("hayfeast.out"));
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        long m = Long.parseLong(st.nextToken());
        List<Hay> list = new ArrayList<>(n);
        for (int i=0; i < n; i++){
            StringTokenizer st1 = new StringTokenizer(br.readLine());
            int f = Integer.parseInt(st1.nextToken());
            int s = Integer.parseInt(st1.nextToken());
            list.add(new Hay (f, s, i));
        }
        Deque<Hay> deque = new LinkedList<>();
        TreeSet<Hay> pq = new TreeSet<>(new Comparator<Hay>() {
            @Override
            public int compare(Hay o1, Hay o2) {
                if (o1.s == o2.s) {
                    if (o1.f == o2.f) {
                        return o2.index - o1.index;
                    }
                    return o2.f - o1.f;
                }
                return o2.s - o1.s;
            }
        });
        int ans = Integer.MAX_VALUE;
        long sum = 0;
        for (Hay i : list){
            deque.addFirst(i);
            pq.add(i);
            sum += i.f;
            Hay removed = deque.peekLast();
            while (sum - removed.f >= m){
                sum -= removed.f;
                deque.pollLast();
                pq.remove(removed);
                removed = deque.peekLast();
            }
            if (sum >= m){
                ans = Math.min(ans, pq.first().s);
            }
        }
        pw.println(ans);
        pw.close();
    }
}
