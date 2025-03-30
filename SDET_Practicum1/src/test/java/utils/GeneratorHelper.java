package utils;

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
}
