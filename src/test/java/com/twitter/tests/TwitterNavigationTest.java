package com.twitter.tests;

import com.twitter.pages.TrendingPage;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.Assert.assertTrue;
import static org.junit.Assume.assumeTrue;

public class TwitterNavigationTest extends BaseTest {

    private TrendingPage trendingPage;

    @Test
    public void testNavigateToTrendingWithScrollAndLike() throws InterruptedException {
        assumeTrue(TwitterLoginTest.isLoginSuccessful);

        System.out.println("Iniciando prueba de navegación a la sección de Tendencias...");

        trendingPage = new TrendingPage(driver);
        WebDriverWait wait = new WebDriverWait(driver, 20);

        try {
            WebElement searchExploreButton = driver.findElementByAccessibilityId("Buscar y explorar");
            searchExploreButton.click();

            wait.until(ExpectedConditions.visibilityOf(trendingPage.getTrendingHeader()));

            performVerticalScroll();

            WebElement secondTrendingItem = driver.findElement(By.xpath("//androidx.recyclerview.widget.RecyclerView[@resource-id='android:id/list']/android.view.ViewGroup[2]"));
            secondTrendingItem.click();

            Thread.sleep(10000);

            wait.until(ExpectedConditions.presenceOfElementLocated(By.id("com.twitter.android:id/outer_layout_row_view_tweet")));
            System.out.println("Página de tendencia cargada correctamente.");

            performVerticalScroll();
            Thread.sleep(5000);
            
            WebElement randomItem = driver.findElement(By.id("com.twitter.android:id/tweet_content_text"));
            randomItem.click();

            Thread.sleep(10000);

            WebElement tweetElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@resource-id=\"com.twitter.android:id/tweet_content_view_stub\"]/android.view.View")));
            assertTrue("El tweet no se ha cargado correctamente", tweetElement.isDisplayed());
            performVerticalScroll();
            performVerticalScroll();

            WebElement tweetComment = driver.findElement(By.xpath("(//android.widget.LinearLayout[@resource-id='com.twitter.android:id/outer_layout_row_view_tweet'])[2]"));
            tweetComment.click();
            Thread.sleep(10000);

            WebElement likeButton = driver.findElementByAccessibilityId("Me gusta");
            likeButton.click();
            Thread.sleep(5000);

            WebElement likedElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("com.twitter.android:id/inline_like")));
            assertTrue("El tweet no ha recibido el 'Me gusta' correctamente", likedElement.isDisplayed());

            System.out.println("El tweet ha recibido el 'Me gusta' correctamente.");
            
        } catch (Exception e) {
            System.err.println("Error durante la prueba de navegación y 'Me gusta': " + e.getMessage());
        }
    }

    private void performVerticalScroll() {
        int startX = 300;
        int startY = 1300;
        int endY = 200;

        // Gesto de desplazamiento con TouchAction
        new TouchAction<>(driver)
                .press(PointOption.point(startX, startY))
                .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1)))
                .moveTo(PointOption.point(startX, endY))
                .release()
                .perform();

        System.out.println("Scroll vertical realizado.");
    }
}
