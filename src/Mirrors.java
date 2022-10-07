import java.io.*;
import java.util.*;

public class Mirrors {

    static boolean done = false;
    static boolean works = false;

    public static void Sim (int x, int y, int dir, Map<Integer, List<Integer>> xmap, Map<Integer, List<Integer>> ymap, Map<Coordinate, Integer> grid, Map<Coordinate, Integer> visited){
        if (done){
            return;
        }
        if (grid.get(new Coordinate(x, y)) == 3){
            works = true;
            done = true;
            return;
        }
        if (visited.get(new Coordinate(x, y)) > 2){
            done = true;
            return;
        }
        visited.put(new Coordinate(x, y), visited.get(new Coordinate(x, y)) + 1);
        if (dir == 0){
            int i = 0;
            while (i < xmap.get(x).size() && xmap.get(x).get(i) <= y){
                i++;
            }
            if (i == xmap.get(x).size()){
                done = true;
                return;
            }
            int newy = xmap.get(x).get(i);
            if (grid.get(new Coordinate(x, newy)) == 1){
                dir = 1;
                Sim (x, newy, dir, xmap, ymap, grid, visited);
            }
            else if (grid.get(new Coordinate(x, newy)) == 2){
                dir = 3;
                Sim (x, newy, dir, xmap, ymap, grid, visited);
            }
            else {
                works = true;
                done = true;
            }
        }
        else if (dir == 1){
            int i = 0;
            while (i < ymap.get(y).size() && ymap.get(y).get(i) <= x){
                i++;
            }
            if (i == ymap.get(y).size()){
                done = true;
                return;
            }
            int newx = ymap.get(y).get(i);
            if (grid.get(new Coordinate(newx, y)) == 1){
                dir = 0;
                Sim (newx, y, dir, xmap, ymap, grid, visited);
            }
            else if (grid.get(new Coordinate(newx, y)) == 2){
                dir = 2;
                Sim (newx, y, dir, xmap, ymap, grid, visited);
            }
            else {
                works = true;
                done = true;
            }
        }
        else if (dir == 2){
            int i = xmap.get(x).size()-1;
            while (i >= 0 && xmap.get(x).get(i) >= y){
                i--;
            }
            if (i < 0){
                done = true;
                return;
            }
            int newy = xmap.get(x).get(i);
            if (grid.get(new Coordinate(x, newy)) == 1){
                dir = 3;
                Sim (x, newy, dir, xmap, ymap, grid, visited);
            }
            else if (grid.get(new Coordinate(x, newy)) == 2){
                dir = 1;
                Sim (x, newy, dir, xmap, ymap, grid, visited);
            }
            else {
                works = true;
                done = true;
            }
        }
        else {
            int i = ymap.get(y).size()-1;
            while (i >= 0 && ymap.get(y).get(i) >= x){
                i--;
            }
            if (i < 0){
                done = true;
                return;
            }
            int newx = ymap.get(y).get(i);
            if (grid.get(new Coordinate(newx, y)) == 1){
                dir = 2;
                Sim (newx, y, dir, xmap, ymap, grid, visited);
            }
            else if (grid.get(new Coordinate(newx, y)) == 2){
                dir = 0;
                Sim (newx, y, dir, xmap, ymap, grid, visited);
            }
            else {
                works = true;
                done = true;
            }
        }
    }

    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new FileReader("cowqueue.in"));
//        PrintWriter pw = new PrintWriter(new FileWriter("cowqueue.out"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        Map<Coordinate, Integer> grid = new HashMap<>();
        Map<Integer, List<Integer>> xmap = new HashMap<>();
        Map<Integer, List<Integer>> ymap = new HashMap<>();
        Map<Integer, Coordinate> index = new HashMap<>();
        Map<Coordinate, Integer> visited = new HashMap<>();
        xmap.putIfAbsent(0, new ArrayList<>());
        ymap.putIfAbsent(0, new ArrayList<>());
        grid.put(new Coordinate(a, b), 3);
        grid.put(new Coordinate(0, 0), 0);
        xmap.putIfAbsent(a, new ArrayList<>());
        xmap.get(a).add(b);
        ymap.putIfAbsent(b, new ArrayList<>());
        ymap.get(b).add(a);
        for (int i=0; i < n; i++){
            StringTokenizer st1 = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st1.nextToken());
            int y = Integer.parseInt(st1.nextToken());
            char temp = st1.nextToken().charAt(0);
            if (temp == '/'){
                grid.put(new Coordinate(x, y), 1);
                xmap.putIfAbsent(x, new ArrayList<>());
                xmap.get(x).add(y);
                ymap.putIfAbsent(y, new ArrayList<>());
                ymap.get(y).add(x);

            }
            else{
                grid.put(new Coordinate(x, y), 2);
                xmap.putIfAbsent(x, new ArrayList<>());
                xmap.get(x).add(y);
                ymap.putIfAbsent(y, new ArrayList<>());
                ymap.get(y).add(x);
            }
            index.put(i+1, new Coordinate(x, y));
            visited.put(new Coordinate(x, y), 0);
        }
        for (int key : xmap.keySet()) {
            Collections.sort(xmap.get(key));
        }
        for (int key : ymap.keySet()) {
            Collections.sort(ymap.get(key));
        }
        visited.put(new Coordinate(0, 0), 0);
        Sim (0, 0, 1, xmap, ymap, grid, visited);
        done = false;
        visited.replaceAll((i, v) -> 0);
        if (works){
            System.out.println(0);
            return;
        }
        for (int i=1; i <= n; i++){
            grid.put(index.get(i), 3 - grid.get(index.get(i)));
            Sim (0, 0, 1, xmap, ymap, grid, visited);
            visited.replaceAll((j, v) -> 0);
            done = false;
            if (works){
                System.out.println(i);
                return;
            }
            grid.put(index.get(i), 3 - grid.get(index.get(i)));
        }
        System.out.println(-1);
    }
}
