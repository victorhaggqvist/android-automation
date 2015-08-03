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
 * Verify that the entered details correspond to what is listed in settings
 */
public class PersonalDetailsTest {

    private WebDriver driver;

    private String kgWeight = "65";
    private String kgGoalWeight = "50";
    private String cmHeight = "165";

    @Test
    public void testVerifyValues() throws InterruptedException {
        driver.get("and-activity://com.sillens.shapeupclub.MainActivity");

        Thread.sleep(500);
        // click first button
        WebElement element1 = driver.findElement(By.xpath("//RecyclerView/FrameLayout[3]"));
        element1.click();

        dateGender();
        height();
        weight();
        goalWeight();
        weekTweak();

        // wait for thingy
        Thread.sleep(20000);
        WebElement skip = driver.findElement(By.id("skip_button"));
        skip.click();

        navToSetting();

        Thread.sleep(1000);
        WebElement goalWeight = driver.findElement(By.xpath("//ListView/RelativeLayout[1]/ProximaNovaTextView[2]"));
        Assert.assertEquals(kgGoalWeight, goalWeight.getText().split(" ")[0]);

        WebElement weight = driver.findElement(By.xpath("//ListView/RelativeLayout[2]/ProximaNovaTextView[2]"));
        Assert.assertEquals(kgWeight, weight.getText().split(" ")[0]);

        WebElement height = driver.findElement(By.xpath("//ListView/RelativeLayout[5]/ProximaNovaTextView[2]"));
        Assert.assertEquals(cmHeight, height.getText().split(" ")[0]);

        WebElement gender = driver.findElement(By.xpath("//ListView/RelativeLayout[7]/ProximaNovaTextView[2]"));
        Assert.assertEquals("Female", gender.getText());
    }

    private void navToSetting() throws InterruptedException {
        Thread.sleep(4000);
        // toggle drawer
        WebElement drawerToggle = driver.findElement(By.xpath("//Toolbar/ImageButton"));
        drawerToggle.click();

        Thread.sleep(500);
        WebElement settings = driver.findElement(By.xpath("//RecyclerView[@id=\"left_drawer\"]/LinearLayout[1]"));
        settings.click();

        Thread.sleep(3000);
        WebElement personalDetails = driver.findElement(By.xpath("//RelativeLayout[1]"));
        personalDetails.click();
    }

    private void weekTweak() throws InterruptedException {
        Thread.sleep(1000);
        WebElement seekbar = driver.findElement(By.id("seekbar"));
        TouchActions flick = new TouchActions(driver).flick(seekbar, -100, 0, 0);
        flick.perform();

        WebElement element1 = driver.findElement(By.id("button_continue"));
        element1.click();
    }

    private void goalWeight() throws InterruptedException {
        Thread.sleep(1000);
        // make sure kg is set
        WebElement element1 = driver.findElement(By.id("spinner_unit"));
        element1.click();
        WebElement element2 = driver.findElement(By.xpath("//ProximaNovaTextView[@value='kg']"));
        element2.click();

        // enter weight
        WebElement element3 = driver.findElement(By.id("edittext_kg"));
        element3.sendKeys(kgGoalWeight);

        // click continue
        WebElement element4 = driver.findElement(By.id("button_continue"));
        element4.click();
    }

    private void weight() throws InterruptedException {
        Thread.sleep(1000);
        // make sure kg is set
        WebElement element1 = driver.findElement(By.id("spinner_unit"));
        element1.click();
        WebElement element2 = driver.findElement(By.xpath("//ProximaNovaTextView[@value='kg']"));
        element2.click();

        // enter weight
        WebElement input = driver.findElement(By.id("edittext_kg"));
        input.sendKeys(kgWeight);

        // click continue
        WebElement next = driver.findElement(By.id("button_continue"));
        next.click();
    }

    private void height() throws InterruptedException {
        Thread.sleep(1500);
        // make sure cm is set
        WebElement element1 = driver.findElement(By.id("spinner_unit"));
        element1.click();
        WebElement element2 = driver.findElement(By.xpath("//ProximaNovaTextView[@value='cm']"));
        element2.click();

        // enter height
        WebElement input = driver.findElement(By.id("edittext_kg"));
        input.sendKeys(cmHeight);

        // click continue
        WebElement next = driver.findElement(By.id("button_continue"));
        next.click();
    }

    private void dateGender() throws InterruptedException {
        // open datepicker
        Thread.sleep(1000);
        WebElement element1 = driver.findElement(By.id("button_age"));
        element1.click();

        // press ok in datepicker
        WebElement element2 = driver.findElement(By.id("button1"));
        element2.click();

        // select female
        WebElement element3 = driver.findElement(By.id("radio_female"));
        element3.click();

        // click continue
        WebElement element4 = driver.findElement(By.id("button_continue"));
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
