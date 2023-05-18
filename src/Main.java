import java.io.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.SQLOutput;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import static java.util.stream.Collectors.toList;

public class Main {


    public static void miniMaxSum(List<Integer> arr) {

        List<Integer> sortedArray = arr.stream().sorted().collect(toList());

        long sum = 0, maxSum = 0, minSum = 0;
        for (int i = 0; i < arr.size(); i++) {
            sum += arr.get(i);
        }
        minSum = sum - arr.get(arr.size()).longValue();
        maxSum = sum - arr.get(0).longValue();

        System.out.println(minSum + " " + maxSum);
    }


    public static String timeConversion(String s) throws ParseException {
        // Write your code here

        DateFormat format = new SimpleDateFormat("hh:mm:ssaa");
        var inputDate = format.parse(s);
        DateFormat output = new SimpleDateFormat("HH:mm:ss");
        String date = output.format(inputDate);
        return date;
    }

    public static int findMedian(List<Integer> arr) {
        // Write your code here
        List<Integer> sorted = arr.stream().sorted().collect(toList());
        int size = sorted.size();

        int median = size / 2;

        return sorted.get(median);

    }

    public static int lonelyinteger(List<Integer> a) {
        // Write your code here
        Map<Integer, Integer> uniqueSet = new HashMap<Integer, Integer>();

        a = a.stream().sorted().collect(toList());
        int index = 0;
        for (int i = 0; i < a.size(); i++) {
            if (uniqueSet.get(a.get(i)) == null)
                uniqueSet.put(a.get(i), 1);
            else
                uniqueSet.remove(a.get(i));
        }

        uniqueSet.entrySet().stream().forEach(c -> System.out.println(c));
        return uniqueSet.entrySet().stream().mapToInt(v -> v.getKey()).findFirst().getAsInt();
    }

    public static int diagonalDifference(List<List<Integer>> arr) {
        // Write your code here
        int firstDiag = 0, secondDiag = 0;
        for (int i = 0; i < arr.size(); i++) {
            for (int k = 0; k < arr.get(i).size(); k++) {
                if (i == k) {
                    firstDiag += arr.get(i).get(k);
                }
                if (i + k == arr.get(i).size() - 1) {
                    secondDiag += arr.get(i).get(k);
                }
            }
        }
        int diagVal = firstDiag - secondDiag;
        return Math.abs(diagVal);
    }

    public static List<Integer> countingSort(List<Integer> arr) {
        var fillArray = IntStream.generate(() -> 0).limit(101).toArray();
        int index = 0;
        // 1 2 2 3 3 3
        for (int arrayElement : arr) {
            index = fillArray[arrayElement];
            fillArray[arrayElement] = ++index;

        }

        Arrays.stream(fillArray).forEach(c -> System.out.print(c + " "));


        return Arrays.stream(fillArray).boxed().collect(toList());

    }

    public static int flippingMatrix(List<List<Integer>> matrix) {
        int sum = 0;
        int arrSize = matrix.size();

        //{ [112, 42, 114, 119] ,
        // [56, 125, 101, 49] ,
        // [15, 78, 56, 43] ,
        // [62, 98, 83, 108] }

        // [0,0],[0,1],[0,2],[0,3],[0,4],[0,5]
        // [1,0],[1,1],[1,2],[1,3],[1,4],[1,5]
        // [2,0],[2,1],[2,2],[2,3],[2,4],[2,5]
        // [3,0],[3,1],[3,2],[3,3],[3,4],[3,5]
        // [4,0],[4,1],[4,2],[4,3],[4,4],[4,5]
        // [5,0],[5,1],[5,2],[5,3],[5,4],[5,5]


        // max of( [0,0] , [0,N-1], [N-1,0]
        // max of [1,1] , [1,4], [4,1],[4,4]
        // max of [1,4], [1,1], [4,1], [4,4]
        // max of [2,2] -> [2,3],[3,2],[3,3]


        // max of [N-1,N-1] -> [0,N-1], [N-1,0], [0,0]
        //max of [r,c] -> [r,c], [N-r-1,c],[r,N-c-1],  [N-r-1, N-c-1]

        matrix.forEach(System.out::println);

        for (int r = 0; r < arrSize / 2; r++) {
            for (int c = 0; c < arrSize / 2; c++) {
                sum += Math.max(Math.max(Math.max(matrix.get(r).get(c), matrix.get(r).get(arrSize - c - 1)), matrix.get(arrSize - r - 1).get(c)),
                        matrix.get(arrSize - r - 1).get(arrSize - c - 1));
            }
        }

        return sum;
    }

    public static void findZigZagSequence(int[] a, int n) {

        Arrays.sort(a);
        int mid = (n + 1) / 2 - 1;
        int temp = a[mid];
        a[mid] = a[n - 1];
        a[n - 1] = temp;
        int st = mid + 1;
        int ed = n - 2;

        while (st <= ed) {

            if (a[st] <= a[ed]) {
                temp = a[ed];
                a[ed] = a[st];
                a[st] = temp;
            }
            st = st + 1;
            ed = ed - 1;
        }
        System.out.println();
        for (int i = 0; i < n; i++) {
            if (i > 0) System.out.print(" ");
            System.out.print(a[i]);
        }
    }

