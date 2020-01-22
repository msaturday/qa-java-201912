package com.otus.lesson2;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class OpenPageTest {
    final static Logger logger = Logger.getLogger(OpenPageTest.class);

    protected WebDriver driver;

    @Parameterized.Parameter
    public Class<WebDriver> driverClass;

    @Parameterized.Parameters(name = "{0}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] { { ChromeDriver.class },
                { FirefoxDriver.class } });
    }

    @Before
    public void setupTest() throws Exception {
        WebDriverManager.getInstance(driverClass).setup();
        driver = driverClass.newInstance();
    }

    @Test
    public void openPage(){
        logger.debug("window opened");
        logger.trace("going to otus.ru");
        driver.get(" https://otus.ru/");
        logger.debug("window closed");
        logger.warn("test finished");
        logger.info("no error");
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}