import java.io.*;
import java.util.*;



public class CowQueue {

    static class Cow {
        int start;
        int ask;

        Cow (int start, int ask) {
            this.start = start;
            this.ask = ask;
        }
    }

 public static void main(String[] args) throws IOException {
         BufferedReader br = new BufferedReader(new FileReader("cowqueue.in"));
         PrintWriter pw = new PrintWriter(new FileWriter("cowqueue.out"));
//       BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//       PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        int n;
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        Cow[] input = new Cow[n];
        for (int i=0; i < n; i++){
            StringTokenizer st1 = new StringTokenizer(br.readLine());
            input[i] = new Cow(Integer.parseInt(st1.nextToken()), Integer.parseInt(st1.nextToken()));
        }
        Comparator<Cow> lambdaComparator = (Cow1, Cow2) -> Cow1.start - Cow2.start;
        Arrays.sort(input, lambdaComparator);
        int ans = 0;
        int index;
        int time = 0;
        for (int i=0; i < n; i++){
            index = input[i].start;
            if (index <= time){
                index = time;
            }
            else{
                time = index;
            }
            time += input[i].ask;
            ans = index + input[i].ask;
        }
        pw.println(ans);
        pw.close();
    }
}
