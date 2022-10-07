import java.io.*;
import java.util.*;

class Volume{
    int height;
    int volume;
    int index;
    public Volume(int height, int volume, int index){
        this.height = height;
        this.volume = volume;
        this.index = index;
    }
}

public class Moooo {

    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new FileReader("moobuzz.in"));
//        PrintWriter pw = new PrintWriter(new FileWriter("moobuzz.out"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        List<Volume> cow = new ArrayList<>();
        for (int i=0; i < n; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int height = Integer.parseInt(st.nextToken());
            int vol = Integer.parseInt(st.nextToken());
            cow.add(new Volume(height, vol, i));
        }
        Stack<Volume> stack = new Stack<>();
        int[] total_vol = new int[n];
        stack.add(cow.get(0));
        for (int i=1; i < n; i++){
            while (stack.size() > 0 && cow.get(i).height > stack.peek().height){
                total_vol[i] += stack.pop().volume;
            }
            stack.add(cow.get(i));
        }
        Stack<Volume> stack1 = new Stack<>();
        stack1.add(cow.get(n-1));
        for (int i=n-2; i >= 0 ; i--){
            while (stack1.size() > 0 && cow.get(i).height > stack1.peek().height){
                total_vol[i] += stack1.pop().volume;
            }
            stack1.add(cow.get(i));
        }
        int ans = 0;
        for (int i=0; i < n; i++){
            ans = Math.max(total_vol[i], ans);
        }
        System.out.println(ans);
    }
}
