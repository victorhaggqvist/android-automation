import io.selendroid.client.SelendroidDriver;
import io.selendroid.common.SelendroidCapabilities;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.touch.TouchActions;

/**
 * Test sign up flow "Lose Weight"
 */
public class RouteTwoTest {
    private static WebDriver driver = null;

    String kcalTest;

    @Test
    public void test() throws InterruptedException {
        driver.get("and-activity://com.sillens.shapeupclub.MainActivity");

        Thread.sleep(500);
        WebElement element = driver.findElement(By.xpath("//RecyclerView/FrameLayout[3]"));
        element.click();

        date();
        genderMale();
        heightFtIn();
        weightLbs();
        goalWeightKg();
        teakWeeks();

        // wait for thingy
        Thread.sleep(15000);
        WebElement skip = driver.findElement(By.id("skip_button"));
        skip.click();

        Thread.sleep(3000);
        WebElement verify = driver.findElement(By.id("textview_diary_left_value1"));
        Assert.assertEquals(kcalTest, verify.getText());
    }

    private void teakWeeks() throws InterruptedException {
        Thread.sleep(1000);
        WebElement seekbar = driver.findElement(By.id("seekbar"));
        TouchActions flick = new TouchActions(driver).flick(seekbar, -100, 0, 0);
        flick.perform();

        WebElement kcal = driver.findElement(By.id("textview_calories"));
        String text = kcal.getText();
        kcalTest = text.split(" ")[0];

        WebElement next = driver.findElement(By.id("button_continue"));
        next.click();
    }

    public void date() throws InterruptedException {
        // open datepicker
        Thread.sleep(1000);
        WebElement element3 = driver.findElement(By.id("button_age"));
        element3.click();

        // press ok in datepicker
        WebElement element4 = driver.findElement(By.id("button1"));
        element4.click();
    }

    public void genderMale() throws InterruptedException {
        // select female
        WebElement element5 = driver.findElement(By.id("radio_male"));
        element5.click();

        // click continue
        WebElement element6 = driver.findElement(By.id("button_continue"));
        element6.click();
    }

    public void heightFtIn() throws InterruptedException {
        Thread.sleep(1000);
        WebElement element1 = driver.findElement(By.id("spinner_unit"));
        element1.click();
        WebElement element2 = driver.findElement(By.xpath("//ProximaNovaTextView[@value='ft / in']"));
        element2.click();

        WebElement inpu1 = driver.findElement(By.id("edittext_stones"));
        inpu1.sendKeys("5");
        WebElement inpu2 = driver.findElement(By.id("edittext_kg"));
        inpu2.sendKeys("6");

        WebElement next = driver.findElement(By.id("button_continue"));
        next.click();
    }

    public void weightLbs() throws InterruptedException {
        Thread.sleep(1000);
        WebElement element1 = driver.findElement(By.id("spinner_unit"));
        element1.click();
        WebElement element2 = driver.findElement(By.xpath("//ProximaNovaTextView[@value='lbs']"));
        element2.click();
        WebElement input = driver.findElement(By.id("edittext_kg"));
        input.sendKeys("165");

        WebElement next = driver.findElement(By.id("button_continue"));
        next.click();
    }

    public void goalWeightKg() throws InterruptedException {
        Thread.sleep(1000);
        WebElement element1 = driver.findElement(By.id("spinner_unit"));
        element1.click();
        WebElement element2 = driver.findElement(By.xpath("//ProximaNovaTextView[@value='kg']"));
        element2.click();

        WebElement input = driver.findElement(By.id("edittext_kg"));
        input.sendKeys("50");

        WebElement next = driver.findElement(By.id("button_continue"));
        next.click();
    }

    @Before
    public void setup() throws Exception {
        driver = new SelendroidDriver(new SelendroidCapabilities("com.sillens.shapeupclub:2.6.2"));
    }

    @After
    public void teardown() {
        driver.quit();
    }

}
