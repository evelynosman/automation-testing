package com.twitter.tests;

import com.twitter.pages.LoginPage;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.offset.PointOption;
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

    private LoginPage loginPage; // Declaración de loginPage

    @Test
    public void testLoginModalOrLoginScreen() {
        assumeTrue(TwitterOpenAppTest.isAppOpened);

        System.out.println("Iniciando prueba del modal de Google o pantalla de inicio de sesión...");

        // Inicializa el WebDriverWait aquí con el tiempo que quieras (por ejemplo, 30 segundos)
        WebDriverWait wait = new WebDriverWait(driver, 30);

        try {
            // Inicializar loginPage
            loginPage = new LoginPage(driver); // Asegúrate de que el constructor de LoginPage reciba el driver correctamente

            // Validar si el modal de Google está presente
            if (isElementPresent(By.id("com.google.android.gms:id/design_bottom_sheet"), 30)) {
                System.out.println("El modal de Google está presente. Se procederá a cerrarlo.");
                driver.findElement(By.id("com.google.android.gms:id/cancel")).click(); // Cerrar modal

                wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.twitter.android:id/scroll_view_cta")));
                assertTrue("La pantalla de inicio de sesión debería estar presente.",
                        isElementPresent(By.id("com.twitter.android:id/scroll_view_cta"), 30));

            } else {
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.twitter.android:id/scroll_view_cta")));
                assertTrue("La pantalla de inicio de sesión debería estar presente.",
                        isElementPresent(By.id("com.twitter.android:id/scroll_view_cta"), 30));
            }

            // Verifica si el texto "Iniciar sesión" está presente antes de interactuar con el login
            WebElement loginTextElement = driver.findElement(By.id("com.twitter.android:id/detail_text"));
            assertTrue("El texto para iniciar sesión no está presente.", loginTextElement.isDisplayed());

            System.out.println("Haciendo clic en el enlace Iniciar Sesión");
            new TouchAction<>(driver)
                .tap(PointOption.point(472, 1444)) // Usar PointOption para especificar las coordenadas
                .perform();
            System.out.println("El clic en el enlace Iniciar Sesión se realizó correctamente.");

            // Ahora interactúa con los campos de usuario y contraseña
            Thread.sleep(5000);
            loginPage.enterUsername("Xuserseek");
            loginPage.clickNext();
            Thread.sleep(5000);
            loginPage.enterPassword("contraseña_incorrecta");
            loginPage.clickLogin();

            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.Toast[@text='Contraseña incorrecta']")));

            loginPage.enterPassword("Xuser-seek");
            Thread.sleep(10000);  // Espera de 10 segundos antes de hacer clic en Iniciar Sesión
            loginPage.clickLogin();

            isLoginSuccessful = true;
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.twitter.android:id/view_pager")));
            System.out.println("Inicio de sesión exitoso. Pantalla principal de Twitter cargada.");

        } catch (TimeoutException | InterruptedException e) {
            System.err.println("Error durante la prueba: " + e.getMessage());
        }
    }

    // Método actualizado para no necesitar pasar wait como parámetro
    private boolean isElementPresent(By by, int timeoutInSeconds) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
            wait.until(ExpectedConditions.presenceOfElementLocated(by));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }
}
