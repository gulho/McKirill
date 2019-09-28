package ee.sda.mckirill.controllers.ui.abstractUITest;

import ee.sda.mckirill.controllers.ui.AbstractUIController;
import ee.sda.mckirill.controllers.ui.AbstractUITestClass;
import ee.sda.mckirill.strings.BaseString;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class AbstractUIControllerStringInputTest extends AbstractUITestClass {

    @Test
    public void selectString() {
        String test_string = "test_string";
        systemInMock.provideLines(test_string);
        Assert.assertEquals(test_string, AbstractUIController.selectString(headerString, errorString, 50));
        assertEquals(headerString, systemOutRule.getLog().trim());

        systemOutRule.clearLog();

        systemOutRule.clearLog();
        systemInMock.provideLines("", headerString);
        AbstractUIController.selectString(headerString, errorString, 50);
        assertEquals(headerString + BR + errorString + BR + headerString, systemOutRule.getLog().trim());

        systemOutRule.clearLog();

        systemInMock.provideLines("23145678912", headerString);
        AbstractUIController.selectString(headerString, errorString, 10);
        assertEquals(headerString + BR + BaseString.SELECT_STRING_TO_LONG + 10 + BR + headerString,
                systemOutRule.getLog().trim());
    }
}