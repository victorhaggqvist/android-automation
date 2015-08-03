import io.selendroid.client.SelendroidDriver;
import io.selendroid.common.SelendroidCapabilities;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Test that weight conventions are displayed correctly when switching between units
 */
public class HeightConvertTest {

    private WebDriver driver;

    @Test
    public void testFtInToCm() throws InterruptedException {
        precondition();

        Thread.sleep(1000);
        WebElement spinner = driver.findElement(By.id("spinner_unit"));
        spinner.click();
        WebElement element1 = driver.findElement(By.xpath("//ProximaNovaTextView[@value='ft / in']"));
        element1.click();

        WebElement inputStones = driver.findElement(By.id("edittext_stones"));
        inputStones.sendKeys("6");
        WebElement inputKg = driver.findElement(By.id("edittext_kg"));
        inputKg.sendKeys("5");

        spinner.click();
        WebElement element2 = driver.findElement(By.xpath("//ProximaNovaTextView[@value='cm']"));
        element2.click();

        Assert.assertEquals("195.6", inputKg.getText());
    }

    @Test
    public void testCmToFtIn() throws InterruptedException {
        precondition();

        Thread.sleep(1000);
        WebElement spinner = driver.findElement(By.id("spinner_unit"));
        spinner.click();
        WebElement element1 = driver.findElement(By.xpath("//ProximaNovaTextView[@value='cm']"));
        element1.click();

        WebElement inputKg = driver.findElement(By.id("edittext_kg"));
        inputKg.sendKeys("165");

        spinner.click();
        WebElement element2 = driver.findElement(By.xpath("//ProximaNovaTextView[@value='ft / in']"));
        element2.click();

        WebElement inputStones = driver.findElement(By.id("edittext_stones"));

        Assert.assertEquals("5", inputKg.getText());
        Assert.assertEquals("5", inputStones.getText());
    }

    public void precondition() throws InterruptedException {
        driver.get("and-activity://com.sillens.shapeupclub.MainActivity");

        Thread.sleep(500);
        WebElement element = driver.findElement(By.xpath("//RecyclerView/FrameLayout[2]"));
        element.click();

        date();
        genderFemale();
    }

    private void genderFemale() {
        // select first sex
        WebElement element5 = driver.findElement(By.id("radio_female"));
        element5.click();

        // click continue
        WebElement element6 = driver.findElement(By.id("button_continue"));
        element6.click();
    }

    private void date() throws InterruptedException {
        // open datepicker
        Thread.sleep(1000);
        WebElement element3 = driver.findElement(By.id("button_age"));
        element3.click();

        // press ok in datepicker
        WebElement element4 = driver.findElement(By.id("button1"));
        element4.click();
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
