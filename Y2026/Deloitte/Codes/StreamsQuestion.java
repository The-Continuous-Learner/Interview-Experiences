package Y2026.Deloitte.Codes;

import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class StreamsQuestion {
    public static void main(String[] args) {
        // Given an array of strings, strings are repeating, find the count of each using streams
        String[] S = {"orange", "mango", "mango", "apple", "apple"};

        Map<String, Long> counts = Arrays.stream(S)
                                    .collect(Collectors.groupingBy(s -> s, Collectors.counting()));

        // Alphabetically sorted 
        Map<String, Long> sortedCount = Arrays.stream(S)
                                            .collect(Collectors.groupingBy(s -> s, TreeMap::new, Collectors.counting()));
        
        System.out.println(counts);
        System.out.println(sortedCount);
    }
}
