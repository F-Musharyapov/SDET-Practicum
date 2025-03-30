package tests;

import config.BaseConfig;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;

/**
 * Общий класс с настройками для всех тестов
 */
public class BaseTest {

    /**
     * Переменная с экземпляром драйвера
     */
    protected WebDriver driver;

    /**
     * Экземпляр файла конфигурации с общими параметрами
     */
    private final BaseConfig config = ConfigFactory.create(BaseConfig.class, System.getenv());

    /**
     * Общие настройки для всех тестов, перед выполнением каждого
     */
    @BeforeMethod
    public void setUp() {
        System.setProperty(config.driverProperty(), config.driverPath());

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");

        driver = new ChromeDriver(options);

        driver.get(config.url());

        driver.manage().window().maximize();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    /**
     * Общие настройки для всех тестов, после выполнения каждого
     */
    @AfterMethod
    public void tearDown() {

        //driver.close();
    }
}