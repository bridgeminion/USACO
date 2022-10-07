import java.io.*;
import java.util.*;

public class Wormholes {

    static int ans = 0;

    public static void printPairs (Map<Coordinate, Coordinate> pairs){
        for (Coordinate i : pairs.keySet()){
            System.out.println("Point 1");
            System.out.println("x = " + i.x);
            System.out.println("y = " + i.y);
            System.out.println("Point 2");
            System.out.println("x = " + pairs.get(i).x);
            System.out.println("y = " + pairs.get(i).y);
        }
    }

    static boolean result;
    static boolean done = false;

    public static void check (Coordinate cur, Map<Coordinate, Coordinate> pairs, Map<Integer, List<Integer>> y, Set<Coordinate> visited){
        if (done){
            return;
        }
        if (y.get(cur.y).size() == 0){
            result = false;
            done = true;
            return;
        }
        if (visited.contains(cur)){
            done = true;
            result = true;
            return;
        }
        visited.add(cur);
        Collections.sort(y.get(cur.y));
        int index = 0;
        while (index < y.get(cur.y).size() && cur.x >= y.get(cur.y).get(index)){
            index++;
        }
        if (index >= y.get(cur.y).size()){
            done = true;
            result = false;
            return;
        }
        Coordinate next = new Coordinate(y.get(cur.y).get(index), cur.y);
        check(pairs.get(next), pairs, y, visited);
    }

    public static void recurse (int index, Coordinate[] list, int n, boolean[] visited, Map<Coordinate, Coordinate> pairs, Map<Integer, List<Integer>> y){
        if (index == n){
            printPairs(pairs);
            System.out.println();
            for (Coordinate i : pairs.keySet()){
                Set<Coordinate> v = new HashSet<>();
                done = false;
                check(i, pairs, y, v);
                if (result){
                    System.out.println("cur x = "  + i.x);
                    System.out.println("cur y = "  + i.y);
                    ans++;
                    break;
                }
            }
            return;
        }
        int i;
        for (i=0; i < n; i++){
            if (!visited[i]){
                break;
            }
        }
        for (int j=i+1; j < list.length; j++){
            if (!visited[i] && !visited[j]){
                visited[i] = true;
                visited[j] = true;
                pairs.put(list[i], list[j]);
                pairs.put(list[j], list[i]);
                recurse(index + 2, list, n, visited, pairs, y);
                pairs.remove(list[i]);
                pairs.remove(list[j]);
                visited[i] = false;
                visited[j] = false;
            }
        }
    }

    public static void main(String[] args) throws IOException {

//        BufferedReader br = new BufferedReader(new FileReader("measurement.in"));
//        PrintWriter pw = new PrintWriter(new FileWriter("measurement.out"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        Coordinate[] list = new Coordinate[n];
        Map<Integer, List<Integer>> y = new HashMap<>();
        for (int i=0; i < n; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            list[i] = new Coordinate(x, y1);
            y.putIfAbsent(y1, new ArrayList<>());
            y.get(y1).add(x);
        }
        recurse(0, list, n, new boolean[n], new HashMap<>(), y);
        System.out.println(ans);
    }
}
