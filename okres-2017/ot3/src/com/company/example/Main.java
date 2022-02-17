package com.company.example;

/**
 * A program to calculate prime numbers to 500
 */
public class Main {

    public static void main(String[] args) {
        calculatePrimeNumber(100);
    }

    public static void calculatePrimeNumber(int max) {
        for (int i = 2; i < max; i++) {
            if (isPrime(i)) System.out.println(i);
        }
    }

    public static boolean isPrime(int x) {
        for (int j = 2; j < x; j++) {
            if (x % j == 0) {
                return false;
            }
        }
        return true;
    }

}
