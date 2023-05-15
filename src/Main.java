import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class Main {


    public static void miniMaxSum(List<Integer> arr) {

        List<Integer> sortedArray = arr.stream().sorted().collect(Collectors.toList());

        long sum=0,maxSum=0,minSum=0;
        for (int i=0;i<arr.size(); i++) {
            sum+=arr.get(i);
        }
        minSum=sum - arr.get(arr.size()).longValue();
        maxSum = sum-arr.get(0).longValue();

        System.out.println(minSum + " "+ maxSum);
    }


    public static String timeConversion(String s) throws ParseException {
        // Write your code here

        DateFormat format = new SimpleDateFormat("hh:mm:ssaa");
        var inputDate= format.parse(s);
        DateFormat output = new SimpleDateFormat("HH:mm:ss");
        String date = output.format(inputDate);
        return date;
    }

    public static int findMedian(List<Integer> arr) {
        // Write your code here
        List<Integer> sorted = arr.stream().sorted().collect(Collectors.toList());
        int size = sorted.size();

        int median = size/2;

        return sorted.get(median);

    }
    public static int lonelyinteger(List<Integer> a) {
        // Write your code here
        Map<Integer,Integer> uniqueSet= new HashMap<Integer,Integer>();

        a = a.stream().sorted().collect(Collectors.toList());
        int index=0;
        for(int i=0; i<a.size();i++){
            if(uniqueSet.get(a.get(i))== null)
                uniqueSet.put(a.get(i),1);
                else
                    uniqueSet.remove(a.get(i));
        }

        uniqueSet.entrySet().stream().forEach(c ->      System.out.println(c));
        return uniqueSet.entrySet().stream().mapToInt(v -> v.getKey()).findFirst().getAsInt();
    }

    public static int diagonalDifference(List<List<Integer>> arr) {
        // Write your code here
        int firstDiag=0, secondDiag=0;
        for (int i=0; i<arr.size();i++){
            for(int k=0;k<arr.get(i).size();k++){
                if(i == k){
                    firstDiag +=arr.get(i).get(k);
                }
                if(i+k == arr.get(i).size()-1){
                    secondDiag += arr.get(i).get(k);
                }
            }
        }
        int diagVal= firstDiag-secondDiag;
        return Math.abs(diagVal);
    }
    public static List<Integer> countingSort(List<Integer> arr) {
        var fillArray =IntStream.generate(()->0).limit(101).toArray();
        int index=0;
        for(int i=0; i<arr.size(); i++){
            int arrayElement = arr.get(i);// 1 2 2 3 3 3
            index= fillArray[arrayElement];
            fillArray[arr.get(i)]= ++index;

        }

        Arrays.stream(fillArray).forEach(c-> System.out.print(c + " "));


        return Arrays.stream(fillArray).boxed().collect(Collectors.toList());

    }

    public static int flippingMatrix(List<List<Integer>> matrix) {
        int sum=0;
        int arrSize=matrix.size();

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

        matrix.stream().forEach(c -> System.out.println(c));

        for(int r=0; r<arrSize/2;r++ ){
            for(int c=0;c<arrSize/2; c++){
                sum+= Math.max(Math.max( Math.max(matrix.get(r).get(c),matrix.get(r).get(arrSize-c-1)),matrix.get(arrSize-r-1).get(c)),
                        matrix.get(arrSize-r-1).get(arrSize-c-1));
            }
        }

        return sum;
    }

    public static void findZigZagSequence(int [] a, int n){

        Arrays.sort(a);
        int mid = (n+1)/2-1;
        int temp = a[mid];
        a[mid] = a[n - 1];
        a[n - 1] = temp;
        int st = mid + 1;
        int ed = n - 2;

      while(st <= ed){

          if(a[st] <= a[ed]) {
              temp = a[ed];
              a[ed] = a[st];
              a[st] = temp;
          }
            st = st + 1;
            ed = ed - 1;
        }
        System.out.println();
        for(int i = 0; i < n; i++){
            if(i > 0) System.out.print(" ");
            System.out.print(a[i]);
        }
    }

    public static int towerBreakers(int n, int m) {
        return (n%2==0 || m==1 )?2:1;
    }

    public static String caesarCipher(String s, int k) {
        // Write your code here
    return "";
    }


    public static void main(String[] args) throws ParseException {

        List<List<Integer>> sqMatrix = new ArrayList<>();
            List<Integer> input = new ArrayList<>();

        input.add(112);
        input.add(42);
        input.add(83);
        input.add(119);

        sqMatrix.add(input);

        List<Integer> input2 = new ArrayList<>();
        input2.add(56);
        input2.add(125);
        input2.add(56);
        input2.add(49);
        sqMatrix.add(input2);

        List<Integer> input3 = new ArrayList<>();

        input3.add(15);
        input3.add(78);
        input3.add(101);
        input3.add(43);
        sqMatrix.add(input3);

        List<Integer> input4 = new ArrayList<>();

        input4.add(62);
        input4.add(98);
        input4.add(114);
        input4.add(108);
        sqMatrix.add(input4);

        int [] a = new int[7];
        a[0]=1; a[1]=2;a[2]= 3;a[3]=4; a[4]=5;a[5]=6;a[6]=7;

        System.out.println(towerBreakers(2,6));

    }
}