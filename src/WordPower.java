import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class WordPower {

    static class Good{
        String str;
        int index;
        int num;
        public Good (String str, int index, int num){
            this.str = str;
            this.index = index;
            this.num = num;
        }
    }

    public static int points (String name, List<Good> good){
        int ans = 0;
        for (int i=0; i < name.length(); i++){
            for (Good j : good){
                if (j.index < j.str.length() && j.str.charAt(j.index) == name.charAt(i)){
                    j.index++;
                    j.num++;
                }
            }
        }
        for (Good i : good){
            if (i.num==i.str.length()){
                ans++;
            }
            i.num = 0;
            i.index = 0;
        }
        return ans;
    }

    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new FileReader("clocktree.in"));
//        PrintWriter pw = new PrintWriter(new FileWriter("clocktree.out"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        List<String> names = new ArrayList<>(n);
        List<Good> good = new ArrayList<>(m);
        for (int i=0; i < n; i++){
            names.add(br.readLine().toLowerCase());
        }
        for (int i=0; i < m; i++){
            good.add(new Good(br.readLine().toLowerCase(), 0, 0));
        }
        for (String name : names){
            System.out.println(points(name, good));
        }
    }
}
