import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class FindCows {

    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new FileReader("cowqueue.in"));
//        PrintWriter pw = new PrintWriter(new FileWriter("cowqueue.out"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int c = 0;
        int o = 0;
        int w = 0;
        char[] word = new char[n];
        int[] numC = new int[n];
        int[] numO = new int[n];
        int[] numW = new int[n];
        StringTokenizer st1 = new StringTokenizer(br.readLine());
        String temp = st1.nextToken();
        for (int i=0; i < n; i++){
            word[i] = temp.charAt(i);
            if (word[i] == 'C'){
                c++;
                numC[i] = c;
                numO[i] = o;
                numW[i] = w;
            }
            else if (word[i] == 'O'){
                o++;
                numC[i] = c;
                numO[i] = o;
                numW[i] = w;
            }
            else{
                w++;
                numC[i] = c;
                numO[i] = o;
                numW[i] = w;
            }
        }
        long ans = 0;
        for (int i=0; i < n; i++){
            if (word[i] == 'O'){
                ans += numC[i]*(w - numW[i]);
            }
        }
        System.out.println(ans);
    }
}
