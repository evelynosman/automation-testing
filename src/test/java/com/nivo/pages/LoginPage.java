package com.nivo.pages;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    private AndroidDriver<MobileElement> driver;

    @FindBy(id = "com.google.android.gms:id/design_bottom_sheet")
    private WebElement googleModal;

    @FindBy(id = "com.google.android.gms:id/cancel")
    private WebElement cancelButton;

    @FindBy(id = "com.twitter.android:id/ocf_text_input_edit")
    private WebElement usernameInput;

    @FindBy(id = "com.twitter.android:id/password_edit_text")
    private WebElement passwordInput;

    @FindBy(xpath = "//android.widget.TextView[@text='Iniciar sesión']")
    private WebElement loginButton;

    @FindBy(id = "com.twitter.android:id/cta_button")
    private WebElement nextButton;

    public LoginPage(AndroidDriver<MobileElement> driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Método para cerrar el modal de Google
    public void closeGoogleModal() {
        if (googleModal.isDisplayed()) {
            cancelButton.click();
        }
    }

    // Método para ingresar el nombre de usuario
    public void enterUsername(String username) {
        usernameInput.sendKeys(username);
    }

    // Método para ingresar la contraseña
    public void enterPassword(String password) {
        passwordInput.sendKeys(password);
    }

    // Método para hacer clic en el botón de 'Iniciar sesión'
    public void clickLogin() {
        loginButton.click();
    }

    // Método para hacer clic en el botón 'Siguiente'
    public void clickNext() {
        nextButton.click();
    }
}
