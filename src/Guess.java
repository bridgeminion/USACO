import java.io.*;
import java.util.*;

public class Guess {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("guess.in"));
        PrintWriter pw = new PrintWriter(new FileWriter("guess.out"));
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        Map<String, Set<String>> map = new HashMap<>();
        int n;
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        Set<String> all_traits = new HashSet<>();
        for (int i=0; i < n; i++){
            StringTokenizer st1 = new StringTokenizer(br.readLine());
            String name = st1.nextToken();
            int temp = Integer.parseInt(st1.nextToken());
            Set<String> traits = new HashSet<>();
            for (int j=0; j < temp; j++){
                String trait = st1.nextToken();
                traits.add(trait);
                all_traits.add(trait);
            }
            map.put(name, traits);
        }
        int ans = 0;
        for (String i : map.keySet()){
            for (String j : map.keySet()){
                if (i != j){
                    int counter = 0;
                    for (String k : all_traits){
                        if (map.get(i).contains(k) && map.get(j).contains(k)){
                            counter++;
                        }
                    }
                    if (counter > ans){
                        ans = counter;
                    }
                }
            }
        }
        pw.println(ans+1);
        pw.close();
    }
}
