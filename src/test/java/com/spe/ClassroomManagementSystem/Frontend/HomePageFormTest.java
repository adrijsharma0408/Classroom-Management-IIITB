package com.spe.ClassroomManagementSystem.Frontend;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class HomePageFormTest {
    private Document document;

    @BeforeEach
    public void setUp() throws IOException {
        File input = new File("src/main/webapp/index.html");
        document = Jsoup.parse(input, "UTF-8");
    }

    @Test
    public void testFormElements() {
        Element usertypeSelect = document.getElementById("usertype");
        assertNotNull(usertypeSelect);

        Element usernameInput = document.getElementById("username");
        assertNotNull(usernameInput);

        Element passwordInput = document.getElementById("password");
        assertNotNull(passwordInput);

        Element loginButton = document.selectFirst("button[type='submit']");
        assertNotNull(loginButton);
    }
}