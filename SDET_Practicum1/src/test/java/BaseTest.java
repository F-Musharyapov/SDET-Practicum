import config.BaseConfig;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.JavascriptExecutor;
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

    // экземпляр файла конфигурации с общими параметрами
    private final BaseConfig config = ConfigFactory.create(BaseConfig.class, System.getenv());

    /**
     * Общие настройки для всех тестов, перед выполнением каждого
     */
    @BeforeMethod
    public void setUp(){
        // Установка настройки с путем к google драйверу
        System.setProperty(config.driverProperty(), config.driverPath());

        // создание экземпляра драйвера
        //driver = new ChromeDriver();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");

        driver = new ChromeDriver(options);

        // открытие страницы по url
        driver.get(config.url());  //открывает и переходит по ссылке

        // разворот страницы на полное окно
        driver.manage().window().maximize(); // максимальный размер окна для удобства

        //Неявное ожидание по умолчанию 10 секунд
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        // Stating the Javascript Executor driver
        JavascriptExecutor js = (JavascriptExecutor)driver;
    }


    /**
     * Общие настройки для всех тестов, после выполнения каждого
     */
    @AfterMethod
    public void tearDown(){

        // остановка работы драйвера
        //driver.close();
        driver.quit();
    }
}