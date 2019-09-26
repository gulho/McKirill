package ee.sda.mckirill.controllers.ui.abstractUITest;

import ee.sda.mckirill.controllers.ui.AbstractUITestClass;
import ee.sda.mckirill.strings.BaseString;
import org.junit.Test;

import java.time.LocalDate;

import static ee.sda.mckirill.controllers.ui.AbstractUIController.selectDate;
import static org.junit.Assert.assertEquals;

public class AbstractUIControllerSelectDateTest extends AbstractUITestClass {
    private final LocalDate dateForCheck = LocalDate.of(2019, 9,23);

    @Test
    public void selectDateTest() {
        String date = "23.09.2019";
        systemInMock.provideLines(date);
        assertEquals(dateForCheck, selectDate(headerString));
        assertEquals(BaseString.SELECT_DATE,systemOutRule.getLog().trim());

        systemOutRule.clearLog();

        systemInMock.provideLines("qwer", date);
        selectDate(headerString);
        assertEquals(BaseString.SELECT_DATE + BR + BaseString.SELECT_DATE_INVALID + BR + BaseString.SELECT_DATE, systemOutRule.getLog().trim());

        systemOutRule.clearLog();

        systemInMock.provideLines("32.09.2019", date);
        selectDate(headerString);
        assertEquals(BaseString.SELECT_DATE + BR + BaseString.SELECT_DATE_INVALID + BR + BaseString.SELECT_DATE, systemOutRule.getLog().trim());
    }
}
