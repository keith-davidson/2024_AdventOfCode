package and.upskill.advent.three;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DayThree {

    private static final String INSTRUCTION_REGEX
            = "mul\\(\\d{1,3},\\d{1,3}\\)";
    private static final String INSTRUCTION_AND_CONDITIONALS_REGEX
            = "mul\\(\\d{1,3},\\d{1,3}\\)|do\\(\\)|don't\\(\\)";


    public static List<String> parseInstructions(String input) {
        Pattern pattern = Pattern.compile(INSTRUCTION_REGEX);
        Matcher matcher = pattern.matcher(input);
        return matcher.results().map(MatchResult::group).toList();
    }

    public static long executeInstruction(String instruction) {
        String[] parts = instruction.split(",");
        int number1 = Integer.parseInt(parts[0].substring(4));
        int number2 = Integer.parseInt(parts[1].substring(0, parts[1].length() - 1));
        return (long) number1 * number2;
    }

    public static List<String> parseInstructionsAndConditionals(String input) {
        Pattern pattern = Pattern.compile(INSTRUCTION_AND_CONDITIONALS_REGEX);
        Matcher matcher = pattern.matcher(input);
        return matcher.results().map(MatchResult::group).toList();
    }

    public static long sumInstructions(List<String> parts) {
        return parts.stream().mapToLong(DayThree::executeInstruction).sum();
    }

    public static long sumInstructionsWithConditionals(List<String> parts) {
        boolean enabled = true;
        long sum = 0;

        for (String part : parts) {
            if (part.equals("do()")) {
                enabled = true;
            } else if (part.equals("don't()")) {
                enabled = false;
            } else if (enabled) {
                sum += executeInstruction(part);
            }
        }

        return sum;
    }


    private static String fileToString(String string) {
        try {
            return Files.readString(Path.of(string));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        Path path = Path.of(DayThree.class.getClassLoader().getResource("day3_input.txt").getPath());
        String input = fileToString(path.toString());

        // Part 1
        //List<String> instructions = parseInstructions(input);
        //long sum = sumInstructions(instructions);

        // Part 2
        List<String> instructionsAndConditionals = parseInstructionsAndConditionals(input);
        long sum = sumInstructionsWithConditionals(instructionsAndConditionals);

        System.out.println("Sum is " + sum);
    }


}
