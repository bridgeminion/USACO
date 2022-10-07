import java.io.*;
import java.util.*;

public class GetEven {

    public static long ans = 0;

    public static void generate (List<Integer> cur, int[] odd, int[] even){
        List<Integer> list = new ArrayList<>();
        list.add(0);
        list.add(1);
        for (int a : list){
            for (int b : list){
                for (int c : list){
                    for (int d : list){
                        for (int e : list) {
                            for (int f : list) {
                                for (int g : list) {
                                    cur.add(a);
                                    cur.add(b);
                                    cur.add(c);
                                    cur.add(d);
                                    cur.add(e);
                                    cur.add(f);
                                    cur.add(g);
                                    if (calculate(cur)) {
                                        long temp = 1;
                                        for (int i = 0; i < 7; i++) {
                                            if (cur.get(i) == 0) {
                                                temp *= even[i];
                                            } else {
                                                temp *= odd[i];
                                            }
                                        }
                                        ans += temp;
                                    }
                                    cur.clear();
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public static boolean calculate(List<Integer> list){
//        int b, int e, int s, int i, int g, int o, int m,
//        return (b + e + s + s + i + e)*(g + o + e + s)*(m + o + o)%2 == 0;
        return (list.get(0)+2*list.get(1)+2*list.get(2)+list.get(3))%2==0 || (list.get(4)+list.get(5)+list.get(1)+list.get(2))%2==0 || (list.get(6)+2*list.get(5))%2==0;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("geteven.in"));
        PrintWriter pw = new PrintWriter(new FileWriter("geteven.out"));
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int[] odd = new int[7];
        int[] even = new int[7];
        for (int i=0; i < n; i++){
            StringTokenizer st1 = new StringTokenizer(br.readLine());
            char letter = st1.nextToken().charAt(0);
            int val = Integer.parseInt(st1.nextToken());
            if (letter == 'B'){
                if (val%2==0){
                    even[0]++;
                }
                else {
                    odd[0]++;
                }
            }
            if (letter == 'E'){
                if (val%2==0){
                    even[1]++;
                }
                else {
                    odd[1]++;
                }
            }
            if (letter == 'S'){
                if (val%2==0){
                    even[2]++;
                }
                else {
                    odd[2]++;
                }
            }
            if (letter == 'I'){
                if (val%2==0){
                    even[3]++;
                }
                else {
                    odd[3]++;
                }
            }
            if (letter == 'G'){
                if (val%2==0){
                    even[4]++;
                }
                else {
                    odd[4]++;
                }
            }
            if (letter == 'O'){
                if (val%2==0){
                    even[5]++;
                }
                else {
                    odd[5]++;
                }
            }
            if (letter == 'M'){
                if (val%2==0){
                    even[6]++;
                }
                else {
                    odd[6]++;
                }
            }
        }
        generate(new ArrayList<>(), odd, even);
        pw.println(ans);
        pw.close();
    }
}
