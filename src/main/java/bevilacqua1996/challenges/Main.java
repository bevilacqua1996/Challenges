package bevilacqua1996.challenges;

import java.util.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        StringAlike stringAlike = new StringAlike();
        System.out.println(stringAlike.halvesAreAlike("textbook"));
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
    }

}

class StringAlike {
    public boolean halvesAreAlike(String s) {
        int n = s.length();
        int middle = n/2;

        int vowelsA = 0;
        int vowelsB = 0;

        String a = s.substring(0, middle);
        String b = s.substring(middle, n);

        for(int i=0; i<a.length(); i++) {
            if(checkVowels(a.charAt(i))) {
                vowelsA ++;
            }
        }

        for(int i=0; i<b.length(); i++) {
            if(checkVowels(b.charAt(i))) {
                vowelsB ++;
            }
        }

        return vowelsB == vowelsA;
    }

    public boolean checkVowels(char c) {
        return c == 'a'
                || c=='e'
                || c=='i'
                || c=='o'
                || c=='u'
                || c=='A'
                || c=='E'
                || c=='I'
                || c=='O'
                || c=='U';
    }
}

class WiggleSort {
    public void wiggleSort(int[] nums) {
        int[] arrayCopy = nums.clone();

        Arrays.sort(arrayCopy);

        int n = nums.length;
        int middle = (n-1)/2;
        int end = (n-1);

        for(int i=0; i<n; i++) {
            if(i%2==0) {
                nums[i] = arrayCopy[middle--];
            } else {
                nums[i] = arrayCopy[end--];
            }
        }

    }
}

class LongestPalindromicSubstring {
    public String longestPalindrome(String str) {

        if (str == null || str.length() == 0) {
            return "";
        }

        // Transforma a string original adicionando caracteres '#' entre cada caractere
        String processada = preProcessar(str);

        int n = processada.length();
        int[] p = new int[n];  // Array para armazenar os tamanhos dos palíndromos centrados em cada posição
        int centro = 0;        // Posição do centro do palíndromo atual
        int direita = 0;       // Extensão à direita do palíndromo atual

        for (int i = 0; i < n; i++) {
            int espelho = 2 * centro - i;

            // Calcula o tamanho do palíndromo centrado em i
            if (i < direita) {
                p[i] = Math.min(direita - i, p[espelho]);
            } else {
                p[i] = 0;
            }

            // Expande o palíndromo centrado em i
            while (i + p[i] + 1 < n && i - p[i] - 1 >= 0 && processada.charAt(i + p[i] + 1) == processada.charAt(i - p[i] - 1)) {
                p[i]++;
            }

            // Atualiza o centro e a extensão à direita, se necessário
            if (i + p[i] > direita) {
                centro = i;
                direita = i + p[i];
            }
        }

        // Encontra a posição e tamanho do maior palíndromo
        int maxPos = 0;
        int maxTam = 0;
        for (int i = 0; i < n; i++) {
            if (p[i] > maxTam) {
                maxTam = p[i];
                maxPos = i;
            }
        }

        // Recupera o maior palíndromo original da string processada
        int inicio = (maxPos - maxTam) / 2;
        return str.substring(inicio, inicio + maxTam);

    }

    private static String preProcessar(String str) {
        StringBuilder sb = new StringBuilder("#");
        for (char c : str.toCharArray()) {
            sb.append(c).append("#");
        }
        return sb.toString();
    }
}

class UniqueOccurrences {

    public boolean uniqueOccurrences(int[] arr) {
        Integer[] integerArray = Arrays.stream(arr).boxed().toArray(Integer[]::new);
        Set<Integer> setTest = new HashSet<>();
        List<Integer> occurrences = new ArrayList<>();
        Map<Integer, Integer> numbersAndOccurrences = new HashMap<>();

        for (Integer element : integerArray) {
            numbersAndOccurrences.put(element, numbersAndOccurrences.getOrDefault(element, 0) + 1);
        }

        numbersAndOccurrences.forEach((element, quantity) -> occurrences.add(quantity));

        for(Integer occurrence : occurrences) {
            if(!setTest.add(occurrence)) {
                return false;
            }
        }

        return true;
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