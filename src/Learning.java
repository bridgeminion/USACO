import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

class Spotted implements Comparable<Spotted> {
    boolean spot;
    boolean mid;
    int weight;
    public Spotted(boolean spot, boolean mid, int weight){
        this.spot = spot;
        this.mid = mid;
        this.weight = weight;
    }
    @Override
    public int compareTo(Spotted x) {
        return this.weight-x.weight;
    }
}

class MidPt {
    boolean leftspot;
    boolean rightspot;
    boolean spot;
    int weight;
    public MidPt(boolean leftspot, boolean rightspot, boolean spot, int weight){
        this.leftspot = leftspot;
        this.rightspot = rightspot;
        this.spot = spot;
        this.weight = weight;
    }
}


public class Learning {

    public static void main(String[] args) throws IOException {

//        BufferedReader br = new BufferedReader(new FileReader("citystate.in"));
//        PrintWriter pw = new PrintWriter(new FileWriter("citystate.out"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        List<Spotted> cows = new ArrayList<>(2*n);
        for (int i=0; i < n; i++){
            StringTokenizer st1 = new StringTokenizer(br.readLine());
            String temp = st1.nextToken();
            if (temp.equals("S")){
                cows.add(new Spotted(true, false, Integer.parseInt(st1.nextToken())));
            }
            else {
                cows.add(new Spotted(false, false, Integer.parseInt(st1.nextToken())));
            }
        }
        Collections.sort(cows);
        List<MidPt> mid = new ArrayList<>();
        for (int i=0; i < n-1; i++){
            int weight = (cows.get(i).weight + cows.get(i+1).weight)/2;
            boolean spot;
            if (weight - cows.get(i).weight < cows.get(i+1).weight - weight && !cows.get(i).spot){
                spot = false;
            }
            else spot = cows.get(i).spot || cows.get(i + 1).spot;
            mid.add(new MidPt(cows.get(i).spot, cows.get(i+1).spot, spot, weight));
        }
        int startindex = 0;
        while (startindex < mid.size() && a > mid.get(startindex).weight){
            startindex++;
        }
        if (startindex >= mid.size()){
            if (mid.get(mid.size()-1).rightspot){
                pw.println(b-a+1);
                pw.close();
                return;
            }
            else {
                pw.println(0);
                pw.close();
                return;
            }
        }
        int ans = 0;
        if (mid.get(startindex).leftspot){
            ans += mid.get(startindex).weight - a;
        }
        int index = startindex;
        while (index < mid.size() - 1 && b > mid.get(index+1).weight){
            if (mid.get(index).rightspot){
                ans += mid.get(index+1).weight - mid.get(index).weight - 1;
            }
            index++;
        }
        if (mid.get(index).rightspot){
            ans += b - mid.get(index).weight;
        }
        for (MidPt i : mid){
            if (i.weight >= a && i.weight <=b && i.spot){
                ans++;
            }
        }
        pw.println(ans);
        pw.close();
    }
}
