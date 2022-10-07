import java.io.*;
import java.util.*;

public class Triangles {

    static class Coordinate{
        int x;
        int y;
        public Coordinate(int x, int y){
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Coordinate that = (Coordinate) o;
            return x == that.x &&
                    y == that.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }

    static class Pair{
        int a;
        int b;
        public Pair (int a, int b){
            this.a = a;
            this.b = b;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Pair pair = (Pair) o;
            return a == pair.a &&
                    b == pair.b;
        }

        @Override
        public int hashCode() {
            return Objects.hash(a, b);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("triangles.in"));
        PrintWriter pw = new PrintWriter(new FileWriter("triangles.out"));
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        Map<Integer, List<Integer>> samex = new HashMap<>();
        Map<Integer, List<Integer>> samey = new HashMap<>();
        Map<Pair, Long> valx = new HashMap<>();
        Map<Pair, Long> valy = new HashMap<>();
        List<Coordinate> list = new ArrayList<>();
        for (int i=0; i < n; i++){
            StringTokenizer st1 = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st1.nextToken());
            int y = Integer.parseInt(st1.nextToken());
            samex.putIfAbsent(x, new ArrayList<>());
            samey.putIfAbsent(y, new ArrayList<>());
            samex.get(x).add(y);
            samey.get(y).add(x);
            list.add(new Coordinate(x, y));
        }
//        int[][] valx = new int[20001][20001];
//        int[][] valy = new int[20001][20001];
        for (int x : samex.keySet()){
            Collections.sort(samex.get(x));
            Long temp = 0L;
            for (int j : samex.get(x)){
                temp += Math.abs(samex.get(x).get(0) - j);
            }
            valx.put(new Pair(x, samex.get(x).get(0)), temp);
//            valx[x+10000][samex.get(x).get(0)+10000] = temp;
            for (int j=1; j < samex.get(x).size(); j++){
                valx.put(new Pair(x, samex.get(x).get(j)), temp + ((long)(j*2-samex.get(x).size()))*(samex.get(x).get(j)-samex.get(x).get(j-1)));
//                valx[x+10000][samex.get(x).get(j)+10000] = temp + (j*2-samex.get(x).size())*(samex.get(x).get(j)-samex.get(x).get(j-1));
            }
        }
        for (int y : samey.keySet()){
            Collections.sort(samey.get(y));
            Long temp = 0L;
            for (int j : samey.get(y)){
                temp += Math.abs(samey.get(y).get(0) - j);
            }
            valy.put(new Pair(samey.get(y).get(0), y), temp);
//            valy[samey.get(y).get(0)+10000][y+10000] = temp;
            for (int j=1; j < samey.get(y).size(); j++){
                valy.put(new Pair(samey.get(y).get(j), y), temp + ((long)(j*2-samey.get(y).size()))*(samey.get(y).get(j)-samey.get(y).get(j-1)));
//                valy[samey.get(y).get(j)+10000][y+10000] = temp + (j*2-samey.get(y).size())*(samey.get(y).get(j)-samey.get(y).get(j-1));
            }
        }
        long ans = 0;
        for (Coordinate i : list){
            ans += valx.get(new Pair(i.x, i.y))*valy.get(new Pair(i.x, i.y));
//            ans += valx[i.x+10000][i.y+10000]*valy[i.x+10000][i.y+10000];
        }
        for (Map.Entry<Pair, Long> i : valx.entrySet()){
             if (i.getValue().compareTo(0L) < 0){
                 System.out.println("!!!!!!!!");
             }
        }
        pw.println((long)(ans%(Math.pow(10, 9) + 7)));
        pw.close();
    }
}
