package ee.sda.mckirill.controllers.ui.abstractUITest;

import ee.sda.mckirill.controllers.ui.AbstractUIController;
import ee.sda.mckirill.controllers.ui.AbstractUITestClass;
import ee.sda.mckirill.strings.BaseString;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

import static org.junit.Assert.assertEquals;

public class AbstractUIControllerMenuActionTest extends AbstractUITestClass {
    private Map<Integer, Consumer> testMenuActionMap = new HashMap<>();
    private final String STAGE1 = "stage1";
    private final String STAGE2 = "stage2";

    @Before
    public void fillMap() {
        testMenuActionMap.put(1, T -> System.out.println(STAGE1));
        testMenuActionMap.put(2, T -> System.out.println(STAGE2));
    }

    @Test
    public void actionIdSelectTest() {
        systemInMock.provideLines("1","0");
        AbstractUIController.selectMenuAction(headerString, testMenuActionMap);
        assertEquals(headerString + BR + STAGE1 + BR + headerString, systemOutRule.getLog().trim());

        systemOutRule.clearLog();

        systemInMock.provideLines("qwer", "0");
        AbstractUIController.selectMenuAction(headerString, testMenuActionMap);
        assertEquals(headerString + BR + BaseString.SELECT_ID_NOT_INTEGER + BR + headerString, systemOutRule.getLog().trim());

        systemOutRule.clearLog();
        systemInMock.provideLines("5", "0");
        AbstractUIController.selectMenuAction(headerString, testMenuActionMap);
        assertEquals(headerString + BR + BaseString.WRONG_COMMAND + BR + headerString, systemOutRule.getLog().trim());

        systemOutRule.clearLog();
    }
}
