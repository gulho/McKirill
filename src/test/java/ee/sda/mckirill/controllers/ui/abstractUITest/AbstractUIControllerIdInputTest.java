package ee.sda.mckirill.controllers.ui.abstractUITest;

import ee.sda.mckirill.controllers.ui.AbstractUIController;
import ee.sda.mckirill.controllers.ui.AbstractUITestClass;
import org.junit.Test;

import java.util.Optional;
import java.util.function.Function;

import static org.junit.Assert.assertEquals;

public class AbstractUIControllerIdInputTest extends AbstractUITestClass {
    Function<Integer, Optional<String>> function = T -> T.equals(Integer.valueOf("1"))?Optional.of("qwer"):Optional.empty();

    @Test
    public void selectIDTest() {
        systemInMock.provideLines("1");
        assertEquals("qwer", AbstractUIController.selectObjectById(headerString, errorString, function));
        assertEquals(headerString, systemOutRule.getLog().trim());

        systemOutRule.getLog();

        systemInMock.provideLines("", "1");
        AbstractUIController.selectObjectById(headerString, errorString, function);
        assertEquals(headerString + BR + headerString, systemOutRule.getLog().trim());

        systemOutRule.clearLog();

        /*systemInMock.provideLines("qwer", "1");
        AbstractUIController.selectObjectById(headerString, errorString, function);
        assertEquals(headerString, systemOutRule.getLog());*/
    }
}
