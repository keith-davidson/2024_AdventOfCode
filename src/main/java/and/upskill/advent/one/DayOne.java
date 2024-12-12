package and.upskill.advent.one;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.lang.Math.abs;


public class DayOne {

    public static List<Pairs> readPairsFromFile(String filePath) throws IOException {
        try (Stream<String> lines = Files.lines(Paths.get(filePath))) {
            return lines.map(line -> {
                String[] parts = line.trim().split("\\s+");
                int id1 = Integer.parseInt(parts[0]);
                int id2 = Integer.parseInt(parts[1]);
                return new Pairs(id1, id2);
            }).toList();
        }
    }

    public static List<Integer> extractFirstList(List<Pairs> pairs) {
        return pairs.stream()
                .map(Pairs::id1)
                .sorted()
                .toList();
    }

    public static List<Integer> extractSecondList(List<Pairs> pairs) {
        return pairs.stream()
                .map(Pairs::id2)
                .sorted()
                .toList();
    }

    public static List<Integer> calculateDistances(List<Integer> list1, List<Integer> list2) {
        if (list1.size() != list2.size()) {
            throw new IllegalArgumentException("Lists must be of the same size");
        }

        return IntStream.range(0, list1.size())
                .mapToObj(i -> abs(list1.get(i) - list2.get(i)))
                .toList();
    }

    public static int frequencyOf(Integer value, List<Integer> list) {
        return (int) list.stream()
                .filter(i -> i.equals(value)).count();
    }

    public static List<Integer> calculateSimilarities(List<Integer> list1, List<Integer> list2) {
        if (list1.size() != list2.size()) {
            throw new IllegalArgumentException("Lists must be of the same size");
        }

        return IntStream.range(0, list1.size())
                .mapToObj(i -> list1.get(i) * frequencyOf(list1.get(i), list2))
                .toList();
    }


    public static int sumValues(List<Integer> distances) {
        AtomicInteger total = new AtomicInteger();
        distances.forEach(total::addAndGet);
        return total.get();
    }

    public static void main(String[] args) {
        Path path = Path.of(DayOne.class.getClassLoader().getResource("day1_input.txt").getPath());
        List<Pairs> input = null;
        try {
            input = DayOne.readPairsFromFile(path.toString());
        } catch (IOException e) {
            System.err.println("Failed read file, exception " + e.toString());
            throw new RuntimeException(e);
        }

        List<Integer> list1 = extractFirstList(input);
        List<Integer> list2 = extractSecondList(input);

        List<Integer> distances = calculateDistances(list1, list2);

        int totalDistance = sumValues(distances);

        System.out.println("Total distance is " + totalDistance );

        List<Integer> similarities = calculateSimilarities(list1, list2);

        int totalSimilarities = sumValues(similarities);

        System.out.println("Total similarities is " + totalSimilarities );

    }


}
