import java.io.*;
import java.util.*;

public class WaterSlides {

    static class Slide{
        int pos;
        List<Integer> adj;
        int degree;
        public Slide (int pos, List<Integer> adj, int degree){
            this.pos = pos;
            this.adj = adj;
            this.degree = degree;
        }
    }

    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new FileReader("moop.in"));
//        PrintWriter pw = new PrintWriter(new FileWriter("moop.out"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        Slide[] arr = new Slide[n+1];
        for (int i=1; i <= n; i++){
            arr[i] = new Slide (Integer.parseInt(br.readLine()), new ArrayList<>(), 0);
        }
        for (int i=0; i < m; i++){
            StringTokenizer st1 = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st1.nextToken());
            int end = Integer.parseInt(st1.nextToken());
            arr[start].degree++;
            arr[end].degree--;
            arr[start].adj.add(end);
        }
        List<Integer> start = new ArrayList<>(m);
        List<Integer> end = new ArrayList<>(m);
        for (int i=1; i <= n; i++){
            if (arr[i].degree < 0){
                for (int j=0; j < -arr[i].degree; j++){
                    end.add(arr[i].pos);
                }
            }
            else {
                for (int j=0; j < arr[i].degree; j++){
                    start.add(arr[i].pos);
                }
            }
        }
        Collections.sort(start);
        Collections.sort(end);
        int ans = 0;
        for (int i=0; i < start.size(); i++){
            ans += Math.abs(start.get(i) - end.get(i));
        }
        System.out.println(ans);
    }
}
