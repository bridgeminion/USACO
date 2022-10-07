import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Censor {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("censor.in"));
        PrintWriter pw = new PrintWriter(new FileWriter("censor.out"));
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder s = new StringBuilder(st.nextToken());
        StringTokenizer st1 = new StringTokenizer(br.readLine());
        String target = st1.nextToken();
//        while (s.indexOf(target) != -1) {
//            int start = s.indexOf(target);
//            s.delete(start, start + target.length());
//        }
        StringBuilder current = new StringBuilder();
        int cur_index = 0;
        for (int i=0; i < s.length(); i++){
            current.append(s.charAt(i));
            if (current.length() >= target.length() && current.substring(cur_index - target.length()+1, cur_index+1).equals(target)){
                current.delete(cur_index - target.length()+1, cur_index+1);
                cur_index -= target.length();
            }
            cur_index++;
        }
        pw.println(current);
        pw.close();
    }
}
