package com.twitter.pages;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    private AndroidDriver<MobileElement> driver;

    // Elemento del modal de Google
    @FindBy(id = "com.google.android.gms:id/design_bottom_sheet")
    private WebElement googleModal;

    // Botón de cancelar en el modal
    @FindBy(id = "com.google.android.gms:id/cancel")
    private WebElement cancelButton;

    // Pantalla de inicio de sesión en Twitter
    @FindBy(id = "com.twitter.android:id/scroll_view_cta")
    private WebElement loginScreen;

    public LoginPage(AndroidDriver<MobileElement> driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Método para verificar si el modal de Google está presente
    public boolean isGoogleModalPresent() {
        try {
            return googleModal.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    // Método para hacer clic en el botón cancelar del modal
    public void clickCancelOnGoogleModal() {
        cancelButton.click();
    }

    // Método para verificar si la pantalla de inicio de sesión está presente
    public boolean isLoginScreenDisplayed() {
        try {
            return loginScreen.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
