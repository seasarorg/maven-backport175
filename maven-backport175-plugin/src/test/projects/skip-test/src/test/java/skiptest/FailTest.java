package skiptest;

import junit.framework.TestCase;

public class FailTest extends TestCase {
    
    public void test1() throws Exception {
        fail("test should be skipped.");
    }

}
