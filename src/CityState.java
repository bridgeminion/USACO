import java.io.*;
import java.util.StringTokenizer;

public class CityState {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new FileReader("citystate.in"));
        PrintWriter pw = new PrintWriter(new FileWriter("citystate.out"));
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        char[][] city = new char[n][2];
        char[][] state = new char[n][2];
        for (int i=0; i < n; i++){
            StringTokenizer st1 = new StringTokenizer(br.readLine());
            String temp1 = st1.nextToken();
            for (int j=0; j < 2; j++){
                city[i][j] = temp1.charAt(j);
            }
            String temp2 = st1.nextToken();
            for (int j=0; j < 2; j++){
                state[i][j] = temp2.charAt(j);
            }
        }
        int counter = 0;
        for (int i=0; i < n; i++){
            for (int j=i+1; j < n; j++){
                boolean match = true;
                for (int k = 0; k < 2; k++){
                    if (city[i][k] != state[j][k]){
                        match = false;
                        break;
                    }
                    if (state[i][k] != city[j][k]){
                        match = false;
                        break;
                    }
                    if (state[i][0] == state[j][0] && state[i][1] == state[j][1]){
                        match = false;
                        break;
                    }
                }
                if (match){
                    counter++;
                }
            }
        }
        pw.println(counter);
        pw.close();
    }
}
