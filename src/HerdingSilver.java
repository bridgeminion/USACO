import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class HerdingSilver {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("herding.in"));
        PrintWriter pw = new PrintWriter(new FileWriter("herding.out"));
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        int[] pos = new int[n];
        for (int i=0; i < n; i++){
            pos[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(pos);
        int closest = 0;
        int index = -1;
        for (int i=0; i < n; i++){
            int cur = 0;
            for (int j=i+1; j < n; j++){
                if (pos[j] - pos[i] < n){
                    cur++;
                }
                else{
                    break;
                }
            }
            if (cur > closest){
                closest = cur;
                index = i;
            }
        }
        boolean consecutive = true;
        for (int i=index; i < index+closest; i++){
            if (pos[i+1] - pos[i] != 1){
                consecutive = false;
                break;
            }
        }
        int min;
        if (consecutive){
            min = n - closest;
        }
        else {
            min = n - closest - 1;
        }
        pw.println(min);
        int max = pos[n-1] - pos[0] - Math.min(pos[1] - pos[0], pos[n-1] - pos[n-2]) - n + 2;
        pw.println(max);
        pw.close();
    }
}
