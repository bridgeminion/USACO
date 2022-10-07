public class BinaryIndexedTree {

    static int[] bit;

    public BinaryIndexedTree (int n) {
        bit = new int[n+1];
    }

    public void updateBIT (int val, int index){
        index++;
        while (index < bit.length){
            bit[index] += val;
            index = getNext(index);
        }
    }

    public int getSum (int index){
        index++;
        int sum = 0;
        while (index > 0){
            sum += bit[index];
            index = getParent(index);
        }
        return sum;
    }

    public void createTree (int input[]){
        for (int i=1; i <= input.length; i++){
            updateBIT(input[i-1], i);
        }
    }

    private int getParent (int index){
        return index - (index & -index);
    }

    private int getNext (int index){
        return index + (index & -index);
    }

}