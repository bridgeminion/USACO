import java.io.*;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Cline_Deque {

    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new FileReader("cowqueue.in"));
//        PrintWriter pw = new PrintWriter(new FileWriter("cowqueue.out"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        Deque<Integer> line = new LinkedList<>();
        int counter = 1;
        for (int i=0; i < n; i++){
            StringTokenizer st1 = new StringTokenizer(br.readLine());
            String first = st1.nextToken();
            if (first.equals("A")){
                String second = st1.nextToken();
                if (second.equals("L")){
                    line.addFirst(counter);
                    counter++;
                }
                else if (second.equals("R")){
                    line.addLast(counter);
                    counter++;
                }
            }
            else{
                String second = st1.nextToken();
                int num = Integer.parseInt(st1.nextToken());
                if (second.equals("L")){
                    for (int j=0; j < num; j++){
                        line.removeFirst();
                    }
                }
                else{
                    for (int j=0; j < num; j++){
                        line.removeLast();
                    }
                }
            }
        }
        for (int i : line){
            pw.println(i);
        }
        pw.close();
    }
}
