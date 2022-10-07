import java.io.*;
import java.util.StringTokenizer;

public class TimeTravel2 {

    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new FileReader("moop.in"));
//        PrintWriter pw = new PrintWriter(new FileWriter("moop.out"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        int[] parent = new int[1000005];
        int[] pointer = new int[1000005];
        int cur;
        pointer[0] = -1;
        parent[0] = -1;
//        for (int i=0; i < n; i++){
//            StringTokenizer st = new StringTokenizer(br.readLine());
//            char temp = st.nextToken().charAt(0);
//            if (temp=='a') {
//                int id = Integer.parseInt(st.nextToken());
//                if (parent[id] != 0) {
//                    System.out.println("fffffff");
//                }
//                parent[id] = cur;
//                cur = id;
//            }
//            else if (temp=='s'){
//                cur = pointer[i] == -1 ? -1 : parent[pointer[i]];
//            }
//            else {
//                cur = pointer[Integer.parseInt(st.nextToken())-1];
//            }
//            pointer[i+1] = cur;
//            System.out.println(cur);
//        }
        for (int i=1; i <= n; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            char temp = st.nextToken().charAt(0);
            if (temp=='a') {
                int id = Integer.parseInt(st.nextToken());
                parent[i] = i-1;
                cur = id;
            }
            else if (temp=='s'){
                cur = pointer[parent[i-1]];
                parent[i] = parent[parent[i-1]];
            }
            else {
                int travelTo = Integer.parseInt(st.nextToken());
                parent[i] = parent[travelTo-1];
                cur = pointer[travelTo-1];
            }
            pointer[i] = cur;
            System.out.println(cur);
        }
        System.out.println();
    }
}
