package com.company.example;

import java.util.Scanner;

/**
 * A program for calculating discounts
 */
public class Main {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.print("Zadejte původní cenu: ");
        float oldPrice = getNumberInputFromUser();
        System.out.print("Zadejte slevu v procentech: ");
        float discountInPercentage = getNumberInputFromUser();

        float onePercent = oldPrice / 100;
        float newPrice = oldPrice - (discountInPercentage * onePercent);

        System.out.println("Nová cena = " + newPrice);
    }

    public static float getNumberInputFromUser() {
        boolean isValid = false;
        float output = 0;
        while (!isValid) {
            try {
                output = scanner.nextFloat();
                isValid = true;
            } catch (NumberFormatException ignored) {}
        }
        return output;
    }

}
