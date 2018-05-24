package com.andrehkarl.primes;

import org.springframework.util.StopWatch;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/***
 * Class representing the list of prime numbers up to a given maximum
 */
public class Primes {
    private int initial;
    private List<Integer> primes;

    /**
     * Constructor
     *
     * @param initial  The number up to which the prime numbers will be calculated
     * @param parallel If true the calculation will be parallelized
     */
    Primes(int initial, Boolean parallel) {
        this.initial = initial;
        this.primes  = calculatePrimes(this.initial, parallel);

    }

    /**
     * Required getter for object serialization
     * @return the initial attribute
     */
    public int getInitial() { return initial; }

    /**
     * Required getter for object serialization
     * @return the list of prime numbers
     */
    public List<Integer> getPrimes() {
        return primes;
    }

    /**
     * Returns whether a number is a prime number
     *
     * @param num The number to be checked
     * @return true if it is a prime number else false
     */
    public static boolean isPrime (int num) {
        if ( 1 >= num ) {
            return false;
        }
        if ( num % 2 == 0 && num > 2 ) {
            return false;
        }
        for (int i = 3; i < num; i+=2) {
            if ( num % i == 0 ) {
                return false;
            }
        }
        return true;
    }

    /**
     * Calculates the prime numbers up to a given maximum and store the result in primes
     *
     * @param max      The number up to which and including the prime numbers will be calculated
     * @param parallel If true the calculation will be parallelized
     * @return The list of prime numbers
     */
    public static List<Integer> calculatePrimes(int max, Boolean parallel) {

        IntStream stream;
        List<Integer> result;
        StopWatch watch = new StopWatch();
        watch.start();
        if (parallel != null && parallel == true) {
            stream = IntStream.range(0, max+1).parallel();
        } else {
            stream = IntStream.range(0, max+1);
        }
        result = stream
                .filter(n -> isPrime(n))
                .boxed()
                .collect(Collectors.toList());

        watch.stop();
        System.out.println("calculatePrimes took " + watch.getTotalTimeMillis() + " ms");
        return result;
    }
}
