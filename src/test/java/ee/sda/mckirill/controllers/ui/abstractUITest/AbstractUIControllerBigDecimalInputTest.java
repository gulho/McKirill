package ee.sda.mckirill.controllers.ui.abstractUITest;

import ee.sda.mckirill.controllers.ui.AbstractUIController;
import ee.sda.mckirill.controllers.ui.AbstractUITestClass;
import ee.sda.mckirill.strings.BaseString;
import org.junit.Test;

import javax.xml.bind.helpers.AbstractUnmarshallerImpl;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class AbstractUIControllerBigDecimalInputTest extends AbstractUITestClass {
    @Test
    public void selectBigDecimalTest() {
        systemInMock.provideLines("6.5");
        assertEquals(new BigDecimal(6.5), AbstractUIController.selectBigDecimal(headerString, errorString));
        assertEquals(headerString, systemOutRule.getLog().trim());

        systemOutRule.clearLog();

        systemInMock.provideLines("", "6.5");
        AbstractUIController.selectBigDecimal(headerString, errorString);
        assertEquals(headerString + BR + BaseString.NUMBER_FORMAT_EXCEPTION + BR + headerString, systemOutRule.getLog().trim());
    }
}
