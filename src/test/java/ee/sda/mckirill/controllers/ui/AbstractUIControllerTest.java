package ee.sda.mckirill.controllers.ui;

import ee.sda.mckirill.strings.BaseString;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemOutRule;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;

import static org.junit.Assert.*;
import static org.junit.contrib.java.lang.system.TextFromStandardInputStream.emptyStandardInputStream;

public class AbstractUIControllerTest {
    private static final String headerString = "header";
    private static final String errorString = "error";
    public static final String BR = System.lineSeparator();
    @Rule
    public final TextFromStandardInputStream systemInMock = emptyStandardInputStream();

    @Rule
    public final SystemOutRule systemOutRule = new SystemOutRule().enableLog();

    @Test
    public void selectString() {
        String test_string = "test_string";
        systemInMock.provideLines(test_string);
        assertEquals(test_string, AbstractUIController.selectString(headerString, errorString, 50));
        assertEquals(headerString, systemOutRule.getLog().trim());
    }

    @Test
    public void selectStringErr() {
        systemOutRule.clearLog();
        systemInMock.provideLines("", headerString);
        AbstractUIController.selectString(headerString, errorString, 50);
        assertEquals(headerString + BR + errorString + BR + headerString, systemOutRule.getLog().trim());
    }

    @Test
    public void selectStringToLong() {
        systemInMock.provideLines("23145678912", headerString);
        AbstractUIController.selectString(headerString, errorString, 10);
        assertEquals(headerString + BR + BaseString.SELECT_STRING_TO_LONG + 10 + BR + headerString,
                systemOutRule.getLog().trim());
    }

    @Test
    public void selectUnsignedNumber() {
        systemInMock.provideLines("255");
        assertEquals(255, AbstractUIController.selectUnsignedInteger(headerString, errorString, 1000));
        assertEquals(headerString, systemOutRule.getLog().trim());
    }

    @Test
    public void selectUnsignedErrorEmpty() {
        systemInMock.provideLines("", "255");
        AbstractUIController.selectUnsignedInteger(headerString, errorString, 1000);
        assertEquals(headerString + BR + BaseString.SELECT_ID_NOT_INTEGER + BR +headerString, systemOutRule.getLog().trim());
    }

    @Test
    public void selectUnsignedErrorAlpha() {
        systemInMock.provideLines("qwer", "255");
        AbstractUIController.selectUnsignedInteger(headerString, errorString, 1000);
        assertEquals(headerString + BR + BaseString.SELECT_ID_NOT_INTEGER + BR +headerString, systemOutRule.getLog().trim());
    }
}