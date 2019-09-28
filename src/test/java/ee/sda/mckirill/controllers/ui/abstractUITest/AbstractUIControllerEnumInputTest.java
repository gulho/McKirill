package ee.sda.mckirill.controllers.ui.abstractUITest;

import ee.sda.mckirill.controllers.ui.AbstractUIController;
import ee.sda.mckirill.controllers.ui.AbstractUITestClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

enum TestEnum {
    FIRST,
    SECOND
}

public class AbstractUIControllerEnumInputTest extends AbstractUITestClass {

    private static final String headerENUM = headerString + BR + TestEnum.FIRST + BR + TestEnum.SECOND;

    @Test
    public void selectEnumTest() {
        systemInMock.provideLines(TestEnum.FIRST.toString());
        assertEquals(TestEnum.FIRST, AbstractUIController.selectEnum(headerString, errorString, TestEnum.class));
        assertEquals(headerENUM, systemOutRule.getLog().trim());

        systemOutRule.clearLog();

        systemInMock.provideLines("", TestEnum.FIRST.toString());
        AbstractUIController.selectEnum(headerString, errorString, TestEnum.class);
        assertEquals(headerENUM + BR + errorString + BR + headerENUM, systemOutRule.getLog().trim());

        systemOutRule.clearLog();

        systemInMock.provideLines("qwrwqer", TestEnum.FIRST.toString());
        AbstractUIController.selectEnum(headerString, errorString, TestEnum.class);
        assertEquals(headerENUM + BR + errorString + BR + headerENUM, systemOutRule.getLog().trim());
    }
}
