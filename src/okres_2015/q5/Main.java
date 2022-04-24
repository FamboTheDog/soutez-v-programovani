package okres_2015.q5;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Main {

    private static BufferedReader reader;
    private static final Scanner scanner = new Scanner(System.in);
    private static int startYear;
    private static int endYear;

    public static void main(String[] args) throws IOException {
        loadFile();

        Main main = new Main();
        HashMap<Integer, ArrayList<LotteryWeek>> lotteryYears = main.loadLotteryYears();

        getMostAppeared(lotteryYears);
        getMostAppearedTwo(lotteryYears);
        getAllApperarences(lotteryYears);

        System.out.println("Chcete vygenerovat náhodná číslo? [A/N]");
        if (Character.toUpperCase(scanner.next().charAt(0)) == 'A') {
            LotteryWeek randomLotteryWeek = generateRandomNumbers();
            ArrayList<Integer> diff = getAllNumbers(randomLotteryWeek);

            TreeSet<LotteryWeek> closest = new TreeSet<>((o1, o2)-> {
                ArrayList<Integer> first = getAllNumbers(o1);
                int firstDifference = 0;
                for (int i = 0; i < first.size(); i++) {
                    firstDifference += Math.abs(diff.get(i) - first.get(i));
                }
                ArrayList<Integer> second = getAllNumbers(o2);
                int secondDifference = 0;
                for (int i = 0; i < second.size(); i++) {
                    secondDifference += Math.abs(diff.get(i) - second.get(i));
                }
                return Integer.compare(firstDifference, secondDifference);
            });

            lotteryYears.forEach((year, lottery) -> closest.addAll(lottery));

            LotteryWeek lotteryWeek = closest.pollFirst();
            assert lotteryWeek != null;
            System.out.println("Největší shoda čísel byla roku: " + lotteryWeek.getYear() + " a týdne: " + lotteryWeek.getWeek());
        }
    }

    private static ArrayList<Integer> getAllNumbers(LotteryWeek o1) {
        ArrayList<Integer> allNumbers1 = new ArrayList<>();
        for (int i1 : o1.getSecondMove()) {
            allNumbers1.add(i1) ;
        }
        allNumbers1.add(o1.getSecondMoveAddition());
        for (int i1 : o1.getFirstMove()) {
            allNumbers1.add(i1) ;
        }
        allNumbers1.add(o1.getFirstMoveAddition());
        return allNumbers1;
    }

    private static LotteryWeek generateRandomNumbers() {
        Random rng = new Random();
        int[] firstMove = new int[6];
        for (int i : firstMove) {
            firstMove[i] = rng.nextInt(100) + 1;
            System.out.print(firstMove[i] + ";");
        }
        int firstMoveAddition = rng.nextInt(100) + 1;
        System.out.print(firstMoveAddition + ";");
        int[] secondMove = new int[6];
        for (int i : secondMove) {
            secondMove[i] = rng.nextInt(100) + 1;
            System.out.print(secondMove[i] + ";");
        }
        int secondMoveAddition = rng.nextInt(100) + 1;
        System.out.println(secondMoveAddition);
        return new LotteryWeek(0, 0, firstMove, firstMoveAddition, secondMove, secondMoveAddition);
    }

    private HashMap<Integer, ArrayList<LotteryWeek>> loadLotteryYears() throws IOException {
        HashMap<Integer, ArrayList<LotteryWeek>> lotteryYears = new HashMap<>();
        String line;
        reader.readLine(); // there is content information on the first line
        while ((line = reader.readLine()) != null) {
            String[] lineParts = line.split(";");
            int[] linePartsAsInts = Arrays.stream(lineParts).mapToInt(Integer::parseInt).toArray();
            int[] firstMoveNumbers = Arrays.copyOfRange(linePartsAsInts, 2, 8);
            int[] secondMoveNumbers = Arrays.copyOfRange(linePartsAsInts, 9, 15);
            LotteryWeek lotteryWeek = new LotteryWeek(linePartsAsInts[0],
                    linePartsAsInts[1],
                    firstMoveNumbers,
                    linePartsAsInts[8],
                    secondMoveNumbers,
                    linePartsAsInts[15]);
            if (lotteryYears.containsKey(linePartsAsInts[0])) {
                ArrayList<LotteryWeek> weeks = lotteryYears.get(linePartsAsInts[0]);
                weeks.add(lotteryWeek);
                lotteryYears.replace(linePartsAsInts[0], weeks);
            } else {
                lotteryYears.put(linePartsAsInts[0], new ArrayList<>(Collections.singletonList(lotteryWeek)));
            }
        }
        startYear = lotteryYears.keySet().stream().min(Integer::compareTo).orElse(1957);
        endYear = lotteryYears.keySet().stream().max(Integer::compareTo).orElse(2006);
        return lotteryYears;
    }

    private static void getMostAppeared(HashMap<Integer, ArrayList<LotteryWeek>> lotteryYears) {
        System.out.println("Zadejte začáteční číslo rozsahu: ");
        int start = getInputNumberInBounds(startYear, endYear);
        System.out.println("Zadejte konečné číslo rozsahu: ");
        int end = getInputNumberInBounds(start, endYear);

        ArrayList<Integer> allNumbers = new ArrayList<>();

        for (int i = start; i <= end; i++) {
            for (LotteryWeek lotteryWeek : lotteryYears.get(i)) {
                for (int i1 : lotteryWeek.getFirstMove()) {
                   allNumbers.add(i1) ;
                }
                allNumbers.add(lotteryWeek.getFirstMoveAddition());
            }
        }

        allNumbers.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .ifPresent(x-> System.out.println(x.getKey() + " je nejvíce objevované číslo v prvním tahu"));
    }

    private static void getMostAppearedTwo(HashMap<Integer, ArrayList<LotteryWeek>> lotteryYears) {
        System.out.println("Zadejte začáteční číslo rozsahu: ");
        int start = getInputNumberInBounds(startYear, endYear);
        System.out.println("Zadejte konečné číslo rozsahu: ");
        int end = getInputNumberInBounds(start, endYear);

        ArrayList<Integer> allNumbers = new ArrayList<>();

        for (int i = start; i <= end; i++) {
            for (LotteryWeek lotteryWeek : lotteryYears.get(i)) {
                for (int i1 : lotteryWeek.getSecondMove()) {
                    allNumbers.add(i1) ;
                }
                allNumbers.add(lotteryWeek.getSecondMoveAddition());
            }
        }

        allNumbers.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .ifPresent(x-> System.out.println(x.getKey() + " je nejvíce objevované číslo v druhém tahu"));
    }
    private static void getAllApperarences(HashMap<Integer, ArrayList<LotteryWeek>> lotteryYears) {
        System.out.println("Zadejte začáteční číslo rozsahu: ");
        int start = getInputNumberInBounds(startYear, endYear);
        System.out.println("Zadejte konečné číslo rozsahu: ");
        int end = getInputNumberInBounds(start, endYear);

        ArrayList<Integer> allNumbers = new ArrayList<>();

        for (int i = start; i <= end; i++) {
            for (LotteryWeek lotteryWeek : lotteryYears.get(i)) {
                for (int i1 : lotteryWeek.getSecondMove()) {
                    allNumbers.add(i1) ;
                }
                allNumbers.add(lotteryWeek.getSecondMoveAddition());
                for (int i1 : lotteryWeek.getFirstMove()) {
                    allNumbers.add(i1) ;
                }
                allNumbers.add(lotteryWeek.getFirstMoveAddition());
            }
        }

        allNumbers.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue())
                .forEach(System.out::println);
    }

    private static int getInputNumberInBounds(int min, int max) {
        int output;
        do {
           output = getInputNumber();
        } while(output < min || output > max);
        return output;
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

    private static void loadFile() {
        final String fileName = "/sportka-nedele.txt";

        InputStream inputStream = Main.class.getResourceAsStream(fileName);
        if (inputStream == null) {
            System.out.println("Soubor sportka-nedele.txt nenalazen :(");
            return;
        }
        InputStreamReader streamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
        reader = new BufferedReader(streamReader);
    }

}
