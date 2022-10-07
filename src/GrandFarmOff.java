import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

class Cow implements Comparable<Cow> {
    long weight;
    long utility;
    public Cow(long weight, long utility){
        this.weight = weight;
        this.utility = utility;
    }

    @Override
    public int compareTo(Cow cow) {
        if (this.utility == cow.utility){
            if (this.weight > cow.weight){
                return (-1);
            }
            else if (this.weight < cow.weight){
                return (1);
            }
            else{
                return(0);
            }
        }
        return ((int)(this.utility - cow.utility));
    }
}

public class GrandFarmOff {


    public static void main(String[] args) throws IOException {

//        BufferedReader br = new BufferedReader(new FileReader("citystate.in"));
//        PrintWriter pw = new PrintWriter(new FileWriter("citystate.out"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        Long a = Long.parseLong(st.nextToken());
        Long b = Long.parseLong(st.nextToken());
        Long c = Long.parseLong(st.nextToken());
        Long d = Long.parseLong(st.nextToken());
        Long e = Long.parseLong(st.nextToken());
        Long f = Long.parseLong(st.nextToken());
        Long g = Long.parseLong(st.nextToken());
        Long h = Long.parseLong(st.nextToken());
        Long m = Long.parseLong(st.nextToken());
        List<Cow> input = new ArrayList<>();
        for (int i=0; i < 3*n; i++) {
            long weight_temp = (long)(((a%d)*Math.pow((i % d), 5)+(b%d)*Math.pow(i, 2)+(c%d))%d);
            long utility_temp = (long)(((e%h)*Math.pow((i % h), 5) +(f%h)*Math.pow((i%h), 3)+(g%h))%h);
            input.add(new Cow(weight_temp % d, utility_temp % h));
        }
        Collections.sort(input);
        long sum = 0;
        for (int i = 3*n - 1; i > 2*n - 1; i--){
            sum += input.get(i).weight%m;
        }
        pw.println(sum%m);
        pw.close();
    }
}
