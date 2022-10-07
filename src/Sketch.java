import java.io.*;
import java.util.*;

public class Sketch {

    public static boolean check (List<Integer> list){
        int first = 10*list.get(0) + list.get(1);
        int second = list.get(2);
        int third = 10*list.get(3) + list.get(4);
        int fourth = 10*list.get(5) + list.get(6);
        int fifth = 10*list.get(7) + list.get(8);
        return first * second == third && third + fourth == fifth;
    }

    public static void generate (int cur, List<Integer> list, boolean[] used){
        if (cur==9){
            if (check(list)){
                for (int i=0; i < 9; i++){
                    System.out.print(list.get(i) + " ");
                }
                System.out.println();
            }
            return;
        }
        for (int i=1; i <= 9; i++){
            if (!used[i]){
                list.add(i);
                used[i] = true;
                generate(cur+1, list, used);
                used[i] = false;
                list.remove(cur);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        List<Integer> list = new ArrayList<>();
        for (int i=1; i <= 9; i++) {
            list.add(i);
        }
        generate (0, new ArrayList<>(), new boolean[10]);
    }
}
