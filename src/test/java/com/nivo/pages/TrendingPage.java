package com.twitter.pages;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class TrendingPage {

    private AndroidDriver<MobileElement> driver;

    @FindBy(id = "com.twitter.android:id/pinned_header_container")
    private WebElement trendingHeader;

    @FindBy(xpath = "//androidx.recyclerview.widget.RecyclerView[@resource-id='android:id/list']/android.view.ViewGroup[2]")
    private WebElement secondTrendingItem;

    public TrendingPage(AndroidDriver<MobileElement> driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Proveer un getter para trendingHeader
    public WebElement getTrendingHeader() {
        return trendingHeader;
    }

    // Método para validar que la página de Tendencias ha cargado
    public boolean isTrendingPageLoaded() {
        return trendingHeader.isDisplayed();
    }

    // Método para hacer clic en el segundo elemento de la lista de Tendencias
    public void clickOnSecondTrendingItem() {
        secondTrendingItem.click();
    }
}
