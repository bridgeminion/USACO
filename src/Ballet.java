import java.io.*;
import java.util.*;

public class Ballet {

    public static boolean check (int[] curx, int[] cury){
        for (int i=0; i < 4; i++){
            for (int j=0; j < 4; j++){
                if (i!=j){
                    if ((curx[i] == curx[j]) && (cury[i] == cury[j])){
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public static void main(String[] args) throws IOException {

//        BufferedReader br = new BufferedReader(new FileReader("measurement.in"));
//        PrintWriter pw = new PrintWriter(new FileWriter("measurement.out"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        int[] foot = new int[n];
        char[] dir = new char[n];
        for (int i=0; i < n; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            String temp = st.nextToken();
            if (temp.charAt(0) == 'F' && temp.charAt(1) == 'L'){
                foot[i] = 0;
            }
            else if (temp.charAt(0) == 'F' && temp.charAt(1) == 'R'){
                foot[i] = 1;
            }
            else if (temp.charAt(0) == 'R' && temp.charAt(1) == 'R'){
                foot[i] = 2;
            }
            else {
                foot[i] = 3;
            }
            dir[i] = temp.charAt(2);
        }
        int minx = 1001;
        int miny = 1001;
        int maxx = -1001;
        int maxy = -1001;
        int[] curx = {0, 1, 1, 0};
        int[] cury = {1, 1, 0, 0};
        int facing = 0;
        for (int i=0; i < n; i++){
            if (facing == 0){
                if (dir[i] == 'F'){
                    cury[foot[i]]++;
                }
                else if (dir[i] == 'B'){
                    cury[foot[i]]--;
                }
                else if (dir[i] == 'L'){
                    curx[foot[i]]--;
                }
                else if (dir[i] == 'R'){
                    curx[foot[i]]++;
                }
                else { //pivot
                    int x = curx[foot[i]];
                    int y = cury[foot[i]];
                    for (int j=0; j < 4; j++){
                        int dx = x - curx[j];
                        int dy = y - cury[j];
                        curx[j] = x - dy;
                        cury[j] = y + dx;
                    }
                    facing = (facing + 1)%4;
                }
            }
            else if (facing == 1){
                if (dir[i] == 'F'){
                    curx[foot[i]]++;
                }
                else if (dir[i] == 'B'){
                    curx[foot[i]]--;
                }
                else if (dir[i] == 'L'){
                    cury[foot[i]]++;
                }
                else if (dir[i] == 'R'){
                    cury[foot[i]]--;
                }
                else { //pivot
                    int x = curx[foot[i]];
                    int y = cury[foot[i]];
                    for (int j=0; j < 4; j++){
                        int dx = x - curx[j];
                        int dy = y - cury[j];
                        curx[j] = x - dy;
                        cury[j] = y + dx;
                    }
                    facing = (facing + 1)%4;
                }
            }
            else if (facing == 2){
                if (dir[i] == 'F'){
                    cury[foot[i]]--;
                }
                else if (dir[i] == 'B'){
                    cury[foot[i]]++;
                }
                else if (dir[i] == 'L'){
                    curx[foot[i]]++;
                }
                else if (dir[i] == 'R'){
                    curx[foot[i]]--;
                }
                else { //pivot
                    int x = curx[foot[i]];
                    int y = cury[foot[i]];
                    for (int j=0; j < 4; j++){
                        int dx = x - curx[j];
                        int dy = y - cury[j];
                        curx[j] = x - dy;
                        cury[j] = y + dx;
                    }
                    facing = (facing + 1)%4;
                }
            }
            else {
                if (dir[i] == 'F'){
                    curx[foot[i]]--;
                }
                else if (dir[i] == 'B'){
                    curx[foot[i]]++;
                }
                else if (dir[i] == 'L'){
                    cury[foot[i]]--;
                }
                else if (dir[i] == 'R'){
                    cury[foot[i]]++;
                }
                else { //pivot
                    int x = curx[foot[i]];
                    int y = cury[foot[i]];
                    for (int j=0; j < 4; j++){
                        int dx = x - curx[j];
                        int dy = y - cury[j];
                        curx[j] = x - dy;
                        cury[j] = y + dx;
                    }
                    facing = (facing + 1)%4;
                }
            }
            if (!check(curx, cury)){
                System.out.println(-1);
                return;
            }
            for (int j=0; j < 4; j++){
                System.out.println("x = " + curx[j]);
                System.out.println("y = " + cury[j]);
                if (curx[j] < minx){
                    minx = curx[j];
                }
                if (curx[j] > maxx){
                    maxx = curx[j];
                }
                if (cury[j] < miny){
                    miny = cury[j];
                }
                if (cury[j] > maxy){
                    maxy = cury[j];
                }
            }
            System.out.println();
        }
        System.out.println((maxx - minx + 1)*(maxy - miny + 1));
    }
}
