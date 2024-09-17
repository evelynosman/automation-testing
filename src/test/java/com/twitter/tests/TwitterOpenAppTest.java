package com.twitter.tests; // Paquete que contiene la clase de prueba

import io.appium.java_client.android.AndroidDriver; // Importación del driver de Appium para Android
import io.appium.java_client.touch.offset.PointOption;
import io.appium.java_client.MobileBy;
import io.appium.java_client.TouchAction;
import io.appium.java_client.MobileElement; // Importación de MobileElement para trabajar con elementos móviles
import org.junit.After; // Importación de la anotación @After para ejecutar después de la prueba
import org.junit.Before; // Importación de la anotación @Before para ejecutar antes de la prueba
import org.junit.Test; // Importación de la anotación @Test para la ejecución de la prueba
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities; // Importación para configurar las capacidades del dispositivo
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.twitter.pages.LoginPage;


import java.net.MalformedURLException; // Importación de la excepción para manejo de URL malformada
import java.net.URL; // Importación de la clase URL para crear la URL del servidor Appium

import static org.junit.Assert.assertNotNull; // Importación de la clase para las aserciones de JUnit
import static org.junit.Assert.assertTrue; // Importación de la clase para aserciones adicionales

public class TwitterOpenAppTest {

    private AndroidDriver<MobileElement> driver; // driver de Android
    private com.twitter.pages.LoginPage loginPage; // Página de inicio

    @Before
    public void setUp() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities(); // inicializacion de las capacidades del dispositivo

        // Config capacidades necesarias para pruebas
        capabilities.setCapability("platformName", "Android"); // sistema operativo
        capabilities.setCapability("appium:deviceName", "R9PT413MMPD"); // Nombre del dispositivo
        capabilities.setCapability("appium:appPackage", "com.twitter.android"); // paquete de la aplicación - twitter en este caso
        capabilities.setCapability("appium:appActivity", "com.twitter.android.StartActivity"); // Actividad principal de la app de Twitter
        capabilities.setCapability("appium:noReset", true); // evita el reinicio de la aplicación entre pruebas
        capabilities.setCapability("appium:autoLaunch", true); // Permitir que Appium inicie la app automáticamente
        capabilities.setCapability("appium:fullReset", false); // No realizar un reinicio completo del dispositivo
        capabilities.setCapability("appium:newCommandTimeout", 300); // tiempo de espera para nuevos comandos
        capabilities.setCapability("appium:dontStopAppOnReset", true); // No detener la app durante el reinicio
        capabilities.setCapability("appium:appWaitActivity", "com.twitter.android.*"); // Especificar actividades de espera

