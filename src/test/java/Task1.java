import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;

public class Task1 {
    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = new EdgeDriver();
        driver.get("https://open.spotify.com/");
        WebElement loginButton = driver.findElement(By.xpath("//*[@id=\"main\"]/div/div[2]/div[3]/header/div[5]/button[2]"));
        loginButton.click();
        //UC-1
        Thread.sleep(5000);

        WebElement emailForm = driver.findElement(By.xpath("/html/body/div[1]/div/div[2]/div/div/div[1]/div[1]/input"));
        emailForm.sendKeys("a");
        emailForm.sendKeys(Keys.BACK_SPACE);

        Thread.sleep(2000);

        WebElement passwordForm = driver.findElement(By.xpath("/html/body/div[1]/div/div[2]/div/div/div[1]/div[2]/div[2]/input"));
        passwordForm.sendKeys("a");
        passwordForm.sendKeys(Keys.BACK_SPACE);

        String exp_err_email_or_name = "Укажіть ім’я користувача Spotify або адресу електронної пошти.";
        String exp_err_pass = "Введіть пароль.";

        Thread.sleep(10000);

        WebElement err_email_or_name = driver.findElement(By.xpath("/html/body/div[1]/div/div[2]/div/div/div[1]/div[1]/div[2]/span"));
        String txt_err_email_or_name = err_email_or_name.getText();
        Assert.assertEquals(exp_err_email_or_name, txt_err_email_or_name);

        WebElement err_pass = driver.findElement(By.xpath("/html/body/div[1]/div/div[2]/div/div/div[1]/div[2]/div[3]/span"));
        String txt_err_pass = err_pass.getText();
        Assert.assertEquals(exp_err_pass, txt_err_pass);

        //UC-2
        // expected error message
        String err = "Неправильне ім’я користувача або пароль.";

        // identify actual error message
        emailForm.sendKeys("a");
        passwordForm.sendKeys("a");

        WebElement submitButton = driver.findElement(By.xpath("/html/body/div[1]/div/div[2]/div/div/div[1]/div[4]/button"));
        submitButton.click();
        Thread.sleep(5000);
        WebElement error = driver.findElement(By.xpath("/html/body/div[1]/div/div[2]/div/div/div[1]/div"));
        String actual = error.getText();
        Assert.assertEquals(err, actual);

        //UC-3
        emailForm.clear();
        passwordForm.clear();
        emailForm.sendKeys("real-email");
        passwordForm.sendKeys("real-password");
        submitButton.click();
        Thread.sleep(10000);
        WebElement account = driver.findElement(By.xpath("/html/body/div[4]/div/div[2]/div[3]/header/button[2]/figure"));
        account.click();
        WebElement profile = driver.findElement(By.xpath("/html/body/div[22]/div/div/ul/li[2]/a/span"));
        profile.click();
        Thread.sleep(5000);
        WebElement name = driver.findElement(By.xpath("/html/body/div[4]/div/div[2]/div[3]/div[1]/div[2]/div[2]/div/div/div[2]/main/section/div/div[1]/div[5]/span[2]/button/span/h1"));
        String txt_name = name.getText();
        String exp_name = "Bogdan Rymar";
        Assert.assertEquals(exp_name, txt_name);
        driver.quit();
    }
}