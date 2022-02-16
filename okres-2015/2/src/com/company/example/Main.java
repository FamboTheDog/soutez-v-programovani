package com.company.example;

import java.util.HashMap;
import java.util.Scanner;

/**
 * A program to convert text to morse code
 */
public class Main {

    private static final HashMap<Character, String> morseCode = new HashMap<>();

    static {
        morseCode.put('A', ".-");
        morseCode.put('B', "-...");
        morseCode.put('C', "-.-.");
        morseCode.put('D', "-..");
        morseCode.put('E', ".");
        morseCode.put('F', "..-.");
        morseCode.put('G', "--.");
        morseCode.put('H', "....");
        morseCode.put('I', "..");
        morseCode.put('J', ".---");
        morseCode.put('K', ".-.");
        morseCode.put('L', ".-..");
        morseCode.put('M', "--");
        morseCode.put('N', "-.");
        morseCode.put('O', "---");
        morseCode.put('P', ".--.");
        morseCode.put('Q', "--.-");
        morseCode.put('R', "-.-");
        morseCode.put('S', "...");
        morseCode.put('T', "-");
        morseCode.put('U', "..-");
        morseCode.put('V', "...-");
        morseCode.put('W', ".--");
        morseCode.put('X', "-..-");
        morseCode.put('Y', "-.--");
        morseCode.put('Z', "--..");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Write what you want to convert: ");
        String input = scanner.nextLine().toUpperCase();

        System.out.println(toMorseCode(input));
    }

    public static String toMorseCode(String input) {
        StringBuilder output = new StringBuilder();

        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == ' ') {
                output.deleteCharAt(output.length() - 1);
                output.append("//");
            } else {
                output.append(morseCode.get(input.charAt(i))).append("/");
            }
        }
        return output.toString();
    }

}
