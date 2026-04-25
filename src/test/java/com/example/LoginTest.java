import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginTest {

    @Test
    void test_login_with_incorrect_credentials() {

        System.setProperty("webdriver.chrome.driver", "/usr/bin/chromedriver");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");

        WebDriver driver = new ChromeDriver(options);

        driver.navigate().to("http://103.139.122.250:4000/");

        // ✅ ADD EXPLICIT WAIT HERE
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("email")))
            .sendKeys("erbjoker6@gmail.com");

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("password")))
            .sendKeys("abcdefg");

        wait.until(ExpectedConditions.elementToBeClickable(By.id("m_login_signin_submit")))
            .click();

        // wait for error message
        String errorText = wait.until(
            ExpectedConditions.visibilityOfElementLocated(
                By.xpath("/html/body/div/div/div[1]/div/div/div/div[2]/form/div[1]")
            )
        ).getText();

        assertTrue(errorText.contains("Incorrect email or password"));

        driver.quit();
    }
}
