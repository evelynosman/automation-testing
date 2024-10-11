package com.nivo.tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import static org.junit.Assert.assertNotNull;

public class NivoOpenAppTest extends BaseTest {
    public static boolean isAppOpened = false;

    // Logger para esta clase
    private static final Logger logger = LogManager.getLogger(NivoOpenAppTest.class);

    @Test
    public void openTwitterAppTest() {
        logger.info("Iniciando la prueba para abrir la app Nivo...");
        takeScreenshot("Inicio_Prueba_Abrir_App");

        // Verifica que el driver no sea null
        try {
            assertNotNull(driver);
            isAppOpened = true;
            logger.info("La app de Nivo se ha abierto correctamente.");
            takeScreenshot("App_Abrir_Correcto");
        } catch (AssertionError e) {
            logger.error("Error al abrir la app de Nivo: el driver es null.");
            takeScreenshot("Error_Abrir_App");
            throw e; // Lanza la excepción para que la prueba falle
        }
    }
}
