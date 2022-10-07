import java.io.*;
import java.util.*;

class Condition {
    int num;
    int res;
    public Condition (int num, int res){
        this.num = num;
        this.res = res;
    }
}

public class CowYahtzee {

    static List<List<Condition>> con = new ArrayList<>();
    static int counter = 0;

    public static void dfs (int num_rolls, int[] count, int num_dice, int num_sides){
        if (num_rolls == num_dice){
            if (check(count)){
                counter++;
            }
            return;
        }
        for (int i=0; i < num_sides; i++){
            count[i]++;
            dfs(num_rolls+1, count, num_dice, num_sides);
            count[i]--;
        }
    }

    public static boolean check (int[] count){
        boolean works = false;
        for (List<Condition> i : con){
            boolean con = true;
            for (Condition j : i){
                if (count[j.res-1] < j.num){
                    con = false;
                    break;
                }
            }
            if (con){
                works = true;
                break;
            }
        }
        return works;
    }

    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new FileReader("cowqueue.in"));
//        PrintWriter pw = new PrintWriter(new FileWriter("cowqueue.out"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int num_dice = Integer.parseInt(st.nextToken());
        int num_sides = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());
        for (int i=0; i < e; i++){
            StringTokenizer st1 = new StringTokenizer(br.readLine(), "+");
            List<Condition> temp = new ArrayList<>();
            while (st1.hasMoreTokens()){
                StringTokenizer st2 = new StringTokenizer(st1.nextToken(), "x");
                int num = Integer.parseInt(st2.nextToken());
                int res = Integer.parseInt(st2.nextToken());
                temp.add(new Condition(num, res));
            }
            con.add(temp);
        }
        int[] count = new int[num_sides];
        dfs(0, count, num_dice, num_sides);
        System.out.println(counter);
        pw.close();
    }
}
