package simple;

import junit.framework.TestCase;

public class HogeTest extends TestCase {

    public void testDecuple() throws Exception {
        assertEquals(7650, new Hoge().decuple(765));
    }

}
