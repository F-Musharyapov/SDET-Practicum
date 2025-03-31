package config;

import org.aeonbits.owner.Config;

/**
 * Интерфейс с основной конфигурацией проекта
 */
@Config.Sources({"classpath:config.properties"})
public interface BaseConfig extends Config {

    @Key("driver.property")
    String driverProperty();

    @Key("driver.path")
    String driverPath();

    @Key("url")
    String url();

}