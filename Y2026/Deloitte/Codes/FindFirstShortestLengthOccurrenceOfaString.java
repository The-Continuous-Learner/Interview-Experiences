package Y2026.Deloitte.Codes;

import java.util.HashMap;

public class FindFirstShortestLengthOccurrenceOfaString {

    public static void main(String[] args) {
        String A = "abcdfgheabcdfgabcbcdacde";
        String B = "cde";

        System.out.println(findShortestLength(A, B));
    }

    public static int findShortestLength(String A, String B) {
        if (A.length() < B.length()) return -1;

        // Frequency map of B
        HashMap<Character, Integer> need = new HashMap<>();
        for (int i = 0; i < B.length(); i++) {
            char c = B.charAt(i);
            need.put(c, need.getOrDefault(c, 0) + 1);
        }

        // Unique characters
        int requiredElements = need.size();


        HashMap<Character, Integer> actual = new HashMap<>();
        int left = 0, minLength = Integer.MAX_VALUE, actualPresent = 0;

        for(int right = 0; right < A.length(); right++){
            Character c = A.charAt(right);

            // check if it is there in need list
            Integer numTimesInB =  need.get(c);

            if (numTimesInB != null) {

                // Check if it is present in A, actual list
                Integer numTimesInA = actual.get(c);
                if (numTimesInA == null) {
                    numTimesInA = 0;
                }
                numTimesInA++;
                actual.put(c, numTimesInA);
                
                // Num of times in A & B matches, so this elements requirement has been satisfied and count can be incremented.
                if (numTimesInA == numTimesInB) {
                    actualPresent++;
                }

                // If both matches shrink
                while(requiredElements == actualPresent){
                    minLength = Math.min(minLength, right - left + 1);
                    Character temp = A.charAt(left);
                    Integer tempTimesInB = need.get(temp);

                    if (tempTimesInB != null) {
                        Integer tempTimesInA = actual.get(temp) - 1;
                        actual.put(temp, tempTimesInA);

                        if (tempTimesInA < tempTimesInB){
                            actualPresent--;
                        }
                    }
                    left++;
                }
            }
        }
        return minLength == Integer.MAX_VALUE ? -1 : minLength;
    }
}
