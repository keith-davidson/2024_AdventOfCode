package and.upskill.advent.two;

import and.upskill.advent.one.DayOne;
import and.upskill.advent.one.Pairs;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.lang.Math.abs;

public class DayTwo {

    private static final int LIMIT = 3;

    public static boolean levelsHaveLargeVariance(List<Integer> report) {
        for (int i = 0; i < report.size() - 1; i++) {
                int difference = abs(report.get(i) - report.get(i + 1));
                if (difference > LIMIT) {
                    return true;
                }
        }
        return false;
    }

    public static boolean levelsAreContinuallyIncreasing(List<Integer> report) {
        if (report.size() == 2) {
            return report.get(0) < report.get(1);
        }
        return IntStream.range(0, report.size() - 1)
                .noneMatch(i -> report.get(i + 1) <= report.get(i));
    }

    public static boolean levelsAreContinuallyDecreasing(List<Integer> report) {
        if (report.size() == 2) {
            return report.get(0) > report.get(1);
        }
        return IntStream.range(0, report.size() - 1)
                .noneMatch(i -> report.get(i + 1) >= report.get(i));
    }

    public static boolean reportIsSafe(List<Integer> report) {
        if (levelsHaveLargeVariance(report))
            return false;

        return levelsAreContinuallyIncreasing(report) || levelsAreContinuallyDecreasing(report);
    }

    public static int countSafeReports(List<List<Integer>> reports) {
        AtomicInteger safe = new AtomicInteger(0);

        reports.forEach(report -> {
            if (reportIsSafe(report)) {
                safe.incrementAndGet();
            }
        });

        return safe.get();
    }

    public static List<List<Integer>> readReportsFromFile(String filePath) throws IOException {
        try (Stream<String> lines = Files.lines(Paths.get(filePath))) {
            return lines.map(line -> {
                String[] levels = line.trim().split("\\s+");
                List<Integer> report = new ArrayList<>(levels.length);
                for (String level : levels) {
                    report.add(Integer.parseInt(level));
                }
                return report;
            }).toList();
        }
    }

    public static boolean dampenReport(List<Integer> unSafeReport) {
        // remove the levels within in the report one at a time and see if the remaining report is safe
        for (int i = 0; i < unSafeReport.size(); i++) {
            List<Integer> dampenedReport = new ArrayList<>(unSafeReport);
            dampenedReport.remove(i);
            if (reportIsSafe(dampenedReport)) {
                return true;
            }
        }
        return false;
    }

    public static int countSafeDampenedReports(List<List<Integer>> reports) {
        AtomicInteger safe = new AtomicInteger(0);

        reports.forEach(report -> {
            if (reportIsSafe(report)) {
                safe.incrementAndGet();
            } else if (dampenReport(report)) {
                safe.incrementAndGet();
            }
        });

        return safe.get();
    }

    public static void main(String[] args) {
        Path path = Path.of(DayTwo.class.getClassLoader().getResource("day2_input.txt").getPath());
        List<List<Integer>> input = null;
        try {
            input = DayTwo.readReportsFromFile(path.toString());
        } catch (IOException e) {
            System.err.println("Failed read file, exception " + e.toString());
            throw new RuntimeException(e);
        }

        int safeReports = DayTwo.countSafeReports(input);
        System.out.println("There are " + safeReports + " safe reports");

        int safeDampenedReports = DayTwo.countSafeDampenedReports(input);
        System.out.println("There are " + safeDampenedReports + " dampened safe reports");
    }
}
