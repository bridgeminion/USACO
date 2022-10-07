import java.io.*;
import java.util.*;

public class FlyingCow {

    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new FileReader("cowqueue.in"));
//        PrintWriter pw = new PrintWriter(new FileWriter("cowqueue.out"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        boolean[] visited = new boolean[1000001];
        Queue<Integer> q = new LinkedList<>();
        q.add(1);
        int counter = 0;
        while (!q.isEmpty()){
            int temp = q.size();
            for (int i=0; i < temp; i++){
                int cur = q.remove();
                if (cur == n){
                    pw.println(counter);
                    pw.close();
                    return;
                }
                q.addAll(getNeighbor(cur, visited));
            }
            counter++;
        }
        pw.close();
    }

    private static List<Integer> getNeighbor(int cur, boolean[] visited) {
        List<Integer> result = new ArrayList<>();
        if (cur*3 >= 0 && cur*3 <= 1000000 && !visited[cur*3]){
            result.add(cur*3);
            visited[cur*3] = true;
        }
        if (cur+1 >= 0 && cur+1 <= 1000000 && !visited[cur+1]){
            result.add(cur+1);
            visited[cur+1] = true;
        }
        if (cur-1 >= 0 && cur-1 <= 1000000 && !visited[cur-1]){
            result.add(cur-1);
            visited[cur-1] = true;
        }
        return result;
    }
}
