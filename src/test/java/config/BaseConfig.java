package config;

import org.aeonbits.owner.Config;

/**
 * Интерфейс с основной конфигурацией проекта
 */
@Config.Sources({"classpath:config.properties"})
public interface BaseConfig extends Config {

    @Key("driver.property")
    @DefaultValue("webdriver.chrome.driver")
    String driverProperty();

    @Key("driver.path")
    @DefaultValue("C:\\Users\\musha\\IdeaProjects\\SDET-Practicum-UI\\chromedriver\\chromedriver.exe")
    String driverPath();

    @Key("url")
    @DefaultValue("https://www.globalsqa.com/angularJs-protractor/BankingProject/#/manager")
    String url();

}