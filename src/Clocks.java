import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Clocks {

    static boolean done = false;

    static int[] clocks = new int[9];
    static boolean[][][][][][][][][] visited = new boolean[4][4][4][4][4][4][4][4][4];

    public static void recurse (int[] clocks, List<List<Integer>> moves, int n, int index, List<Integer> cur){
        if (done){
            return;
        }
        if (check(clocks)){
//            System.out.println(n);
            for (int i : cur){
                System.out.print(i + " ");
            }
            done = true;
        }
        if (visited[clocks[0]][clocks[1]][clocks[2]][clocks[3]][clocks[4]][clocks[5]][clocks[6]][clocks[7]][clocks[8]]){
            return;
        }
        visited[clocks[0]][clocks[1]][clocks[2]][clocks[3]][clocks[4]][clocks[5]][clocks[6]][clocks[7]][clocks[8]] = true;
        for (int i=index; i < 9; i++){
            for (int j : moves.get(i)){
                clocks[j] = (clocks[j] + 1)%4;
            }
            cur.add(i+1);
            recurse(clocks, moves, n+1, i, cur);
            cur.remove(cur.size()-1);
            for (int j : moves.get(i)){
                clocks[j] = (clocks[j] + 3)%4;
            }
        }
    }

    public static boolean check (int[] clocks){
        boolean works = true;
        for (int i=0; i < 9; i++){
            if (clocks[i] != 0){
                works = false;
                break;
            }
        }
        return works;
    }

    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new FileReader("cowqueue.in"));
//        PrintWriter pw = new PrintWriter(new FileWriter("cowqueue.out"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        for (int i=0; i < 3; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j=0; j < 3; j++){
                clocks[3*i+j] = (Integer.parseInt(st.nextToken())/3)%4;
            }
        }
        List<List<Integer>> move = new ArrayList<>(9);
        List<Integer> zero = new ArrayList<>();
        zero.add(0);
        zero.add(1);
        zero.add(3);
        zero.add(4);
        move.add(zero);
        List<Integer> one = new ArrayList<>();
        one.add(0);
        one.add(1);
        one.add(2);
        move.add(one);
        List<Integer> two = new ArrayList<>();
        two.add(1);
        two.add(2);
        two.add(4);
        two.add(5);
        move.add(two);
        List<Integer> three = new ArrayList<>();
        three.add(0);
        three.add(3);
        three.add(6);
        move.add(three);
        List<Integer> four = new ArrayList<>();
        four.add(1);
        four.add(3);
        four.add(4);
        four.add(5);
        four.add(7);
        move.add(four);
        List<Integer> five = new ArrayList<>();
        five.add(2);
        five.add(5);
        five.add(8);
        move.add(five);
        List<Integer> six = new ArrayList<>();
        six.add(3);
        six.add(4);
        six.add(6);
        six.add(7);
        move.add(six);
        List<Integer> seven = new ArrayList<>();
        seven.add(6);
        seven.add(7);
        seven.add(8);
        move.add(seven);
        List<Integer> eight = new ArrayList<>();
        eight.add(4);
        eight.add(5);
        eight.add(7);
        eight.add(8);
        move.add(eight);
        List<Integer> empty = new ArrayList<>();
        recurse(clocks, move, 0, 0, empty);
    }
}
