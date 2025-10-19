package calculator;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringCalculator {
    public static int add(String text) {
        if (text == null || text.isEmpty()) {
            return 0;
        }

        String delimiter = ",|:";
        String numbersText = text;

        Pattern pattern = Pattern.compile("//(.)\\\\\\\\n(.*)");
        Matcher matcher = pattern.matcher(text);
        if (matcher.find()) {
            delimiter = Pattern.quote(matcher.group(1));
            numbersText = matcher.group(2);
        }

        if (numbersText.isEmpty()) {
            return 0;
        }

        try {
            int[] numbers = Arrays.stream(numbersText.split(delimiter))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            if (Arrays.stream(numbers).anyMatch(n -> n < 0)) {
                throw new IllegalArgumentException("Negative numbers are not allowed.");
            }

            return Arrays.stream(numbers).sum();
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Input contains non-numeric values.");
        }
    }
}