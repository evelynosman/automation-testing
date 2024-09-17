package com.twitter.tests; // Paquete que contiene la clase de prueba

import io.appium.java_client.android.AndroidDriver; // Importación del driver de Appium para Android
import io.appium.java_client.MobileElement; // Importación de MobileElement para trabajar con elementos móviles
import org.junit.After; // Importación de la anotación @After para ejecutar después de la prueba
import org.junit.Before; // Importación de la anotación @Before para ejecutar antes de la prueba
import org.junit.Test; // Importación de la anotación @Test para la ejecución de la prueba
import org.openqa.selenium.remote.DesiredCapabilities; // Importación para configurar las capacidades del dispositivo

import java.net.MalformedURLException; // Importación de la excepción para manejo de URL malformada
import java.net.URL; // Importación de la clase URL para crear la URL del servidor Appium

import static org.junit.Assert.assertNotNull; // Importación de la clase para las aserciones de JUnit

public class TwitterOpenAppTest {

    private AndroidDriver<MobileElement> driver; // driver de Android

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

    @After // metodo a ejecutar después de la prueba
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
