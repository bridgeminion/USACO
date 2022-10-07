import java.io.*;
import java.util.StringTokenizer;

public class LostCow {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("lostcow.in"));
        PrintWriter pw = new PrintWriter(new FileWriter("lostcow.out"));
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        int ans = 0;
        StringTokenizer st1 = new StringTokenizer(br.readLine());
        int fj = Integer.parseInt(st1.nextToken());
        int cow = Integer.parseInt(st1.nextToken());
        int counter = 0;
        while (true){
            for (int i=fj+1; i <= fj+java.lang.Math.pow(2, counter); i++){
                ans++;
                if (i == cow){
                    pw.println(ans);
                    pw.close();
                    return;
                }
            }
            ans += java.lang.Math.pow(2, counter);
//            pw.println(ans);
            counter++;
            for (int i=fj-1; i >= fj-java.lang.Math.pow(2, counter); i--){
                ans++;
                if (i == cow){
                    pw.println(ans);
                    pw.close();
                    return;
                }
            }
            ans += java.lang.Math.pow(2, counter);
//            pw.println(ans);
            counter++;
        }
    }
}
