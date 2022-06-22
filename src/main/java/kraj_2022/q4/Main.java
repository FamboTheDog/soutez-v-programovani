package kraj_2022.q4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

class Main {

    public static void main(String args[]) throws IOException {
        Scanner scan = new Scanner(System.in);
        System.out.println("Zadejte vaše písmena (Min délka 1 max délka 10)");
        String letters = "";
        while (letters.length() < 1 || letters.length() > 10) {
            letters = scan.next().toLowerCase(); // Chnage to lowercase to match exact words.
        }

        BufferedReader reader = new BufferedReader(new FileReader(args[0]));
        String line;
        StringBuilder fileInput = new StringBuilder();
        while ((line = reader.readLine()) != null) {
            fileInput.append(line).append("\n");
        }
        String[] dictionary = fileInput.toString().split("\n");

        // Array list to store all the possible words that can be made.
        ArrayList<String> possibleWords = new ArrayList<>();
        int longestPossibleWord = 1;

        // Loop through each word in dictionary and check if that word can be formed with our letters.
        for (String word : dictionary) {
            if (canFormWord(letters, word)) { // If we can form this word with our letters.
                if (word.length() >= longestPossibleWord) { // If longer than the longest possible word we have so far, add it to the list.
                    longestPossibleWord = word.length();
                    possibleWords.add(word);
                }
            }
        }

        // Loop through possible words and print the largest possible words.
        for (String word : possibleWords) {
            if (word.length() == longestPossibleWord) {
                System.out.println("Nejdelší slovo je: " + word);
                break;
            }
        }
    }

    /* Method checks if the passed in word can be formed with the letters we have. */
    public static boolean canFormWord(String letters, String word) {

        // Foreach char check if that letter exists, if it does remove from letter string as we have used them.
        for(char c : word.toCharArray()) {
            int charIndex = letters.indexOf(c);
            if(charIndex == -1) return false; // If not found, the word can't be formed.
            letters = letters.substring(0, charIndex) + letters.substring(charIndex + 1);
        }

        return true; // Making this word is possible with the set of letters we have.
    }

}