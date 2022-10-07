import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.*;

public class Scramble {

    public static String alpha(String input){
        char tempArray[] = input.toCharArray();
        Arrays.sort(tempArray);
        return new String (tempArray);
    }

    public static String reverse(String input){
        char[] tempArray = input.toCharArray();
        Arrays.sort(tempArray);
        char[] output = new char[tempArray.length];
        for (int i=0; i < tempArray.length; i++){
            output[i] = tempArray[tempArray.length-i-1];
        }
        return new String (output);
    }

    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new FileReader("haybales.in"));
//        PrintWriter pw = new PrintWriter(new FileWriter("haybales.out"));
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        Scanner sc = new Scanner(System.in);
        StringTokenizer st = new StringTokenizer(sc.nextLine());
        int n = Integer.parseInt(st.nextToken());
        String[] min = new String[n];
        String[] max = new String[n];
        String[] sorted_min = new String[n];
        String[] sorted_max = new String[n];
        for (int i=0; i < n; i++){
            StringTokenizer st1 = new StringTokenizer(sc.nextLine());
            String name = st1.nextToken();
            min[i] = alpha(name);
            max[i] = reverse(name);
            sorted_min[i] = min[i];
            sorted_max[i] = max[i];

        }
        Arrays.sort(sorted_min);
        Arrays.sort(sorted_max);
        int[] min_ans = new int[n];
        int[] max_ans = new int[n];
        for (int i=0; i < n; i++){
            int temp = Arrays.binarySearch(sorted_max, min[i]);
            if (temp < 0){
                min_ans[i] = -(temp + 1) + 1;
            }
            else{
                min_ans[i] = temp + 1;
            }
        }
        for (int i=0; i < n; i++){
            int temp = Arrays.binarySearch(sorted_min, max[i]);
            if (temp < 0){
                max_ans[i] = -(temp + 1);
            }
            else{
                max_ans[i] = temp + 1;
            }
        }
        for (int i=0; i < n; i++){
            pw.print(min_ans[i]);
            pw.print(" ");
            pw.print(max_ans[i]);
            pw.println();
        }
        pw.close();
    }
}
