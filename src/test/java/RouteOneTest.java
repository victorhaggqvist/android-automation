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
 * Test sign up flow "be healthier"
 */
public class RouteOneTest {
    private static WebDriver driver = null;

    @Test
    public void testRouteOne() throws InterruptedException {
        driver.get("and-activity://com.sillens.shapeupclub.MainActivity");
        // give init some time
        Thread.sleep(500);
        // click first button
        WebElement element1 = driver.findElement(By.xpath("//RecyclerView/FrameLayout[2]"));
        element1.click();

        date();
        genderFemale();
        heightCm();
        weightKg();

        // wait for thingy
        Thread.sleep(15000);

        WebElement skip = driver.findElement(By.id("skip_button"));
        skip.click();

        Thread.sleep(3000);
        // check that there is a calorie goal
        WebElement kcal = driver.findElement(By.id("textview_diary_left_value1"));
        Assert.assertNotEquals("", kcal.getText());
    }

    public void date() throws InterruptedException {
        // open datepicker
        Thread.sleep(1500);
        WebElement element3 = driver.findElement(By.id("button_age"));
        element3.click();

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

    public void weightKg() throws InterruptedException {
        Thread.sleep(1000);
        WebElement element1 = driver.findElement(By.id("spinner_unit"));
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
