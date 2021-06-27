package src.datafetchers;

public interface DataFetcher {
    // Abstract method for fetching data.
    public abstract int fetchData(String searchInput);

    // Abstract method for splitting the body result
    public abstract int splitBodyResult(String body);
}
