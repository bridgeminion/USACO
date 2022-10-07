import java.io.*;
import java.util.StringTokenizer;

public class CowTag {

    public static double distance (int x1, int x2, int y1, int y2){
        double k = (Math.abs(x1- x2)*Math.abs(x1- x2) + Math.abs(y1-y2)*Math.abs(y1-y2));
        return Math.sqrt(k);
    }

    public static void main(String[] args) throws IOException {

//        BufferedReader br = new BufferedReader(new FileReader("citystate.in"));
//        PrintWriter pw = new PrintWriter(new FileWriter("citystate.out"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int[] x = new int[n];
        int[] y = new int[n];
        boolean[] tagged = new boolean[n];
        for (int i=0; i < n; i++){
            StringTokenizer st1 = new StringTokenizer(br.readLine());
            x[i] = Integer.parseInt(st1.nextToken());
            y[i] = Integer.parseInt(st1.nextToken());
            tagged[i] = false;
        }
        int index = 0;
        int i = 0;
        while(true){
            i = i%n;
            if (!tagged[i]){
                double least = 99999999;
                for (int j=0; j < n; j++){
                    if (distance(x[i], x[j], y[i], y[j]) < least && !tagged[j] && j != i){
                        least = distance(x[i], x[j], y[i], y[j]);
                        index = j;
                    }
                }
                tagged[index] = true;
            }
            int counter = 0;
            int ans = 0;
            for (int j=0; j < n; j++){
                if (!tagged[j]){
                    counter++;
                    ans = j;
                }
            }
            if (counter == 1){
                System.out.println(ans + 1);
                return;
            }
            i++;
        }
    }
}
