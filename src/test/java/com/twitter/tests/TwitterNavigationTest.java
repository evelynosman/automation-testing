package com.twitter.tests;

import com.twitter.pages.TrendingPage;
import static org.junit.Assume.assumeTrue;

import org.junit.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TwitterNavigationTest extends BaseTest {

    private TrendingPage trendingPage; // Ya no es necesario inicializar el driver aquí, ya se hereda de BaseTest

    @Test
    public void testNavigateToTrending() {
        // Verifica que el login fue exitoso antes de continuar
        assumeTrue(TwitterLoginTest.isLoginSuccessful);

        System.out.println("Iniciando prueba de navegación a la sección de Tendencias...");

        // Inicializa la página de TrendingPage
        trendingPage = new TrendingPage(driver);

        // Inicializa WebDriverWait con el driver compartido
        WebDriverWait wait = new WebDriverWait(driver, 20);

        try {
            // Encuentra y hace clic en el botón de "Buscar y explorar" usando AccessibilityId
            WebElement searchExploreButton = driver.findElementByAccessibilityId("Buscar y explorar");
            searchExploreButton.click();

            // Espera a que se cargue el encabezado de tendencias
            wait.until(ExpectedConditions.visibilityOf(trendingPage.getTrendingHeader()));

            // Verifica si la página de Tendencias se ha cargado y realiza la acción
            if (trendingPage.isTrendingPageLoaded()) {
                trendingPage.clickOnSecondTrendingItem();
                System.out.println("Navegación a Tendencias completada.");
            } else {
                System.err.println("No se pudo cargar la sección de Tendencias.");
            }
        } catch (Exception e) {
            System.err.println("Error durante la prueba de navegación a Tendencias: " + e.getMessage());
        }
    }
}
