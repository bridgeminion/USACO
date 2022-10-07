import java.io.*;
import java.util.*;

public class HayBales {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("haybales.in"));
        PrintWriter pw = new PrintWriter(new FileWriter("haybales.out"));
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        TreeMap <Integer, Integer> map = new TreeMap<>();
        int num_bales, num_query;
        StringTokenizer st = new StringTokenizer(br.readLine());
        num_bales = Integer.parseInt(st.nextToken());
        num_query = Integer.parseInt(st.nextToken());
        int[] bale = new int[num_bales];
        Map<Integer, Integer> treeMap = new TreeMap<>();
        int [] start = new int[num_query];
        int [] end = new int[num_query];
        StringTokenizer st1 = new StringTokenizer(br.readLine());
        for (int i=0; i < num_bales; i++){
            bale[i] = Integer.parseInt(st1.nextToken());
        }
        Arrays.sort(bale);
        for (int i=0; i<bale.length; i++) {
            map.put(bale[i], i);
        }
        for (int i=0; i < num_query; i++){
            StringTokenizer st2 = new StringTokenizer(br.readLine());
            start[i] = Integer.parseInt(st2.nextToken());
            end[i] = Integer.parseInt(st2.nextToken());
        }
        for (int i=0; i < num_query; i++){
            Map.Entry<Integer, Integer> temp1 = map.ceilingEntry(start[i]);
            Map.Entry<Integer, Integer> temp2 = map.floorEntry(end[i]);
            if (temp1 != null && temp2 != null){
                pw.println(temp2.getValue() - temp1.getValue() + 1);
            }
            else{
                pw.println(0);
            }
        }
        pw.close();
    }
}
