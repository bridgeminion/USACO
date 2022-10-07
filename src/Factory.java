import java.io.*;
import java.util.*;

public class Factory {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new FileReader("factory.in"));
        PrintWriter pw = new PrintWriter(new FileWriter("factory.out"));
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        Map<Integer, Set<Integer>> input = new HashMap<>();
        int n = Integer.parseInt(st.nextToken());
        for (int i=1; i <=n; i++){
            input.put(i, new HashSet<>());
            input.get(i).add(i);
        }
        for (int i=0; i < n-1; i++){
            StringTokenizer st1 = new StringTokenizer(br.readLine());
            int dest = Integer.parseInt(st1.nextToken());
            int cur = Integer.parseInt(st1.nextToken());
            input.get(cur).add(dest);
        }
//        for (int i : input.keySet()){
//             for (int j : input.get(i)){
//                 for (int k : input.get(j)){
//                     if (k != i){
//                         input.get(i).add(k);
//                         ans.get(i).add(k);
//                     }
//                 }
//             }
//        }
        for (Map.Entry<Integer, Set<Integer>> entry : input.entrySet()){
            for (int j : entry.getValue()){
                entry.getValue().addAll(input.get(j));
            }
        }
        for (int i : input.keySet()){
            if (input.get(i).size() == n){
                pw.println(i);
                pw.close();
                return;
            }
        }
        pw.println("-1");
        pw.close();

    }
}
