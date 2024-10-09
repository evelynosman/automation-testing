package com.nivo.tests;

import com.nivo.pages.TrendingPage;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.Assert.assertTrue;
import static org.junit.Assume.assumeTrue;

public class NivoNavigationTest extends BaseTest {

    private TrendingPage trendingPage;
    private static final Logger logger = LogManager.getLogger(NivoNavigationTest.class);

    @Test
    public void testNavigateToTrendingWithScrollAndLike() throws InterruptedException {
        assumeTrue(NivoLoginTest.isLoginSuccessful);

        logger.info("Iniciando prueba de navegación a la sección de Tendencias...");
        takeScreenshot("Inicio_Prueba_Navegacion_Tendencias");

        trendingPage = new TrendingPage(driver);
        WebDriverWait wait = new WebDriverWait(driver, 20);

        try {
            WebElement searchExploreButton = driver.findElementByAccessibilityId("Buscar y explorar");
            searchExploreButton.click();
            logger.info("Clic en icono de tendencias.");
            takeScreenshot("Clic_Icono_Tendencias");

            wait.until(ExpectedConditions.visibilityOf(trendingPage.getTrendingHeader()));
            logger.info("Sección de Tendencias cargada correctamente.");
            takeScreenshot("Seccion_Tendencias_Cargada");

            performVerticalScroll();

            WebElement secondTrendingItem = driver.findElement(By.xpath("//androidx.recyclerview.widget.RecyclerView[@resource-id='android:id/list']/android.view.ViewGroup[2]"));
            secondTrendingItem.click();
            logger.info("Clic en un ítem de la lista de Tendencias.");
            takeScreenshot("Clic_Item_Tendencia");

            Thread.sleep(10000);

            wait.until(ExpectedConditions.presenceOfElementLocated(By.id("com.twitter.android:id/outer_layout_row_view_tweet")));
            logger.info("Página de la tendencia seleccionada cargada correctamente.");
            takeScreenshot("Pagina_Tendencia_Cargada");

            performVerticalScroll();
            Thread.sleep(5000);

            WebElement randomItem = driver.findElement(By.id("com.twitter.android:id/tweet_content_text"));
            randomItem.click();
            logger.info("Clic en un tweet aleatorio de la tendencia.");
            takeScreenshot("Clic_Tweet_Aleatorio");

            Thread.sleep(10000);

            WebElement tweetElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@resource-id=\"com.twitter.android:id/tweet_content_view_stub\"]/android.view.View")));
            assertTrue("El tweet no se ha cargado correctamente", tweetElement.isDisplayed());
            logger.info("Detalle del Tweet cargado correctamente.");
            takeScreenshot("Detalle_Tweet_Cargado");

            performVerticalScroll();
            performVerticalScroll();

            WebElement tweetComment = driver.findElement(By.xpath("(//android.widget.LinearLayout[@resource-id='com.twitter.android:id/outer_layout_row_view_tweet'])[2]"));
            tweetComment.click();
            logger.info("Clic en un comentario del tweet.");
            takeScreenshot("Clic_Comentario_Tweet");

            Thread.sleep(10000);

            WebElement likeButton = driver.findElementByAccessibilityId("Me gusta");
            likeButton.click();
            logger.info("Clic en 'Me gusta' del Tweet.");
            takeScreenshot("Clic_Me_Gusta");

            Thread.sleep(5000);

            WebElement likedElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("com.twitter.android:id/inline_like")));
            assertTrue("El tweet no ha recibido el 'Me gusta' correctamente", likedElement.isDisplayed());
            logger.info("El tweet ha recibido el 'Me gusta' correctamente.");
            takeScreenshot("Me_Gusta_Exitoso");

        } catch (Exception e) {
            logger.error("Error durante la prueba de navegación y 'Me gusta': " + e.getMessage());
            takeScreenshot("Error_Navegacion_Tendencias");
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

        logger.info("Scroll vertical realizado.");
        takeScreenshot("Scroll_Vertical_Realizado");
    }
}
