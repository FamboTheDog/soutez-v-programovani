package com.company.example;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/**
 * Calculates how many days have passed since the beginning of the year
 */
public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Zadejte datum: ");
        String input = scanner.next();

        LocalDate date = LocalDate.parse(input, DateTimeFormatter.ofPattern("d.M.yyyy"));
        System.out.println(date.getDayOfYear() + " dní uběhlo od počátku roku");
    }

}
