import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class DNAAssembly {

    static int ans = 2147483647;

    public static String merge(String str1, String str2){
        int minLength = Math.min(str1.length(), str2.length());
        for (int i=minLength; i>=0; i--){
            if (str1.substring(str1.length() - i).equals(str2.substring(0, i))){
                return (str1.substring(0, str1.length() - i)  + str2);
            }
        }
        return "S";
    }

    public static void assembly(String cur, List<String> strings){
        if (strings.size() == 0){
            if (cur.length() < ans){
                ans = cur.length();
            }
            return;
        }
        for (int i=0; i < strings.size(); i++){
            String temp = merge(cur, strings.get(i));
            String removed = strings.get(i);
            strings.remove(i);
            assembly(temp, strings);
            strings.add(i, removed);
        }
    }

    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new FileReader("moobuzz.in"));
//        PrintWriter pw = new PrintWriter(new FileWriter("moobuzz.out"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        List<String> strings = new ArrayList<>();
        for (int i=0; i < n; i++){
            StringTokenizer st1 = new StringTokenizer(br.readLine());
            strings.add(st1.nextToken());
        }
        assembly("", strings);
        System.out.println(ans);
    }
}
