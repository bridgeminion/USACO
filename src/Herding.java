import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Herding {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("herding.in"));
        PrintWriter pw = new PrintWriter(new FileWriter("herding.out"));
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        long[] cow = new long[3];
        StringTokenizer st1 = new StringTokenizer(br.readLine());
        for (int i=0; i < 3; i++){
            cow[i] = Long.parseLong(st1.nextToken());
        }
        Arrays.sort(cow);
        if (cow[2] == cow[0] + 2){
            pw.println(0);
            pw.println(0);
            pw.close();
            return;
        }
        if (cow[1] == cow[0] + 2 || cow[2] == cow[1] + 2){
            pw.println(1);
            pw.println(java.lang.Math.max((cow[1] - cow[0]), (cow[2] - cow[1])) - 1);
            pw.close();
            return;
        }
        pw.println(2);
        long big =  java.lang.Math.max((cow[1] - cow[0]), (cow[2] - cow[1])) - 1;
        pw.println(big);
        pw.close();
    }
}
