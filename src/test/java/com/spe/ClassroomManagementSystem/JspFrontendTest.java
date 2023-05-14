package com.spe.ClassroomManagementSystem;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class JspFrontendTest {
    private WebDriver driver;
    private URL indexUrl;

    @BeforeEach
    public void setUp() throws MalformedURLException {
        System.setProperty("webdriver.gecko.driver", "/usr/local/bin/geckodriver");
        driver = new FirefoxDriver();
        indexUrl = new File("index.html").toURI().toURL();
    }

    @Test
    public void testPageTitle() {
        driver.get(indexUrl.toString());
        // driver.get("http://0.0.0.0:8082/");
        String pageTitle = driver.getTitle();
        assertEquals("IIIT-B Classroom Manager", pageTitle);
    }

    @Test
    public void testLoginFormElements() {
        driver.get(indexUrl.toString());
        // driver.get("http://0.0.0.0:8082/");

        WebElement userTypeSelect = driver.findElement(By.id("usertype"));
        assertNotNull(userTypeSelect);

        WebElement usernameInput = driver.findElement(By.id("username"));
        assertNotNull(usernameInput);

        WebElement passwordInput = driver.findElement(By.id("password"));
        assertNotNull(passwordInput);

        WebElement loginButton = driver.findElement(By.cssSelector("button[type='submit']"));
        assertNotNull(loginButton);

        userTypeSelect.sendKeys("Admin");
        usernameInput.sendKeys("admin");
        passwordInput.sendKeys("admin");
        loginButton.click();

        String expectedUrl = "http://0.0.0.0:8082/AdminDashboard.jsp";
        assertEquals(expectedUrl, driver.getCurrentUrl());
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}