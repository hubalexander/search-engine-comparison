package src.resultscomparers;

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
}