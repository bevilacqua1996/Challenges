package bevilacqua1996.challenges;

import java.util.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
    }

}

class RandomizedCollection {

    private Map<Integer, Integer> mapToCount;
    private List<Integer> list;
    private Random random;

    public RandomizedCollection() {
        this.list = new ArrayList<>();
        this.mapToCount = new HashMap<>();
        this.random = new Random();
    }

    public boolean insert(int val) {
        boolean isPresent = !mapToCount.containsKey(val);

        mapToCount.merge(val, 1, Integer::sum);
        list.add(val);
        return isPresent;
    }

    public boolean remove(int val) {
        if(!mapToCount.containsKey(val)) {
            return false;
        }

        mapToCount.merge(val, -1, Integer::sum);

        if(mapToCount.get(val)==0) mapToCount.remove(val);
        list.remove(Integer.valueOf(val));

        return true;
    }

    public int getRandom() {
        int indexRandom = random.nextInt(list.size());
        return list.get(indexRandom);
    }
}

/**
 * Your RandomizedCollection object will be instantiated and called as such:
 * RandomizedCollection obj = new RandomizedCollection();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */

class RandomizedSet {

    private Set<Integer> set;
    private Random random;
    private Integer[] arrayNumbers;

    public RandomizedSet() {
        this.set = new HashSet<>();
        this.random = new Random();
    }

    public boolean insert(int val) {
        if (set.contains(val)) {
            return false;
        } else {
            set.add(val);
            return true;
        }
    }

    public boolean remove(int val) {
        if (!set.contains(val)) {
            return false;
        } else {
            set.remove(val);
            return true;
        }
    }

    public int getRandom() {
        arrayNumbers = set.toArray(new Integer[set.size()]);
        return arrayNumbers[random.nextInt(set.size())];
    }
}

/**
 * Your RandomizedSet object will be instantiated and called as such:
 * RandomizedSet obj = new RandomizedSet();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */