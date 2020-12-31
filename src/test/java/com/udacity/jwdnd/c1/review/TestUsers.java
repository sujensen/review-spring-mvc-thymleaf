package com.udacity.jwdnd.c1.review;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestUsers {

    @LocalServerPort
    private Integer port;

    static WebDriver driver;

    private SignupPage signupPageObject;
    private LoginPage loginPageObject;
    private ChatPage chatPageObject;

    // Set up WebDriver
    @BeforeAll
    static public void beforeAll() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();


    }

    // Initialize page objects
    @BeforeEach
    public void beforeEach() {
        signupPageObject = new SignupPage(driver);
        loginPageObject = new LoginPage(driver);
        chatPageObject = new ChatPage(driver);
    }

    @Test
    public void testOneUser() {

        // signup
        driver.get("http://localhost:" + port + "/signup");
        signupPageObject.signupAlready("Susan", "Jensen", "Sue", "foobar");

        // login
        driver.get("http://localhost:" + port + "/login");
        loginPageObject.loginAlready("Sue", "foobar");

        // test first message was posted to chat
        driver.get("http://localhost:" + port + "/chat");
        chatPageObject.postChatAlready("Hi there", "Say");
        String expectedChatMessage = "Sue: Hi there";
        List<String> actualChatMessage = chatPageObject.getChatHistory();
        assertEquals(1, actualChatMessage.size());
        assertEquals(expectedChatMessage, actualChatMessage.get(0));

        // logout
        chatPageObject.logoutAlready();

        // signup
        driver.get("http://localhost:" + port + "/signup");
        signupPageObject.signupAlready("Stephen", "Jensen", "Steve", "fizzbuzz");

        // login
        driver.get("http://localhost:" + port + "/login");
        loginPageObject.loginAlready("Steve", "fizzbuzz");

        // test next message was posted to chat
        driver.get("http://localhost:" + port + "/chat");
        chatPageObject.postChatAlready("Hi back at you", "Say");
        expectedChatMessage = "Steve: Hi back at you";
        actualChatMessage = chatPageObject.getChatHistory();
        assertEquals(2, actualChatMessage.size());
        assertEquals(expectedChatMessage, actualChatMessage.get(1));

    }

    // Quit WebDriver
    @AfterAll
    static public void afterAll() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            System.out.println(e);
        }
        driver.quit();
    }

}
