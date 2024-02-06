package bevilacqua1996.challenges;

import java.util.*;
import java.util.stream.Collectors;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        System.out.println(GroupAnagrams.groupAnagrams(new String[]{"eat","tea","tan","ate","nat","bat"}));

        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
    }

}

class GroupAnagrams {
    public static List<List<String>> groupAnagrams(String[] strs) {
        List<String> listStrings = new ArrayList<>(Arrays.asList(strs));
        List<String> anagrams;
        List<List<String>> anagramsList = new ArrayList<>();
        while(!listStrings.isEmpty()) {
            anagrams = new ArrayList<>();
            String stringToCompare = listStrings.get(0);
            listStrings.remove(0);
            anagrams.add(stringToCompare);
            List<String> copyListString = new ArrayList<>(List.copyOf(listStrings));
            for(int i=0; i<listStrings.size(); i++) {
                String anotherStringToCompare = listStrings.get(i);
                if(stringToCompare.length() != anotherStringToCompare.length()) {
                    continue;
                }
                char[] compare1 = stringToCompare.toCharArray();
                char[] compare2 = anotherStringToCompare.toCharArray();
                Arrays.sort(compare1);
                Arrays.sort(compare2);
                String convertedStringToCompare = new String(compare1);
                String convertedAnotherStringToCompare = new String(compare2);
                if(convertedStringToCompare.equals(convertedAnotherStringToCompare)) {
                    anagrams.add(anotherStringToCompare);
                    copyListString.remove(anotherStringToCompare);
                }
            }
            listStrings = new ArrayList<>(copyListString);
            anagramsList.add(anagrams);
        }
        return anagramsList;
    }
}

class SequentialDigits {
    public static List<Integer> sequentialDigits(int low, int high) {
        int lowLength = (int) (Math.log10(low) + 1);
        int highLength = (int) (Math.log10(high) + 1);

        List<Integer> answer = new ArrayList<>();

        int lenght = lowLength;

        while(lenght<=highLength) {
            for(int i = 1; i<=10-lenght; i++) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(i);
                int j = i+1;
                while (stringBuilder.length()<lenght) {
                    stringBuilder.append(j);
                    j++;
                }
                int candidate = Integer.parseInt(stringBuilder.toString());
                if(candidate>=low && candidate<=high) {
                    answer.add(candidate);
                }
            }
            lenght++;

        }

        return answer;

    }
}

class DivideArray {
    public static int[][] divideArray(int[] nums, int k) {

        int matrixLength;

        if(nums.length%3 != 0) {
            return new int[][]{};
        } else {
            matrixLength = nums.length/3;
        }

        Arrays.sort(nums);

        int[][] formedList = new int[matrixLength][3];
        int countMax = 0;
        for(int i=0; i<nums.length; i++) {
            if(nums[i+1] - nums[i] <= k && nums[i+2] - nums[i]<=k) {
                formedList[countMax][0] = nums[i];
                formedList[countMax][1] = nums[i+1];
                formedList[countMax][2] = nums[i+2];
                i = i+2;
                countMax++;
            } else {
                return new int[][]{};
            }
        }

        return formedList;

    }
}

class WarmerTemperatures {
    public static int[] dailyTemperatures(int[] temperatures) {
        int n = temperatures.length;
        int[] answer = new int[n];
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && temperatures[i] > temperatures[stack.peek()]) {
                int index = stack.pop();
                answer[index] = i - index;
            }
            stack.push(i);
        }

        return answer;

    }
}

class FindDifference {
    public static List<List<Integer>> findDifference(int[] nums1, int[] nums2) {

        List<Integer> answer1 = new ArrayList<>();

        Set<Integer> nums1Set = Arrays.stream(nums1)
                .boxed()
                .collect(Collectors.toSet());

        Set<Integer> nums2Set = Arrays.stream(nums2)
                .boxed()
                .collect(Collectors.toSet());


        for(Integer nums1Value : nums1Set) {
            if(nums2Set.contains(nums1Value)) {
                nums2Set.remove(nums1Value);
                continue;
            } else {
                answer1.add(nums1Value);
            }
        }

        return List.of(answer1, new ArrayList<>(nums2Set));

    }
}

class MyQueue {

    public int head;
    public MyQueue tail;

    public MyQueue(int head, MyQueue tail) {
        this.head = head;
        this.tail = tail;
    }

    public MyQueue() {

    }

    public void push(int x) {
        if(this.head==0) {
            this.tail = new MyQueue(this.head, this.tail);
            this.head = x;
            return;
        }
        MyQueue newTail = this.tail;
        while(newTail.head!=0) {
            newTail = newTail.tail;
        }
        MyQueue lastTail = new MyQueue(newTail.head, newTail.tail);
        newTail.head = x;
        newTail.tail = lastTail;
    }

