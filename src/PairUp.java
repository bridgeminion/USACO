import java.io.*;
import java.util.*;

public class PairUp {

    static class Cows {
        int num;
        int milk;

        public Cows(int num, int milk) {
            this.num = num;
            this.milk = milk;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("pairup.in"));
        PrintWriter pw = new PrintWriter(new FileWriter("pairup.out"));
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        List<Cows> list = new ArrayList<>();
        for (int i=0; i < n; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());
            int milk = Integer.parseInt(st.nextToken());
            list.add(new Cows (num, milk));
        }
        Collections.sort(list, new Comparator<Cows>() {
            @Override
            public int compare(Cows o1, Cows o2) {
                return o1.milk - o2.milk;
            }
        });
        Deque<Cows> deque = new LinkedList<>(list);
        Cows cur = new Cows (0, 0);
        boolean front = true;
        int ans = 0;
        while (!deque.isEmpty()){
            if (cur.num == 0) {
                cur = deque.pollFirst();
                front = true;
            }
            else {
                if (front){
                    Cows temp = deque.pollLast();
                    ans = Math.max(ans, cur.milk + temp.milk);
                    if (cur.num >= temp.num) {
                        cur.num = cur.num - temp.num;
                    }
                    else {
                        cur.num = temp.num - cur.num;
                        cur.milk = temp.milk;
                        front = false;
                    }
                }
                else {
                    Cows temp = deque.pollFirst();
                    ans = Math.max(ans, cur.milk + temp.milk);
                    if (cur.num >= temp.num) {
                        cur.num = cur.num - temp.num;
                    }
                    else {
                        cur.num = temp.num - cur.num;
                        cur.milk = temp.milk;
                        front = true;
                    }
                }
            }
        }
        pw.println(ans);
        pw.close();
    }
}
