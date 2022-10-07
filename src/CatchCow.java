import org.w3c.dom.ls.LSOutput;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class CatchCow {

    public static void main(String[] args) throws IOException {

//        BufferedReader br = new BufferedReader(new FileReader("measurement.in"));
//        PrintWriter pw = new PrintWriter(new FileWriter("measurement.out"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());
        boolean[] visited = new boolean[1000001];
        Queue<Integer> q = new LinkedList<>();
        q.add(start);
        int counter = 0;
        while (!q.isEmpty()){
            int temp = q.size();
            for (int i=0; i < temp; i++){
                int cur = q.remove();
                if (cur == end){
                    System.out.println(counter);
                    return;
                }
                if (cur+1 >= 0 && cur + 1 <= 100001 && !visited[cur+1]){
                    visited[cur+1] = true;
                    q.add(cur+1);
                }
                if (cur-1 >= 0 && cur - 1 <= 100001 && !visited[cur-1]){
                    visited[cur-1] = true;
                    q.add(cur-1);
                }
                if (2*cur >= 0 && 2*cur <= 100001 && !visited[2*cur]){
                    visited[2*cur] = true;
                    q.add(2*cur);
                }
            }
            counter++;
        }
        System.out.println(counter);
    }
}
