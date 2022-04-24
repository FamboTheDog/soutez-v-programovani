package okres_2017.q4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class Main {

    private static BufferedReader reader;
    private static HashMap<String, Integer> data;

    public static void main(String[] args) throws IOException {
        loadFile("/darky.txt");

        data = loadDataFromFile();

        Collection<Partition> partitions = simplePartition(3);
        String[] names = {"Jana", "Petra", "Hanka"};
        int i = 0;
        for (Partition partition : partitions) {
            System.out.print(names[i++] + " dostala: ");
            partition.getNames().forEach(x-> System.out.print(x + " "));
            System.out.println();
        }
    }

    private static HashMap<String, Integer> loadDataFromFile() throws IOException {
        HashMap<String, Integer> dataFromFile = new HashMap<>();
        String line;
        while ((line = reader.readLine()) != null && !line.isEmpty()) {
            String[] lineParts = line.split(";");
            dataFromFile.put(lineParts[0], Integer.parseInt(lineParts[1]));
        }
        return dataFromFile;
    }

    private static void loadFile(String fileName) {
        InputStream inputStream = okres_2016.q1.Main.class.getResourceAsStream(fileName);
        if (inputStream == null) {
            System.out.println("Soubor sportka-nedele.txt nenalazen :(");
            return;
        }
        InputStreamReader streamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
        reader = new BufferedReader(streamReader);
    }

    public static Collection<Partition> simplePartition(int partitionCount) {
        PartitionComparator partitionComparator = new PartitionComparator();
        int pointer = 0;
        ArrayList<String> names = new ArrayList<>(data.keySet());
        Queue<Integer> numberQueue = new ArrayDeque<>(data.values());
        List<Partition> partitions = new ArrayList<>();
        for (int i = 0; i < partitionCount; i++) {
            partitions.add(new Partition());
        }
        while (!numberQueue.isEmpty()) {
            Integer number = numberQueue.poll();
            Partition lowestSumPartition = getLowestSumPartition(partitions, partitionComparator);

            lowestSumPartition.increaseSum(number);
            lowestSumPartition.addName(names.get(pointer));
            pointer++;
        }
        return partitions;
    }

    private static Partition getLowestSumPartition(List<Partition> partitions, PartitionComparator partitionComparator) {
        partitions.sort(partitionComparator);
        return partitions.get(0);
    }

    private static class Partition {
        private int sum;
        private final ArrayList<String> names = new ArrayList<>();

        Partition() {
            this.sum = 0;
        }

        public void increaseSum(int amount) {
            this.sum += amount;
        }

        public int getSum() {
            return this.sum;
        }

        public void addName(String name) {
            this.names.add(name);
        }

        public ArrayList<String> getNames() {
            return this.names;
        }
    }

    private static class PartitionComparator implements Comparator<Partition> {
        @Override
        public int compare(Partition partitionA, Partition partitionB) {
            return Integer.compare(partitionA.getSum(), partitionB.getSum());
        }
    }

}
