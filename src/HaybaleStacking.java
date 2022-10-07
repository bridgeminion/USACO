import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class HaybaleStacking {

    public static void main(String[] args) throws IOException {

//        BufferedReader br = new BufferedReader(new FileReader("citystate.in"));
//        PrintWriter pw = new PrintWriter(new FileWriter("citystate.out"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int num_stack = Integer.parseInt(st.nextToken());
        int num_query = Integer.parseInt(st.nextToken());
        int[] stack = new int[num_stack];
        int[] diff = new int[num_stack];
        for (int i=0; i < num_query; i++){
            StringTokenizer st1 = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st1.nextToken());
            int end = Integer.parseInt(st1.nextToken());
            diff[start-1]++;
            diff[end]--;
        }
        int cur_val = 0;
        for (int i=0; i < num_stack; i++){
            cur_val += diff[i];
            stack[i] = cur_val;
        }
        Arrays.sort(stack);
        System.out.println(stack[(num_stack-1)/2]);
    }
}
