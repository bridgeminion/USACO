import java.io.*;
import java.util.*;

class Unfriendly implements Comparable<Unfriendly> {
    int pos;
    int counter;
    public Unfriendly(int pos, int counter){
        this.pos = pos;
        this.counter = counter;
    }

    @Override
    public int compareTo(Unfriendly x) {
        return (this.pos - x.pos);
    }
}

public class CowPhoto {

    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new FileReader("cowqueue.in"));
//        PrintWriter pw = new PrintWriter(new FileWriter("cowqueue.out"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        List<Unfriendly> line = new ArrayList<>();
        boolean[] found = new boolean[k];
        int counter = 0;
        for (int i=0; i < k; i++){
            StringTokenizer st1 = new StringTokenizer(br.readLine());
            int first = Integer.parseInt(st1.nextToken());
            int second = Integer.parseInt(st1.nextToken());
            Unfriendly temp1 = new Unfriendly(first, counter);
            Unfriendly temp2 = new Unfriendly(second, counter);
            line.add(temp1);
            line.add(temp2);
            counter++;
        }
        Collections.sort(line);
        int ans = 1;
        for (Unfriendly i : line){
            if (found[i.counter]){
                ans++;
                for (int j=0; j < k; j++){
                    found[j] = false;
                }
            }
            else{
                found[i.counter] = true;
            }
        }
        System.out.println(ans);
    }
}
