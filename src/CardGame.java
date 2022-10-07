import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class CardGame {

    static class Card {
        int value;
        boolean bes;

        public Card(int value, boolean bes) {
            this.value = value;
            this.bes = bes;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("cardgame.in"));
        PrintWriter pw = new PrintWriter(new FileWriter("cardgame.out"));
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        boolean[] who = new boolean[2*n+1];
        List<Card> first = new ArrayList<>();
        List<Card> second = new ArrayList<>();
        for (int i=0; i < n/2; i++){
            int temp = Integer.parseInt(br.readLine());
            who[temp] = true;
            first.add(new Card(temp, false));
        }
        for (int i=0; i < n/2; i++){
            int temp = Integer.parseInt(br.readLine());
            who[temp] = true;
            second.add(new Card (temp, false));
        }
        int counter = 0;
        for (int i=1; i <= 2*n; i++){
            if (!who[i]){
                if (counter < n/2){
                    second.add(new Card(i, true));
                }
                else {
                    first.add(new Card(i, true));
                }
                counter++;
            }
        }
        Collections.sort(first, new Comparator<Card>() {
            @Override
            public int compare(Card o1, Card o2) {
                return o2.value - o1.value;
            }
        });
        Collections.sort(second, new Comparator<Card>() {
            @Override
            public int compare(Card o1, Card o2) {
                return o1.value - o2.value;
            }
        });
        int ans = 0;
        int num_1 = 0;
        for (Card i : first){
            if (i.bes){
                num_1++;
            }
            else {
                if (num_1 > 0){
                    num_1--;
                    ans++;
                }
            }
        }
        int num_2 = 0;
        for (Card i : second){
            if (i.bes){
                num_2++;
            }
            else {
                if (num_2 > 0){
                    num_2--;
                    ans++;
                }
            }
        }
        pw.println(ans);
        pw.close();
    }
}
