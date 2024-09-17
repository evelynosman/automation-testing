package com.twitter.tests;

import org.junit.Test;
import static org.junit.Assert.assertNotNull;

public class TwitterOpenAppTest extends BaseTest {
    public static boolean isAppOpened = false;

    @Test
    public void openTwitterAppTest() {
        System.out.println("Iniciando la prueba para abrir la app de Twitter...");
        assertNotNull(driver); // Verifica que el driver no sea null
        isAppOpened = true;
        System.out.println("La app de Twitter se ha abierto correctamente.");
    }
}
