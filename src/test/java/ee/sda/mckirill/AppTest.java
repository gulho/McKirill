package ee.sda.mckirill;

import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemOutRule;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;

import static org.junit.Assert.*;
import static org.junit.contrib.java.lang.system.TextFromStandardInputStream.emptyStandardInputStream;

@Ignore
public class AppTest {
    @Rule
    public final TextFromStandardInputStream systemInMock
            = emptyStandardInputStream();

    @Rule
    public final SystemOutRule systemOutRule = new SystemOutRule().enableLog();


    @Test
    public void numb() {
        //systemInMock.provideLines("2", "3");
        System.out.println("5");
        assertEquals("5", systemOutRule.getLog());
        //assertEquals(5, App.numb());
    }

    @Test
    public void mainLogint() {
        //systemInMock.provideLines("qwer@qwer.ee", "123456");
        systemInMock.provideLines("manager@mckirill.ee", "123456", "2", "2", "asdf", "beer", "6.7", "0", "0", "exit");
        App.main(new String[]{});
    }

    @Test
    public void testTable() {
        //systemInMock.provideLines("qwer@qwer.ee", "123456");
        //systemInMock.provideLines("manager@mckirill.ee", "123456", "2", "2", "asdf", "beer", "6.7", "0", "0", "exit");
        systemInMock.provideLines("1", "1", "0", "0", "exit");
        App.main(new String[]{});
    }
}