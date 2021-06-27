package src.searchcomparers;

import src.datafetchers.BingDataFetcher;
import src.datafetchers.DataFetcher;
import src.datafetchers.GoogleDataFetcher;
import src.resultscomparers.ResultComparer;
import src.resultscomparers.SingleResultComparer;

public class NumberComparer implements Comparer {

        @Override
        public String compareSearchNumbers(String[] searchTerms) {
                String result = "";
                // Creating instances of the dataFetchers
                DataFetcher googleFetcher = new GoogleDataFetcher();
                DataFetcher bingFetcher = new BingDataFetcher();

                // Adding the result of the first search term.
                int googleFirstResult = googleFetcher.fetchData(searchTerms[0]);
                int bingFirstResult = bingFetcher.fetchData(searchTerms[0]);
                result = searchTerms[0] + ": " + "Google: " + googleFirstResult + " Bing: " + bingFirstResult + "\n";

                // Adding the result of the second search term.
                int googleSecondResult = googleFetcher.fetchData(searchTerms[1]);
                int bingSecondResult = bingFetcher.fetchData(searchTerms[1]);
                result = result + searchTerms[1] + ": " + "Google: " + googleSecondResult + " Bing: " + bingSecondResult
                                + "\n";

                // Creating an instance of the comparer.
                ResultComparer singleComparer = new SingleResultComparer();

                // Adding the numeric results of each search term.
                int firstTotal = googleFirstResult + bingFirstResult;
                int secondTotal = googleSecondResult + bingSecondResult;

                // Printing the compared search results.
                result = result + "Google winner: "
                                + singleComparer.getWinner(
                                                searchTerms[0], googleFirstResult, searchTerms[1], googleSecondResult)
                                + "\n" + "Bing winner: "
                                + singleComparer.getWinner(searchTerms[0], bingFirstResult, searchTerms[1],
                                                bingSecondResult)
                                + "\n" + "Total winner: "
                                + singleComparer.getWinner(searchTerms[0], firstTotal, searchTerms[1], secondTotal);
                return result;
        }
}
