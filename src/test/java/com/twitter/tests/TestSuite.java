package com.twitter.tests;

import org.junit.FixMethodOrder;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.MethodSorters;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.BeforeClass;
import org.junit.AfterClass;

@RunWith(Suite.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING) // Asegura que el orden de ejecución siga el nombre
@Suite.SuiteClasses({
    TwitterOpenAppTest.class,    // Se ejecuta primero
    TwitterLoginTest.class,      // Se ejecuta segundo
    TwitterNavigationTest.class  // Se ejecuta tercero
})
public class TestSuite {

    // Logger para la suite de pruebas
    private static final Logger logger = LogManager.getLogger(TestSuite.class);

    @BeforeClass
    public static void beforeSuite() {
        logger.info("Iniciando la siguiente suite de pruebas: TestSuite");
    }

    @AfterClass
    public static void afterSuite() {
        logger.info("Finalizando la suite de pruebas: TestSuite");
    }
}
