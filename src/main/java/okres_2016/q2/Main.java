package okres_2016.q2;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        ArrayList<Integer> integers = new ArrayList<>();
        int newInt;
        do {
            System.out.println("Zadejte číslo od 1 do 100");
            newInt = getNumberInputFromUser();
            if (newInt < 1 || newInt > 100) {
                System.out.println(newInt + " je neplatné číslo");
                continue;
            }
            integers.add(newInt);
        } while (newInt > 0);

        System.out.println("Nejmenší zadané číslo: " + integers.stream().mapToInt(Integer::intValue).min().orElse(0));
        System.out.println("Nejmenší zadané číslo: " + integers.stream().mapToInt(Integer::intValue).max().orElse(0));
        System.out.println("Nejmenší zadané číslo: " + integers.stream().mapToInt(Integer::intValue).sorted().limit(integers.size()-1)
                .skip(integers.size()-2).findFirst().orElse(0));
    }

    public static int getNumberInputFromUser() {
        boolean isValid = false;
        int output = 0;
        while (!isValid) {
            try {
                output = scanner.nextInt();
                isValid = true;
            } catch (NumberFormatException ignored) {}
        }
        return output;
    }

}
