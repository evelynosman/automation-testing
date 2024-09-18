package com.twitter.tests;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.MobileElement;
import org.junit.BeforeClass;
import org.junit.AfterClass;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.net.MalformedURLException;
import java.net.URL;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BaseTest {

    // Driver compartido entre las pruebas
    protected static AndroidDriver<MobileElement> driver;

    private static final Logger logger = LogManager.getLogger(BaseTest.class);

    @BeforeClass
    public static void setUp() throws MalformedURLException {
        logger.info("Iniciando configuración del driver.");
        if (driver == null) {
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("platformName", "Android");
            capabilities.setCapability("appium:deviceName", "R9PT413MMPD");
            capabilities.setCapability("appium:appPackage", "com.twitter.android");
            capabilities.setCapability("appium:appActivity", "com.twitter.android.StartActivity");
            capabilities.setCapability("appium:noReset", true);
            capabilities.setCapability("appium:autoLaunch", true);
            capabilities.setCapability("appium:fullReset", false);
            capabilities.setCapability("appium:newCommandTimeout", 300);
            capabilities.setCapability("appium:dontStopAppOnReset", true);
            capabilities.setCapability("appium:appWaitActivity", "com.twitter.android.*");

            try {
                driver = new AndroidDriver<>(new URL("http://127.0.0.1:4750/wd/hub"), capabilities);
                logger.info("Driver configurado correctamente.");
            } catch (MalformedURLException e) {
                logger.error("Error al configurar el driver: " + e.getMessage());
                throw e;
            }
        } else {
            logger.info("El driver ya estaba configurado.");
        }
    }

    @AfterClass
    public static void tearDown() {

    }
}
