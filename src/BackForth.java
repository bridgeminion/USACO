import java.io.*;
import java.util.*;

public class BackForth {
    static Set<Integer> value = new HashSet<Integer>();
    public static void recurse(List<Integer> first, List<Integer> second, Integer counter, Integer current_first, Integer current_second, Integer total_first, Integer total_second) {
        if (counter == 2){
            value.add(current_first);
//            System.out.println(current_first);
            return;
        }
        for(int i = 0; i < first.size(); i++){
            for (int j = 0; j < second.size(); j++){
                current_first += second.get(j);
                current_first -= first.get(i);
                current_second += first.get(i);
                current_second -= second.get(j);
                recurse(first, second, counter + 1, current_first, current_second, total_first, total_second);
                current_first -= second.get(j);
                current_first += first.get(i);
                current_second -= first.get(i);
                current_second += second.get(j);
                recurse(first, second, counter + 1, current_first, current_second, total_first, total_second);
            }
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("backforth.in"));
        PrintWriter pw = new PrintWriter(new FileWriter("backforth.out"));
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        int total_first = 0;
        int total_second = 0;
        List<Integer> first = new ArrayList<>();
        List<Integer> second = new ArrayList<>();
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i=0; i < 10; i++){
            int temp = Integer.parseInt(st.nextToken());
            first.add(temp);
            total_first += temp;
        }
        StringTokenizer st1 = new StringTokenizer(br.readLine());
        for (int i=0; i < 10; i++){
            int temp = Integer.parseInt(st1.nextToken());
            second.add(temp);
            total_second += temp;
        }
        recurse(first, second, 0, total_first, total_second, total_first, total_second);
        pw.println(value.size());
        for (int i : value){
            System.out.println(i);
        }
        pw.close();
    }
}
