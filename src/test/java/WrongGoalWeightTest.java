import io.selendroid.client.SelendroidDriver;
import io.selendroid.common.SelendroidCapabilities;
import static io.selendroid.client.waiter.TestWaiter.waitForElement;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Test "Lose Weight" path entering higher goal weight than start weight
 * This test will most likely fail, see comment bellow
 */
public class WrongGoalWeightTest {

    private WebDriver driver;

    @Test
    public void testToBelowKg() throws InterruptedException {
        driver.get("and-activity://com.sillens.shapeupclub.MainActivity");

        preconditionBelow();

        Thread.sleep(1000);
        WebElement element1 = driver.findElement(By.xpath("//Spinner[@id='spinner_unit']"));
        element1.click();
        WebElement element2 = driver.findElement(By.xpath("//ProximaNovaTextView[@value='kg']"));
        element2.click();

        WebElement input = driver.findElement(By.id("edittext_kg"));
        input.sendKeys("90");

        WebElement next = driver.findElement(By.id("button_continue"));
        next.click();

        // this should in theory check that the toast appears, according to https://github.com/selendroid/selendroid/blob/85b1f6dc95e41c721261e5b969bbd6a95cfe12a5/selendroid-test-app/src/test/java/io/selendroid/nativetests/WaitForProgressBarGoneAwayTest.java#L87-L93
        // though I have had no luck getting it to work, tested on nexus 5 avd api 22
        WebElement toast = waitForElement(By.partialLinkText("must be below"), 6, driver);
        Assert.assertNotNull(toast);
    }

    @Test
    public void testMustBeAboveKg() throws InterruptedException {
        driver.get("and-activity://com.sillens.shapeupclub.MainActivity");

        preconditionAbove();

        Thread.sleep(1000);
        WebElement element1 = driver.findElement(By.xpath("//Spinner[@id='spinner_unit']"));
        element1.click();
        WebElement element2 = driver.findElement(By.xpath("//ProximaNovaTextView[@value='kg']"));
        element2.click();

        WebElement input = driver.findElement(By.id("edittext_kg"));
        input.sendKeys("44");

        WebElement next = driver.findElement(By.id("button_continue"));
        next.click();

        // this should in theory check that the toast appears, according to https://github.com/selendroid/selendroid/blob/85b1f6dc95e41c721261e5b969bbd6a95cfe12a5/selendroid-test-app/src/test/java/io/selendroid/nativetests/WaitForProgressBarGoneAwayTest.java#L87-L93
        // though I have had no luck getting it to work, tested on nexus 5 avd api 22
        WebElement toast = waitForElement(By.partialLinkText("must be above"), 6, driver);
        Assert.assertNotNull(toast);
    }

    public void preconditionBelow() throws InterruptedException {
        Thread.sleep(500);
        WebElement element = driver.findElement(By.xpath("//RecyclerView/FrameLayout[3]"));
        element.click();

        date();
        genderMale();
        heightFtIn();
        weightKg();
    }

    public void preconditionAbove() throws InterruptedException {
        Thread.sleep(500);
        WebElement element = driver.findElement(By.xpath("//RecyclerView/FrameLayout[4]"));
        element.click();
        element.click();

        date();
        genderMale();
        heightFtIn();
        weightKg();
    }

    public void date() throws InterruptedException {
        // open datepicker
        Thread.sleep(1000);
        WebElement element3 = driver.findElement(By.xpath("//ProximaNovaTextView[@id='button_age']"));
        element3.click();

        // press ok in datepicker
        WebElement element4 = driver.findElement(By.xpath("//Button[@id='button1']"));
        element4.click();
    }

    public void genderMale() throws InterruptedException {
        // select female
        WebElement element5 = driver.findElement(By.xpath("//RadioButton[@id='radio_male']"));
        element5.click();

        // click continue
        WebElement element6 = driver.findElement(By.id("button_continue"));
        element6.click();
    }

    public void heightFtIn() throws InterruptedException {
        Thread.sleep(1000);
        WebElement element1 = driver.findElement(By.xpath("//Spinner[@id='spinner_unit']"));
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

    public void weightKg() throws InterruptedException {
        Thread.sleep(1000);
        WebElement element1 = driver.findElement(By.xpath("//Spinner[@id='spinner_unit']"));
        element1.click();
        WebElement element2 = driver.findElement(By.xpath("//ProximaNovaTextView[@value='kg']"));
        element2.click();
        WebElement input = driver.findElement(By.id("edittext_kg"));
        input.sendKeys("65");

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
