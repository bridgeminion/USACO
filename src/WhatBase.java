import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class WhatBase {

    static class Pair {
        int a;
        int b;
        public Pair (int a, int b){
            this.a = a;
            this.b = b;
        }
    }

    static int y = 0;

    public static boolean bs (int input, int target){
        int low = 10;
        int high = 15000;
        while (low <= high) {
            int mid = (high+low)/2;
            if (get (input, mid) == target){
                y = mid;
                return true;
            } else if (get (input, mid) < target){
                low = mid+1;
            }
            else {
                high = mid-1;
            }
        }
        return false;
    }

    public static int get (int input, int b){
        return input/100 *b*b + (input/10)%10 * b + input%10;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("whatbase.in"));
        PrintWriter pw = new PrintWriter(new FileWriter("whatbase.out"));
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        List<Pair> input = new ArrayList<>(n);
        for (int i=0; i < n; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            input.add(new Pair (x, y));
        }
        for (Pair i : input){
            for (int j=10; j <= 15000; j++){
                int temp = get (i.a, j);
                y = -1;
                if (bs (i.b, temp)){
                    pw.println(j + " " + y);
                    break;
                }
            }
        }
        pw.close();
    }
}