        // crear el driver con la URL del servidor Appium y las capacidades
        try {
            driver = new AndroidDriver<>(new URL("http://127.0.0.1:4725/wd/hub"), capabilities); // Inicialización del driver
            loginPage = new com.twitter.pages.LoginPage(driver); // Inicializamos LoginPage
            System.out.println("El driver se ha configurado correctamente.");
        } catch (MalformedURLException e) {
            System.err.println("Error al configurar el driver: " + e.getMessage());
            throw e; // Propagar el error
        }
    }

    @Test // Método de prueba
    public void openTwitterAppTest() {
        System.out.println("Iniciando la prueba para abrir la app de Twitter...");
        try {
            assertNotNull(driver);  // verificar que el driver no se null
            System.out.println("La app de Twitter se ha abierto correctamente.");
        } catch (AssertionError e) {
            System.err.println("Error en la prueba: No se pudo abrir la app de Twitter.");
            throw e; // Propagar el error para que falle la prueba
        }
    }

    @Test
    public void testLoginModalOrLoginScreen() {
        System.out.println("Iniciando prueba del modal de Google o pantalla de inicio de sesión...");

        WebDriverWait wait = new WebDriverWait(driver, 20);

        try {
            // valida si el modal de Google está presente
            if (isElementPresent(By.id("com.google.android.gms:id/design_bottom_sheet"), 20)) {
                System.out.println("El modal de Google está presente. Se procederá a cerrarlo.");
                driver.findElement(By.id("com.google.android.gms:id/cancel")).click(); // Cerrar modal

                System.out.println("Esperando que aparezca la pantalla de inicio de sesión después de cerrar el modal...");
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.twitter.android:id/scroll_view_cta")));

                System.out.println("Verificando si la pantalla de inicio de sesión está presente...");
                assertTrue("La pantalla de inicio de sesión debería estar presente después de cerrar el modal.",
                    isElementPresent(By.id("com.twitter.android:id/scroll_view_cta"), 10));

                System.out.println("Éxito: La pantalla de inicio de sesión está presente después de cerrar el modal.");

            } else {
                System.out.println("No se encontró el modal de Google. Verificando si está presente la pantalla de inicio de sesión.");

                wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.twitter.android:id/scroll_view_cta")));

                System.out.println("Verificando si la pantalla de inicio de sesión está presente...");
                assertTrue("La pantalla de inicio de sesión debería estar presente",
                    isElementPresent(By.id("com.twitter.android:id/scroll_view_cta"), 10));

                System.out.println("Éxito: La pantalla de inicio de sesión está presente.");
            }

            WebElement loginTextElement = driver.findElement(By.id("com.twitter.android:id/detail_text"));
            assertTrue("El texto para iniciar sesión no está presente.", loginTextElement.isDisplayed());

            System.out.println("Haciendo clic en el enlace Iniciar Sesión");
            new TouchAction<>(driver)
                .tap(PointOption.point(472, 1444))
                .perform();

            System.out.println("El clic en el enlace Iniciar Sesión se realizó correctamente.");

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.twitter.android:id/ocf_text_input_edit")));

            // Utilizando el usuario real Xuserseek
            System.out.println("Ingresando usuario: Xuserseek");
            WebElement usernameInput = driver.findElement(By.id("com.twitter.android:id/ocf_text_input_edit"));
            usernameInput.sendKeys("Xuserseek");  // Nombre de usuario real

            System.out.println("Haciendo clic en el botón 'Siguiente'...");
            WebElement nextButton = driver.findElement(By.id("com.twitter.android:id/cta_button"));
            nextButton.click();

            System.out.println("Esperando la pantalla para ingresar la contraseña...");
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.twitter.android:id/password_edit_text")));

            // Ingresamos la contraseña incorrecta
            System.out.println("Ingresando contraseña incorrecta...");
            WebElement passwordInput = driver.findElement(By.id("com.twitter.android:id/password_edit_text"));
            passwordInput.sendKeys("contraseña_incorrecta");

            System.out.println("Haciendo clic en el botón 'Iniciar sesión'...");
            WebElement loginButton = driver.findElement(By.xpath("//android.widget.TextView[@text='Iniciar sesión']"));
            loginButton.click();

            System.out.println("Esperando que aparezca el mensaje de error 'Contraseña incorrecta'...");
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.Toast[@text='Contraseña incorrecta']")));

            System.out.println("Mensaje de error 'Contraseña incorrecta' validado correctamente.");

            // Ingresar contraseña correcta
            System.out.println("Corrigiendo contraseña...");

            passwordInput.clear();
            passwordInput.sendKeys("Xuser-seek"); // Contraseña correcta

            System.out.println("Esperando 10 segundos antes de hacer clic en 'Iniciar sesión'...");
            Thread.sleep(10000);

            System.out.println("Haciendo clic en 'Iniciar sesión' con credenciales correctas...");
            loginButton.click();

            // Esperamos la pantalla principal de la app de Twitter para confirmar el login exitoso
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.twitter.android:id/view_pager"))); // Ajustar el ID si es necesario
            System.out.println("Inicio de sesión exitoso. Pantalla principal de Twitter cargada.");

        } catch (TimeoutException e) {
            System.err.println("Error: El tiempo de espera se agotó antes de encontrar el elemento. El modal o la pantalla de inicio de sesión no apareció.");
            throw new AssertionError("Error en la prueba: el modal de Google o la pantalla de inicio de sesión no se encontraron en el tiempo esperado.");
        } catch (InterruptedException e) {
            System.err.println("Error: El hilo fue interrumpido durante la espera de 10 segundos.");
            throw new AssertionError("Error en la prueba: la espera de 10 segundos fue interrumpida.");
        } catch (Exception e) {
            System.err.println("Error inesperado durante la prueba: " + e.getMessage());
            throw e; // propaga cualquier error inesperado
        }
    }
    
    public boolean isElementPresent(By by, int timeoutInSeconds) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
            wait.until(ExpectedConditions.presenceOfElementLocated(by));
            return true; // El elemento fue encontrado
        } catch (TimeoutException e) {
            return false; // El elemento no fue encontrado en el tiempo especificado
        }
    }

    @After // a ejecutar después de la prueba
    public void tearDown() {
        System.out.println("Finalizando la sesión de Appium...");
        if (driver != null) {
            driver.quit();
            System.out.println("La sesión de Appium se cerró correctamente.");
        } else {
            System.err.println("Error: El driver es null, no se pudo cerrar la sesión");
        }
    }
}
