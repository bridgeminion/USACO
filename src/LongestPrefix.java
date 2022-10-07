import java.io.*;
import java.util.*;

public class LongestPrefix {

    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new FileReader("countcross.in"));
//        PrintWriter pw = new PrintWriter(new FileWriter("countcross.out"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        List<String> list = new ArrayList<>();
        String temp1 = br.readLine();
        while (!temp1.equals(".")){
            StringTokenizer st = new StringTokenizer(temp1);
            while (st.hasMoreTokens()){
                list.add(st.nextToken());
            }
            temp1 = br.readLine();
        }
        StringBuilder input = new StringBuilder();
        String temp = br.readLine();
        while (temp != null && !temp.equals("")){
            input.append(temp);
            temp = br.readLine();
        }
        boolean[] valid = new boolean[input.length()+1];
        valid[0] = true;
        int ans = 0;
        for (int i=0; i <= input.length(); i++){
            if (valid[i]){
                for (String j : list){
                    if (i+j.length() <= input.length()){
                        if (input.substring(i, i+j.length()).equals(j)){
                            valid[i+j.length()] = true;
                            ans = Math.max(ans, i+j.length());
                        }
                    }
                }
            }
        }
        System.out.println(ans);
    }
}
