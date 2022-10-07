import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class ForgottenPassword {

    public static boolean check (String s, int start, String d){
        for (int i=0; i < d.length(); i++){
            if (d.charAt(i) != s.charAt(i+start) && s.charAt(i+start) != '?'){
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new FileReader("mooyomooyo.in"));
//        PrintWriter pw = new PrintWriter(new FileWriter("mooyomooyo.out"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int l = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());
        String s = br.readLine();
        List<String> dic = new ArrayList<>(n);
        for (int i=0; i < n; i++){
            dic.add(br.readLine());
        }
        String[] ans = new String[l+1];
        for (int i=1; i <= l; i++){
            ans[i] = "zzzzzzzzzzzzzzzzzzzzzz";
        }
        boolean[] valid = new boolean[l+1];
        valid[0] = true;
        ans[0] = "";
        for (int i=1; i <= l; i++){
            for (String j : dic){
                if (i-j.length() >= 0 && valid[i-j.length()] && check (s, i-j.length(), j)){
                    valid[i] = true;
                    String temp = ans[i-j.length()] + j;
                    if (temp.compareTo(ans[i]) < 0){
                        ans[i] = temp;
                    }
                }
            }
        }
        System.out.println(ans[l]);
    }
}
