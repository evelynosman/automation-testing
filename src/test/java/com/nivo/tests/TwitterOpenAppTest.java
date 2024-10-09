package com.nivo.tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import static org.junit.Assert.assertNotNull;

public class TwitterOpenAppTest extends BaseTest {
    public static boolean isAppOpened = false;

    // Logger para esta clase
    private static final Logger logger = LogManager.getLogger(TwitterOpenAppTest.class);

    @Test
    public void openTwitterAppTest() {
        logger.info("Iniciando la prueba para abrir la app de Twitter...");
        takeScreenshot("Inicio_Prueba_Abrir_App");

        // Verifica que el driver no sea null
        try {
            assertNotNull(driver);
            isAppOpened = true;
            logger.info("La app de Twitter se ha abierto correctamente.");
            takeScreenshot("App_Abrir_Correcto");
        } catch (AssertionError e) {
            logger.error("Error al abrir la app de Twitter: el driver es null.");
            takeScreenshot("Error_Abrir_App");
            throw e; // Lanza la excepción para que la prueba falle
        }
    }
}
