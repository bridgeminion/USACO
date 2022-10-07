import java.io.*;
import java.util.*;

public class Shopping {

    static class Offer {
        int n;
        int[] amt;
        int p;

        public Offer(int n, int[] amt, int p) {
            this.n = n;
            this.amt = amt;
            this.p = p;
        }
    }

    static class State {
        int a;
        int b;
        int c;
        int d;
        int e;
        int key;
        public State(int a, int b, int c, int d, int e, int key) {
            this.a = a;
            this.b = b;
            this.c = c;
            this.d = d;
            this.e = e;
            this.key = key;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            State state = (State) o;
            return a == state.a &&
                    b == state.b &&
                    c == state.c &&
                    d == state.d &&
                    e == state.e;
        }

        @Override
        public int hashCode() {
            return Objects.hash(a, b, c, d, e);
        }
    }

    static int[] da = {1, 0, 0, 0, 0};
    static int[] db = {0, 1, 0, 0, 0};
    static int[] dc = {0, 0, 1, 0, 0};
    static int[] dd = {0, 0, 0, 1, 0};
    static int[] de = {0, 0, 0, 0, 1};

    static class Temp {
        boolean valid;
        int price;

        public Temp(boolean valid, int price) {
            this.valid = valid;
            this.price = price;
        }
    }

    public static boolean inbounds (int na, int nb, int nc, int nd, int ne, int[] total, int[] index){
        return total[index[0]] >= na && total[index[1]] >= nb && total[index[2]] >= nc && total[index[3]] >= nd && total[index[4]] >= ne;
    }

    public static boolean isAns (State cur, int[] total, int[] index){
        return total[index[0]] == cur.a && total[index[1]] == cur.b && total[index[2]] == cur.c && total[index[3]] == cur.d && total[index[4]] == cur.e;
    }

    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new FileReader("moop.in"));
//        PrintWriter pw = new PrintWriter(new FileWriter("moop.out"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        int s = Integer.parseInt(br.readLine());
        List<Offer> offers = new ArrayList<>(s);
        for (int i = 0; i < s; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int[] temp = new int[1000];
            for (int j=0; j < n; j++){
                int c = Integer.parseInt(st.nextToken());
                int k = Integer.parseInt(st.nextToken());
                temp[c] = k;
            }
            int p = Integer.parseInt(st.nextToken());
            offers.add(new Offer (n, temp, p));
        }
        int b = Integer.parseInt(br.readLine());
        int[] total = new int[1000];
        int[] price = new int[1000];
        int[] index = new int[5];
        for (int i=0; i < b; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int c = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
            int p = Integer.parseInt(st.nextToken());
            total[c] = k;
            price[c] = p;
            index[i] = c;
        }
        int[][][][][] val = new int[6][6][6][6][6];
        Set<State> visited = new HashSet<>();
        PriorityQueue<State> pq = new PriorityQueue<>(new Comparator<State>() {
            @Override
            public int compare(State o1, State o2) {
                if (o1.key == o2.key){
                    return 1;
                }
                return o1.key - o2.key;
            }
        });
        pq.add(new State (0, 0, 0, 0, 0, 0));
        while (true){
            State cur = pq.poll();
            visited.add(cur);
            if (isAns(cur, total, index)){
                System.out.println(cur.key);
                return;
            }
            for (int i=0; i < 5; i++){
                int na = cur.a + da[i];
                int nb = cur.b + db[i];
                int nc = cur.c + dc[i];
                int nd = cur.d + dd[i];
                int ne = cur.e + de[i];
                int key = cur.key + price[index[i]];
                if (inbounds (na, nb, nc, nd, ne, total, index)){
//                    Temp temp = isOffer(na, nb, nc, nd, ne, offers, index);
//                    if (temp.valid){
//                        key = Math.min(key, temp.price);
//                    }
                    State j = new State (na, nb, nc, nd, ne, key);
                    if (!visited.contains(j) || val[na][nb][nc][nd][ne] > key){
                        val[na][nb][nc][nd][ne] = key;
                        pq.add(j);
                        visited.add(j);
                    }
                }
            }
            for (Offer i : offers){
                int na = cur.a + i.amt[index[0]];
                int nb = cur.b + i.amt[index[1]];
                int nc = cur.c + i.amt[index[2]];
                int nd = cur.d + i.amt[index[3]];
                int ne = cur.e + i.amt[index[4]];
                int key = cur.key + i.p;
                if (inbounds (na, nb, nc, nd, ne, total, index)){
//                    Temp temp = isOffer(na, nb, nc, nd, ne, offers, index);
//                    if (temp.valid){
//                        key = Math.min(key, temp.price);
//                    }
                    State j = new State (na, nb, nc, nd, ne, key);
                    if (!visited.contains(j) || val[na][nb][nc][nd][ne] > key){
                        val[na][nb][nc][nd][ne] = key;
                        pq.add(j);
                        visited.add(j);
                    }
                }
            }
        }
    }
}
