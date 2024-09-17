package com.twitter.tests;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.MobileElement;
import org.junit.BeforeClass;
import org.junit.AfterClass;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.net.MalformedURLException;
import java.net.URL;

public class BaseTest {

    // Driver compartido entre las pruebas
    protected static AndroidDriver<MobileElement> driver;

    @BeforeClass
    public static void setUp() throws MalformedURLException {
        if (driver == null) { // Inicializamos el driver solo si no ha sido inicializado previamente
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("platformName", "Android");
            capabilities.setCapability("appium:deviceName", "R9PT413MMPD");
            capabilities.setCapability("appium:appPackage", "com.twitter.android");
            capabilities.setCapability("appium:appActivity", "com.twitter.android.StartActivity");
            capabilities.setCapability("appium:noReset", true); // Evita el reinicio de la aplicación
            capabilities.setCapability("appium:autoLaunch", true);
            capabilities.setCapability("appium:fullReset", false);
            capabilities.setCapability("appium:newCommandTimeout", 300);
            capabilities.setCapability("appium:dontStopAppOnReset", true);
            capabilities.setCapability("appium:appWaitActivity", "com.twitter.android.*");

            // Inicializa el driver
            driver = new AndroidDriver<>(new URL("http://127.0.0.1:4733/wd/hub"), capabilities);
            System.out.println("El driver de Appium se ha configurado correctamente.");
        }
    }

    @AfterClass
    public static void tearDown() {
        
    }
}
