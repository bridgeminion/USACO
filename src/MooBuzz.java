import java.io.*;
import java.util.StringTokenizer;

public class MooBuzz {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("moobuzz.in"));
        PrintWriter pw = new PrintWriter(new FileWriter("moobuzz.out"));
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        long n = Long.parseLong(st.nextToken());
        long quotient = (n-1)/8;
        long remainder = n%8;
        long r = 0;
        if (remainder == 0){
            r = 14;
        }
        else if (remainder == 1){
            r = 1;
        }
        else if (remainder == 2){
            r = 2;
        }
        else if (remainder == 3){
            r = 4;
        }
        else if (remainder == 4){
            r = 7;
        }
        else if (remainder == 5){
            r = 8;
        }
        else if (remainder == 6){
            r = 11;
        }
        else if (remainder == 7){
            r = 13;
        }
        long ans = quotient*15 + r;
        pw.println(ans);
        pw.close();
    }
}
