import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class Person {
    String name;
    int giftMoney;
    int numPeople;
    List<String> rec;
    int account;
    public Person(String name, int giftMoney, int numPeople, List<String> rec, int account){
        this.name = name;
        this.giftMoney = giftMoney;
        this.numPeople = numPeople;
        this.rec = rec;
        this.account = account;
    }
}

public class GiftGivers {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new FileReader("gift1.in"));
        PrintWriter pw = new PrintWriter(new FileWriter("gift1.out"));
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        List<Person> people = new ArrayList<>();
        List<String> order = new ArrayList<>();
        for (int i=0; i < n; i++){
            StringTokenizer st4 = new StringTokenizer(br.readLine());
            order.add(st4.nextToken());
        }
        for (int i=0; i < n; i++){
            StringTokenizer st1 = new StringTokenizer(br.readLine());
            String name = st1.nextToken();
            StringTokenizer st2 = new StringTokenizer(br.readLine());
            int giftMoney = Integer.parseInt(st2.nextToken());
            int numPeople = Integer.parseInt(st2.nextToken());
            List<String> names = new ArrayList<>();
            for (int j=0; j < numPeople; j++){
                StringTokenizer st3 = new StringTokenizer(br.readLine());
                names.add(st3.nextToken());
            }
            Person temp = new Person(name, giftMoney, numPeople, names, -giftMoney);
            people.add(temp);
        }
        for (int i=0; i < n; i++){
            if (people.get(i).numPeople == 0){
                people.get(i).account += people.get(i).giftMoney;
            }
            else{
                int amountGiven = people.get(i).giftMoney/people.get(i).numPeople;
                people.get(i).account += people.get(i).giftMoney % people.get(i).numPeople;
                for (String j : people.get(i).rec){
                    for (int k=0; k < n; k++){
                        if (people.get(k).name.equals(j)){
                            people.get(k).account += amountGiven;
                            break;
                        }
                    }
                }
            }
        }
        for (int i=0; i < n; i++){
            for (int j=0; j < n; j++){
                if (people.get(j).name.equals(order.get(i))){
                    pw.print(people.get(j).name);
                    pw.print(" ");
                    pw.print(people.get(j).account);
                    pw.println();
                    break;
                }
            }
        }
        pw.close();
    }
}
