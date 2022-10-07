import java.io.*;
import java.util.*;

public class WormHoleSort {

    static class Wormhole{
        int a;
        int b;
        int val;
        public Wormhole(int a, int b, int val){
            this.a = a;
            this.b = b;
            this.val = val;
        }
    }

    static class Out{
        int cur;
        int des;
        public Out(int cur, int des){
            this.cur = cur;
            this.des = des;
        }
    }
    public static boolean test(List<Out> unsorted, List<Wormhole> worm, int x){
        Map<Integer, Set<Integer>> adj = new HashMap<>();
        for (Wormhole i : worm){
            if (i.val >= x){
                adj.putIfAbsent(i.a, new HashSet<>());
                adj.putIfAbsent(i.b, new HashSet<>());
                adj.get(i.a).add(i.b);
                adj.get(i.b).add(i.a);
                for (int j : adj.get(i.a)){
                    adj.get(i.b).add(j);
                }
                for (int j : adj.get(i.b)){
                    adj.get(i.a).add(j);
                }
            }
        }
        for (Out i : unsorted){
            if ((adj.containsKey(i.cur) && !adj.get(i.cur).contains(i.des)) || (adj.containsKey(i.des) && !adj.get(i.des).contains(i.cur))){
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("wormsort.in"));
        PrintWriter pw = new PrintWriter(new FileWriter("wormsort.out"));
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int num_cows = Integer.parseInt(st.nextToken());
        int num_holes = Integer.parseInt(st.nextToken());
        int[] pos = new int[num_cows];
        StringTokenizer st1 = new StringTokenizer(br.readLine());
        List<Out> unsorted = new ArrayList<>();
        for (int i=0; i < num_cows; i++){
            pos[i] = Integer.parseInt(st1.nextToken());
            if (pos[i] != i+1){
                unsorted.add(new Out(pos[i], i+1));
            }
        }
        if (unsorted.size() == 0){
            pw.println(-1);
            pw.close();
            return;
        }
        List<Wormhole> worm = new ArrayList<>();
        for (int i=0; i < num_holes; i++){
            StringTokenizer st2 = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st2.nextToken());
            int b = Integer.parseInt(st2.nextToken());
            int val = Integer.parseInt(st2.nextToken());
            worm.add(new Wormhole(a, b, val));
        }
        int low = 0;
        int high = 1000000001;
        while (low <= high) {
            int mid = (high+low)/2;
            if (test(unsorted, worm, mid)){
                System.out.println("YEA");
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        pw.println(low);
        pw.close();
    }
}