    public int pop() {
        int returnNumber = this.head;
        this.head = this.tail.head;
        this.tail = this.tail.tail;
        return returnNumber;
    }

    public int peek() {
        return this.head;
    }

    public boolean empty() {
        return this.tail==null && head==0;
    }
}

class PlaceFlowers {
    public static boolean canPlaceFlowers(int[] flowerbed, int n) {

        if(flowerbed.length==1) {
            if(flowerbed[0]==0 && n<=1) {
                return true;
            } else if(flowerbed[0] == 0) {
                return false;
            } else return flowerbed[0] == 1 && n == 0;
        }


        for(int i=0; i<flowerbed.length; i++) {
            if(i==0 || i==flowerbed.length-1) {
                if(i==0 && flowerbed[i]==0 && flowerbed[i+1]==0) {
                    n--;
                    flowerbed[i] = 1;
                } else if(i==flowerbed.length-1 && flowerbed[i]==0 && flowerbed[i-1]==0) {
                    n--;
                    flowerbed[i] = 1;
                }
                continue;
            }
            if(flowerbed[i]==0 && flowerbed[i-1]==0 && flowerbed[i+1]==0) {
                n--;
                flowerbed[i] = 1;
            } else if(flowerbed[i-1]==0) {
                i++;
            }
        }

        return n <= 0;

    }
}

class TripletSequence {
    public static boolean increasingTriplet(int[] nums) {
        int firstMin = Integer.MAX_VALUE;
        int secondMin = Integer.MAX_VALUE;

        for (int num : nums) {
            if (num <= firstMin) {
                firstMin = num;
            } else if (num <= secondMin) {
                secondMin = num;
            } else {
                return true;
            }
        }

        return false;
    }
}

class Factors {
    public static int getTotalX(List<Integer> a, List<Integer> b) {

        int betweenNumber = 0;
        int number = a.get(a.size()-1);
        int i=1;

        while(number*i <= b.get(0)) {
            boolean notFactor = false;
            for (Integer integer : a) {
                if ((number * i) % integer != 0) {
                    notFactor = true;
                    break;
                }
            }
            if(notFactor) {
                i++;
                continue;
            }
            for (Integer integer : b) {
                if (integer%(number * i)  != 0) {
                    notFactor = true;
                    break;
                }
            }
            if(notFactor) {
                i++;
                continue;
            }
            i++;
            betweenNumber++;
        }

        return betweenNumber;

    }
}

class SuperDigit {
    public static int superDigit(String n, int k) {
        long firstSum = (long) Integer.parseInt(superDigitOperation(n)) * k;
        String superDigit=String.valueOf(firstSum);

        while(superDigit.length() != 1) {
            superDigit = superDigitOperation(superDigit);
        }

        return Integer.parseInt(superDigit);
    }

    private static String superDigitOperation(String superDigit) {
        long superDigitSum = 0;
        for(int i=0; i<superDigit.length(); i++) {
            superDigitSum += Integer.parseInt(String.valueOf(superDigit.charAt(i)));
        }

        return String.valueOf(superDigitSum);

    }
}

class JumpingTheClouds {
    public static int jumpingOnClouds(List<Integer> c) {
        int jumps = 0;

        for(int i=0; i<c.size()-1; i++) {
            if(i+1==c.size()-1) {
                jumps++;
                break;
            }
            if(c.get(i+2)==0) {
                jumps++;
                i++;
            } else {
                jumps++;
            }
        }

        return jumps;

    }
}

class ClimbingTheLeaderboard {
    public static List<Integer> climbingLeaderboard(List<Integer> ranked, List<Integer> player) {
        Map<Integer, Integer> rankMap = new HashMap<>();
        int ranking = 0;
        List<Integer> playerPositions = new ArrayList<>();
        for(int i=0; i<ranked.size(); i++) {
            if(i!=0 && Objects.equals(ranked.get(i), ranked.get(i - 1))) {
                continue;
            } else {
                ranking++;
                rankMap.put(ranking, ranked.get(i));
            }
        }

        for (Integer score : player) {
            if (score < rankMap.get(rankMap.size())) {
                playerPositions.add(rankMap.size() + 1);
                continue;
            }
            for (Map.Entry<Integer, Integer> entry : rankMap.entrySet()) {
                if (score >= entry.getValue()) {
                    playerPositions.add(entry.getKey());
                    break;
                }
            }
        }

        return playerPositions;

    }
}

