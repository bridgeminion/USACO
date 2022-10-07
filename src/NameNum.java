import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;
import java.util.Scanner;

public class NameNum {

    static int number = 0;

    public static int NumDigits (long n){
        int counter = 0;
        while (n > 0){
            n = n/10;
            counter++;
        }
        return counter;
    }

    public static int convert (char a){
        if (a < 'Q'){
            return ((a - 'A')/3 + 2);
        }
        else{
            return ((a - 'A' - 1)/3 + 2);
        }
    }

    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new FileReader("haybales.in"));
//        PrintWriter pw = new PrintWriter(new FileWriter("haybales.out"));
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        Scanner sc = new Scanner(System.in);
        StringTokenizer st = new StringTokenizer(sc.nextLine());
        Long n = Long.parseLong(st.nextToken());
        List<String> dict = new ArrayList<>();
        String a;
        while (sc.hasNextLine()){
            a = sc.nextLine();
            if (!"".equals(a)) {
                st = new StringTokenizer(a);
                String name = st.nextToken();
                dict.add(name);
            } else {
                break;
            }
        }
        Collections.sort(dict);
        for (String i : dict){
            if (i.length() == NumDigits(n)){
                boolean match = true;
                for (int j=0; j < i.length(); j++){
                    if (convert(i.charAt(j)) != (n/(long)Math.pow(10, NumDigits(n) - j - 1))%10){
                        match = false;
                        break;
                    }
                }
                if (match){
                    pw.println(i);
                    number++;
                }
            }
        }
        if (number == 0){
            pw.println("NONE");
        }
        pw.close();
    }
}
