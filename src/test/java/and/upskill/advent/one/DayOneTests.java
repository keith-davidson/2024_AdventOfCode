package and.upskill.advent.one;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertThrows;


class DayOneTests {

    public static final List<Pairs> SAMPLE = List.of(
            new Pairs(3, 4),
            new Pairs(4, 3),
            new Pairs(2, 5),
            new Pairs(1, 3),
            new Pairs(3, 9),
            new Pairs(3, 3)
    );


    @Test
    void canExtractEmptyFirstList() {
        List<Integer> extracted = DayOne.extractFirstList(Collections.emptyList());

        assertThat(extracted, hasSize(0));
    }

    @Test
    void canExtractFirstList() {
        List<Integer> sorted = DayOne.extractFirstList(SAMPLE);

        assertThat(sorted, contains(1,2,3,3,3,4));
    }


    @Test
    void canExtractEmptySecondList() {
        List<Integer> extracted = DayOne.extractSecondList(Collections.emptyList());

        assertThat(extracted, hasSize(0));
    }

    @Test
    void canExtractSecondList() {
        List<Integer> sorted = DayOne.extractSecondList(SAMPLE);

        assertThat(sorted, contains(3,3,3,4,5,9));
    }


    @Test
    void canCalculateOnEmptyLists() {
        List<Integer> list1 = Collections.emptyList();
        List<Integer> list2 = Collections.emptyList();

        List<Integer> distances = DayOne.calculateDistances(list1, list2);

        assertThat(distances, hasSize(0));
    }

    @Test
    void cannotCalculateDifferentSized() {
        List<Integer> list1 = List.of(1,2,8);
        List<Integer> list2 = List.of(4,7);

        assertThrows(IllegalArgumentException.class, () -> {
            DayOne.calculateDistances(list1, list2);
        });
    }

    @Test
    void canCalculateDistances() {
        List<Integer> list1 = List.of(1,2,8);
        List<Integer> list2 = List.of(4,7,2);

        List<Integer> distances = DayOne.calculateDistances(list1, list2);

        assertThat(distances, contains(3,5,6));
    }

    @Test
    void canFindFrequencyOfMissingItems() {
        List<Integer> list = List.of(2,1,2);

        int itemOneFreq = DayOne.frequencyOf(3,list);

        assertThat(itemOneFreq, equalTo(0));
    }

    @Test
    void canFindFrequencyOfItems() {
        List<Integer> list = List.of(2,1,2);

        int itemOneFreq = DayOne.frequencyOf(2,list);

        assertThat(itemOneFreq, equalTo(2));
    }

    @Test
    void similarityZeroProducesZero() {
        List<Integer> list1 = List.of(1,2,8);
        List<Integer> list2 = List.of(4,7,3);

        List<Integer> similarities = DayOne.calculateSimilarities(list1, list2);

        assertThat(similarities, contains(0,0,0));
    }

    @Test
    void similarityOneProducesValue() {
        List<Integer> list1 = List.of(1,2,8);
        List<Integer> list2 = List.of(2,7,3);

        List<Integer> similarities = DayOne.calculateSimilarities(list1, list2);

        assertThat(similarities, contains(0,2,0));
    }

    @Test
    void similarityTwoProducesValue() {
        List<Integer> list1 = List.of(1,2,8);
        List<Integer> list2 = List.of(3,8,8);

        List<Integer> similarities = DayOne.calculateSimilarities(list1, list2);

        assertThat(similarities, contains(0,0,16));
    }

    @Test
    void similarityWithMultipleValues() {
        List<Integer> list1 = List.of(
                3,
                4,
                2,
                1,
                3,
                3
        );
        List<Integer> list2 = List.of(
                4,
                3,
                5,
                3,
                9,
                3
        );

        List<Integer> similarities = DayOne.calculateSimilarities(list1, list2);

        assertThat(similarities, contains(9,4,0,0,9,9));
    }

    @Test
    void sumEmptyListProducesZero() {
        int sum = DayOne.sumValues(Collections.emptyList());

        assertThat(sum, equalTo(0));
    }

    @Test
    void canSumValuesList() {
        int sum = DayOne.sumValues(List.of(3,5,6));

        assertThat(sum, equalTo(14));
    }


    @Test
    void canReadPairsFromFile() throws IOException {
        Path path = Path.of(getClass().getClassLoader().getResource("day1_test_input.txt").getPath());
        List<Pairs> pairs = DayOne.readPairsFromFile(path.toString());

        assertThat(pairs, contains(
                new Pairs(3, 4),
                new Pairs(4, 3),
                new Pairs(2, 5),
                new Pairs(1, 3),
                new Pairs(3, 9),
                new Pairs(3, 3)));
    }

}