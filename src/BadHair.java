import java.io.*;
import java.util.*;

public class BadHair {

    static class Height{
        int index;
        Long height;
        public Height(int index, Long height){
            this.index = index;
            this.height = height;
        }
    }

    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new FileReader("cowqueue.in"));
//        PrintWriter pw = new PrintWriter(new FileWriter("cowqueue.out"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        List<Height> height = new ArrayList<>();
        long ans = 0L;
        for (int i=0; i < n; i++){
            StringTokenizer st1 = new StringTokenizer(br.readLine());
            Height temp = new Height(i, Long.parseLong(st1.nextToken()));
            height.add(temp);
        }
        Stack<Height> num = new Stack<>();
        for (int i=0; i < n; i++){
            while (!num.isEmpty() && num.peek().height <= height.get(i).height){
                num.pop();
            }
            ans += num.size();
            num.add(height.get(i));
        }
        pw.println(ans);
        pw.close();
    }

}
