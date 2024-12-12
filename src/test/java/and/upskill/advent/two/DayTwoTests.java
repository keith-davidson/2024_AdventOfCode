package and.upskill.advent.two;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

class DayTwoTests {

    public static final List<Integer> EXAMPLE_1 = List.of(7, 6, 4, 2, 1);
    public static final List<Integer> EXAMPLE_2 = List.of(1, 2, 7, 8, 9);
    public static final List<Integer> EXAMPLE_3 = List.of(9, 7, 6, 2, 1);
    public static final List<Integer> EXAMPLE_4 = List.of(1, 3, 2, 4, 5);
    public static final List<Integer> EXAMPLE_5 = List.of(8, 6, 4, 4, 1);
    public static final List<Integer> EXAMPLE_6 = List.of(1, 3, 6, 7, 9);

    @Test
    void reportIsUnsafeIfLevelsDifferByMoreThanThree() {
        List<Integer> report = List.of(2, 7);
        assertThat(DayTwo.levelsHaveLargeVariance(report), equalTo(true));
    }

    @Test
    void reportIsSafeIfLevelsDifferByThreeOrLess() {
        List<Integer> report = List.of(2, 3);
        assertThat(DayTwo.levelsHaveLargeVariance(report), equalTo(false));
    }


    @Test
    void canDetectIncreasingLevels() {
        List<Integer> report = List.of(1, 3, 5);
        assertThat(DayTwo.levelsAreContinuallyIncreasing(report), equalTo(true));
    }

    @Test
    void canDetectIncreasingLevelsForShortLists() {
        List<Integer> report = List.of(1, 3);
        assertThat(DayTwo.levelsAreContinuallyIncreasing(report), equalTo(true));
    }

    @Test
    void canDetectNonIncreasingLevels() {
        List<Integer> report = List.of(1, 3, 1);
        assertThat(DayTwo.levelsAreContinuallyIncreasing(report), equalTo(false));
    }

    @Test
    void canDetectEqualLevelsAsNotIncreasing() {
        List<Integer> report = List.of(1, 1, 1);
        assertThat(DayTwo.levelsAreContinuallyIncreasing(report), equalTo(false));
    }

    @Test
    void canDetectNonIncreasingLevelsForShortLists() {
        List<Integer> report = List.of(3, 1);
        assertThat(DayTwo.levelsAreContinuallyIncreasing(report), equalTo(false));
    }

    @Test
    void canDetectDecreasingLevels() {
        List<Integer> report = List.of(5, 3, 1);
        assertThat(DayTwo.levelsAreContinuallyDecreasing(report), equalTo(true));
    }

    @Test
    void canDetectEqualLevelsAsNotDecreasing() {
        List<Integer> report = List.of(1, 1, 1);
        assertThat(DayTwo.levelsAreContinuallyDecreasing(report), equalTo(false));
    }

    @Test
    void canDetectDecreasingLevelsForShortLists() {
        List<Integer> report = List.of(3, 1);
        assertThat(DayTwo.levelsAreContinuallyDecreasing(report), equalTo(true));
    }

    @Test
    void sample1_isSafe() {
        assertThat(DayTwo.reportIsSafe(EXAMPLE_1), equalTo(true));
    }

    @Test
    void sample2_isUnsafe() {
        assertThat(DayTwo.reportIsSafe(EXAMPLE_2), equalTo(false));
    }

    @Test
    void sample3_isUnsafe() {
        assertThat(DayTwo.reportIsSafe(EXAMPLE_3), equalTo(false));
    }

    @Test
    void sample4_isUnsafe() {
        assertThat(DayTwo.reportIsSafe(EXAMPLE_4), equalTo(false));
    }

    @Test
    void sample5_isUnsafe() {
        assertThat(DayTwo.reportIsSafe(EXAMPLE_5), equalTo(false));
    }

    @Test
    void sample6_isSafe() {
        assertThat(DayTwo.reportIsSafe(EXAMPLE_6), equalTo(true));
    }

    @Test
    void canCountNumberOfSafeReports() {
        List<List<Integer>> reports = List.of(
                EXAMPLE_1,
                EXAMPLE_2,
                EXAMPLE_3,
                EXAMPLE_4,
                EXAMPLE_5,
                EXAMPLE_6
        );
        assertThat(DayTwo.countSafeReports(reports), equalTo(2));
    }

    @Test
    void canReadReportsFromFile() throws IOException {
        Path path = Path.of(getClass().getClassLoader().getResource("day2_test_input.txt").getPath());
        List<List<Integer>> reports = DayTwo.readReportsFromFile(path.toString());
        assertThat(reports.size(), equalTo(8));
    }

    @Test
    void dampenerCanMakeReportSafe_example4() {
        assertThat(DayTwo.reportIsSafe(EXAMPLE_4), equalTo(false));
        assertThat(DayTwo.dampenReport(EXAMPLE_4), equalTo(true));
    }

    @Test
    void dampenerCanMakeReportSafe_example5() {
        assertThat(DayTwo.reportIsSafe(EXAMPLE_5), equalTo(false));
        assertThat(DayTwo.dampenReport(EXAMPLE_5), equalTo(true));
    }

    @Test
    void cannotDampenerToMakeReportSafe_example2() {
        assertThat(DayTwo.reportIsSafe(EXAMPLE_2), equalTo(false));
        assertThat(DayTwo.dampenReport(EXAMPLE_2), equalTo(false));
    }

    @Test
    void cannotDampenerToMakeReportSafe_example3() {
        assertThat(DayTwo.reportIsSafe(EXAMPLE_3), equalTo(false));
        assertThat(DayTwo.dampenReport(EXAMPLE_3), equalTo(false));
    }

    @Test
    void canCountNumberOfSafeDampenedReports() {
        List<List<Integer>> reports = List.of(
                EXAMPLE_1,
                EXAMPLE_2,
                EXAMPLE_3,
                EXAMPLE_4,
                EXAMPLE_5,
                EXAMPLE_6
        );
        assertThat(DayTwo.countSafeDampenedReports(reports), equalTo(4));
    }
}
