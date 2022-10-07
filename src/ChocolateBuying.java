import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class ChocolateBuying {

    static class Choc implements Comparable<Choc>{
        Long price;
        Long num;
        public Choc (long price, Long num){
            this.price = price;
            this.num = num;
        }

        @Override
        public int compareTo(Choc choc) {
            if (this.price.equals(choc.price)){
                return this.num.compareTo(choc.num);
            }
            return this.price.compareTo(choc.price);
        }
    }

    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new FileReader("mountains.in"));
//        PrintWriter pw = new PrintWriter(new FileWriter("mountains.out"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int num_choc = Integer.parseInt(st.nextToken());
        long b = Long.parseLong(st.nextToken());
        List<Choc> list = new ArrayList<>(num_choc);
        for (int i=0; i < num_choc; i++){
            StringTokenizer st1 = new StringTokenizer(br.readLine());
            long price = Long.parseLong(st1.nextToken());
            long num = Long.parseLong(st1.nextToken());
            list.add(new Choc(price, num));
        }
        Collections.sort(list);
        long ans = 0;
        for (Choc i : list){
            if (b==0 || b-i.price < 0){
                System.out.println(ans);
                return;
            }
            if (b/i.price > i.num){
                b -= i.num*i.price;
                ans += i.num;
            }
            else {
                long temp = b/i.price;
                ans += temp;
                b = b%i.price;
            }
        }
        System.out.println(ans);
    }
}
