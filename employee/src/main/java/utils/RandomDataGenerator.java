package utils;

import java.util.Random;

public abstract class RandomDataGenerator {

    private static Random RANDOM = new Random();
    private static String NAME_PREFIX = "0test";
    private static String UPPERCASE_ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static String AVAILABLE_SYMBOLS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

    public static String getName() {
        return  NAME_PREFIX + Math.floor(Math.random() * 11111);
    }

    public static String getThreeLettersCode() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 3; i++)
            builder.append(UPPERCASE_ALPHABET.charAt(RANDOM.nextInt(UPPERCASE_ALPHABET.length())));
        return builder.toString();
    }

    public static String getThreeDigitsCode() {
        return String.valueOf(RANDOM.nextInt(900) + 100);
    }

    public static String getSymbol() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 2; i++)
            builder.append(AVAILABLE_SYMBOLS.charAt(RANDOM.nextInt(AVAILABLE_SYMBOLS.length())));
        return builder.toString();
    }

}
