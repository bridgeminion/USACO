import java.io.*;
import java.util.*;

public class ThreeLines {

    public static boolean check2x1y (Map<Integer, Set<Integer>> x){
        return x.keySet().size() <= 2;
    }

    public static boolean check1x2y (Map<Integer, Set<Integer>> y){
        return y.keySet().size() <= 2;
    }

    public static void main(String[] args) throws IOException {

//        BufferedReader br = new BufferedReader(new FileReader("measurement.in"));
//        PrintWriter pw = new PrintWriter(new FileWriter("measurement.out"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        Map<Integer, Set<Integer>> xkey = new HashMap<>();
        Map<Integer, Set<Integer>> ykey = new HashMap<>();
        for (int i=0; i < n; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            xkey.putIfAbsent(x, new HashSet<>());
            ykey.putIfAbsent(y, new HashSet<>());
            xkey.get(x).add(y);
            ykey.get(y).add(x);
        }
        if (xkey.keySet().size() <= 3 || ykey.keySet().size() <= 3){
            System.out.println(1);
            return;
        }
        //2x1y
        for (int i : ykey.keySet()){
            List<Integer> x = new ArrayList<>();
            List<Integer> y = new ArrayList<>();
            for (int j : ykey.get(i)){
                xkey.get(j).remove(i);
                if (xkey.get(j).isEmpty()){
                    xkey.remove(j);
                }
                x.add(j);
                y.add(i);
            }
            if (check2x1y(xkey)){
                System.out.println(1);
                return;
            }
            for (int j=0; j < x.size(); j++){
                xkey.putIfAbsent(x.get(j), new HashSet<>());
                xkey.get(x.get(j)).add(y.get(j));
            }
        }
        //1x2y
        for (int i : xkey.keySet()){
            List<Integer> x = new ArrayList<>();
            List<Integer> y = new ArrayList<>();
            for (int j : xkey.get(i)){
                ykey.get(j).remove(i);
                if (ykey.get(j).isEmpty()){
                    ykey.remove(j);
                }
                x.add(i);
                y.add(j);
            }
            if (check1x2y(ykey)){
                System.out.println(1);
                return;
            }
            for (int j=0; j < x.size(); j++){
                ykey.putIfAbsent(y.get(j), new HashSet<>());
                ykey.get(y.get(j)).add(x.get(j));
            }
        }
        System.out.println(0);
    }
}
