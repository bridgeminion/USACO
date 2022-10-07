import java.io.*;
import java.util.StringTokenizer;

public class Odometer {

    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new FileReader("cowqueue.in"));
//        PrintWriter pw = new PrintWriter(new FileWriter("cowqueue.out"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        String start = st.nextToken();
        String end = st.nextToken();
        int startLen = numDigits(start);
        int endLen = numDigits(end);
//        System.out.println(startLen);
////        System.out.println(endLen);
        String newStart = start;
        for (int i=startLen; i < endLen; i++){
            newStart = "0" + newStart;
        }
//        System.out.println(newStart);
        int counter = 0;
        for (int length = startLen; length < endLen+1; length++){
            for (int main = 0; main < 10; main++){
                for (int rep = 0; rep < 10; rep++){
                    for (int index = 0; index < length; index++){
                        if (rep != main){
                            if (!(rep == 0 && index == 0)){
                                if (!(main == 0 && index != 0)){
                                    String cur = "";
                                    for (int i=0; i < length; i++){
                                        if (i == index){
                                            cur += rep;
                                        }
                                        else{
                                            cur += main;
                                        }
                                    }
                                    for (int i=cur.length(); i < endLen; i++){
                                        cur = "0" + cur;
                                    }
                                    if (cur.compareTo(newStart) >= 0 && cur.compareTo(end) <= 0){
//                                        System.out.println(cur);
                                        counter++;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        System.out.println(counter);
    }

    private static int numDigits(String start) {
        Long temp = Long.parseLong(start);
        int counter = 0;
        while (temp > 0){
            temp = temp/10;
            counter++;
        }
        return(counter);
    }
}
