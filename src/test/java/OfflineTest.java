import io.selendroid.client.SelendroidDriver;
import io.selendroid.common.SelendroidCapabilities;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * Test that dialog appears when trying to process offline
 */
public class OfflineTest {

    private SelendroidDriver driver;

    @Test
    public void test() throws InterruptedException {
        driver.get("and-activity://com.sillens.shapeupclub.MainActivity");
        precondition();

        Thread.sleep(1000);
        WebElement element1 = driver.findElement(By.id("spinner_unit"));
        element1.click();
        WebElement element2 = driver.findElement(By.xpath("//ProximaNovaTextView[@value='kg']"));
        element2.click();
        WebElement input = driver.findElement(By.id("edittext_kg"));
        input.sendKeys("65");

        WebElement next = driver.findElement(By.id("button_continue"));
        next.click();

        // wait for thingy
        Thread.sleep(2000);

        WebElement alert = driver.findElement(By.partialLinkText("the internet"));
        Assert.assertNotNull(alert);
    }

    private void precondition() throws InterruptedException {
        // give init some time
        Thread.sleep(500);
        // click first button
        WebElement element1 = driver.findElement(By.xpath("//RecyclerView/FrameLayout[2]"));
        element1.click();

        date();
        genderFemale();
        heightCm();
    }

    public void date() throws InterruptedException {
        // open datepicker
        Thread.sleep(1000);
        WebElement element3 = driver.findElement(By.id("button_age"));
        element3.click();
        System.out.println();

        // press ok in datepicker
        WebElement element4 = driver.findElement(By.id("button1"));
        element4.click();
    }

    public void genderFemale() throws InterruptedException {
        // select female
        WebElement element5 = driver.findElement(By.id("radio_female"));
        element5.click();

        // click continue
        WebElement element6 = driver.findElement(By.id("button_continue"));
        element6.click();
    }

    public void heightCm() throws InterruptedException {
        Thread.sleep(1000);
        WebElement element1 = driver.findElement(By.id("spinner_unit"));
        element1.click();
        WebElement element2 = driver.findElement(By.xpath("//ProximaNovaTextView[@value='cm']"));
        element2.click();
        WebElement inputField = driver.findElement(By.id("edittext_kg"));
        inputField.sendKeys("165");

        WebElement next = driver.findElement(By.id("button_continue"));
        next.click();
    }

    @Before
    public void setup() throws Exception {
        driver = new SelendroidDriver(new SelendroidCapabilities("com.sillens.shapeupclub:2.6.2"));
        driver.setAirplaneMode(true);
    }

    @After
    public void teardown() {
        driver.setAirplaneMode(false);
        driver.quit();
    }

}
