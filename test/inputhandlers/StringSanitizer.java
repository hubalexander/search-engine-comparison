package test.inputhandlers;

import org.junit.Test;
import static org.junit.Assert.*;

public class StringSanitizer {
    public String handleInputString(String searchInput) {
        // Removed additonal white spaces.
        String inputString = removeAdditionalWhiteSpaces(searchInput);
        // Number of quotation marks.
        int numberofWhiteSpaces = inputString.length() - inputString.replaceAll("\\s+", "").length();
        // Number of white spaces.
        int numberOfQuotation = inputString.length() - inputString.replace("\"", "").length();

        // Checks for separated search terms, and multiple white spaces and multiple
        // quotation marks.
        if (numberofWhiteSpaces == 0 || inputString.length() < 3 || numberofWhiteSpaces > 1 && numberOfQuotation < 2) {
            return null;
        }

        // Removing quotation mark and white space combinations.
        if (inputString.contains("\" \"") || inputString.contains(" \"") || inputString.contains("\" ")) {
            return quotationFormatter(inputString);
        }

        // Checks for single quotation mark.
        if (inputString.contains("\"")) {
            return null;
        }

        // Replaces single search term white space.
        String finalSingleString = inputString.replaceAll(" ", "&&&");
        return finalSingleString;
    }

    private String removeAdditionalWhiteSpaces(String search) {
        // Removing redundant white spaces.
        String trimmedInput = search.replaceAll("\\s{2,}", " ").trim();
        trimmedInput = trimmedInput.stripLeading();
        return trimmedInput.stripTrailing();
    }

    private String quotationFormatter(String input) {
        // Quotation mark and white space combinations. Removes quotation marks.
        String trimmedQuotation = input.replaceAll("\" \"", "&&&");
        trimmedQuotation = trimmedQuotation.replaceAll(" \"", "&&&");
        trimmedQuotation = trimmedQuotation.replaceAll("\" ", "&&&");
        trimmedQuotation = trimmedQuotation.replaceAll("\"", "");
        return trimmedQuotation.replaceAll(" ", "+");
    }

    // Unit test for handleInputString
    @Test
    public void testHandleInputString() {
        StringSanitizer sanitizer = new StringSanitizer();
        String result = sanitizer.handleInputString("\"test text\" \"test text\"");
        assertEquals("test+text&&&test+text", result);
    }
}
