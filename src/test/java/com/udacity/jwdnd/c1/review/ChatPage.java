package com.udacity.jwdnd.c1.review;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class ChatPage {

    @FindBy(id = "messageText")
    private WebElement messageText;

    @FindBy(id = "messageType")
    private WebElement messageType;

    @FindBy(id = "submit-button")
    private WebElement submitButton;

    @FindBy(id = "prettyHistoryId")
    private List<WebElement> chatHistory;

    @FindBy(id = "logout-button")
    private WebElement logoutButton;

    public ChatPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void postChatAlready(String text, String type) {
        messageText.sendKeys(text);
        messageType.sendKeys(type);
        submitButton.click();
    }

    public List<String> getChatHistory() {
        List<String> retList = new ArrayList<String>();
        for (WebElement e : chatHistory) {
            retList.add(e.getText());
        }
        return retList;
    }

    public void logoutAlready() {
        logoutButton.click();
    }
}
