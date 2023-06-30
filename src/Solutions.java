import org.w3c.dom.ls.LSOutput;

import java.io.*;
import java.math.BigDecimal;
import java.sql.Array;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.function.IntConsumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

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
//        solution18();
//        solution19();
//        solution19_withJavaDate();
//        solution20();
//        solution21();
//        solution22();
//        solution23();
//        solution24();
//        solution25();
//        solution26();
//        solution24_improved();
//        solution27();
//        solution28();
//        solution29();
//        solution30();
//        solution31();
//        solution32();
//        solution33();
//        solution34();
//        solution35();
//        solution36();
//        solution37();
//        solution38();
//        solution38_improved();
//        solution39();
//        solution40();
//        solution41();
//        solution42();
//        solution43();
//        solution44();
//        solution45();
//        solution46();
//        solution47();
//        solution48();
        solution49();
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

        try {
            List<Integer[]> pyramidOriginal = getIntegerArrByPyramid(new File("./src/input/solution18_input.txt"));
            List<Integer[]> pyramidInput    = getIntegerArrByPyramid(new File("./src/input/solution18_input.txt"));

            printPyramid(pyramidOriginal);

            int maxPathSumOfPyramid = getMaxPathSumOfPyramid(pyramidInput);

            System.out.println("maxPathSumOfPyramid : " + maxPathSumOfPyramid);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    void solution19() {
        /**
         * 1 Jan 1900 was a Monday.
         * Thirty days has September, April, June and November.
         * All the rest have thirty-one,
         * Saving February alone, which has twenty-eight, and on leap years, twenty-nine.
         * A leap year occurs on any year evenly divisible by 4, but not on a century unless it is divisible by 400.
         * How many Sundays fell on the first of the month during the twentieth century (1 Jan 1901 to 31 Dec 2000)?
         */

        // 1900.1.1 is sunday.
        // count sundays of the first of the month
        // 1900.1.1 is monday --> set base ( 0 value )
        // so.. 1900.1.6 is 6 and is sunday.
        // then, base+1 % 7 == 0 means sunday.

        int[] dayOfMonth = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

        int days = 0; // days from base(1900.1.1)
        int result = 0;

        /* count sundays from 1901 to 2000 */
        for (int i = 1900; i <= 2000; i++) {
            int leap = 0;
            //leap year
            if (i % 400 == 0 || (i % 4 == 0 && i % 100 != 0)) leap = 1;

            for (int j = 0; j < 12; j++) {
                if ((days + 1) % 7 == 0 && i != 1900 ) result++;

                days += dayOfMonth[j];
                if( j == 1 ) days += leap;
            }
        }

        System.out.println("result : " + result);
    }

    void solution19_withJavaDate() {
        LocalDate date = LocalDate.of(1901, 1, 1);

        int count = 0;
        while (true) {
            if(date.getYear() > 2000) break;
            if( date.getDayOfMonth() == 1 && date.getDayOfWeek() == DayOfWeek.SUNDAY ) count++;
            date = date.plusDays(1);
        }

        System.out.println("count : " + count);
    }

    void solution20() {
        /**
         * Find the sum of the digits in the number 100!
         * (ex. the sum of the digits of 4! -> 4*3*2*1 -> 24 -> 2+4 -> 6
         */

        BigDecimal bd = new BigDecimal(1);
        BigDecimal temp = null;
        long result = 0L;

        for (int i = 100; i >= 1; i--) {
            temp = new BigDecimal(i);
            bd = bd.multiply(temp);
        }

        String bdString = bd.toString();
        for (int i = 0; i < bdString.length(); i++) {
            result += bdString.charAt(i) - '0';
        }

        System.out.println("result : " + result);
    }

    void solution21() {
        /**
         * Find sum of all amicable numbers under 10000.
         * Amicable number :
         *  d(x) : sum of divisors of x
         *  if d(n) = m, d(m) = n, then m and n are amicable numbers.
         */

        int limit = 10000;
        List<Integer> resultList = new ArrayList<>();


        for (int i = 1; i <10000; i++) {
            // search if i is already added to list as pair
            if( resultList.indexOf(i) != -1 ) continue;

            // continue loop if i is not a amicable number
            int temp = getSumOfDivisors(i);
            if( i == temp || i != getSumOfDivisors(temp)) continue;

            //amicable pair, add pair to list
            resultList.add(i);
            resultList.add(temp);
        }

        long result = 0L;
        for (Integer integer : resultList) {
            result += integer;
        }

        System.out.println("result : " + result );
    }

    void solution22() {
        /**
         *
         * Rank of name is determined by sort names alphabetically in input file.
         * Name value is determined by sum of alphabets' values( a:1, b:2 ... case insensitive ) in each name.
         * Name score is determined by (rank of name * name value)
         * What is the total of all the name scores in the input file
         */

        String fileName = "src/input/solution22_input.txt";
        List<String> contentList = new ArrayList<>();

        //Add names in file to list
        try {
            String content = getFileByOneline(new File(fileName));

            //replace double quotations to empty character
            content = content.replaceAll("\"", "");

            //split by comma, add to list
            String[] contentArray = content.split(",");
            contentList = Arrays.asList(contentArray);

        } catch (Exception e) {
            e.printStackTrace();
        }

        //sort list by character order
        contentList.sort(String.CASE_INSENSITIVE_ORDER);


        //calculate total score of names in list
        int seq = 0;
        long result = 0L;
        for (String s : contentList) {
            seq++;
            result += getValueOfAlphabet(s) * seq;
        }

        System.out.println("result : " + result);

    }

    void solution23() {
        /**
         * Find the sum of all the positive integers cannot be written as the sum of two abundant numbers.
         * ( All integers greater than 28123 can be written as the sum of two abundant numbers )
         */


        Set<Integer> abundantNumbers = new HashSet<>();
        int limit = 28123;

        //make a list of abundant numbers
        for (int i = 1; i <= limit; i++) {
            int sumOfDivisors = getSumOfDivisors(i);
            if (sumOfDivisors > i) abundantNumbers.add(i);
        }


        long result = 1L;
        for (int i = 2; i <= limit; i++) {
            if (!validNumberTwoSumOfSet(i, abundantNumbers)) result += i;
        }


        System.out.println("result : " + result );

    }

    void solution24() {
        /**
         * If there's list of permutation ordred by numerically or alphabetically, that's called lexicographic order.
         * 0,1,2 numbers' lexicographic permutations are 012, 021, 102, 120, 201, 210
         * What is the millionth lexicographic permutation of the digits 0, 1, 2, 3, 4, 5, 6, 7, 8 and 9?
         */

        int targetOrder = 1000000; /* millionth lexicographic */
        String[] elements = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};

        List<String> fullPermutations = getFullPermutations(Arrays.asList(elements));

        System.out.println(fullPermutations.get(targetOrder-1));
    }

    void solution25() {
        /**
         *  Find first 1000-digit number of fibonacci sequence's index
         */

        BigDecimal prev = new BigDecimal(1);
        BigDecimal curr = new BigDecimal(1);
        BigDecimal temp = null;

        int index = 2;

        while (true) {
            if( curr.precision() >= 1000 ) break;
            temp = curr;
            curr = curr.add(prev);
            prev = temp;

            index++;
        }

        System.out.println("index : " + index);
        System.out.println("sequence : " + curr.toString());
    }

    void solution26() {
        /**
         * Find a unit fraction which has longest digit recurring cycle ( 1 < denominator < 1000 )
         * digit recurring cycle : 0.123123123... --> 123 is a recurring cycle in this fraction.
         */
        int maxSize = 0;
        int maxD    = 0;
        String maxRecurringCycle = "";

        for (int i = 1; i <= 1000; i++) {

            /* first, need to know whether unit fraction given is infinite or not */
            if(!getUnitFractionsInfiniteOrNot(i)) continue;

            /* calculate recurring cycle */
            List<Integer> recurringCycleOfUnitFraction = getRecurringCycleOfUnitFraction(i);
            if (maxSize > recurringCycleOfUnitFraction.size()) continue;

            StringBuilder sb = new StringBuilder();

            maxSize = recurringCycleOfUnitFraction.size();
            for (Integer integer : recurringCycleOfUnitFraction) {
                sb.append(integer);
            }
            maxRecurringCycle = sb.toString();
            maxD = i;
        }

        System.out.println("denominator which has a max size of recurring cycle : " + maxD);
        System.out.println("max size of recurring cycle : " + maxSize);
        System.out.println("max recurring cycle : " + maxRecurringCycle);
    }


    void solution24_improved() {
        /**
         * lexicongraphic order, 0~9, find millionth order
         */
        List<Long> nums = new ArrayList<>(List.of(0L, 1L, 2L, 3L, 4L, 5L, 6L, 7L, 8L, 9L));

        StringBuilder sb = new StringBuilder();
        long target = 1000000L-1L;

        // faster by ignoring unwanted factorial using target / factorial and target % factorial.
        // quotient of target / factorial means, we can ignore target * factorial count because those are unwanted factorial.
        while (!nums.isEmpty()) {
            long fact = nums.size() == 1 ? 1 : getFactorialCount(nums.size() - 1);
            int index = (int) (target / fact);
            target = target % fact;
            sb.append(nums.remove(index) + "");
        }

        System.out.println("result : " + sb.toString());
    }


    void solution27() {
        /**
         *  with formula "n^2 + an + b" where |a| < 1000, |b| <= 1000,
         *  Find the product of coefficients(a and b) which produces maximum number of primes for consecutive values of n
         */

        // presumed cardinality : 1998 * 2000 * 500 : 1,998,000,000
        int resultA = 0;
        int resultB = 0;
        int maximumNumberOfPrimes = 0;

        // |a| < 1000, |b| <= 1000, iterate.
        for (int a = -1000+1; a <1000; a++) {
            for (int b = -1000; b <= 1000 ; b++) {

                // get count of primes of formula "n*n + a*n + b*n"
                int n = 0;
                int tempNumberOfPrimes = 0;
                while (true) {
                    if( !validPrimeNumber(n*n + a*n + b) ) break;
                    n++;
                    tempNumberOfPrimes++;
                }

                //compare count of primes with previous maximum count of primes
                if (tempNumberOfPrimes > maximumNumberOfPrimes) {
                    maximumNumberOfPrimes = tempNumberOfPrimes;
                    resultA = a;
                    resultB = b;
                }
            }
        }

        System.out.println("resultA : " + resultA);
        System.out.println("resultB : " + resultB);
        System.out.println("maximum number of primes : " + maximumNumberOfPrimes);
        System.out.println("coeefficient of a and b " + (resultA * resultB));
    }

    void solution28() {
        /**
         * Number spiral diagonals
         * From 1, moves to right in a clockwise direction, 5 by 5 spiral forms below
         * 21 22 23 24 25
         * 20  7  8  9 10
         * 19  6  1  2 11
         * 18  5  4  3 12
         * 17 16 15 14 13
         *
         * Sum of diagonal numbers is 101.
         * Find sum of diagonal numbers of 1001 by 1001 spiral.
         */

        // 1           --> start point.
        // 3  5  7  9  --> +2    ( 3x3 rectangle )
        // 13 17 21 25 --> +4    ( 5x5 rectangle )
        // ...
        // numbers...  --> +1000 ( 1001x1001 rectangle )

        long num = 1L;    // number increasing by moving
        long adder = 2L;  // adder increasing by rectangle size
        long maxSpiralSize = 1001L; // spiral size
        long result = 0L; //sum of diagonal numbers


        result += num;
        while (true) {
            // calculate diagonal numbers and add it to result.
            for (int i = 0; i < 4; i++) {
                num += adder;
                result += num;
            }

            adder = adder+2;

            // adder must be under spiral size.
            if( adder >= maxSpiralSize ) break;
        }

        System.out.println("result : " + result);

    }

    void solution29() {
        /**
         * integer combinations of a^b ( 2 <= a <= 5, 2 <= b <= 5 )
         * are below.
         * -----------------------------------
         * 2^2=4,  2^3=8,   2^4=16,  2^5=32
         * 3^2=9,  3^3=27,  3^4=81,  3^5=243
         * 4^2=16, 4^3=64,  4^4=256, 4^5=1024
         * 5^2=25, 5^3=125, 5^4=625, 5^5=3125
         * -----------------------------------
         * place in numerical order, with any repeats removed.
         * 4, 8, 9, 16, 25, 27, 32, 64, 81, 125, 243, 256, 625, 1024, 3125
         *
         * Find count of distinct terms in combinations of a^b (2 <= a <= 100 and 2 <= b <= 100)
         *
         */

        List<Map<Long, Long>> resultList = new ArrayList<>();


        for (int a = 2; a <= 100; a++) {

            //for a, get Factorization.
            Map<Long, Long> factorizedA = getResultOfPrimeFactorization(a);

            for (int b = 2; b <= 100; b++) {
                // copy of factorized a
                Map<Long, Long> resultOfPrimeFactorization = new HashMap<>(factorizedA);

                // multiply b and primes' power in factorization
                for (Long aLong : resultOfPrimeFactorization.keySet()) {
                    resultOfPrimeFactorization.put(aLong, resultOfPrimeFactorization.get(aLong) * b);
                }

                //add list if not duplicated
                boolean isEqual = false;

                for (Map<Long, Long> longLongMap : resultList) {
                    if (longLongMap.equals(resultOfPrimeFactorization)) {
                        isEqual = true;
                        break;
                    }
                }

                if (!isEqual) {
                    resultList.add(resultOfPrimeFactorization);
                }
            }
        }

        System.out.println("size of result : " + resultList.size());
    }


    void solution30() {
        /**
         * Find the sum of all the numbers that can be written as the sum of fifth powers of their digits.
         */

        List<Integer> resultList = new ArrayList<>();
        int result = 0;

        //Find maximum number that can be represented by 9^5 * digits
        int digits = 1;
        int pow = 5;
        int maximumNumber = 0;

        while (true) {
            int naturalLimitOfDigits = powInt(10,digits) - 1;
            int poweredOfDigits = powInt(9, pow) * digits;

            if (poweredOfDigits < naturalLimitOfDigits) {
                maximumNumber = poweredOfDigits;
                break;
            }

            digits++;
        }

        //iterate to maximum number
        for (int i = 2; i <= maximumNumber; i++) {
            //check if number can be represented by sum of digit^5s
            int sum = 0;
            int remainder = i;
            while (remainder != 0) {
                sum += powInt(remainder%10, pow);
                remainder = remainder / 10;
            }

            if (sum == i) {
                resultList.add(i);
            }
        }

        for (Integer integer : resultList) {
            result += integer;
        }

        System.out.println("result : " + result);
    }

    void solution31() {
        /**
         * Using britain's coins 1p, 2p, 5p, 10p, 20p, 50p, £1(100p), and £2(200p),
         * Find ways to make £2(200p) using any number of coins.
         */

        List<Integer> coins = new ArrayList<>(List.of(1, 2, 5, 10, 20, 50, 100, 200));
        int[] result = new int[201];  //result. index is the amount can be made by coin. value is the ways to make that amount

        //first, need to start from 0. making 0 only has one case. nothing selected
        result[0] = 1;

        //now, from 1 pence to 2 pounds, find ways to make each amount. using previous value
        //ex. using 1,2,5 pence, we can calculate the ways to make 20 pence by adding some ways of using 5 pence at least once
        //    to the ways to make 20 pence by only 1 and 2 pences
        //    and the ways of using 5 pence at least once to make 20 pence is the ways of making 15 pence using 1,2,5 pences
        //    ( 20 - 5 = 15. so, getting the ways of making 15 pences by using 1,2,5 pences is same as making 20 pences by using 5 pence at least once )
        //so, the ways of making 20 pence by 1, 2 pences + the ways of making 15 pence by 1,2,5 pences = the ways of making 20 pence by 1,2,5 pences

        for (Integer coin : coins) {
            for (int i = coin; i <= 200; i++) {
                result[i] += result[i - coin];
            }
        }

        System.out.println("result : " + result[200]);
    }

    void solution32() {
        /**
         * n-pandigital number is a number makes use of all the digits 1 to n only once.
         * 5-pandigital number can be a 12345, 54321 ...
         * Find sum of all products whose concatenation of multiplicand, multiplier, product is 9-pandigital number
         * ex. 7254 = 39 x 186
         */

        /**
         *  digit of product can not be bigger than sum of digits of multiplicand and multiplier
         *  digit of i can be 1 or 2
         *  digit of j can be 4 or 3
         */
        int productLimit = powInt(10, 4); // multiplicand * multiplier must be under 10000
        Set<Integer> productSet = new HashSet<>();

        for (int i = 1; i <= 99; i++) {
            for (int j = 100; j <= 9999; j++) {
                int temp = i*j;
                if( temp >= productLimit ) continue;
                if (isPanDigital(i + "" + j + "" + temp)) {
                    productSet.add(temp);
                }
            }
        }

        int result = 0;
        for (Integer integer : productSet) {
            result += integer;
        }

        System.out.println("result : " + result);
    }


    void solution33() {
        /**
         * A correctly simplified fraction of 49/98 is same as a incorrectly simplified one ( incorrectly simplifying means canceling numbers exist both in numerator and denominator ).
         * ( A correctly simplified fraction of 49/98 is 1/2. A incorrectly simplified fraction of 49/98 is 4/8 by canceling 9s. )
         * There are 4 fractions, like above less than one in value, which containing two digits in the numerator and denominator. ( trivial type excluded. like 30/50 )
         * Find the value of denominator of product of these fractions, as its lowest common terms.
         */

        /**
         * 10 <= numerator   <= 99
         * 10 <= denominator <= 99
         */

        List<int[]> result = new ArrayList<>();

        for (int i = 10; i < 100; i++) {
            for (int j = 10; j < 100; j++) {
                if( i >= j ) continue;
                if( i % 10 == 0 ) continue;

                //incorrect version of fraction
                String[] canceledString = cancelingSameCharacters(i + "", j + "");

                //skip fractions canceling result is none, only zero(0) remains, or doesn't have numbers can be canceled
                if( canceledString[0].equals("") || canceledString[1].equals("") ) continue;
                if( canceledString[0].equals("0") || canceledString[1].equals("0") ) continue;
                if( canceledString[0].equals(i+"") || canceledString[1].equals(j+"") ) continue;

                int[] canceledSimplifiedFraction = simplifyFraction(Integer.parseInt(canceledString[0]), Integer.parseInt(canceledString[1]));

                //correct version of fraction
                int[] normalSimplifiedFraction = simplifyFraction(i, j);

                if (Arrays.compare(normalSimplifiedFraction, canceledSimplifiedFraction) == 0) {
                    int[] temp = {i, j};
                    result.add(temp);
                }
            }
        }

        int productOfNumeratorsInResult = 1;
        int productOfDenominatorsInResult = 1;

        for (int[] ints : result) {
            productOfNumeratorsInResult = productOfNumeratorsInResult * ints[0];
            productOfDenominatorsInResult = productOfDenominatorsInResult * ints[1];
        }

        int[] ints = simplifyFraction(productOfNumeratorsInResult, productOfDenominatorsInResult);
        System.out.println(Arrays.toString(ints));
    }


    void solution34() {
        /**
         * Find the sum of all numbers which are equal to the sum of the factorial of their digits.
         * Note: As 1! = 1 and 2! = 2 are not sums they are not included.
         */

        /**
         *  9! = 362880
         *  number : input number
         *  digit count : number's digit count, ex. digit count of number 2312 is 4.
         *  so.. adding 1 digit, max of digit factorial count will be 362880 up.
         *  362880 * digit count > max value of number will be a maximum number.
         */

        //Find number that digit factorial can't not be bigger after that number.
        long nineFactorial = getFactorialCount(9);
        long maxNumber = 1L;
        long digits = 1L;
        long result = 0L;

        while (maxNumber <= nineFactorial * digits) {
            maxNumber = maxNumber * 10;
            digits++;
        }

        //adjusting max number to max of 9!s
        maxNumber = nineFactorial * (digits-1) + 1;

        //from 10 to max number, need to check sum of digit factorial is same as original number. ( As 1! = 1 and 2! = 2 are not sums they are not included. )
        for (int i = 10; i <= maxNumber; i++) {
            if (i == sumOfDigitFactorial(i)) {
                result += i;
            }
        }

        System.out.println("result : " + result);
    }

    void solution35() {
        /**
         * Find count of all circular primes below one million
         * circular prime : if all rotated numbers are prime, it's circular prime.( ex. 197 and rotated numbers of 197 are all primes. 197, 719, 971 )
         */

        int limit = 1000000;
        List<Integer> resultList = new ArrayList<>();

        //from 2 to one million
        for (int i = 2; i < limit; i++) {
            if(!validPrimeNumber(i)) continue;

            //rotate and check prime
            boolean allPrimesYn = true;
            for (int j = 1; j < Integer.toString(i).length(); j++) {
                if (!validPrimeNumber(rotateIntegerToRight(j, i))) {
                    allPrimesYn = false;
                    break;
                }
            }

            //if there's none-prime in rotated numbers, then continue.
            if( !allPrimesYn ) continue;

            resultList.add(i);
        }

        for (Integer integer : resultList) {
            System.out.println("result : " + integer);
        }

        System.out.println("count : " + resultList.size());

    }

    void solution36() {
        /**
         * Find the sum of all numbers below one million that are plindrome both in base 10 and base 2.
         * ex. 585 is palindrome, and binary of 585, 1001001001 is also palindrome.
         */

        int limit = 1000000; //max

        List<Integer> resultList = new ArrayList<>();

        //check palindrome from 1 to one million
        for (int i = 1; i < limit; i++) {
            if( !isPalindrome(i) ) continue;
            if( !isBinaryPalindrome(i) ) continue;
            resultList.add(i);
        }

        long result = 0L;
        for (Integer integer : resultList) {
            result += integer;
        }

        System.out.println("result : " + result);
    }

    void solution37() {
        /**
         * The number 3797 has an interesting property. Being prime itself,
         * it is possible to continuously remove digits from left to right, and remain prime at each stage: 3797, 797, 97, and 7.
         * Similarly we can work from right to left: 3797, 379, 37, and 3.
         * Find the sum of the only eleven primes that are both truncatable from left to right and right to left.
         * NOTE: 2, 3, 5, and 7 are not considered to be truncatable primes.
         */

        List<Integer> resultList = new ArrayList<>();

        int num = 9;
        boolean primeYn = true;
        while (true) {
            num++;

            //check num is prime
            primeYn = validPrimeNumber(num);
            if( primeYn == false ) continue;

            String numStr = num+""; //string of number


            //check substrings of num are prime
            for (int i = 1; i < numStr.length(); i++) {
                String temp = numStr.substring(i);

                primeYn = validPrimeNumber(Long.parseLong(temp));
                if( primeYn == false ) break;
            }
            if( primeYn == false ) continue;


            //check substrings of num are prime ( reverse order )
            for (int i = numStr.length(); i >=1; i--) {
                primeYn = validPrimeNumber(Long.parseLong(numStr.substring(0, i)));
                if( primeYn == false ) break;
            }
            if( primeYn == false ) continue;


            resultList.add(num);
            if( resultList.size() >= 11 ) break;
        }

        int result = 0;
        for (Integer integer : resultList) {
            result += integer;
        }

        System.out.println("result : " + result);
    }



    //too slow. need to find another way
    void solution38() {
        /**
         * 192 x 1 = 192, 192 x 2 = 384, 192 x 3 = 576. Then concatenated number of products becomes 1-9 pandigital ( 192384576 ).
         * And it can be written as 192 x (1,2,3) = 192384576
         * what is the largest pandigital 9-digit number by ( numeric x (1,2...n) ) ? ( n > 1 )
         */

        // numeric * (1,2 .. n )
        // limitation of numeric can be a number that is over 9 digit number when multiply 1 + multiply 2
        int limit = (1000000000/3) + 1;
        int maxPandigital = 0;

        //iter 1 to limit, product 1 to n.., if concatenated product's length is 9, then check, if over 9, then continue.
        for (int i = 1; i < limit; i++) {

            StringBuilder sb = new StringBuilder();
            boolean pandigitalYn = false;

            int n = 1;
            while (true) {
                sb.append(Integer.toString(i * n));

                //check length
                if (sb.length() == 9) {
                    pandigitalYn = isPanDigital(sb.toString());
                    break;
                } else if (sb.length() > 9) {
                    break;
                }

                if( isDupCharString(sb.toString()) ) break;

                n++;
            }

            if( n <= 1 || pandigitalYn == false ) continue;


            int currPandigital = Integer.parseInt(sb.toString());
            if (maxPandigital < currPandigital ) maxPandigital = currPandigital;
        }

        System.out.println("result : " + maxPandigital);


    }


    void solution39() {
        /**
         * If p is the perimeter of a right angle triangle with integral length sides, {a,b,c}, there are exactly three solutions for p = 120.
         * {20,48,52}, {24,45,51}, {30,40,50}
         * For which value of p ≤ 1000, is the number of solutions maximised?
         */

        // 3 <= p <= 1000
        int pOfMaxSolutions = 0;
        int maxSolutions = 0;
        
        for (int p = 3; p <= 1000; p++) {
            int solutions = getRightAngleTriangleCountByPerimeter(p);

            if (maxSolutions < solutions) {
                maxSolutions = solutions;
                pOfMaxSolutions = p;
            }
        }

        System.out.println("p of max : " + pOfMaxSolutions);
        System.out.println("max sols : " + maxSolutions);
    }

    void solution40() {
        /**
         * there's an irrational fraction which is created by concatenating positive integers.
         *   0.123456789101112131415161718192021...
         * 12th digit of this fractional part is 1
         * When d(n) is nth digit of fractional part, find d(1) * d(10) * d(100) ... * d(1000000)
         */

        int maxSize = 1000000;  //max legnth of fractional part
        StringBuilder sb = new StringBuilder();

        //make a fraction by concatenating positive integers until fractional length under maxSize
        int num = 0;
        while (true) {
            num++;
            sb.append(num);
            if( sb.length() > maxSize ) break;
        }

        String fraction = sb.toString();
        int result = 1;
        //get result of d(10^0) * ... * d(10^6)
        for (int i = 0; i <= 6; i++) {
            result = result * Integer.parseInt(fraction.charAt(powInt(10,i)-1)+"");
        }

        System.out.println("result : " + result);
    }

    void solution41() {
        /**
         * Find largest n-digit pandigital prime number
         * (n-digit pandigital means number made use of all digits 1 to n only once )
         */

        // limitation is 1000000000 ( 1-9, only once used, must be below minimum of 10-digit number )
        int limit = 1000000000;

        // from limitaion-1 to 2, find maximum pandigital prime number.
        for (int i = limit-1; i >= 2; i--) {
            if ( isNDigitPanDigital(i, false) && validPrimeNumber(i)) {
                System.out.println("result : " + i);
                break;
            }
        }
    }

    void solution42() {
        /**
         *  triangle numbers is given by this formula : 1/2 * n ( n+1 )
         *  By converting each letter in a word to a number corresponding to its alphabetical position and adding these values we form a word value.
         *  For example, the word value for SKY is 19 + 11 + 25 = 55.
         *  If the word value is a triangle number then we shall call the word a triangle word.
         *  how many are triangle words in solution42_input.txt ?
         */

        int result = 0;
        String fileName = "./src/input/solution42_input.txt";
        String fileContents = null;
        File file = new File(fileName);
        try {
            fileContents = getFileByOneline(file);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if(fileContents == null ) return;

        String s = fileContents.replaceAll("\"", "");
        String[] words = s.split(",");

        for (String word : words) {
            int t = getAlphabetNumberIgnoreCase(word);
            t = t*2; // 1/2 of n(n+1) goes to *2 of t [ 1/2*n(n+1) = t ]

            // root of t must be has precisions, and root(t) * (root(t)+1) should be t
            double rt = Math.sqrt(t);
//            System.out.println("word : " + word + ", t : " + t + ", rt: " + rt + ", (int)rt : " + (int)rt );
            if( rt == (int)rt ) continue;
            if( (int)rt * (int)(rt+1) == t ) result++;
//            System.out.println("result : " + result);
        }

        System.out.println("result : " + result);

    }

    void solution43() {
        /**
         *
         * 1406357289 is a pandigital number
         * that d(1)d(2)d(3) is divisible by 2
         *      d(3)d(4)d(5) is divisible by 3
         *      d(4)d(5)d(6) is divisible by 5
         *      ...
         *      d(8)d(9)d(10) is divisible by 17
         *
         * Find sum of all numbers that have those properties.
         */

        List<Long> resultList = new ArrayList<>();


        //Get pandigital numbers
        List<String> pandigitals = makeNDigitPandigital(10, true);

        for (String pandigital : pandigitals) {
            //non-count for start with 0
            if( pandigital.charAt(0) == '0' ) continue;

            long temp = Long.parseLong(pandigital);

            //valid if sub-strings are divisible by primes orderly.
            if(!isSubStringsDivisibleByPrimeOrderly(temp) ) continue;

            resultList.add(temp);
        }

        long result = 0L;
        for (Long aLong : resultList) {
            result += aLong;
        }

        System.out.println("result : " + result);
    }

    void solution44() {
        /**
         * Pentagonal numbers are created by formula P(n) = n(3n-1)/2.
         * first five numbers are : 1, 5, 12, 22, 35 ..
         * Find difference of pair of pentagonal numbers whose sum and difference also pentagonal numbers and difference is minimised.
         */

        //Find first pair of pentagonal numbers.( approximately with minimised difference )
        int firstLimit = 10;
        long baseDifference = 0L;
        while (true) {
            boolean foundYn = false;

            for (int i = 1; i <= firstLimit - 1; i++) {
                for (int j = i+1; j <= firstLimit; j++) {
                    long pM = getPentagonalNumber(i);
                    long pN = getPentagonalNumber(j);

                    if( !isPentagonalNumber(pM+pN) ) continue;
                    if( !isPentagonalNumber(pN-pM) ) continue;

                    foundYn = true;
                    baseDifference = pN - pM;
                    break;
                }
                if(foundYn) break;
            }

            if(foundYn) break;
            firstLimit = firstLimit + 10;
        }

        //Find limitation of n by comparing first pair of pentagonal numbers and consecutive pair(n, n+1)
        int limit = 0;
        while (true) {
            limit++;
            if (getPentagonalNumber(limit + 1) - getPentagonalNumber(limit) > baseDifference) {
                break;
            }
        }

        //Find pair of pentagonal numbers whose sum and difference also pentagonal numbers and difference is minimised.
        long minimumDifference = baseDifference;

        for (int i = 1; i <= limit - 1; i++) {
            for (int j = i+1; j <= limit; j++) {
                long pM = getPentagonalNumber(i);
                long pN = getPentagonalNumber(j);
                long sum = pM + pN;
                long diff = pN - pM;

                if (diff > minimumDifference) break;
                if( !isPentagonalNumber(sum) ) continue;
                if( !isPentagonalNumber(diff) ) continue;

                minimumDifference = diff;

            }
        }

        System.out.println("result : " + minimumDifference);
    }

    void solution45() {
        /**
         * Triangle number formula is n(n+1)/2
         * Pentagonal number formula is n(3n-1)/2
         * Hexagonal number formula is n(2n-1)
         * and Triangle(285) = Pentagonal(165) = Hexagonal(143) = 40755.
         * Find another value that Triangle, Pentagonal, Hexagonal numbers are all equal as next of 40755
         */

        long r = 40755;
        while (true) {
            r++;

            double d1 = getUnknownNumberOfTPH(TPHNum.TRIANGLE, r);
            double d2 = getUnknownNumberOfTPH(TPHNum.PENTAGONAL, r);
            double d3 = getUnknownNumberOfTPH(TPHNum.HEXAGONAL, r);

            if(d1 == (long) d1 && d2 == (long) d2 && d3 == (long) d3){
                System.out.println("d1: " + d1 + ", d2 : " + d2 + ", d3 : " + d3);
                break;
            }
        }

        System.out.println("result : " + r);

    }

    void solution46() {
        /**
         * It was proposed by Christian Goldbach that every odd composite number can be written as the sum of a prime and twice a square.
         * It turns out that the conjecture was false.
         * What is the smallest odd composite that cannot be written as the sum of a prime and twice a square?
         */

        // from 3 to infinite, first non-goldbach number which is also a composite odd number
        long result = 0L;
        for (long l = 3L; l < Long.MAX_VALUE; l = l+2) {
            if(validPrimeNumber(l)) continue;
            if(isGoldbachNumber(l)) continue;
            result = l;
            break;
        }

        System.out.println("result : " + result);
    }

    void solution47() {
        /** The first two consecutive numbers to have two distinct prime factors are:
         *  14 = 2 * 7
         *  15 = 3 * 5
         * Find the first four consecutive integers to have four distinct prime factors each. What is the first of these numbers?
         */

        //from 1 to infinite, iter
        long num = 2L;
        int targetDistinctCount = 4;
        int targetConsecutiveCount= 4;
        int count = 0;
        while (true) {
//            System.out.println("start input..");
            count = getCountOfConsecutiveNumbersWithPrimes(num, 0, targetDistinctCount);

            if (count == targetConsecutiveCount) {
                System.out.println("result : " + num);
                break;
            }
            num = num + count + 1;
        }
    }

    void solution48() {
        /**
         * Find the last ten digits of the self powered number series, 1^2 + 2^2 ... 1000^1000
         */

        //from 1 to 1000,
        //calculate self powered number by remaining only 10 digits.
        //then, calculate sum of self powered number by remaining only 10 digits

        long result = 0L;
        long lastDigits = 10L;
        long divider = powLong(10, lastDigits);

        for (long i = 1L; i <= 1000L; i++) {
            result += powLongWithLastDigits(i, i, lastDigits);
            result = result % divider;
        }

        System.out.println("result : " + result);
    }

    void solution49() {
        /**
         * 1487, 4817, 8147 are terms they are all primes, and permutations each other.
         * Find concatenation of anohter 4-digit three terms that exhibit those properties
         */

        int min = 1000;
        int max = 9999;

        //from 1000 to 9999, get permutations, valid primes, and has common difference
        List<List<Integer>> results = new ArrayList<>();

        for (int i = min; i <= max; i++) {
            //i must be a smallest number in result array list.

            List<String> elements = new ArrayList<>(Arrays.asList(Integer.toString(i).split("")));
            List<String> combs = new ArrayList<>(new HashSet<String>(getFullPermutations(elements)));
            Collections.sort(combs);

            List<Integer> result = new ArrayList<>();
            result.add(i);

            int diff = 0;
            for (String comb : combs) {
                int temp = Integer.parseInt(comb);
                // < min, continue
                if( temp < min ) continue;

                // same as i, continue.
                if( temp == i ) continue;

                // valid primes
                if( validPrimeNumber(temp) == false ) continue;

                // add to result
                result.add(temp);
            }

            if( result.size() >= 3 ) results.add(result);
        }

        //find numbers which have common difference
        List<List<Integer>> newResults = new ArrayList<>();
        for (List<Integer> result : results) {
            for (int i = 0; i < result.size()-2; i++) {
                for (int j = i+1; j < result.size()-1; j++) {
                    int diff = result.get(j) - result.get(i);
                    if (result.contains((Integer) (diff + result.get(j)))) {
                        newResults.add(new ArrayList<Integer>(List.of(result.get(i), result.get(j), result.get(j) + diff)));
                    }
                }
            }
        }

        for (List<Integer> newResult : newResults) {

        }

        //need to continue.

    }

    /**********************************************************************************************************/

    /**
     * get how many consecutive numbers have distinct prime numbers as input targetDistinctPrimeCount.
     * @param num initial number for starting.
     * @param progress current count of consecutive numbers ( always input 0 from starting. )
     * @param targetDistinctPrimeCount
     * @return count of consecutive numbers have distinct prime numbers.
     */
    int getCountOfConsecutiveNumbersWithPrimes(long num, int progress, int targetDistinctPrimeCount ) {
        if( num <= 1 || progress < 0 || targetDistinctPrimeCount <= 0 ) return 0;

        Map<Long, Long> resultOfPrimeFactorization = getResultOfPrimeFactorization(num + progress);
        int size = resultOfPrimeFactorization.size();

//        System.out.println("num : " + num + ", progress : " + progress + ", size : " + size);
//        System.out.println("result : " + resultOfPrimeFactorization.toString() );

        return size == targetDistinctPrimeCount ? getCountOfConsecutiveNumbersWithPrimes(num, progress + 1, targetDistinctPrimeCount) : progress;
    }

    /**
     * valid input n is Goldbach number or not.
     * (Goldbach numbers are composite odd that can be written as sum of a prime number and twice a square ( prime number + 2 x natural number )
     * @param n  input number
     * @return true if n is Goldbach number
     * otherwise, return false.
     */
    boolean isGoldbachNumber(long n) {

        //valid 1
        if( validPrimeNumber(n)) return false;

        //Find limit for iter
        long limit1 = n - 1;
        long limit2 = (long) Math.sqrt(n/2);

        //valid 2
        for (int i = 2; i <= limit1; i++) {
            if( !validPrimeNumber(i) ) continue;

            for (int j = 1; j <= limit2; j++) {
                if (i + 2 * j * j == n) {
//                    System.out.println("i : " + i + ", j : " + j );
                    return true;
                }
            }
        }

        return false;
    }

    private enum TPHNum {
        TRIANGLE, PENTAGONAL, HEXAGONAL
    }

    /**
     *  Triangle number formula is n(n+1)/2
     *  Pentagonal number formula is n(3n-1)/2
     *  Hexagonal number formula is n(2n-1)
     *  calculate n of T,P,H by input
     * @param e  enum value ( TRIANGLE, PENTAGONAL, HEXAGONAL )
     * @param r  Triangle, Pentagonal, Hexagonal number
     * @return unknown n by formula
     */
    double getUnknownNumberOfTPH(TPHNum e, long r) {
        //Triangle number formula is n(n+1)/2
        //So, n can be found by sqrt(2*r+1/4)-1/2

        //Pentagonal number formula is n(3n-1)/2
        //So, n can be found by sqrt(2/3*r+1/36)+1/6

        //Hexagonal number formula is n(2n-1)
        //So, n can be found by sqrt(1/2*r+1/16) + 1/4
        double result = 0.0;

        switch(e){
            case TRIANGLE -> result = (Math.sqrt(8 * r + 1) - 1) / 2;
            case PENTAGONAL -> result = (Math.sqrt(24 * r + 1) + 1) / 6;
            case HEXAGONAL -> result = (Math.sqrt(8 * r + 1) + 1) / 4;
        }
        return result;
    }

    /**
     * valid input number is pentagonal number or not ( pentagonal number of natural number )
     * @param num
     * @return  true if input number is pentagonal number
     * otherwise, return false
     */
    boolean isPentagonalNumber(long num) {
        // formula is.. n(3n-1)/2.
        // if num = n(3n-1)/2 then root(num*2/3 + 1/36) + 1/6 must be a natural number
        // for calculation, multiply sqrt(36) to reverse formula
        double reverse = Math.sqrt(num * 2 * 12 + 1 ) + 1;
        return reverse / 6 == (int) (reverse / 6) ? true : false;
    }

    /**
     * calculate formula n(3n-1)/2
     * @param n
     * @return result of formula by input n
     */
    long getPentagonalNumber(int n) {
        return n * (3L * n - 1L) / 2L;
    }

    /**
     * make n-digit pandigital numbers.
     * if zeroBaseYn is true, than make 0-n pandigital number
     * else, make 1-n pandigital number
     * @param n number as n-digit's n
     * @param zeroBaseYn start from 0(true) or 1(false)
     * @return list of n-digit pandigital numbers as string
     * if input is not valid for making pandigital, return null
     */
    List<String> makeNDigitPandigital(int n, boolean zeroBaseYn) {
        int max = zeroBaseYn ? n - 1 : n;
        int min = zeroBaseYn ? 0 : 1;

        if( max >= 10 ) return null;

        List<String> elements = new ArrayList<>();
        for (int i = min; i <= max; i++) {
            elements.add(i + "");
        }

        return getFullPermutations(elements);
    }

    /**
     * valid if 3-substrings are equal to primes orderly.
     * num[2]num[3]num[4] should be divisible by 2
     * num[3]num[4]num[5] should be divisible by 3
     * ...
     * num[8]num[9]num[10] should be divisible by 17
     * ...
     *
     * @param num
     * @return true if all 3-substrings are divisible by primes orderly
     * otherwise return false
     */
    boolean isSubStringsDivisibleByPrimeOrderly(long num) {

        int[] primes = {2, 3, 5, 7, 11, 13, 17, 19}; //declare primes under 20
        String numStr = Long.toString(num);

        // for... 2 to len-3, check divisible by prime
        int primeIndex = 0;
        for (int i = 1; i <numStr.length()-2; i++) {
            int newDigits = Integer.parseInt("" + numStr.charAt(i) + numStr.charAt(i + 1) + numStr.charAt(i + 2));
//            System.out.println("new Digit : " + newDigits);
            if( newDigits % primes[primeIndex++] != 0 ) return false;
        }

        return true;
    }

    /**
     * get sum of alphabetical number of input string.
     * ex. SKY -> 19 + 11 + 25 = 55
     *     abc -> 1 + 2 + 3 = 6
     * @param str  input string
     * @return sum of alphabetical number of input string as positive number
     *         return -1 if input string contains non-numeric character
     */
    int getAlphabetNumberIgnoreCase(String str) {
        String s = str.toUpperCase();
        int result = 0;

        //valid numeric value
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if( c < 'A' || c > 'Z') return -1;

            result += c - 'A' +1;
        }

        return result;
    }

    /**
     * Check input number is pandigital number.
     * if zeroBaseYn is true, then number which has all 0 to n-1 digits and has each only once is pandigital number
     * if zeroBaseYn is false, then number which has all 1 to n digits and has each only once is pandigital number
     * for example,
     * under zeroBaseYn is true
     * 123456 return false.( n = 6, so it should has 0..5 )
     * 125045 return false
     * 123045 return true
     * under zeroBaseYn is false
     * 123456 return true.
     * 125045 return false
     * 123045 return false ( should not contain 0 )
     * @param num
     * @param zeroBaseYn
     * @return true if input number is pandigital
     * otherwise, return false
     */
    boolean isNDigitPanDigital(long num, boolean zeroBaseYn) {

        String str = Long.toString(num);
        int strLen = str.length();
        int maxLen = zeroBaseYn ? 10 : 9;
        int base = zeroBaseYn ? 0 : 1;

        // 9-digit or 10-digit is maximum.
        if( strLen > maxLen  ) return false;

        // split number to char array
        char[] chars = str.toCharArray();

        // check duplicated number
        for (int i = 0; i < chars.length-1; i++) {
            for (int j = i+1; j < chars.length; j++) {
                if( chars[i] == chars[j]) return false;
            }
        }

        //check 1(or 0) to n
        int digit = base;
        while (digit <= strLen) {
            boolean isExistYn = false;

            for (int i = 0; i < str.length(); i++) {
                if (chars[i] == (char) (digit + '0')) {
                    isExistYn = true;
                    break;
                }
            }

            if( isExistYn == false ) return false;
            digit++;
        }

        return true;
    }


    /**
     * Get count of right angled triangle with input perimeter
     * @param p perimeter
     * @return  count of right angled triangle with perimeter
     */
    int getRightAngleTriangleCountByPerimeter(int p) {
        /**
         *  a <= b < c
         *  (a+b+c)/3 < c < a+b
         *  a^2 + b^2 = c^2
         */

        int result = 0; //count
        int c;  //adjacent of triagle
        int adder = p % 2 == 0 ? -1 : 0;

        //find value of minimum c and maximum c
        int minC = p / 3 + 1;
        int maxC = p / 2 + adder;

//        System.out.println("minC : " + minC + ", maxC : " + maxC);

        c = maxC;
        while (c >= minC) {

            //get a, b
            int b = p - c;
            int a = 0;
            while (--b >= ++a) {
//                System.out.println("a : " + a + ", b : " + b + ", c : " + c);
                if( b >= c ) continue;
                if (a * a + b * b == c * c) result++;
            }

            c--;
        }

        return result;
    }

    /**
     * valid if input number's binary is a palindrome or not
     * @param num : integer want to know if binary is palindrome
     * @return true if input number's binary is a palindrome
     *         false if not.
     */
    boolean isBinaryPalindrome(int num) {
        String b = Integer.toBinaryString(num);
        return isPalindromeStr(b);
    }


    /**
     * rotate integer to right. for example, rotateIntegerToRight(3,1234) returns 2341.
     * @param shift  : integer indicate how many cells want to move to right.
     * @param number : input number will be rotated.
     * @return rotated number
     */
    int rotateIntegerToRight(int shift, int number) {

        String numStr = Integer.toString(number);
        int numLen = numStr.length();
        char[] charArr = new char[numLen];  //rotated characters.

        for (int i = 0; i < numLen; i++) {
            int newIndex = i + shift;
            newIndex = newIndex % numLen;
            charArr[newIndex] = numStr.charAt(i);
        }

        String resultStr = new String(charArr);
        System.out.println(resultStr);

        return Integer.parseInt(resultStr);
    }

    /**
     * return sum of factorials using digit in input number.
     * (ex. input number : 323 -> return 3!+2!+3!)
     * @param number : input number
     * @return sum of digit factorials of input number
     */
    long sumOfDigitFactorial(int number) {

        long result = 0L;
        int res = number;
        int digit = 0;

        while (res != 0) {
            digit  = res % 10;
            res = res /10;
            result += getFactorialCount(digit);
        }

        return result;
    }

    /**
     * returns an array of strings which same characters removed on both input string1 and input string2
     * @param str1
     * @param str2
     * @return an string array length of 2. array[0] : canceled str1, array[1] : canceled str2
     */
    String[] cancelingSameCharacters(String str1, String str2) {

        char[] chars1 = str1.toCharArray();
        char[] chars2 = str2.toCharArray();

        for (int i = 0; i < chars1.length; i++) {
            for (int j = 0; j < chars2.length; j++) {
                if (chars1[i] == chars2[j]) {
                    chars1[i] = 0x00;
                    chars2[j] = 0x00;
                }
            }
        }

        StringBuilder sb1 = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();

        for (int i = 0; i < chars1.length; i++) {
            if( chars1[i] != 0x00 ) sb1.append(chars1[i]);
        }

        for (int i = 0; i < chars2.length; i++) {
            if( chars2[i] != 0x00 ) sb2.append(chars2[i]);
        }

        String[] result = {sb1.toString(), sb2.toString()};

        return result;
    }


    /**
     * returns an array of integers of simplified numerator and denominator
     * @param numerator    numerator of fraction need to be simplified
     * @param denominator  denominator of fraction need to be simplified
     * @return  integer array length of 2. array[0] is numerator simplified, array[1] is denominator simplified.
     */
    int[] simplifyFraction(int numerator, int denominator) {

        List<Integer> primesOfNumerator = getListOfPrimeFactorization(numerator);
        List<Integer> primesOfDenominator = getListOfPrimeFactorization(denominator);
        List<Integer> duplicateNumbers = new ArrayList<>(); //same numbers of numerator and denominator

        //find same prime numbers and canceling it in numerator
        for (Integer d : primesOfDenominator) {
            if (primesOfNumerator.contains(d)) {
                duplicateNumbers.add(d);
                primesOfNumerator.remove(d);
            }
        }

        //canceling denominator
        for (Integer d : duplicateNumbers) {
            if (primesOfDenominator.contains(d)) {
                primesOfDenominator.remove(d);
            }
        }


        int[] result = {1, 1};

        for (Integer n : primesOfNumerator) {
            result[0] = result[0] * n;
        }

        for (Integer d : primesOfDenominator) {
            result[1] = result[1] * d;
        }

        return result;
    }


    /**
     * Check input string is 9-pandigital number or not
     * @param str  input string
     * @return true if concatenation of input string is 9-pandigital number
     *         otherwise, return false
     */
    boolean isPanDigital(String str) {

        List<Character> oneToNine = new ArrayList<>(List.of('1', '2', '3', '4', '5', '6', '7', '8', '9'));

        //check length
        if( str.length() != 9 ) return false;

        //compare input string and List of 1 to 9 by removing element in list
        for (int i = 0; i < str.length(); i++) {
            if (oneToNine.remove((Character)str.charAt(i)) != true) return false;
        }

        return true;
    }

    /**
     * Check input string has duplicated characters or not
     * @param str  input string
     * @return  true if str has duplicate characters itself
     *          ,otherwise return false
     */
    boolean isDupCharString(String str) {

        for (int i = 0; i < str.length()-1; i++) {
            for (int j = i+1; j < str.length(); j++) {
                if( str.charAt(i) == str.charAt(j) ) return true;
            }
        }

        return false;
    }

    /**
     * returns recurring cycle of unit fraction ( 1/ input number )
     * @param d denominator of unit fraction
     * @return list of integers that contains recurring cycle numbers ( ex. 0.012340123401234 -> {0,1,2,3,4} )
     */
    List<Integer> getRecurringCycleOfUnitFraction(int d) {
        int numerator = 1;
        int denominator = d;
        int multiplier = 10;    // multiply 10 in each loop
        int recurringPoint = 0; // recurring cycle's index of first number in remainderList.

        // list to save remainders, check recurring cycle by this list.
        List<Integer> remainderList = new ArrayList<>();

        // quotient value right to decimal points
        List<Integer> quotientList = new ArrayList<>();


        /* find recurring cycle starting point */
        numerator = numerator * multiplier;
        while (true) {

            int quotient = numerator / denominator;
            int remainder = numerator % denominator;

            // check if remainder is not a new one, else add remainder integer as -1
            int tempIndex = remainderList.indexOf(remainder);

            if (quotient == 0) {
                remainderList.add(-1);
            } else if (tempIndex != -1) {
                recurringPoint = tempIndex;
                break;
            } else {
                remainderList.add(remainder);
            }

            // add quotient integer
            quotientList.add(quotient);

            numerator = remainder * multiplier;
        }

        // copy recurring cycle in list of quotient to result list
        List<Integer> resultList = new ArrayList<>();
        for (int i = recurringPoint; i < quotientList.size(); i++) {
            resultList.add(quotientList.get(i));
        }

        return resultList;
    }

    /**
     *
     * @param d  denominator of unit fraction
     * @return true if 1/d is infinite, false if it is not.
     */
    Boolean getUnitFractionsInfiniteOrNot(int d) {
        Map<Long, Long> resultOfPrimeFactorization = getResultOfPrimeFactorization(d);

        for (Long aLong : resultOfPrimeFactorization.keySet()) {
            if (aLong != 2L && aLong != 5L) {
                return true;
            }
        }

        return false;
    }

    /**
     *
     * @param elements : List of elements for factorial
     * @return ascending ordered factorial list made by all input elements.
     */
    List<String> getFullPermutations(List<String> elements) {
        List<String> resultList = new ArrayList<>();

        //sort input elements
        Collections.sort(elements);

        //return last element in recursive
        if( elements.size() == 1 ) return elements;

        for (String element : elements) {

            //copy input elements, remove current element and toss to recursive method
            List<String> anotherElements = new ArrayList<>();
            anotherElements.addAll(elements);
            anotherElements.remove(element);

            List<String> recursiveResult = getFullPermutations(anotherElements);

            for (String s : recursiveResult) {
                StringBuilder sb = new StringBuilder();
                sb.append(element);
                sb.append(s);
                resultList.add(sb.toString());
            }
        }

        return resultList;
    }


    long getFactorialCount(int n) {
        long result = 1L;

        for (int i = 1; i <= n; i++) {
            result = (result == 0 ? 1 : result) * i;
        }

        return result;
    }

    /**
     *
     * @param number    : target number to valid whether it is consisted of two number of input set
     * @param numberSet : set of nominee numbers that can be a part of target number
     * @return true if target number is consisted of two numbers in numberSet ( same number in set can be used twice. ex. 24 = 12+12 )
     *
     */
    boolean validNumberTwoSumOfSet(int number, Set<Integer> numberSet) {

        // inspect from 1 to half of number
        for (int i = 1; i <= (number+1)/2 ; i++) {
            if(!numberSet.contains(i)) continue;

            int rest = number - i;
            if (numberSet.contains(rest)) return true;
        }

        return false;
    }

    /**
     *
     * @param s  input string
     * @return  get sum of integer values of characters in string by alphabetically. (A -> 1, B -> 2 ...  case insensitive.)
     * Only alphabets will be counted
     * ( ex. "A$B!C" --> 1+2+3 --> return 6.   $ and ! will be ignored )
     */
    int getValueOfAlphabet(String s) {
        int result = 0;

        String s1 = s.toUpperCase();

        for (int i = 0; i < s1.length(); i++) {
            char c = s1.charAt(i);

            if (c < 0x41 || c > 0x5A) continue;
            result += s1.charAt(i) - 0x40;
        }

        return result;
    }

    /**
     *
     * @param number
     * @return sum of divisors of input number ( divisors less than n )
     */
    int getSumOfDivisors(int number) {
        int result = 0;

        for (int i = 1; i*i <= number; i++) {

            if( number % i != 0 || i == number ) continue;

            result += i;
            if( number / i < number && number / i != i ) result += number/i;
        }

        return result;
    }

    /**
     *
     * @param startDate start date for counting specific weekday
     * @param endDate end date for counting specific weekday
     * @param weekday weekday for counting. ( 0:monday ... 6:sunday )
     * @return count of specific weekday from start date to end date ( containing end date, except start date )
     */
    int getCountOfWeekDayPeriod(String startDate, String endDate, int weekday) throws Exception{

        int count1 = getCountOfWeekDay(startDate, weekday);
        int count2 = getCountOfWeekDay(endDate, weekday);

        return count2 - count1;
    }

    /**
     *
     * @param date end date for counting specific weekday
     * @param weekday weekday for counting. ( 0:monday ... 6:sunday )
     * @return count of specific weekday from 1.Jan.1900 to input date ( containing input date itself )
     */
    int getCountOfWeekDay(String date, int weekday) throws Exception{

        if( date.length() != 8 ) throw new Exception("invalid date");
        if( weekday < 0 || weekday > 6 ) throw new Exception("invalid weekday");

        int year = Integer.parseInt(date.substring(0, 4));
        int month = Integer.parseInt(date.substring(4, 6));
        int day = Integer.parseInt(date.substring(6, 8));

        LocalDate date1 = LocalDate.of(1900, 1, 1);
        LocalDate date2 = LocalDate.of(year, month, day);

        long days = ChronoUnit.DAYS.between(date1, date2);
        days += weekday;
        System.out.println("count of weekday : " + days/7);

        return 0;
    }

    /**
     *
     * @param pyramid : list of integer arrays
     * @return integer value of max path sum of input pyramid
     * caution : this method modifies input list
     */
    int getMaxPathSumOfPyramid(List<Integer[]> pyramid) {
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

        int rows = pyramid.size();

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

        if (num < 1) {
            return null;
        }

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
     * @return last "digits" of result of a^b
     * for example, a:5, b:3, digits:2
     * return 25. ( last 2 digits of 125 [5^3] )
     */
    long powLongWithLastDigits(long a, long b, long digits) {
        if( a == 0L ) return 0L;

        long result = 1L;
        long divider = powLong(10L, digits);
        for (long i = 0L; i < b; i++) {
            result = (result * a) % divider;
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
        if( a == 0L ) return 0L;

        long result = 1L;
        for (long i = 0L; i < b; i++) {
            result = result * a;
        }

        return result;
    }


    /**
     *
     * @param a
     * @param b
     * @return result of a^b
     */
    int powInt(int a, int b) {
        if( a == 0 ) return 0;

        int result = 1;
        for (int i = 0; i < b; i++) {
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


    /** valid if input number is palindrome number or not
     * @param num
     * @return true if input is a palindrome number
     *         false if input is not a palindrome number
     */
    boolean isPalindrome(int num) {
        String s = Integer.toString(num);
        return isPalindromeStr(s);
    }


    /**
     * valid if input string is palindrome or not
     * @param s
     * @return true if input is a palindrome string
     *         false if input is not a palindrome string
     */
    boolean isPalindromeStr(String s) {
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

        if( number < 0 ) number = number * -1;

        if( number == 1 ) return false;

        for (long i = 2L; i*i <= number; i++) {
            if( number % i == 0 ) return false;
        }

        return true;
    }

}


