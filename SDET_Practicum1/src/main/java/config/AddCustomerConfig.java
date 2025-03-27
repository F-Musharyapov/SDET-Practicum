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
    String getFirstName();
    String getLastName();
    String getPostCode();
}