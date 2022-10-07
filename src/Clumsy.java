import java.io.*;

public class Clumsy {

    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new FileReader("clocktree.in"));
//        PrintWriter pw = new PrintWriter(new FileWriter("clocktree.out"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        String temp = br.readLine();
        int n = temp.length();
        int cur = 0;
        int ans = 0;
        for (int i=0; i < n; i++){
             if (temp.charAt(i) == '('){
                 cur++;
             }
             else {
                 cur--;
             }
             if (cur == -1){
                 cur = 1;
                 ans++;
             }
        }
        ans += cur/2;
        System.out.println(ans);
    }
}
