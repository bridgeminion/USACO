import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Block {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("blocks.in"));
        PrintWriter pw = new PrintWriter(new FileWriter("blocks.out"));
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        int[] ans = new int[26];
        int n;
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        for (int i=0; i < n; i++){
            int[] cur1 = new int[26];
            int[] cur2 = new int[26];
            StringTokenizer st1 = new StringTokenizer(br.readLine());
            String temp1 = st1.nextToken();
            ArrayList<Character> letter1 = new ArrayList<>();
            for (int j=0; j < temp1.length(); j++){
                letter1.add(temp1.charAt(j));
            }
            String temp2 = st1.nextToken();
            ArrayList <Character> letter2= new ArrayList<>();
            for (int k=0; k < temp2.length(); k++){
                letter2.add(temp2.charAt(k));
            }
            for (char a : letter1){
                cur1[a - 'a']++;
            }
            for (char b : letter2){
                cur2[b - 'a']++;
            }
            for (int c=0; c < 26; c++){
                ans[c] += java.lang.Math.max(cur1[c], cur2[c]);
            }
            for (int d=0; d < 26; d++){
                cur1[d] = 0;
                cur2[d] = 0;
            }
        }
        for (int i=0; i < 26; i++){
            pw.println(ans[i]);
        }
        pw.close();
    }
}
