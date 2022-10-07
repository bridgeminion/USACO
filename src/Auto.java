import java.io.*;
import java.util.*;

public class Auto {

    static class Prefix{
        int num;
        String prefix;
        public Prefix(int num, String prefix){
            this.num = num;
            this.prefix = prefix;
        }
    }

    public static boolean compare (String a, String p){
        for (int i=0; i < p.length(); i++){
            if (a.charAt(i) != p.charAt(i)){
                return false;
            }
        }
        return true;
    }

    public static void search (List<String> dic, Prefix cur, Map<String, Integer> index1){
        int index = Collections.binarySearch(dic, cur.prefix);
        if (index < 0){
            index = -(index+1);
//            System.out.println("prefix = " + cur.prefix);
//            System.out.println("index = " + index);
            for (int i=0; i < cur.num; i++){
                if (!(index + i < dic.size() && compare(dic.get(index+i), cur.prefix))){
                    System.out.println(-1);
                    return;
                }
            }
        }
        else {
//            System.out.println("prefix = " + cur.prefix);
//            System.out.println("index = " + index);
            for (int i=0; i < cur.num; i++){
                if (!(index + i < dic.size() && compare(dic.get(index+i), cur.prefix))){
                    System.out.println(-1);
                    return;
                }
            }
        }
        System.out.println(index1.get(dic.get(index + cur.num-1)));
    }

    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new FileReader("moobuzz.in"));
//        PrintWriter pw = new PrintWriter(new FileWriter("moobuzz.out"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int num_words = Integer.parseInt(st.nextToken());
        int num_auto = Integer.parseInt(st.nextToken());
        List<String> dic = new ArrayList<>(num_words);
        List<Prefix> prefix = new ArrayList<>(num_auto);
        Map<String, Integer> index = new HashMap<>();
        for (int i=0; i < num_words; i++){
            String temp = br.readLine();
            dic.add(temp);
            index.put(temp, i+1);
        }
        for (int i=0; i < num_auto; i++){
            StringTokenizer st1 = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st1.nextToken());
            String p = st1.nextToken();
            prefix.add(new Prefix(num, p));
        }
        Collections.sort(dic);
        for (int i=0; i < num_auto; i++){
            search(dic, prefix.get(i), index);
        }
    }
}
