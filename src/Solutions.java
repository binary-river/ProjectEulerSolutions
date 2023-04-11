import java.io.*;
import java.math.BigDecimal;
import java.sql.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solutions {

    void run() {
        /* solution 1 ~ 30(or over) had done before.
           1 ~ 30 is just for script.
         */
//        solution1();
//        solution2();
//        solution3();
//        solution4();
//        solution4_2();
//        solution5();
//        solution5_2();
//        solution6();
//        solution7();
//        solution8();
//        solution9();
//        solution10();
//        solution11();
//        solution12();
//        solution13();
//        solution14();
//        solution15();
//        solution16();
//        solution17();
        solution18();
    }




    void solution1(){

        int limit = 1000;
        int sum = 0;

        for(int i=1; i<limit; i++) {
            if( i % 3 == 0 || i % 5 == 0 ) sum += i;
        }

        System.out.println("Sum of all multiples of 3 or 5 is " + sum);
    }


    void solution2() {
        long limit = 4000000;
        long n1 = 1;
        long n2 = 2;
        long n3 = 0;
        long sum = 2;

        while (true) {
            n3 = n1 + n2;
            if (n3 > limit) break;
            if( n3 % 2 == 0 ) sum += n3;

            n1 = n2;
            n2 = n3;
        }

        System.out.println("sum of even valued fibonacci sequence under 4 millions : " + sum);
    }


    void solution3() {
        long largestPrimeFactor = 1;
        long inputNumber = 600851475143L;

        for (long i = 2; i < inputNumber/2; i++ ) {

            long n = inputNumber / i;

            if( inputNumber % n != 0 ) continue;

            if (validPrimeNumber(n)) {
                largestPrimeFactor = n;
                break;
            }
        }

        System.out.println("largest prime factor :" + largestPrimeFactor);
    }

    /**
     * Find the largest palindrome made from the product of two 3-digit numbers.
     */
    void solution4() {
        //given
            // a(100~999), b(100~999)  ++ each. 900 x 900 : 810,000 cardinal
            // for reducing cardinality, second loop should be start from first loop's number.
        //when
            // multiple.
            // valid result is palindrome.
        //then
            // store max value of palindrome number.

        int a = 100;
        int maxPalindrome = 0;
        while (a <= 999) {
            for (int b = a; b <= 999; b++) {
                int n = a*b;
                if (isPalindrome(n) && maxPalindrome < n ) {
                    maxPalindrome = n;
                }
            }
            a++;
        }

        System.out.println("max value of palindrome number : " + maxPalindrome);
    }

    void solution4_2() {
        /* Same method with solution4
         * not using 'for' statement
         */

        int a = 100;
        int b = a;
        int maxPalindrome = 0;
        while (a <= 999) {

            int n = a*b;

            if( isPalindrome(n) && maxPalindrome < n ) maxPalindrome = n;

            /* b loop */
            if (b != 999) {
                b++;
                continue;
            }

            /* reset b and up a */
            a++;
            b = a;
        }

        System.out.println("max value of palindrome number : " + maxPalindrome);
    }

    void solution5() {
        /**
         Find smallest number evenly divisible from 1 to 20
         */

        /* find denominator really needed to get result */
        ArrayList<Long> reallyNeededDenominatorList = new ArrayList<>();
        long maxNum = 20L;

        for ( long i=1; i<= maxNum; i++ ) {
            boolean needYn = true;
            for (long j = i + 1; j <= maxNum; j++) {
                if (j % i == 0) needYn = false;
            }
            if( needYn ) reallyNeededDenominatorList.add(i);
        }

        /* up 20 by step */
        long result = 0L;

        while (true) {
            result = result+20;

            boolean foundYn = true;
            for (Long aLong : reallyNeededDenominatorList) {
                if (result % aLong != 0) {
                    foundYn = false;
                    break;
                }
            }

            if( foundYn ) break;
        }

        System.out.println("Evenly divisible number from 1 to 20 is.. : " + result);
    }


    void solution5_2() {
        /**
         * Find smallest number evenly divisible from 1 to 20
         * using least common multiple
         */

        long limit = 20L;
        long lcm = 1L;

        for (long i = 1; i <= limit; i++) {
            lcm = getLeastCommonMultiple(lcm, i);
        }

        System.out.println("result : " + lcm);
    }

    void solution6() {
        /**
         * Find difference between (1+2+..+n)^2 and 1^2+2^2+...+n^2
         */

        long max = 100L;
        long result1 = 0L;
        long result2 = 0L;

        for (long i = 1L; i <=max; i++) {
            result1 += i;
        }
        result1 = result1 * result1;

        for (long i = 1L; i <=max; i++) {
            result2 += i*i;
        }

        System.out.println("result1 : " + result1);
        System.out.println("result2 : " + result2);
        System.out.println("result1 - result2 : " + (result1 - result2));
    }


    void solution7() {
        /**
         * Find 10001st prime number
         */

        int target = 10001;
        int cnt    = 0;
        long number = 1L;

        while (cnt < target) {
            number++;
            if (validPrimeNumber(number)) cnt++;
        }

        System.out.println("cnt : " + cnt + ", number : " + number);
    }

    void solution8() {
        /**
         * Find greatest product of 13 adjacent numbers
         */
        String fileName = "./src/input/solution8_input.txt";
        String contents = "";
        int adjacentCnt = 13;
        long greatestProduct = 0L;

        try {
            contents = getFileByOneline(new File(fileName));
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("contents : " + contents);
        for (int i = 0; i < contents.length()-(adjacentCnt-1); i++) {
            long temp = 1L;
            for(int j=0; j<adjacentCnt; j++) temp = temp * Long.parseLong(contents.charAt(i+j)+"");
            if( temp > greatestProduct ) greatestProduct = temp;
        }

        System.out.println("greatestProduct : " + greatestProduct);
    }

    void solution9() {
        /**
         * Find the product of a,b,c which is pythagorean triplet for which a+b+c = 1000 ( a < b < c )   
         */

        long a =0L,b =0L,c = 0L;
        long max = 1000L;
        long maxProduct = 0L;
        long[] nums = new long[3];

        // a < b < c
        // a + b must be bigger than c
        while (true) {
            a++;
            for (b = a+1; b < max/2L; b++) {
                c = max - (a + b);
                if( a+b < c ) continue;
                if( a*a + b*b != c*c ) continue;
                if( maxProduct < a*b*c ) maxProduct = a*b*c;
                nums[0] = a;
                nums[1] = b;
                nums[2] = c;
            }
            if( a >= max/3L ) break;
        }

        System.out.println("maxProduct : "  + maxProduct);
        System.out.println("nums : " + nums[0] + ", " + nums[1] + ", " + nums[2] );
    }

    void solution10() {
        /**
         * Find the sum of all the primes below two million
         */

        long sum = 0L;
        long limit = 2000000L;

        for (long i = 2; i < limit; i++) {
            if( validPrimeNumber(i) ) sum += i;
        }

        System.out.println("sum : " + sum);

    }

    void solution11() {
        /**
         * Find the greatest product of four adjacent numbers in the same direction(up, down, right, left, diagonal
         */
        String fileName = "./src/input/solution11_input.txt";
        File file = new File(fileName);
        String[][] s = new String[1][1];

        try {
            s = getFileBySquareArray(file, " ");
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (String[] strings : s) {
            StringBuilder sb = new StringBuilder();
            for (String string : strings) {
                sb.append(string);
                sb.append(" ");
            }
            System.out.println(sb.toString());
        }

        /**
         * move from zero to end. check size: 4 square.
         * left -> right moving unit : 1
         * up   -> down moving unit  : 1
         */

        int adjacentUnit = 4;
        int width  = s[0].length;
        int height = s.length;

        int i=0; /* height */
        int j=0; /* width */
        long maxProduct = 0L;

        while (true) {
            /* make an unit square */
            long[][] square = new long[adjacentUnit][adjacentUnit];
            for (int k = 0; k < adjacentUnit; k++) {
                for (int l = 0; l < adjacentUnit; l++) {
                    square[k][l] = Long.parseLong(s[i + k][j + l]);
                }
            }

            /* get max product of unit square */
            long tempProduct = getMaxProductOfAdjacentFromSquare(square);

            if( tempProduct > maxProduct ) maxProduct = tempProduct;

            j++;
            if (j > width - adjacentUnit) {
                i++;
                j = 0;
            }

            if (i > height - adjacentUnit) {
                break;
            }
        }

        System.out.println("max product : " + maxProduct);

    }


    void solution12() {
        /**
         * Find the least value of triangle number that has over five hundred divisors.
         * Triangle number is generated by natural numbers( ex. 1st triangle number : 1
         *                                                      2nd triangle number : 1+2
         *                                                      3rd triangle number : 1+2+3
         *                                                      4th triangle number : 1+2+3+4
         */
        long i = 1L;
        long triangleNumber = 0L;
        long divisorCount  = 0L;
        while (true) {
            /* get Nth Triangle Number */
            triangleNumber = getNthTriangleNumber(i);
            /* valid if triagle number above has over five hundred diviors */
            if( (divisorCount = getCountOfDivisors(triangleNumber)) > 500 ) break;

            if (i % 1000 == 0) {
                System.out.println("triangle:" + triangleNumber + ", count : "+ divisorCount);
            }
            i++;
        }

        System.out.println("first triangle number to have over five hundred divisors : " + triangleNumber);

    }


    void solution13() {
        /**
         * Print first 10 digit of sum of one-hundred 50-digit numbers in file
         */
        BigDecimal result = new BigDecimal(0L);
        String filename = "./src/input/solution13_input.txt";


        // read and sum line by line
        try {
            File file = new File(filename);
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line = null;

            while ((line = br.readLine()) != null) {
                result = result.add(new BigDecimal(line));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("result : " + result.toString());

        String resultString = result.toString();
        StringBuilder sb = new StringBuilder();

        //print first 10 digits
        for (int i = 0; i < 10; i++) {
            sb.append(resultString.charAt(i) + "");
        }

        System.out.println("first 10 digit of result : "+ sb.toString());
    }

    void solution14() {
        /**
         * Find starting number of the longest collatz chain.
         *  - start number must be under one million
         *  - number can go above one million except starting number
         *  Collatz sequence' formula
         *  : n -> n/2  ( n is even )
         *    n -> 3n+1 ( n id odd  )
         */

        long limit = 1000000L;
        long maxStartingNumber = 0L;
        long maxCount          = 0L;

        for (long i = 1L; i < limit; i++) {
            long collatzSequenceCount = getCollatzSequenceCount(i);
            if (collatzSequenceCount > maxCount) {
                maxStartingNumber = i;
                maxCount = collatzSequenceCount;
            }
        }

        System.out.println("max starting number : " + maxStartingNumber + ", max count : " + maxCount);
    }

    void solution15() {
        /**
         * 2x2 grid has 6 routes from top left corner to bottom right. ( it moves only to right or down == shortest path )
         * Find count of all routes 20x20 grid has
         */

        /* getting shortest paths are same to getting combination of one direction positions ) */
        System.out.println(getCombinationByPrime(40,20));
    }

    void solution16() {
        /**
         * Find sum of digits of 2^1000 ( ex. sum of digits of 2^10 = 1024 = 1+0+2+4 = 7
         */

        int pow = 1000;
        int unit = 2;
        long result = 0L;

        BigDecimal bd = new BigDecimal(unit);
        bd = bd.pow(1000);

        String bdString = bd.toString();

        for (int i = 0; i < bdString.length(); i++) {
//            result += Long.parseLong(bdString.charAt(i) + "");         // version 1
            result += (long) ((byte) bdString.charAt(i) - (byte) '0');   // version 2
        }

        System.out.println("result : " + result);
    }

    void solution17() {
        /**
         * Find count of spells of numbers from 1 to 1000.
         *   20  -> twenty : 6
         *   342 -> three hundred and forty-two : 23
         * ( Do not count spaces or hyphens )
         */

        /* get spells */
        StringBuilder sb = new StringBuilder();

        try {
            for (int i = 1; i <= 1000; i++) {
                sb.append(getSpellsOfNumber(i));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        /* count spells */
        String resultString = sb.toString();
        long resultCnt = 0L;

        for (int i = 0; i < resultString.length(); i++) {
            if (resultString.charAt(i) != ' ' && resultString.charAt(i) != '-') resultCnt++;
        }

        System.out.println("count of spells of 1 - 1000 is.. : " + resultCnt);

    }

    void solution18() {
        /**
         * Find maximum path sum
         * path from top to bottom. can only move adjacent number downward.
         * Maximum total of below example is 23 ( 3 + 7 + 4 + 9 )
         *      3
         *     7 4
         *    2 4 6
         *   8 5 9 3
         *
         * Find the maximum total of solution18_input.txt triangle
         */

        /**
         * 3
         * 7 4
         * 2 4 6
         * 8 5 9 3
         *
         * Calculating maximum total from bottom to top
         *
         * arr[3][0] = max( arr[4][0], arr[4][1] ) + arr[3][0]
         * arr[3][1] = max( arr[4][1], arr[4][2] ) + arr[3][1]
         * arr[3][2] = max( arr[4][2], arr[4][3] ) + arr[3][2]
         * ...
         * arr[0][0] = max( arr[1][0], arr[1][1] ) + arr[0][0]
         *
         *  --> arr[n][m] = max( arr[n+1][m], arr[n+1][m+1] ) + arr[n][m]
         */

        try {
            List<Integer[]> pyramidOriginal = getIntegerArrByPyramid(new File("./src/input/solution18_input.txt"));
            List<Integer[]> pyramidInput    = getIntegerArrByPyramid(new File("./src/input/solution18_input.txt"));

            printPyramid(pyramidOriginal);

            int maxPathSumOfPyramid = getMaxPathSumOfPyramid(pyramidInput);

            System.out.println("maxPathSumOfPyramid : " + maxPathSumOfPyramid);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // continue...
    }


    /**********************************************************************************************************/

    /**
     *
     * @param pyramid : list of integer arrays
     * @return integer value of max path sum of input pyramid
     * caution : this method modifies input list
     */
    int getMaxPathSumOfPyramid(List<Integer[]> pyramid) {

        int rows = pyramid.size();

        /* starts from 2nd line from bottom to top
           compare 2 ways each element can go below.
           then put max value of ways(element + element below) to element itself
         */

        int row = rows - 2;
        while (true) {
            Integer[] elements = pyramid.get(row);
            Integer[] elementsBelow = pyramid.get(row + 1);

            for (int i = 0; i < elements.length; i++) {
                elements[i] = Math.max(elements[i] + elementsBelow[i], elements[i] + elementsBelow[i + 1]);
            }

            row--;
            if (row < 0) break;
        }

        System.out.println("result  -------------------------------");
        printPyramid(pyramid);
        return pyramid.get(0)[0];
    }

    /**
     * @param file
     * @return list consisted of numbers from input file as integer arrays. rows to rows, cols to cols.
     * spaces in input file will be ignored
     * example
     *    input file        -->     array transformed ( arr[][] )
     *        1                     1
     *       2 3                    2 3
     *      4 5 6                   4 5 6
     *
     * so, return list would be like below
     * {{1}, {2,3}, {4,5,6}}
     */
    List<Integer[]> getIntegerArrByPyramid(File file) throws IOException{

        List<Integer[]> result = new ArrayList<>();

        BufferedReader br = new BufferedReader(new FileReader(file));

        while (true) {
            String s = br.readLine();
            if( s == null ) break;

            String[] sArr = s.trim().split(" {1,}");
            Integer[] temp = new Integer[sArr.length];
            for (int i = 0; i < sArr.length; i++) {
                temp[i] = Integer.parseInt(sArr[i]);
            }
            result.add(temp);
        }

//        printPyramid(result);

        return result;
    }

    void printPyramid(List<Integer[]> input) {
        StringBuilder sb = new StringBuilder();

        for (Integer[] integers : input) {
            for (int i = 0; i < integers.length; i++) {
                sb.append(integers[i]);
                sb.append(" ");
            }
            sb.append("\n");

        }

        System.out.println(sb.toString());
    }

    /**
     *
     * @param num
     * @return String of spells of input number ( 25 -> twenty-five )
     */
    String getSpellsOfNumber(int num) throws Exception{
        String[] oneToNineteen = {"one", "two", "three", "four", "five", "six", "seven", "eight", "nine"
                , "ten", "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen"};
        String[] tensWithoutTen = {"twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety"};

        Map<Integer, String> spells = new HashMap<>();

        for (int i = 0; i < oneToNineteen.length; i++) {
            spells.put(i + 1, oneToNineteen[i]);
        }

        for (int i = 0; i < tensWithoutTen.length; i++) {
            spells.put((i + 2) * 10, tensWithoutTen[i]);
        }

        spells.put(100, "hundred");
        spells.put(1000, "thousand");

        if( num <= 0 || num > 1000 ) throw new Exception();

        /**
         *  rules
         *  from 1 to 19, get value from map straightly
         *  from 20 to 99, get combination value of tens-units
         *  from 100 to 999 get combination value of hundreds and tens-units(or straight value[1-20])
         */

        StringBuilder sb = new StringBuilder();
        int unit = 10000; /* max unit of number * 10 */
        int rest = num;

        while (unit >= 1) {
            unit = unit / 10;
            if( rest == 0 ) break;

            int head = rest / unit;

            if( head == 0 ) continue;

            /* append spells */
            if (rest >= 1 && rest <= 19) {
                sb.append(spells.get(rest));
                break;
            } else if (rest <= 99) {
                sb.append(spells.get(head * unit));
            } else {
                sb.append(spells.get(head));
                sb.append(" ");
                sb.append(spells.get(unit));
            }

            sb.append(" ");

            rest = rest % unit;

            /* hundreds 'and' */
            if (unit == 100 && rest != 0) sb.append("and ");
        }

        return sb.toString();
    }



    /**
     *
     * @param n
     * @param r
     * @return result of n Combination r ( nCr )
     * it's faster and can handle bigger numbers than getCombinationNaturally because it uses reduction before calculate multiple
     */
    long getCombinationByPrime(int n, int r) {

        /* get prime factors of multiples from n to n-r+1 */
        /* get prime factors of multiples from r!         */
        /* reduction                                      */

        List<Integer> numeratorPrimes = new ArrayList<>();
        List<Integer> denominatorPrimes = new ArrayList<>();
        List<Integer> commonPrimes = new ArrayList<>();

        /* get primes of numerators */
        for (int i = n; i > n-r; i--) {
            if( i == 1 ) continue;
            numeratorPrimes.addAll(getListOfPrimeFactorization(i));
        }

        /* get primes of denominator */
        for (int i = r; i > 0 ; i--) {
            if( i == 1 ) continue;
            denominatorPrimes.addAll(getListOfPrimeFactorization(i));
        }

        /* reduction numerators */
        for (Integer denominatorPrime : denominatorPrimes) {
            for (Integer numeratorPrime : numeratorPrimes) {
                if (denominatorPrime == numeratorPrime) {
                    numeratorPrimes.remove(denominatorPrime);
                    commonPrimes.add(denominatorPrime);
                    break;
                }
            }
        }

        /* reduction denominator */
        for (Integer commonPrime : commonPrimes) {
            for (Integer denominatorPrime : denominatorPrimes) {
                if (commonPrime == denominatorPrime) {
                    denominatorPrimes.remove(commonPrime);
                    break;
                }
            }
        }


        long numerator = 1L;
        long denominator = 1L;

        for (Integer numeratorPrime : numeratorPrimes) {
            numerator = numerator * numeratorPrime;
        }

        for (Integer denominatorPrime : denominatorPrimes) {
            denominator = denominator * denominatorPrime;
        }


        return numerator / denominator;
    }


    /**
     *
     * @param num
     * @return list of prime factors of input num. same prime factors might be listed unordered (ex. 24 --> 2,2,3,2 )
     */
    List<Integer> getListOfPrimeFactorization(int num) {

        List<Integer> result = new ArrayList<>();

        int midResult = num;
        int denominator = 1;

        while (true) {
            denominator++;

            if( midResult == 1 ) break;

            if( !validPrimeNumber(denominator) ) continue;
            if( midResult % denominator != 0 ) continue;

            result.add(denominator);
            midResult = midResult / denominator;

            denominator = 1;
        }

        return result;
    }




    /**
     *
     * @param n
     * @param r
     * @return result of n Combination r ( nCr )
     */
    long getCombinationNaturally(int n, int r) {
        long numerator = 1L;
        long denominator = 1L;

        /* numerator */
        for (long i = 1L; i <= n; i++) {
            numerator = numerator * i;
        }

        /* denominator */
        for (long i = 1L; i <= r; i++) {
            denominator = denominator * i;
        }

        for (long i = 1L; i <= n-r ; i++) {
            denominator = denominator * i;
        }

        return numerator / denominator;
    }

    /**
     *
     * @param num
     * @return count of collatz sequence ( starts from 1. include last chain to 1 )
     */
    long getCollatzSequenceCount(long num) {
        long result = 1L;
        long temp = num;
        while (true) {
            temp = getNextCollatzNumber(temp);
            result++;

            if( temp == 1 ) break;
        }

        return result;
    }

    /**
     *
     * @param num
     * @return next collatz number ( num / 2 if num is even, else num * 3 + 1 will be returned )
     */
    long getNextCollatzNumber(long num) {
        return num % 2 == 0 ? num / 2 : num * 3 + 1;
    }

    /**
     *
     * @param num
     * @return return number of divisors input num has
     */
    long getCountOfDivisors(long num) {
        long result = 1L;

        Map<Long, Long> resultOfPrimeFactorization = getResultOfPrimeFactorization(num);
        for (Map.Entry<Long, Long> longLongEntry : resultOfPrimeFactorization.entrySet()) {
            Long key = longLongEntry.getKey();
            Long value = longLongEntry.getValue();

            result = result * (value+1);
        }

        return result;
    }


    /**
     * @param nth ( Nth )
     * @return Nth triangle number ( nth : 3 --> return : (1+2+3)
     */
    long getNthTriangleNumber(long nth) {
        long result = 0L;

        /* exception case : nth=1 --> always return 1 */
        if( nth == 1 ) return 1;

        /* odd or even */
        boolean oddYn = false;
        if( nth % 2L != 0 ) oddYn = true;

        /* odd  : (first + last) * (nth/2) + (nth/2)+1
           even : (first + last) * (nth/2)               */
        result = (1+nth) * (nth/2);
        if( oddYn ) result += (nth/2)+1;

        return result;
    }


    /**
     *
     * @param square ( 2 dimensional integer array )
     * @return get max product of adjacent numbers in input square. adjacent unit is height of sqaure
     */
    long getMaxProductOfAdjacentFromSquare(long[][] square) {
        int size = square.length;
        long maxProduct = 0L;
        long tempProduct = 1L;

        /* calculate horizontal adjacent numbers */
        for (int i = 0; i < size; i++) {
            tempProduct = 1L;
            for (int j = 0; j < size; j++) {
                tempProduct = tempProduct * square[i][j];
            }
            if( tempProduct > maxProduct ) maxProduct = tempProduct;
        }

        /* calculate vertical adjacent numbers */
        for (int i = 0; i < size; i++) {
            tempProduct = 1L;
            for (int j = 0; j < size; j++) {
                tempProduct = tempProduct * square[j][i];
            }
            if( tempProduct > maxProduct ) maxProduct = tempProduct;
        }

        /* calculate diagonal adjacent numbers */
        tempProduct = 1L;
        for (int i = 0, j = 0; i < size && j < size; i++, j++) {
            tempProduct = tempProduct * square[i][j];
        }
        if( tempProduct > maxProduct ) maxProduct = tempProduct;

        tempProduct = 1L;
        for (int i = 0, j = size-1; i < size && j >= 0; i++, j--) {
            tempProduct = tempProduct * square[i][j];;
        }
        if( tempProduct > maxProduct ) maxProduct = tempProduct;

        return maxProduct;
    }

    /**
     *
     * @param file
     * @param separator
     * @return 2 dimension array that contains file contents. ( file should be writed in 2 dimension form )
     *         characters in one line are separated by input separator  )
     */
    String[][] getFileBySquareArray(File file, String separator) throws IOException {

        ArrayList<String[]> resultTemp = new ArrayList<>();  //temp result
        BufferedReader br = new BufferedReader(new FileReader(file));

        while (true) {
            String line = br.readLine();
            if( line == null ) break;
            String[] splitedLine = line.split(separator);
            resultTemp.add(splitedLine);
        }

        int rowCnt = resultTemp.size();
        int colCnt = resultTemp.get(0).length;

        String[][] result = new String[rowCnt][colCnt];
        int r = 0;
        for (String[] line : resultTemp) {
            for (int i = 0; i < line.length; i++) {
                result[r][i] = line[i];
            }
            r++;
        }

        return result;
    }

    /**
     *
     * @param file
     * @return String object that cotains all bytes of file without carriage return and line feed
     */
    String getFileByOneline(File file) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(file));
        StringBuilder sb = new StringBuilder();

        while (true) {
            String line = br.readLine();
            if( line == null ) break;
            sb.append(line);
        }

        return sb.toString();
    }

    /**
     *
     * @param a long type number
     * @param b long type number
     * @return least common multiple of a and b
     */
    long getLeastCommonMultiple(long a, long b) {

        Map<Long, Long> commonMap = new HashMap<>();

        // Map a, b
        Map<Long, Long> resultOfA = getResultOfPrimeFactorization(a);
        Map<Long, Long> resultOfB = getResultOfPrimeFactorization(b);

        //a, b compare. upload bigger a.
        for (Long e : resultOfA.keySet()) {
            if (resultOfB.get(e) != null && resultOfA.get(e) < resultOfB.get(e)) continue;
            commonMap.put(e, resultOfA.get(e));
        }

        //b, a compare. upload bigger b.
        for (Long e : resultOfB.keySet()) {
            if(resultOfA.get(e) != null && resultOfB.get(e) <= resultOfA.get(e)) continue;
            commonMap.put(e, resultOfB.get(e));
        }

        //get result
        long result = 1L;
        for (Long e : commonMap.keySet()) {
            result = result * powLong(e, commonMap.get(e));
        }

        return result;
    }

    /**
     *
     * @param a
     * @param b
     * @return result of a^b
     */
    long powLong(long a, long b) {
        long result = 1L;
        for (long i = 0L; i < b; i++) {
            result = result * a;
        }

        return result;
    }

    /**
     *
     * @param n : number need to be prime factorized
     * @return a hashMap of prime factorization to input number( {2,2}, {3,5}... )
     *                                                            2^2 * 3^5
     */
    Map<Long,Long> getResultOfPrimeFactorization(long n) {

        Long midResult = n;
        Map<Long, Long> result = new HashMap<>();

        long denominator = 1;

        while (true){
            denominator++;
            if( midResult == 1 ) break;

            if( !validPrimeNumber(denominator)) continue;

            if( midResult % denominator != 0 ) continue;

            Long mappedValue = result.get(denominator);
            Long count = 1L;

            if (mappedValue != null) count = mappedValue+1;
            result.put(denominator, count);

            midResult = midResult / denominator;
            denominator = 1L;
        }

        return result;
    }


    /**
     * @param num
     * @return true if input is a palindrome number
     *         false if input is not a palindrome number
     */
    boolean isPalindrome(int num) {
        String s = Integer.toString(num);

        for (int i = 0; i < s.length()/2; i++) {
            if( s.charAt(i) != s.charAt(s.length()-1-i)) return false;
        }

        return true;
    }


    /**
     * valid by square root
     * @param number ( should be a positive number )
     * @return true if number is a prime number
     *         false if number is not a prime number
     */
    boolean validPrimeNumber(long number) {

        for (long i = 2L; i*i <= number; i++) {
            if( number % i == 0 ) return false;
        }

        return true;
    }

}