class PickingNumbers {
    public static int pickingNumbers(List<Integer> a) {
        Collections.sort(a);
        int subArrayLength;
        int maxSubArrayLength = 0;
        for(int i=0; i<a.size(); i++) {
            if(i!=0 && Objects.equals(a.get(i), a.get(i - 1))) {
                continue;
            }
            subArrayLength = 1;
            for(int j=i+1; j<a.size(); j++) {
                if(a.get(j) - a.get(i) <= 1) {
                    subArrayLength++;
                }
            }
            maxSubArrayLength = Math.max(subArrayLength, maxSubArrayLength);
        }

        return maxSubArrayLength;
    }
}

class MaxLengthConcatenatedString {
    public static int maxLength(List<String> arr) {
        int subwordSize = 0;

        arr.sort(Comparator.comparingInt(String::length));
        Collections.reverse(arr);

        for(int i=0; i<arr.size(); i++) {
            Set<String> duplicatedChar = new HashSet<>();
            StringBuilder subword = new StringBuilder();
            boolean duplicated = false;
            subword.append(arr.get(i));
            for(int count = 0; count<subword.length(); count++) {
                if(!duplicatedChar.add(String.valueOf(subword.charAt(count)))) {
                    duplicated = true;
                    break;
                }
            }
            if(duplicated) {
                continue;
            }
            subwordSize = Math.max(subwordSize, subword.length());
            for(int j=0; j<arr.size(); j++) {
                duplicatedChar = new HashSet<>();

                subword.append(arr.get(j));
                duplicated = checkCombinations(duplicatedChar, subword, arr, j);

                StringBuilder optionalSubword = new StringBuilder(arr.get(i) + arr.get(j));

                if(duplicated && subword.length()<(arr.get(i) + arr.get(j)).length()) {
                    duplicatedChar = new HashSet<>();
                    duplicated = checkCombinations(duplicatedChar, optionalSubword, arr, j);
                } else {
                    subwordSize = Math.max(subwordSize, subword.length());
                    duplicatedChar = new HashSet<>();
                    duplicated = checkCombinations(duplicatedChar, optionalSubword, arr, j);
                }

                if(duplicated) {
                    continue;
                }
                subword = subword.length()>optionalSubword.length() ? subword : optionalSubword;
                subwordSize = Math.max(subwordSize, subword.length());
            }
            subword = new StringBuilder(arr.get(i));
            for(int j=i+1; j<arr.size(); j++) {
                duplicatedChar = new HashSet<>();

                subword.append(arr.get(j));
                duplicated = checkCombinations(duplicatedChar, subword, arr, j);

                StringBuilder optionalSubword = new StringBuilder(arr.get(i) + arr.get(j));

                if(duplicated && subword.length()<(arr.get(i) + arr.get(j)).length()) {
                    duplicatedChar = new HashSet<>();
                    duplicated = checkCombinations(duplicatedChar, optionalSubword, arr, j);
                } else {
                    subwordSize = Math.max(subwordSize, subword.length());
                    duplicatedChar = new HashSet<>();
                    duplicated = checkCombinations(duplicatedChar, optionalSubword, arr, j);
                }

                if(duplicated) {
                    continue;
                }
                subword = subword.length()>optionalSubword.length() ? subword : optionalSubword;
                subwordSize = Math.max(subwordSize, subword.length());
            }
        }
        return subwordSize;
    }

    private static boolean checkCombinations(Set<String> duplicatedChar, StringBuilder subword, List<String> arr, int j) {
        boolean duplicated = false;
        for(int count = 0; count<subword.length(); count++) {
            if(!duplicatedChar.add(String.valueOf(subword.charAt(count)))) {
                duplicated = true;
                subword.replace(subword.length()-arr.get(j).length(),subword.length(), "");
                break;
            }
        }
        return duplicated;
    }
}

class EnoughBribes {
    public static void minimumBribes(List<Integer> q) {

        int bribes = 0;
        int n = q.size();

        for(int i=0; i< n; i++) {
            int indexOriginal = q.get(i) - 1;
            if(indexOriginal - i>2) {
                System.out.println("Too chaotic");
                return;
            }
            for(int j=Math.max(0, indexOriginal-1); j<i; j++) {
                if(q.get(j)>q.get(i)) {
                    bribes++;
                }
            }
        }

        System.out.println(bribes);

    }
}

class SetMismatch {
    public int[] findErrorNums(int[] nums) {

        Set<Integer> setCheck = new HashSet<>();
        int[] result = new int[2];

        for(int num : nums) {
            if (!setCheck.add(num)) {
                result[0] = num;
            }
        }

        List<Integer> list = new ArrayList<>(setCheck);
        Collections.sort(list);

        for(int i=0; i<nums.length; i++) {
            if(i+1== nums.length) {
                result[1] = nums.length;
                break;
            } else if(list.get(i)-i != 1) {
                result[1] = list.get(i)-i > 1 ? list.get(i)-1 : list.get(i)+1;
                break;
            }
        }

        return result;
    }
}

