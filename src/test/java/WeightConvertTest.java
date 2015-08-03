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
 *
 * Additional tests between units in matrix would probably be appropriate in production
 * Especially when switching between different input fields, to make sure values goes to correct field
 */
public class WeightConvertTest {

    private WebDriver driver;

    @Test
    public void testKgToLbs() throws InterruptedException {
        precondition();

        Thread.sleep(1000);
        WebElement spinner = driver.findElement(By.xpath("//Spinner[@id='spinner_unit']"));
        spinner.click();
        WebElement element2 = driver.findElement(By.xpath("//ProximaNovaTextView[@value='kg']"));
        element2.click();
        WebElement input = driver.findElement(By.id("edittext_kg"));
        input.sendKeys("65");

        spinner.click();
        WebElement element3 = driver.findElement(By.xpath("//ProximaNovaTextView[@value='lbs']"));
        element3.click();
        Assert.assertEquals("143.3", input.getText());
    }

    @Test
    public void testKgToStLbs() throws InterruptedException {
        precondition();
        Thread.sleep(1000);
        WebElement spinner = driver.findElement(By.xpath("//Spinner[@id='spinner_unit']"));
        spinner.click();
        WebElement element2 = driver.findElement(By.xpath("//ProximaNovaTextView[@value='kg']"));
        element2.click();
        WebElement inputKg = driver.findElement(By.id("edittext_kg"));
        inputKg.sendKeys("65");

        spinner.click();
        WebElement element3 = driver.findElement(By.xpath("//ProximaNovaTextView[@value='st / lbs']"));
        element3.click();

        WebElement inputStones = driver.findElement(By.id("edittext_stones"));

        Assert.assertEquals("10", inputStones.getText());
        Assert.assertEquals("3.3", inputKg.getText());
    }

    @Test
    public void testStLbsToLbs() throws InterruptedException {
        precondition();
        Thread.sleep(1000);
        WebElement spinner = driver.findElement(By.xpath("//Spinner[@id='spinner_unit']"));
        spinner.click();
        WebElement element1 = driver.findElement(By.xpath("//ProximaNovaTextView[@value='st / lbs']"));
        element1.click();

        WebElement inputKg = driver.findElement(By.id("edittext_kg"));
        inputKg.sendKeys("6");
        WebElement inputStones = driver.findElement(By.id("edittext_stones"));
        inputStones.sendKeys("5");

        spinner.click();
        WebElement element2 = driver.findElement(By.xpath("//ProximaNovaTextView[@value='lbs']"));
        element2.click();
        Assert.assertEquals("76", inputKg.getText());
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
