import java.io.*;
import java.util.StringTokenizer;

public class TimeTravel {

    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new FileReader("moop.in"));
//        PrintWriter pw = new PrintWriter(new FileWriter("moop.out"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        int[] parent = new int[1000005];
        int[] pointer = new int[80005];
        int cur = -1;
        pointer[0] = -1;
        for (int i=0; i < n; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            char temp = st.nextToken().charAt(0);
            if (temp=='a') {
                int id = Integer.parseInt(st.nextToken());
                parent[id] = cur;
                cur = id;
            }
            else if (temp=='s'){
                cur = parent[cur];
            }
            else {
                cur = pointer[Integer.parseInt(st.nextToken())-1];
            }
            pointer[i+1] = cur;
            System.out.println(cur);
        }
        System.out.println();
    }
}
