package com.company.example;

import java.util.*;

/**
 * Program na seřazení závodníků v hodu oštěpem
 */
public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static final HashMap<String, ArrayList<Throw>> throwers = new HashMap<>();

    private static final int NUMBER_OF_WINNERS_TO_SHOW = 3;

    public static void main(String[] args) {
        boolean wantToContinue = true;
        while (wantToContinue) {
            loadPerson();
            System.out.println("Chcete pokračovat? [A/N]");
            wantToContinue = Character.toUpperCase(scanner.next().charAt(0)) == 'A';
        }
        TreeSet<String> winners = sortThrowersToSet();
        printWinners(winners);
    }

    private static void printWinners(TreeSet<String> winners) {
        for (int i = 0; i < NUMBER_OF_WINNERS_TO_SHOW; i++) {
            String name = winners.pollLast();
            if (name == null) break;

            ArrayList<Throw> hisThrows = throwers.get(name);
            Throw bestThrow = new Throw(0, 0);
            for (Throw hisThrow : hisThrows) {
                if (hisThrow.getLength() > bestThrow.getLength()) {
                    bestThrow = new Throw(hisThrow.getNumber(), hisThrow.getLength());
                }
            }

            System.out.println("| pořadí: " + (i + 1) + " | jméno: " + name +
                               " | číslo pokusu: " + bestThrow.getNumber() +
                               " | délka hodu: " + bestThrow.getLength() + " |");
        }
    }

    private static TreeSet<String> sortThrowersToSet() {
        TreeSet<String> treeSet = new TreeSet<>((o1, o2) -> {
            ArrayList<Throw> throwsOfFirstPerson = throwers.get(o1);
            int firstPersonScore = throwsOfFirstPerson.stream().mapToInt(Throw::getLength).sum();
            ArrayList<Throw> throwsOfSecondPerson = throwers.get(o2);
            int secondPersonScore = throwsOfSecondPerson.stream().mapToInt(Throw::getLength).sum();
            if (firstPersonScore == secondPersonScore) return 0;
            else if (firstPersonScore > secondPersonScore) return 1;
            return -1;
        });
        treeSet.addAll(throwers.keySet());
        return treeSet;
    }

    private static void loadPerson() {
        System.out.print("Zadejte jméno závodníka: ");
        String name = scanner.next();

        if (throwers.get(name) != null && throwers.get(name).size() > 2) {
            System.out.println("Tento závodník již vyčerpal všechny jeho pokusy :(");
        } else {
            loadThrows(name);
        }
    }

    private static void loadThrows(String playerName) {
        ArrayList<Throw> currentThrows = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            System.out.print("Zadejte číslo pokusu: ");
            int tryNumber = getInputNumber();
            System.out.print("Zadejte délku hodu: ");
            int throwLength = getInputNumber();

            Throw newThrow = new Throw(tryNumber, throwLength);
            currentThrows.add(newThrow);
        }

        throwers.put(playerName, currentThrows);
    }

    private static int getInputNumber() {
        boolean isValid = true;
        int output = 0;
        while (isValid) {
            try {
                output = scanner.nextInt();
                isValid = false;
            } catch (NumberFormatException ignored) {}
        }
        return output;
    }

}
