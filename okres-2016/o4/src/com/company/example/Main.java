package com.company.example;

import java.util.HashMap;
import java.util.Scanner;

/**
 * Converts a number in roman form to a number in arabic form
 */
public class Main {

    private static final HashMap<Character, Integer> romanValues = new HashMap<>();

    static {
        romanValues.put('I', 1);
        romanValues.put('V', 5);
        romanValues.put('X', 10);
        romanValues.put('L', 50);
        romanValues.put('C', 100);
        romanValues.put('D', 500);
        romanValues.put('M', 1000);
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.print("Zadejte číslo v římském tvaru: ");
        String input = scanner.next();

        System.out.println("Číslo v arabském tvaru: " + convert(input));
    }

    public static int convert(String s) {
        int integer = 0;

        for(int i = 0; i < s.length(); i++){
            if(i == s.length()-1 || (romanValues.get(s.charAt(i)) >= romanValues.get(s.charAt(i+1)))){
                integer+=romanValues.get(s.charAt(i));
            }else{
                integer+=romanValues.get(s.charAt(i+1))-romanValues.get(s.charAt(i));
                i++;
            }
        }
        return integer;
    }

}
