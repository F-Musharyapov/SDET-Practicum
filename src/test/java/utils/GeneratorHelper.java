package utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Класс с методами генераторами данных для полей
 */
public class GeneratorHelper {

    /**
     * Генерирует 10 рандомных цифр для поля Post Code.
     */
    public static String generatePostCode() {
        Random random = new Random();
        String postCode = "";

        for (int i = 0; i < 10; i++) {
            postCode += random.nextInt(10);
        }

        return postCode;
    }

    /**
     * Генерирует имя для поля FirstName на основе цифр из поля Post Code.
     *
     * @param postCode элемент содержащий рандомные цифры для преобразования
     */
    public static String generateFirstName(String postCode) {
        String firstName = "";

        for (int i = 0; i < postCode.length(); i += 2) {

            String pair = postCode.substring(i, Math.min(i + 2, postCode.length()));
            int number = Integer.parseInt(pair);

            char letter = (char) ('a' + (number % 26));
            firstName += letter;
        }

        return firstName;
    }

    /**
     * Метод вычисления средней длины имен
     *
     * @param names список имен
     * @return средняя длина имен
     */
    public static double calculateAverageLength(List<String> names) {
        if (names.isEmpty()) {
            return 0;
        }
        double totalLength = 0;
        for (String name : names) {
            totalLength += name.length();
        }
        return totalLength / names.size();
    }

    /**
     * Метод поиска имен, ближайших к средней длине
     *
     * @param names         список имен
     * @param averageLength средняя длина имен
     * @return список имен, ближайших к средней длине
     */
    public static List<String> findClosestNames(List<String> names, double averageLength) {
        List<String> closestNames = new ArrayList<>();
        double closestDifference = Double.MAX_VALUE;

        for (String name : names) {
            double difference = Math.abs(name.length() - averageLength);
            if (difference < closestDifference) {
                closestDifference = difference;
                closestNames.clear();
                closestNames.add(name);
            } else if (difference == closestDifference) {
                closestNames.add(name);
            }
        }
        return closestNames;
    }
}
