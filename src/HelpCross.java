import java.io.*;
import java.util.*;

public class HelpCross {

    static class Cow {
        int a;
        int b;
        boolean isCow;
        public Cow (int a, int b, boolean isCow){
            this.a = a;
            this.b = b;
            this.isCow = isCow;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("helpcross.in"));
        PrintWriter pw = new PrintWriter(new FileWriter("helpcross.out"));
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int c = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());
        List<Cow> list = new ArrayList<>();
        for (int i=0; i < c; i++){
            int temp = Integer.parseInt(br.readLine());
            list.add(new Cow(temp, temp, false));
        }
        for (int i=0; i < n; i++){
            StringTokenizer st1 = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st1.nextToken());
            int b = Integer.parseInt(st1.nextToken());
            list.add(new Cow (a, b, true));
        }
        Collections.sort(list, new Comparator<Cow>() {
            @Override
            public int compare(Cow o1, Cow o2) {
                if (o1.a == o2.a){
                    if (o1.isCow && !o2.isCow){
                        return -1;
                    }
                    else if (!o1.isCow && o2.isCow){
                        return 1;
                    }
                    else {
                        return o1.b - o2.b;
                    }
                }
                return o1.a - o2.a;
            }
        });
        int ans = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (Cow i : list){
            if (i.isCow){
                pq.add(i.b);
            }
            else {
                while (!pq.isEmpty() && pq.peek() < i.a){
                    pq.poll();
                }
                if (!pq.isEmpty()){
                    ans++;
                    pq.poll();
                }
            }
        }
        pw.println(ans);
        pw.close();
    }
}
