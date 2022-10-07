import java.io.*;
import java.util.Arrays;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Baseball {

    public static int UpperBound (int[] array, int val){

        int low = 0;
        int high = array.length;
        while (low < high){
            int mid = (low + high)/2;
            if (array[mid] < val){
                low = mid + 1;
            }
            else if (array[mid] > val){
                high = mid;
            }
            else{
                int i = 1;
                while (mid + i < array.length && array[mid+i] == val){
                    i++;
                }
                return mid+i;
            }
        }
        return low;
    }

    public static int LowerBound (int[] array, int val){

        int low = 0;
        int high = array.length;
        while (low < high){
            int mid = (low + high)/2;
            if (array[mid] < val){
                low = mid + 1;
            }
            else if (array[mid] > val){
                high = mid;
            }
            else{
                int i = -1;
                while (mid + i > 0 && array[mid+i] == val){
                    i--;
                }
                return mid+i+1;
            }
        }
        return low;
    }

    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new FileReader("haybales.in"));
//        PrintWriter pw = new PrintWriter(new FileWriter("haybales.out"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        int num_cows;
        StringTokenizer st = new StringTokenizer(br.readLine());
        num_cows = Integer.parseInt(st.nextToken());
        int[] pos = new int[num_cows];
        for (int i=0; i < num_cows; i++){
            StringTokenizer st1 = new StringTokenizer(br.readLine());
            pos[i] = Integer.parseInt(st1.nextToken());
        }
        Arrays.sort(pos);
        int ans = 0;
        for (int i=0; i < num_cows; i++){
            for (int j=i+1; j < num_cows; j++){
                int start = pos[j] + (pos[j]-pos[i]);
                int end = pos[j] + (pos[j]-pos[i])*2;
                int temp1 = UpperBound(pos, end);
                int temp2 = LowerBound(pos, start);
                ans += temp1 - temp2;
            }
        }
        pw.println(ans);
        pw.close();
    }
}
