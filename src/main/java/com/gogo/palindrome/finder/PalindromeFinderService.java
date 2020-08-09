package com.gogo.palindrome.finder;

import com.gogo.palindrome.container.IncomingText;
import com.gogo.palindrome.container.Palindrome;
import com.gogo.palindrome.container.PalindromeRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class PalindromeFinderService {

    PalindromeRepository palindromeRepository;

    PalindromeFinderService(PalindromeRepository palindromeRepository) {
        this.palindromeRepository = palindromeRepository;
    }

    /**
     * The method clears the incoming strings from special characters and finds the palindromes in it.
     *
     * If the incomingText is less than 2 - with invalid length
     * to have palindromes contained in it a bad request with the
     * error message is returned. If the requested incomingText is
     * already persistent in the database the existing record's palindromes
     * will be returned - no recalculation will be performed, otherwise a new
     * record in the database will be created and it's palindromes - returned
     *
     * @param incomingText String with length more than 2
     * @return ResponseEntity with the requested palindromes or error message in body
     */
    public ResponseEntity findPalindromesInIncomingText(IncomingText incomingText) {
        if (incomingText.getClearedString().length() < 2) {
            return ResponseEntity.badRequest()
                    .body("Invalid inputText");
        }
        IncomingText incomingTextExistingInDB = palindromeRepository.findByClearedString(incomingText.getClearedString());
        if (incomingTextExistingInDB != null) {
            incomingText.setPalindromes(incomingTextExistingInDB.getPalindromes());
            return ResponseEntity.ok()
                    .body(incomingText.getPalindromes());
        }
        Set<Palindrome> palindromes = new HashSet<>();
        String containerString = incomingText.getClearedString();
        String tempString;
        for (int i = 0; i < containerString.length(); i++) {
            for (int j = i + 1; j < containerString.length(); j++) {
                tempString = containerString.substring(i, j + 1);
                if (tempString.equals(reverseString(tempString))) {
                    palindromes.add(new Palindrome(tempString));
                }
            }
        }
        incomingText.setPalindromes(palindromes);
        palindromeRepository.save(incomingText);
        return ResponseEntity.ok()
                .body(incomingText.getPalindromes());
    }

    /**
     * Reverses the given string backwards
     *
     * @param stringToReverse
     * @return The reversed string
     */
    private String reverseString(String stringToReverse) {
        byte[] stringAsByteArray = stringToReverse.getBytes();
        byte[] result = new byte[stringAsByteArray.length];
        for (int i = 0; i < stringAsByteArray.length; i++)
            result[i] = stringAsByteArray[stringAsByteArray.length - i - 1];
        return new String(result);
    }
}
