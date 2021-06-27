package src.resultscomparers;

public interface ResultComparer {

    // Abstract method for comparing results.
    public abstract String getWinner(String firstSearch, int firstResult, String secondSearch, int secondResult);
}