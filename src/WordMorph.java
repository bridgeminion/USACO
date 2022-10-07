import java.io.*;
        import java.util.*;

public class WordMorph {

    static Set<String> visited = new HashSet<>();
    static Set<String> dic = new HashSet<>();
    static String[] letters = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};
    static String start = "";
    static String end = "";
    static int counter = 0;

    public static List<String> bfs (String s){
        List<String> ans = new ArrayList<>();
        for (int i=0; i < s.length(); i++){
            String temp = Character.toString(s.charAt(i));
            for (int j=0; j < 26; j++){
                if (!letters[j].equals(temp)){
                    if (i < s.length()-1){
//                        if ((s.substring(0, i) + letters[j] + s.substring(i+1)).equals(end)){
//                            System.out.println(counter);
//                        }
                        if (dic.contains(s.substring(0, i) + letters[j] + s.substring(i+1))){
                            ans.add(s.substring(0, i) + letters[j] + s.substring(i+1));
                        }
                    }
                    else{
                        if (dic.contains(s.substring(0, i) + letters[j])){
                            ans.add(s.substring(0, i) + letters[j]);
                        }
                    }
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) throws IOException {

//        BufferedReader br = new BufferedReader(new FileReader("measurement.in"));
//        PrintWriter pw = new PrintWriter(new FileWriter("measurement.out"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        start = st.nextToken();
        StringTokenizer st1 = new StringTokenizer(br.readLine());
        end = st1.nextToken();
        int len = start.length();
        String s = br.readLine();
        while (s!=null && !s.equals("")){
            StringTokenizer st2 = new StringTokenizer(s);
            String temp = st2.nextToken();
            if (temp.length() == len){
                dic.add(temp);
            }
            s = br.readLine();
        }
//        System.out.println(dic);
        Queue<String> q = new LinkedList<>();
        visited.add(start);
        q.add(start);
        while (!q.isEmpty()){
//            System.out.println(q);
            int temp = q.size();
            for (int i=0; i < temp; i++){
                String cur = q.remove();
//                System.out.println(cur);
                if (cur.equals(end)){
                    System.out.println(counter);
                    return;
                }
//                System.out.println("BFS: " + bfs(cur));
                for (String j : bfs(cur)){
                    if (!visited.contains(j)){
                        visited.add(j);
                        q.add(j);
                    }
                }
            }
            counter++;
        }
        pw.close();
    }
}

