import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class MinNum {

    static String ans = "9";

    public static void recurse(String s, String password, List<String> nums, int index){
        if (s.length() > password.length()){
            return;
        }
        if (s.length() == password.length()){
            boolean match = true;
            for (int i=0; i < s.length(); i++){
                if (!(password.charAt(i) == '?' || password.charAt(i) == s.charAt(i))){
                    match = false;
                    break;
                }
            }
            if (match && (int)s.charAt(0) < (int)ans.charAt(0)){
                ans = s;
            }
            return;
        }
        boolean match = true;
        for (int i=0; i < s.length(); i++){
            if (!(password.charAt(i) == '?' || password.charAt(i) == s.charAt(i))){
                match = false;
                break;
            }
        }
        if (match){
            for (int i=0; i < nums.size(); i++){
                recurse(s + nums.get(i), password, nums, s.length());
            }
        }

    }

    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new FileReader("berries.in"));
//        PrintWriter pw = new PrintWriter(new FileWriter("berries.out"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int length = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());
        StringTokenizer st1 = new StringTokenizer(br.readLine());
        String password = st1.nextToken();
        List<String> nums = new ArrayList<>();
        for (int i=0; i < n; i++){
            StringTokenizer st2 = new StringTokenizer(br.readLine());
            nums.add(st2.nextToken());
        }
        Collections.sort(nums);
        for (int i=0; i < n; i++){
            recurse(nums.get(i), password, nums,0);
        }
        if(ans.equals("9")){
            System.out.println("NO SOLUTION");
            return;
        }
        System.out.println(ans);
    }
}
