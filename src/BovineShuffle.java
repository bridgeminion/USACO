import java.io.*;
import java.util.*;

public class BovineShuffle {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("shuffle.in"));
        PrintWriter pw = new PrintWriter(new FileWriter("shuffle.out"));
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        StringTokenizer st1 = new StringTokenizer(br.readLine());
        int[] leadTo = new int[n];
        int[] goesTo = new int[n];
        for (int i=0; i < n; i++){
            int temp = Integer.parseInt(st1.nextToken());
            leadTo[temp-1]++;
            goesTo[i] = temp - 1;
        }
        List<Integer> list = new LinkedList<>();
        for (int i=0; i < n; i++){
            if (leadTo[i] == 0){
                list.add(i);
            }
        }
        int i = 0;
        while (i < list.size()) {
            int temp = list.get(i++);
            leadTo[goesTo[temp]]--;
            if (leadTo[goesTo[temp]] <= 0) {
                list.add(goesTo[temp]);
            }
        }
        pw.println(n - list.size());
        pw.close();
    }
}
