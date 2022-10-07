import java.io.*;
import java.util.StringTokenizer;

public class Cline {

    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new FileReader("cowqueue.in"));
//        PrintWriter pw = new PrintWriter(new FileWriter("cowqueue.out"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int[] line = new int[200001];
        int front = 100000;
        int back = 99999;
        int counter = 1;
        for (int i=0; i < n; i++){
            StringTokenizer st1 = new StringTokenizer(br.readLine());
            String first = st1.nextToken();
            if (first.equals("A")){
                String second = st1.nextToken();
                if (second.equals("L")){
                    front--;
                    line[front] = counter;
                    counter++;
                }
                else if (second.equals("R")){
                    back++;
                    line[back] = counter;
                    counter++;
                }
            }
            else{
                String second = st1.nextToken();
                int num = Integer.parseInt(st1.nextToken());
                if (second.equals("L")){
                    front += num;
                }
                else{
                    back -= num;
                }
            }
        }
        for (int i=front; i <= back; i++){
            pw.println(line[i]);
        }
        pw.close();
    }
}
