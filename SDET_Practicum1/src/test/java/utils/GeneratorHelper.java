package utils;

import java.util.Random;

/**
 * Класс с методами генераторами
 */
public class GeneratorHelper {

    // Генерация 10-значного номера для поля Post Code


    //метод генерации 10 цифр
    public static String generatePostCode() {
        Random random = new Random();
        String postCode = "";

        for (int i = 0; i < 10; i++) {
            postCode += random.nextInt(10); // Генерация цифр от 0 до 9
        }

        return postCode;
    }

    // Метод для генерации имени на основе Post Code
    public static String generateFirstName(String postCode) {
        String firstName = "";

        for (int i = 0; i < postCode.length(); i += 2) {
            // Берем две цифры
            String pair = postCode.substring(i, Math.min(i + 2, postCode.length()));
            int number = Integer.parseInt(pair);

            // Преобразуем в букву
            char letter = (char) ('a' + (number % 26)); // 0-25 -> a-z
            firstName += letter; // Добавляем букву к имени
        }

        return firstName;
    }
}
