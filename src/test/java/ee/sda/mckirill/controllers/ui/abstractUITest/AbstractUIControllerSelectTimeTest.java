package ee.sda.mckirill.controllers.ui.abstractUITest;

import ee.sda.mckirill.controllers.ui.AbstractUIController;
import ee.sda.mckirill.controllers.ui.AbstractUITestClass;
import ee.sda.mckirill.strings.BaseString;
import org.junit.Test;

import java.time.LocalTime;

import static junit.framework.TestCase.assertEquals;

public class AbstractUIControllerSelectTimeTest extends AbstractUITestClass {
    private final String time = "12.00";
    private final LocalTime timeForCheck = LocalTime.of(12,0);
    @Test
    public void selectTimeTest() {
        systemInMock.provideLines(time);
        assertEquals(timeForCheck, AbstractUIController.selectTime(headerString));
        assertEquals(BaseString.SELECT_TIME,systemOutRule.getLog().trim());

        systemOutRule.clearLog();

        systemInMock.provideLines("qwer", time);
        AbstractUIController.selectTime(headerString);
        assertEquals(BaseString.SELECT_TIME + BR + BaseString.SELECT_TIME_INVALID + BR + BaseString.SELECT_TIME, systemOutRule.getLog().trim());

        systemOutRule.clearLog();

        systemInMock.provideLines("1200", time);
        AbstractUIController.selectTime(headerString);
        assertEquals(BaseString.SELECT_TIME + BR + BaseString.SELECT_TIME_INVALID + BR + BaseString.SELECT_TIME, systemOutRule.getLog().trim());
    }
}
