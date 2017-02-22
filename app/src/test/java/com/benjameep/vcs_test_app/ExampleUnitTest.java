package com.benjameep.vcs_test_app;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
        int[] array = {1,2,3};
        assertEquals(6, myfunction.addUp(array));
        assertEquals(720, myfunction.factorial(6));
        assertEquals(46656, myfunction.powerUp(6));
    }
}