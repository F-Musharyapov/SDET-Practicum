package config;

import org.aeonbits.owner.Config;

/**
 * Интерфейс с конфигурацией проекта для инпутов
 */
@Config.Sources({"classpath:input_config.properties"})
public interface AddCustomerConfig extends Config {

    /**
     * Метод возвращает параметр LastName из input_config.properties
     */
    String getLastName();

}