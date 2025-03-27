package config;

import org.aeonbits.owner.Config;

/**
 * Интерфейс с конфигурацией проекта для тестов FormPage
 */
@Config.Sources({"classpath:form_test_config.properties"})
public interface AddCustomerConfig extends Config {

    /**
     * Метод возвращает параметр firstName из form_test_config.properties
     *
     * @return параметр поля ввода First Name
     */
    String firstName();

    /**
     * Метод возвращает параметр lastName из form_test_config.properties
     *
     * @return параметр поля ввода Last Name
     */
    String lastName();

    /**
     * Метод возвращает параметр userEmail из form_test_config.properties
     *
     * @return параметр поля ввода User Email
     */
    String userEmail();

    /**
     * Метод возвращает параметр userNumber из form_test_config.properties
     *
     * @return параметр поля ввода User Number
     */
    String userNumber();

    /**
     * Метод возвращает параметр subjectsInput из form_test_config.properties
     *
     * @return параметр поля ввода Subjects
     */
    String subjectsInput();

    /**
     * Метод возвращает параметр testPicture из form_test_config.properties
     *
     * @return параметр поля загрузки изображения Picture
     */
    String testPicture();

    /**
     * Метод возвращает параметр currentAddress из form_test_config.properties
     *
     * @return параметр поля ввода Current Address
     */
    String currentAddress();
}