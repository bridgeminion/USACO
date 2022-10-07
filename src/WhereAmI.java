import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class WhereAmI {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("whereami.in"));
        PrintWriter pw = new PrintWriter(new FileWriter("whereami.out"));
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int len = Integer.parseInt(st.nextToken());
        StringTokenizer st1 = new StringTokenizer(br.readLine());
        String mail = st1.nextToken();
        int counter = 1;
        while (counter < len){
            List<String> found = new ArrayList<>();
            for (int i=0; i < len-counter+1; i++){
                String temp = mail.substring(i, i+counter);
                found.add(temp);
            }
            boolean match = true;
            for (int i=0; i < len-counter+1; i++){
                String temp = mail.substring(i, i+counter);
                int x = 0;
                for (String j : found){
                    if (temp.equals(j)){
                        x++;
                    }
                }
                if (x != 1){
                    match = false;
                    break;
                }
            }
            if (match){
                pw.println(counter);
                pw.close();
                return;
            }
            counter++;
        }
    }
}
