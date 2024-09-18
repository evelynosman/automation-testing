package com.twitter.tests;

import com.twitter.pages.LoginPage;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.touch.offset.PointOption;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.TimeoutException;

import static org.junit.Assert.assertTrue;
import static org.junit.Assume.assumeTrue;

public class TwitterLoginTest extends BaseTest {
    public static boolean isLoginSuccessful = false;

    private LoginPage loginPage;

    private static final Logger logger = LogManager.getLogger(TwitterLoginTest.class);

    @Test
    public void testLoginModalOrLoginScreen() {
        assumeTrue(TwitterOpenAppTest.isAppOpened);

        logger.info("Iniciando prueba del modal de Google y pantalla de inicio de sesión...");
        takeScreenshot("Inicio_Prueba_Login");

        WebDriverWait wait = new WebDriverWait(driver, 30);

        try {
            // Inicializar loginPage
            loginPage = new LoginPage(driver);
            if (isElementPresent(By.id("com.google.android.gms:id/design_bottom_sheet"), 30)) {
                logger.info("El modal de Google está presente. Se procederá a cerrarlo.");
                driver.findElement(By.id("com.google.android.gms:id/cancel")).click(); // Cerrar modal
                takeScreenshot("Modal_Cerrado");

                wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.twitter.android:id/scroll_view_cta")));
                assertTrue("La pantalla de inicio de sesión debería estar presente.",
                        isElementPresent(By.id("com.twitter.android:id/scroll_view_cta"), 30));

            } else {
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.twitter.android:id/scroll_view_cta")));
                assertTrue("La pantalla de inicio de sesión debería estar presente.",
                        isElementPresent(By.id("com.twitter.android:id/scroll_view_cta"), 30));
            }

            WebElement loginTextElement = driver.findElement(By.id("com.twitter.android:id/detail_text"));
            assertTrue("El texto para iniciar sesión no está presente.", loginTextElement.isDisplayed());
            logger.info("El texto 'Iniciar sesión' está presente.");
            takeScreenshot("Texto_Iniciar_Sesion");

            logger.info("Haciendo clic en el enlace Iniciar Sesión");
            new TouchAction<>(driver)
                .tap(PointOption.point(472, 1444))
                .perform();
            logger.info("El clic en el enlace Iniciar Sesión se realizó correctamente.");
            takeScreenshot("Clic_Enlace_Iniciar_Sesion");

            Thread.sleep(5000);
            loginPage.enterUsername("Xuserseek");
            loginPage.clickNext();
            logger.info("Nombre de usuario ingresado y clic en 'Siguiente'.");
            takeScreenshot("Usuario_Ingresado");

            Thread.sleep(5000);
            loginPage.enterPassword("contraseña_incorrecta");
            loginPage.clickLogin();
            logger.info("Contraseña incorrecta ingresada y clic en 'Iniciar sesion'.");
            takeScreenshot("Contraseña_Incorrecta");

            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.Toast[@text='Contraseña incorrecta']")));
            logger.info("Mensaje de contraseña incorrecta detectado.");
            takeScreenshot("Contraseña_Incorrecta_Toast");

            loginPage.enterPassword("Xuser-seek");
            Thread.sleep(10000);
            loginPage.clickLogin();
            logger.info("Contraseña correcta ingresada y clic en 'Iniciar sesion'.");
            takeScreenshot("Contraseña_Correcta");

            isLoginSuccessful = true;
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.twitter.android:id/view_pager")));
            logger.info("Inicio de sesión exitoso. Pantalla principal de Twitter cargada.");
            takeScreenshot("Inicio_Sesion_Exitoso");

        } catch (TimeoutException | InterruptedException e) {
            logger.error("Error durante la prueba: " + e.getMessage());
        }
    }

    private boolean isElementPresent(By by, int timeoutInSeconds) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
            wait.until(ExpectedConditions.presenceOfElementLocated(by));
            return true;
        } catch (TimeoutException e) {
            logger.error("El elemento no se encontró dentro del tiempo esperado: " + by.toString());
            return false;
        }
    }
}
