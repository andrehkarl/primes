package com.andrehkarl.primes;

import org.junit.Test;

import static org.junit.Assert.*;

public class PrimesTest {

    @Test
    public void isPrimeTest() {
        assertEquals("-10 is not a prime number", false, Primes.isPrime(-10));
        assertEquals("-1 is not a prime number", false, Primes.isPrime(-1));
        assertEquals("0 is not a prime number", false, Primes.isPrime(0));
        assertEquals("1 is not a prime number", false, Primes.isPrime(1));
        assertEquals("2 is a prime number", true , Primes.isPrime(2));
        assertEquals("3 is a prime number", true , Primes.isPrime(3));
        assertEquals("7 is a prime number", true , Primes.isPrime(7));
        assertEquals("11 is a prime number", true , Primes.isPrime(11));
        assertEquals("1009 is a prime number", true , Primes.isPrime(1009));
    }

    @Test
    public void calculatePrimesUpTo10() {
        int[] expected = new int[]{2,3,5,7};
        assertArrayEquals("Prime numbers up to 10 are 2,3,5,7", expected,
                Primes.calculatePrimes(10, null).stream().mapToInt(Integer::intValue).toArray());
    }

    @Test
    public void calculatePrimesUpToMinus10() {
        int[] expected = new int[]{};
        assertArrayEquals("Prime numbers up to -10 are non existent", expected,
                Primes.calculatePrimes(-10, null).stream().mapToInt(Integer::intValue).toArray());
    }
}