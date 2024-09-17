package com.twitter.tests;

import org.junit.FixMethodOrder;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.MethodSorters;

@RunWith(Suite.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING) // Asegura que el orden de ejecución siga el nombre
@Suite.SuiteClasses({
    TwitterOpenAppTest.class,    // Se ejecuta primero
    TwitterLoginTest.class,      // Se ejecuta segundo
    TwitterNavigationTest.class  // Se ejecuta tercero
})
public class TestSuite {
}
