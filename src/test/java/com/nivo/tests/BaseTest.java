package com.twitter.tests;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.TakesScreenshot;
import org.apache.commons.io.FileUtils;
import org.junit.BeforeClass;
import org.junit.AfterClass;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.net.MalformedURLException;
import java.net.URL;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
            capabilities.setCapability("appium:appPackage", "com.meridiangroupsa.nivo");
            capabilities.setCapability("appium:appActivity", "com.meridiangroupsa.nivo.MainActivity");
            capabilities.setCapability("appium:noReset", true);
            capabilities.setCapability("appium:autoLaunch", true);
            capabilities.setCapability("appium:fullReset", false);
            capabilities.setCapability("appium:newCommandTimeout", 300);
            capabilities.setCapability("appium:dontStopAppOnReset", true);
            capabilities.setCapability("appium:appWaitActivity", "com.meridiangroupsa.nivo.*");

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

    public static String takeScreenshot(String testName) {
        String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        String screenshotName = testName + "_" + timestamp + ".png";
        String screenshotPath = "logs/screenshots/" + screenshotName;

        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(screenshot, new File(screenshotPath));
            logger.info("Captura de pantalla guardada en: " + screenshotPath);
        } catch (IOException e) {
            logger.error("No se pudo guardar la captura de pantalla: " + e.getMessage());
        }

        return screenshotPath;
    }

    @AfterClass
    public static void tearDown() {

    }
}
