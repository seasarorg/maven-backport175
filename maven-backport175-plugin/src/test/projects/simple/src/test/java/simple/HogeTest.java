package simple;

import ascii.AsciiAnnotated;
import junit.framework.TestCase;

public class HogeTest extends TestCase {

    public void testDecuple() throws Exception {
        assertEquals(7650, new AsciiAnnotated().decuple(765));
    }

}
