import java.io.*;
import java.util.StringTokenizer;

public class Moo {

    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new FileReader("moobuzz.in"));
//        PrintWriter pw = new PrintWriter(new FileWriter("moobuzz.out"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int index = 0;
        int[] length = new int[29];
        length[0] = 3;
        for (int i=1; i < 29; i++){
            length[i] = 2*length[i-1] + i + 3;
        }
        for (int i=0; i < 29; i++){
            System.out.println(length[i]);
        }
        for (int i=1; i < 29; i++){
            if (length[i-1] < n && length[i] >= n){
                index = i;
            }
        }
        while (n > 3){
            System.out.println("n = " + n);
            System.out.println("index = " + index);
            System.out.println("previous = " + length[index-1]);
            if (n-length[index-1] < index+3){
                if (n == length[index-1] + 1){
                    System.out.println("m");
                    return;
                }
                else{
                    System.out.println("o");
                    return;
                }
            }
            else{
                n = n - length[index-1] - index - 3;
                for (int i=1; i < 29; i++){
                    if (length[i-1] < n && length[i] >= n){
                        index = i;
                    }
                }
            }
        }
        if (n == 1){
            System.out.println("m");
        }
        else{
            System.out.println("o");
        }
    }
}
