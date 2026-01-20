package ru.practicum.practicum.generate;

import org.apache.commons.lang3.RandomStringUtils;
import ru.practicum.practicum.model.User;

/**
 * Генератор тестовых данных пользователя
 */

public class UserDataFactory {

    public static String generateName() {
        return RandomStringUtils.randomAlphabetic(10);
    }

    public static String generateEmail() {
        return String.format("%s@%s.ru",
                RandomStringUtils.randomAlphabetic(5),
                RandomStringUtils.randomAlphabetic(4));
    }

    public static String generateValidPassword() {
        return RandomStringUtils.randomAlphanumeric(8);
    }

    public static String generateInvalidPassword() {
        return RandomStringUtils.randomAlphanumeric(4); // меньше 6 символов
    }
}
