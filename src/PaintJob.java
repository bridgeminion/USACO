import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class PaintJob {

    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new FileReader("cowqueue.in"));
//        PrintWriter pw = new PrintWriter(new FileWriter("cowqueue.out"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int bessie = Integer.parseInt(st.nextToken());
        int target = Integer.parseInt(st.nextToken());
        StringTokenizer st1 = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st1.nextToken());
        int numColor = Integer.parseInt(st1.nextToken());
        int[] colors = new int[n];
        boolean[] visited = new boolean[numColor+1];
        StringTokenizer st2 = new StringTokenizer(br.readLine());
        for (int i=0; i < n; i++){
            colors[i] = Integer.parseInt(st2.nextToken());
        }
        Queue<Integer> q = new LinkedList<>();
        int counter = 0;
        q.add(bessie);
        while (!q.isEmpty()){
            int temp = q.size();
            counter++;
            for (int i=0; i < temp; i++){
                int cur = q.remove();
                for (int j=0; j < n; j++){
                    int paint = (cur*colors[j])%numColor;
                    if (paint == target){
                        pw.println(counter);
                        pw.close();
                        return;
                    }
                    if (!visited[paint]){
                        q.add(paint);
                        visited[paint] = true;
                    }
                }
            }
        }
        pw.println(-1);
        pw.close();
    }
}
