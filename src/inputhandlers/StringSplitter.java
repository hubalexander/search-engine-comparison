package src.inputhandlers;

public class StringSplitter {

    // Splitting the result string into two separate strings.
    public String[] split(String searchTerms) {
        String[] searchData = searchTerms.split("&&&");
        return searchData;
    }
}
