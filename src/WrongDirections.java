import java.io.*;
import java.util.*;

class Coordinate{
    int x;
    int y;
    public Coordinate(int x, int y){
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinate that = (Coordinate) o;
        return x == that.x &&
                y == that.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}

public class WrongDirections {

    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new FileReader("cowqueue.in"));
//        PrintWriter pw = new PrintWriter(new FileWriter("cowqueue.out"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        String s = st.nextToken();
        int endx = 0;
        int endy = 0;
        int endd = 0;
        List<Coordinate> pos = new ArrayList<>(s.length());
        List<Integer> dir = new ArrayList<>(s.length());
        for (int i=0; i < s.length(); i++){
            if (s.charAt(i) == 'F'){
                if (endd == 0){
                    endy++;
                }
                else if (endd == 1){
                    endx++;
                }
                else if (endd == 2){
                    endy--;
                }
                else {
                    endx--;
                }
            }
            else if (s.charAt(i) == 'R'){
                endd = (endd + 1)%4;
            }
            else {
                endd = (endd + 3)%4;
            }
            pos.add(new Coordinate(endx, endy));
            dir.add(endd);
        }
        endx = pos.get(s.length()-1).x;
        endy = pos.get(s.length()-1).y;
        Set<Coordinate> ans = new HashSet<>();
        for (int i=0; i < s.length(); i++){
            int x = pos.get(i).x;
            int y = pos.get(i).y;
            int d = dir.get(i);
            int dx = endx - x;
            int dy = endy - y;
            if (s.charAt(i) == 'F'){
                if (d == 0){
                    y--;
                }
                else if (d == 1){
                    x--;
                }
                else if (d == 2){
                    y++;
                }
                else {
                    x++;
                }
                // change to L
                int nendx = x - dy;
                int nendy = y + dx;
                ans.add(new Coordinate(nendx, nendy));
                // change to R
                nendx = x + dy;
                nendy = y - dx;
                ans.add(new Coordinate(nendx, nendy));
            }
            else if (s.charAt(i) == 'R'){
                // change to L
                int nendx = x - dx;
                int nendy = y - dy;
                ans.add(new Coordinate(nendx, nendy));
                // change to F
                if (d == 0){
                    x--;
                }
                else if (d == 1){
                    y++;
                }
                else if (d == 2){
                    x++;
                }
                else {
                    y--;
                }
                nendx = x - dy;
                nendy = y + dx;
                ans.add(new Coordinate(nendx, nendy));
            }
            else {
                // change to R
                int nendx = x - dx;
                int nendy = y - dy;
                ans.add(new Coordinate(nendx, nendy));
                // change to F
                if (d == 0){
                    x++;
                }
                else if (d == 1){
                    y--;
                }
                else if (d == 2){
                    x--;
                }
                else {
                    y++;
                }
                nendx = x + dy;
                nendy = y - dx;
                ans.add(new Coordinate(nendx, nendy));
            }
        }
        System.out.println(ans.size());
        pw.close();
    }
}
