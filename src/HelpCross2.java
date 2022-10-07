import java.io.*;
import java.util.*;

public class HelpCross2 {

    static class Cow {
        int a;
        int b;
        public Cow (int a, int b){
            this.a = a;
            this.b = b;
        }
    }

    static class TreeSetWithDuplicate<T extends Comparable<T>> {
        private final TreeMap<T, Integer> map;

        public TreeSetWithDuplicate() {
            map = new TreeMap<>();
        }

        public T floorKey(T t) {
            return map.floorKey(t);
        }

        public T ceilingKey(T t) {
            return map.ceilingKey(t);
        }

        public void remove(T t) {
            if (map.get(t) == 1) {
                map.remove(t);
            } else {
                map.put(t, map.get(t) - 1);
            }
        }

        public void insert(T t) {
            map.putIfAbsent(t, 0);
            map.put(t, map.get(t) + 1);
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
        TreeSetWithDuplicate<Integer> chickens = new TreeSetWithDuplicate<>();
        List<Cow> cows = new ArrayList<>();
        for (int i=0; i < c; i++){
            int temp = Integer.parseInt(br.readLine());
            chickens.insert(temp);
        }
        for (int i=0; i < n; i++){
            StringTokenizer st1 = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st1.nextToken());
            int b = Integer.parseInt(st1.nextToken());
            cows.add(new Cow (a, b));
        }
        Collections.sort(cows, new Comparator<Cow>() {
            @Override
            public int compare(Cow o1, Cow o2) {
                if (o1.b == o2.b){
                    return o1.a - o2.a;
                }
                return o1.b - o2.b;
            }
        });
        int ans = 0;
        for (Cow i : cows){
            Integer cur = chickens.ceilingKey(i.a);
            if (cur != null && cur <= i.b){
                ans++;
                chickens.remove(cur);
            }
        }
        pw.println(ans);
        pw.close();
    }
}
