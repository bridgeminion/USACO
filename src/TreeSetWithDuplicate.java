import java.util.TreeMap;

public class TreeSetWithDuplicate<T extends Comparable<T>> {
    private final TreeMap<T, Integer> map;

    public TreeSetWithDuplicate() {
        map = new TreeMap<>();
    }

    public T floorKey(T t) {
        return map.floorKey(t);
    }

    public T ceilingKey(T t) {
        return map.ceilingKey(t);
    }

    public void remove(T t) {
        if (map.get(t) == 1) {
            map.remove(t);
        } else {
            map.put(t, map.get(t) - 1);
        }
    }

    public void insert(T t) {
        map.putIfAbsent(t, 0);
        map.put(t, map.get(t) + 1);
    }

}
