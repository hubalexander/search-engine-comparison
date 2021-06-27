package test.inputhandlers;

import org.junit.Test;
import static org.junit.Assert.*;

public class StringSplitter {

    // Splitting the result string into two separate strings.
    public String[] split(String searchTerms) {
        String[] searchData = searchTerms.split("&&&");
        return searchData;
    }

    // Unit test for split
    @Test
    public void testSplit() {
        StringSplitter splitter = new StringSplitter();
        String[] result = splitter.split("Test&&&text");
        assertEquals(("Test"), result[0]);
    }

}
