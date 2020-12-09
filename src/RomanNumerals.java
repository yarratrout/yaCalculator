import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public enum RomanNumerals {
    I(1), IV(4), V(5), IX(9), X(10),
    XL(40), L(50), XC(90), C(100);

        private int value;

            RomanNumerals(int value) {
                this.value = value;
            }

            public int getValue() {
                return value;
            }

            public static List<RomanNumerals> getReverseSortedValues() {
                return Arrays.stream(values())
                        .sorted(Comparator.comparing((RomanNumerals e) -> e.value).reversed())
                        .collect(Collectors.toList());
            }

    public static String arabicToRoman(int number) {


        List<RomanNumerals> romanNumerals = RomanNumerals.getReverseSortedValues();

        int i = 0;
        StringBuilder sb = new StringBuilder();

        while ((number > 0) && (i < romanNumerals.size())) {
            RomanNumerals currentSymbol = romanNumerals.get(i);
            if (currentSymbol.getValue() <= number) {
                sb.append(currentSymbol.name());
                number -= currentSymbol.getValue();
            } else {
                i++;
            }
        }

        return sb.toString();
    }


    public static int romanToArabic(String input) {
        String romanNumeral = input.toUpperCase();
        int result = 0;

        List<RomanNumerals> romanNumerals = RomanNumerals.getReverseSortedValues();

        int i = 0;

        while ((romanNumeral.length() > 0) && (i < romanNumerals.size())) {
            RomanNumerals symbol = romanNumerals.get(i);
            if (romanNumeral.startsWith(symbol.name())) {
                result += symbol.getValue();
                romanNumeral = romanNumeral.substring(symbol.name().length());
            } else {
                i++;
            }
        }

        if (romanNumeral.length() > 0) {
            throw new IllegalArgumentException( "Оба числа должны быть арабскими или римскими цифрами одновременно");
        }

        return result;
    }
}


