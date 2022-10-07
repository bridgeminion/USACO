import java.io.*;
import java.util.*;

public class Cheat {

    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new FileReader("cowqueue.in"));
//        PrintWriter pw = new PrintWriter(new FileWriter("cowqueue.out"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int num_players = Integer.parseInt(st.nextToken());
        int num_cards = Integer.parseInt(st.nextToken());
        int num_replace = Integer.parseInt(st.nextToken());
        Deque<Integer> stack = new LinkedList<>();
        for (int i=1; i <= num_cards; i++){
            stack.add(i);
        }
        List<Integer> ans = new ArrayList<>();
        int counter = 1;
        while (!stack.isEmpty()){
            if (counter % num_players == 0){
                ans.add(stack.pollFirst());
                if (!stack.isEmpty()){
                    for (int i=0; i < num_replace; i++){
                        int temp = stack.pollFirst();
                        stack.addLast(temp);
                    }
                }
                counter++;
            }
            else{
                stack.pollFirst();
                if (!stack.isEmpty()){
                    for (int i=0; i < num_replace; i++){
                        int temp = stack.pollFirst();
                        stack.addLast(temp);
                    }
                }
                counter++;
            }
        }
        Collections.sort(ans);
        for (int i : ans){
            pw.println(i);
        }
        pw.close();
    }

}
