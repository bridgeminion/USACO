import java.io.*;
import java.util.StringTokenizer;

public class CowCode {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("cowcode.in"));
        PrintWriter pw = new PrintWriter(new FileWriter("cowcode.out"));
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        String s = st.nextToken();
        Long n = Long.parseLong(st.nextToken()) - 1;
        Long cur = (long)s.length();
        while (cur <= n){
            cur *= 2;
        }
        long temp = cur/2;
        while (n >= s.length()){
            n %= temp;
            n = (n-1+temp)%temp;
            while (temp > n){
                temp /= 2;
            }
        }
        pw.println(s.charAt(n.intValue()));
        pw.close();
    }
}
