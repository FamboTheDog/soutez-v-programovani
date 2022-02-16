package com.company.example;

import java.util.Arrays;
import java.util.Scanner;

/**
 * A program to calculate how many seconds have passed since the start of the day
 * @author Tomáš Podivínský
 */
public class Main {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean isValid = false;

        int[] dividedAsNumbers = new int[3];
        while (!isValid) {
            System.out.println("Zadejte čas (HH:mm:ss)");
            String input = scanner.next();
            String[] divided = input.split(":");
            if (divided.length < 3) continue;
            dividedAsNumbers = Arrays.stream(divided).mapToInt(Integer::parseInt).toArray();

            if (dividedAsNumbers[0] > 24 || dividedAsNumbers[1] > 59 || dividedAsNumbers[2] > 59) continue;
            isValid = true;
        }


        int secondsFromStartOfDay = dividedAsNumbers[2];
        secondsFromStartOfDay += dividedAsNumbers[1] * 60;
        secondsFromStartOfDay += dividedAsNumbers[0] * 60 * 60;

        System.out.println("Od začátku dne uběhlo: " + secondsFromStartOfDay);
    }

}
