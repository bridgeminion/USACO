import java.io.*;

public class FuelEconomy {

    static class Fuel {
        int pos;
        int cost;
        public Fuel (int pos, int cost){
            this.pos = pos;
            this.cost = cost;
        }
    }

    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new FileReader("berries.in"));
//        PrintWriter pw = new PrintWriter(new FileWriter("berries.out"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        int g = Integer.parseInt(br.readLine());
        int b = Integer.parseInt(br.readLine());
        int d = Integer.parseInt(br.readLine());

    }
}
