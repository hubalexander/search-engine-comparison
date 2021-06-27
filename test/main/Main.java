package test.main;

import src.inputhandlers.StringSanitizer;
import src.inputhandlers.StringSplitter;
import src.searchcomparers.NumberComparer;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // Welcome text.
        System.out.println(
                "-------------------------------------------" + "\n" + "Search comparision between: Google and Bing"
                        + "\n" + "Separate the two search terms with a blank space" + "\n"
                        + "Use quotation marks in case of blank space, \"Search term\" " + "\n"
                        + "Please enter two search terms:");

        // Scanning the user input.
        Scanner inputScanner = new Scanner(System.in);
        String inputString = inputScanner.nextLine();
        inputScanner.close();

        // Creating instances of helpers.
        StringSanitizer sanitizer = new StringSanitizer();
        StringSplitter splitter = new StringSplitter();
        NumberComparer comparer = new NumberComparer();

        // Managing the user input.
        String sanitizedData = sanitizer.handleInputString(inputString);
        try {
            String[] splittedData = splitter.split(sanitizedData);
            String resultText = comparer.compareSearchNumbers(splittedData);
            System.out.println(resultText);
        } catch (Exception e) {
            System.out.println("Error running the program");
        }

    }
}