class ReverseInteger {
    public int reverse(int x) {

        boolean negative = false;
        String integer;
        if(x<0) {
            negative = true;
            integer = String.valueOf(x).replace("-","");
        } else {
            integer = String.valueOf(x);
        }
        StringBuilder integerReversed = new StringBuilder();

        if(negative) {
            integerReversed.append("-");
        }

        for(int i = integer.length()-1; i>=0; i--) {
            integerReversed.append(integer.charAt(i));
        }

        long convertedInteger = Long.parseLong(integerReversed.toString());

        if(convertedInteger > Integer.MAX_VALUE || convertedInteger < Integer.MIN_VALUE) {
            return 0;
        }

        return (int) convertedInteger;
    }
}

class StairsWays {
    private int ways = 0;
    public int climbStairs(int n) {

        return fibonnacci(n, 1, 0);

    }

    public int fibonnacci(int index, int next, int current) {
        if(index==0) {
            ways = next;
        } else {
            fibonnacci(index-1, next+current, next);
        }

        return ways;
    }
}

class LongPrefix {
    public String longestCommonPrefix(String[] strs) {
        Map<String, Integer> mapLength = new HashMap<>();
        if(strs.length == 0) {
            return "";
        } else if(strs.length == 1) {
            return strs[0];
        }
        for(int i=0; i<strs.length; i++) {
            if(strs[i].isEmpty()) {
                return "";
            } else {
                mapLength.put(strs[i], strs[i].length());
            }
        }
        StringBuilder longPrefix = new StringBuilder();
        int words = strs.length;
        boolean differentPrefix = false;

        for(int counter=0; counter<Collections.min(mapLength.values()); counter++) {
            for(int i=1; i<words; i++) {
                if(strs[i].charAt(counter) != strs[i-1].charAt(counter)) {
                    differentPrefix = true;
                    break;
                } else {
                    continue;
                }
            }
            if(differentPrefix) {
                break;
            }
            longPrefix.append(strs[0].charAt(counter));
        }

        return longPrefix.toString();
    }
}

class JesseAndCookies {
    public static int iterations = 0;

    public static int cookies(int k, List<Integer> A) {

        PriorityQueue<Integer> queue = new PriorityQueue<>(A);

        while(queue.peek()<k) {
            if(queue.size()==1) {
                break;
            }
            testSweetness(k, queue);
        }

        if(queue.size()==1 && queue.peek()<k) {
            return -1;
        }

        return iterations;

    }

    public static void testSweetness(int k, PriorityQueue<Integer> sortedList) {

        int newSweetness = sortedList.poll() + sortedList.poll()*2;

        sortedList.offer(newSweetness);

        iterations++;

    }
}

class CaesarCipher {
    public String caesarCipher(String s, int k) {
        char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();
        Map<Character, Character> cipherMap = new HashMap<>();

        if(k>26) {
            k = k%26;
        }

        for(int i=0; i<alphabet.length; i++) {
            if(i+k > alphabet.length-1) {
                cipherMap.put(alphabet[i], alphabet[(i+k)-alphabet.length]);
            } else {
                cipherMap.put(alphabet[i], alphabet[i+k]);
            }
        }

        StringBuilder cipherWord = new StringBuilder();

        for(int i=0; i<s.length(); i++) {
            if(Character.isUpperCase(s.charAt(i))) {
                cipherWord.append(Character.toUpperCase(cipherMap.get(Character.toLowerCase(s.charAt(i)))));
                continue;
            } else if(!cipherMap.containsKey(s.charAt(i))) {
                cipherWord.append(s.charAt(i));
                continue;
            }
            cipherWord.append(cipherMap.get(s.charAt(i)));
        }

        return cipherWord.toString();
    }
}

class HoursConversion {
    public String timeConversion(String s) {
        // Write your code here
        int hours = Integer.parseInt(s.substring(0,2));
        if(s.contains("PM") && hours == 12) {
            s = s.replace("PM", "");
        } else if(s.contains("PM")) {
            String hoursConversion = String.valueOf(hours+12);
            s = s.replace(s.substring(0,2), hoursConversion).replace("PM", "");
        } else if(s.contains("AM") && hours == 12) {
            s = s.replace(s.substring(0,2), "00").replace("AM", "");
        } else {
            s = s.replace("AM", "");
        }

        return s;
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