package and.upskill.advent.three;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;

class DayThreeTests {

    @Test
    void canUseRegexToProcessEmptyInput() {
        String input = "";
        List<String> parts = DayThree.parseInstructions(input);
        assertThat(parts, hasSize(0));
    }

    @Test
    void canUseRegexToIdentifyInstructions() {
        String input = "xmul(2,4)%&mul[3,7]!@^do_not_mul(5,5)+mul(32,64]then(mul(11,8)mul(8,5))";
        List<String> parts = DayThree.parseInstructions(input);
        assertThat(parts, contains("mul(2,4)", "mul(5,5)", "mul(11,8)", "mul(8,5)"));
    }

    @Test
    void canExecuteInstructionForSmallInput() {
        String instruction = "mul(2,4)";
        long result = DayThree.executeInstruction(instruction);
        assertThat(result, equalTo(8L));
    }

    @Test
    void canExecuteInstructionForLargeInput() {
        String instruction = "mul(200,400)";
        long result = DayThree.executeInstruction(instruction);
        assertThat(result, equalTo(80000L));
    }

    @Test
    void canSumInstructionCalculations() {
        List<String> parts = List.of("mul(2,4)", "mul(5,5)", "mul(11,8)", "mul(8,5)");
        long result = DayThree.sumInstructions(parts);
        assertThat(result, equalTo(161L));
    }

    @Test
    void canFindInstructionsAndConditionals() {
        String input = "xmul(2,4)&mul[3,7]!^don't()_mul(5,5)+mul(32,64](mul(11,8)undo()?mul(8,5))";
        List<String> parts = DayThree.parseInstructionsAndConditionals(input);
        assertThat(parts, contains("mul(2,4)", "don't()", "mul(5,5)", "mul(11,8)", "do()", "mul(8,5)"));
    }

    @Test
    void canProcessInstructionWithConditionals() {
        List<String> parts = List.of("mul(2,4)", "don't()", "mul(5,5)", "mul(11,8)", "do()", "mul(8,5)");
        long result = DayThree.sumInstructionsWithConditionals(parts);
        assertThat(result, equalTo(48L));
    }

}

