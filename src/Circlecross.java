import java.io.*;
import java.util.*;

public class Circlecross {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("circlecross.in"));
        PrintWriter pw = new PrintWriter(new FileWriter("circlecross.out"));
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        List<Integer> list = new ArrayList<>(2*n);
        for (int i=0; i < 2*n; i++){
            list.add(Integer.parseInt(br.readLine()));
        }
        List<Integer> sorted = new ArrayList<>();
        int counter = 1;
        Set<Integer> visited = new HashSet<>();
        int[] num = new int[n+1];
        for (int i : list){
            if (visited.contains(i)){
                sorted.add(num[i]);
            }
            else {
                visited.add(i);
                sorted.add(counter);
                num[i] = counter;
                counter++;
            }
        }
        List<Integer> temp = new ArrayList<>();
        int ans = 0;
        for (int i : sorted){
            int index = Collections.binarySearch(temp, i);
            if (index >= 0){
                ans += temp.size() - index - 1;
                temp.remove(index);
            }
            else {
                temp.add(i);
            }
        }
        pw.println(ans);
        pw.close();
    }
}
