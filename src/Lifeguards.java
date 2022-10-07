
import java.io.*;
import java.util.*;

class Timestamp implements Comparable<Timestamp>{
    int index;
    int time;
    public Timestamp(int index, int start){
        this.index = index;
        this.time = start;
    }
    public int compareTo(Timestamp x) {
        return time - x.time;
    }
}

public class Lifeguards {

    public static void main(String[] args) throws IOException {

//        BufferedReader br = new BufferedReader(new FileReader("measurement.in"));
//        PrintWriter pw = new PrintWriter(new FileWriter("measurement.out"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        List<Timestamp> temp = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            StringTokenizer st1 = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st1.nextToken());
            int end = Integer.parseInt(st1.nextToken());
            temp.add(new Timestamp(i, start));
            temp.add(new Timestamp(i, end));
        }
        Collections.sort(temp);
        int total = 0;
        int last = 0;
        int[] alone = new int[n];
        Set<Integer> set = new HashSet<>();
        for (Timestamp i : temp){
            if (set.size() == 1){
                int j = set.iterator().next();
                alone[j] += i.time - last;
            }
            if (!set.isEmpty()){
                total += i.time - last;
            }
            if (set.contains(i.index)){
                set.remove(i.index);
            }
            else {
                set.add(i.index);
            }
            last = i.time;
        }
        int least = 1000000001;
        for (int i : alone){
            least = Math.min(least, i);
        }
        System.out.println(total);
        System.out.println(least);
        System.out.println(total - least);
    }
}