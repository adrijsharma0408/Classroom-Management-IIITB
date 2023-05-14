package com.spe.ClassroomManagementSystem.Frontend;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

// import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RegisterUserFormTest {

    private Document document;

    @BeforeEach
    public void setUp() throws IOException {
        String html = new String(Files.readAllBytes(Paths.get("src/main/webapp/RegisterUser.jsp")));
        document = Jsoup.parse(html);
    }

    @Test
    public void testFormExists() {
        assertTrue(document.select("form#register-user-form").size() == 1);
    }

    @Test
    public void testFormAction() {
        String formAction = document.select("form#register-user-form").attr("action");
        assertEquals("/register", formAction);
    }

    @Test
    public void testFormMethod() {
        String formMethod = document.select("form#register-user-form").attr("method");
        assertEquals("post", formMethod);
    }

    @Test
    public void testInputFields() {
        assertTrue(document.select("select[name=usertype][required]").size() == 1);
        assertTrue(document.select("input[type=text][name=username][required]").size() == 1);
        assertTrue(document.select("input[type=text][name=name][required]").size() == 1);
        assertTrue(document.select("input[type=email][name=email][required]").size() == 1);
        assertTrue(document.select("input[type=password][name=password][required]").size() == 1);
        assertTrue(document.select("input[type=password][name=pass][required]").size() == 1);
    }

    @Test
    public void testSubmitButton() {
        assertTrue(document.select("button[type=submit]").size() == 1);
    }
}