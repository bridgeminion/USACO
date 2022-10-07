import java.io.*;
import java.util.StringTokenizer;

public class NewCityState {

    private static int convert(String input){
        return (input.charAt(0) - 'A')*26 + input.charAt(1) - 'A';
    }

    public static void main(String[] args) throws IOException {

//        BufferedReader br = new BufferedReader(new FileReader("citystate.in"));
//        PrintWriter pw = new PrintWriter(new FileWriter("citystate.out"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        String[] city = new String[n];
        String[] state = new String[n];
        int[][] big = new int[700][700];
        for (int i=0; i < n; i++){
            StringTokenizer st1 = new StringTokenizer(br.readLine());
            String temp1 = st1.nextToken();
            city[i] = "";
            for (int j=0; j < 2; j++){
                city[i] += temp1.charAt(j);
            }
            state[i] = st1.nextToken();
        }
        for (int i=0; i < n; i++){
            int row = convert(city[i]);
            int col = convert(state[i]);
            big[row][col]++;
        }
        int ans = 0;
        for (int i=0; i < 700; i++){
            for (int j=i; j < 700; j++){
                ans += big[i][j]*big[j][i];
            }
        }
        for (int i=0; i < 700; i++){
            ans -= big[i][i]*big[i][i];
        }
        pw.println(ans);
        pw.close();
    }
}
