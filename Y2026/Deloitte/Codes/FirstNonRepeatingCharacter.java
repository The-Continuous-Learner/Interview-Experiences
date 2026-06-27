package Y2026.Deloitte.Codes;

import java.util.Arrays;
import java.util.HashMap;

public class FirstNonRepeatingCharacter {
    // Given a lowercase string return first non repeating character
    public static void main(String[] args) {
        String s = "aabccdbe";
        System.out.println(findFirstNonRepeatingChar(s));
        System.out.println(findFirstNonRepeatingV2(s));

    }

    public static String findFirstNonRepeatingChar(String s){
        boolean[] charDP = new boolean[26];

        for(int i = 0; i < s.length(); i++){
            if (s.charAt(i) != ' '){
                if(!charDP[s.charAt(i) - 'a']){
                    int counter = 0;
                    for(int j = i + 1; j < s.length(); j++){
                        if (s.charAt(j) == s.charAt(i)){
                            counter++;
                        }
                    }
                    if (counter == 0){
                        return String.valueOf(s.charAt(i));
                    }
                    charDP[s.charAt(i) - 'a'] = true;
                }
            }
            
        }
        System.out.println(Arrays.toString(charDP));
        return "";
    }

    public static String findFirstNonRepeatingV2(String s) {
        HashMap<Character, Integer> hm = new HashMap<>();

        for(int i = 0; i < s.length(); i++){
            hm.put(s.charAt(i), hm.getOrDefault(s.charAt(i), 0) + 1);
        }

        for (int i = 0; i < s.length(); i++) {
            if (hm.get(s.charAt(i)) == 1) {
                return String.valueOf(s.charAt(i));
            }
        }
        return s;
    }
}
