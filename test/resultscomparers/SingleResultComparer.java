package test.resultscomparers;

import org.junit.Test;
import static org.junit.Assert.*;

public class SingleResultComparer implements ResultComparer {

    @Override
    public String getWinner(String firstSearch, int firstResult, String secondSearch, int secondResult) {

        // Returns the greatest value.
        if (firstResult > secondResult) {
            return firstSearch;
        } else {
            return secondSearch;
        }
    }

    // Unit test for getWinner
    @Test
    public void testGetWinner() {
        ResultComparer comparer = new SingleResultComparer();
        String result = comparer.getWinner("firstSearch", 10, "secondSearch", 5);
        assertEquals("firstSearch", result);
    }
}