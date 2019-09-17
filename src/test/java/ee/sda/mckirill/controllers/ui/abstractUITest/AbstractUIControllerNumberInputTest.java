package ee.sda.mckirill.controllers.ui.abstractUITest;

import ee.sda.mckirill.controllers.ui.AbstractUIController;
import ee.sda.mckirill.controllers.ui.AbstractUITestClass;
import ee.sda.mckirill.strings.BaseString;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AbstractUIControllerNumberInputTest extends AbstractUITestClass {
    @Test
    public void selectUnsignedNumber() {
        systemOutRule.clearLog();
        systemInMock.provideLines("255");
        assertEquals(255, AbstractUIController.selectUnsignedInteger(headerString, errorString, 1000));
        assertEquals(headerString, systemOutRule.getLog().trim());

        systemOutRule.clearLog();

        systemInMock.provideLines("", "255");
        AbstractUIController.selectUnsignedInteger(headerString, errorString, 1000);
        assertEquals(headerString + BR + BaseString.SELECT_ID_NOT_INTEGER + BR + headerString, systemOutRule.getLog().trim());

        systemOutRule.clearLog();

        systemInMock.provideLines("qwer", "255");
        AbstractUIController.selectUnsignedInteger(headerString, errorString, 1000);
        assertEquals(headerString + BR + BaseString.SELECT_ID_NOT_INTEGER + BR + headerString, systemOutRule.getLog().trim());
    }
}
