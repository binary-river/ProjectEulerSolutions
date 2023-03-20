import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
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
//        solutions6();
//        solution7();
//        solution8();
        solutions9();
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

    void solutions6() {
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

    void solutions9() {
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

    /**********************************************************************************************************/

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
     * @return a hashMap of prime factorization to input number
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
