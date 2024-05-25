package CW2;

import org.junit.Test;

import java.io.IOException;
import static org.junit.Assert.assertEquals;

public class WorkWithFileTest {

    @Test
    public void testCheckEven() {
        assertEquals(0, WorkWithFile.checkEven(new byte[0]));
        assertEquals(0, WorkWithFile.checkEven(new byte[]{2, 4, 6}));
        assertEquals(1, WorkWithFile.checkEven(new byte[]{1, 2, 3, 4}));
    }
}
