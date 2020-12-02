package com.rover;

public class AppTest {

    public AppTest(String testName) {
        super(testName);
    }

    public static Test suite() {
        return new TestSuite(AppTest.class);
    }

    @Test
    public void testApp() {
        assertTrue(true);
    }
}