    public static int towerBreakers(int n, int m) {
        return (n % 2 == 0 || m == 1) ? 2 : 1;
    }

    public static String caesarCipher(String s, int k) {
        //fff.jkl.gh
        char[] charArray = s.toCharArray();
        char singleChar;
        int modChar = 0;
        StringBuffer output = new StringBuffer();


        for (int i = 0; i < charArray.length; i++) {

            if (Character.isAlphabetic(charArray[i])) {

                singleChar = Character.toLowerCase(charArray[i]);

                k = k % 26;

                System.out.println("k%26: " + k);
                modChar = singleChar + k;
                if (modChar > 122) {
                    modChar = 96 + (modChar - 122);
                }


                singleChar = (char) (modChar);


                singleChar = Character.isUpperCase(charArray[i]) ? Character.toUpperCase(singleChar) : singleChar;
                output.append(singleChar);
            } else {
                output.append(charArray[i]);
            }
        }

        return output.toString();
    }

    public static int palindromeIndex(String s) {
        char[] charArray = s.toCharArray();
        int size = charArray.length;//8
        System.out.println(Arrays.toString(charArray));
        int left = 0, l = 0, r = 0, right = size - 1;
        boolean isRight, isLeft;
        while (left <= right) {
            if (charArray[left] != charArray[right]) {
                isLeft = checkPalindrome(charArray, left, right-1);
                isRight = checkPalindrome(charArray, left+1, right);

                if (isLeft) return right;
                else if (isRight) return left;
            }
            left++;
            right--;
        }

        return -1;
    }

    public static String gridChallenge(List<String> grid) {
        List<String> characterList = new ArrayList<>();



        for(int c=0; c<grid.size(); c ++){
            char [] charArray= grid.get(c).toCharArray();
            Arrays.sort(charArray);
            characterList.add(new String(charArray));
        }
        String stringArray = grid.get(0);
        int size = stringArray.length();

        for(int c=1; c<grid.size();c++){
            for(int r=0; r<size; r++){
                if((characterList.get(c-1).charAt(r)>characterList.get(c).charAt(r))){

                    return "NO";
                }
            }
        }

        return "YES";

    }



    public static boolean checkPalindrome(char[] array, int left, int right) {
        boolean isRight = false;
        for (int i = left; i < right; i++) {
            if (array[i] == array[right]) {
                isRight = true;
                right--;

            } else {
                return false;
            }

        }
        return isRight;
    }

    public static int palindromeIndex1(String s) {

        char[] charArray = s.toCharArray();
        int size = charArray.length;//8
        System.out.println(Arrays.toString(charArray));
        int palIndex = -1;
        for (int i = 0; i < (size + 1) / 2 - 1; i++) {
            if (charArray[i] != charArray[size - 1 - i]) {
                //q u yjjdcgsvvsgcdjjy q
                //hgygsvlf w cw nswwsn w c flvsgygh
                //hgygsvlf c w nswwsn wc w flvsgygh

                if (charArray[i + 1] == charArray[size - i - 1]) {
                    if (charArray[i + 2] == charArray[size - i - 2])
                        palIndex = i;
                    else
                        palIndex = size - i - 1;

                }
                if (charArray[i] == charArray[size - i - 2]) {
                    if (charArray[i + 2] == charArray[size - i - 3])
                        palIndex = size - i - 1;
                    else
                        palIndex = i;
                }

                System.out.println(charArray[(i + 1)] + "" + charArray[(i + 2)] + charArray[i + 3] + " first index:" + i + ":" + charArray[i] + " && " + charArray[size - i - 3] + charArray[size - i - 2] + "" + charArray[size - i - 1] + " last index: " + (size - i - 1) + ":" + charArray[size - i - 1]);
                // System.out.println(charArray[i]+" "+charArray[(i+1)]+""+charArray[(i+2)]+" " +(i)+" && "+charArray[size - i-2]+""+charArray[size - i-1]+ " index: "+i);
                System.out.println(charArray[palIndex]);
                return palIndex;
            }
        }

        return palIndex;

    }
    public static int superDigit(String n, int k) {
        // Write your code here
        //System.out.println(concatString);
        int superD=superDigitRec(n)*k;
        if(superD>9){
            superD =superDigitRec(String.valueOf(superD));
        }
        //System.out.println(superD);
        return superD;
    }

    public static int superDigitRec(String number){

        if(number.length() == 1){
            return Integer.parseInt(number);
        }
        int sum=0;
        for(int i=0; i<number.length(); i++){
            sum+=Character.getNumericValue(number.charAt(i));
        }


        return superDigitRec(String.valueOf(sum));
    }

    public static void main(String[] args) throws IOException {
        System.out.println(superDigit("3546630947312051453014172159647935984478824945973141333062252613718025688716704470547449723886626736",100000));


    }
}