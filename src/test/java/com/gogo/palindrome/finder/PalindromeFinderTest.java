package com.gogo.palindrome.finder;

import com.gogo.palindrome.container.IncomingText;
import com.gogo.palindrome.container.Palindrome;
import com.gogo.palindrome.container.PalindromeRepository;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

public class PalindromeFinderTest {
    @Mock
    private PalindromeRepository palindromeRepository;
    @InjectMocks
    private PalindromeFinderService palindromeFinderService;
    Set<Palindrome> palindromes;
    IncomingText incomingText;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        incomingText = new IncomingText("A man, a plan, a canal, Panama!");
        palindromes = new HashSet<>();
        palindromes.add(new Palindrome("ANA"));
        palindromes.add(new Palindrome("AMA"));
        palindromes.add(new Palindrome("AMANAPLANACANALPANAMA"));
        palindromes.add(new Palindrome("NACAN"));
        palindromes.add(new Palindrome("ANACANA"));
        palindromes.add(new Palindrome("NAPLANACANALPAN"));
        palindromes.add(new Palindrome("MANAPLANACANALPANAM"));
        palindromes.add(new Palindrome("APLANACANALPA"));
        palindromes.add(new Palindrome("ANAPLANACANALPANA"));
        palindromes.add(new Palindrome("ACA"));
        palindromes.add(new Palindrome("PLANACANALP"));
        palindromes.add(new Palindrome("LANACANAL"));
    }

    @Test
    public void findPalindromesFromContainerStringInvalidInputText() {
        IncomingText incomingText = new IncomingText("0");
        assertEquals("Invalid inputText",
                palindromeFinderService.findPalindromesInIncomingText(incomingText).getBody());
    }

    @Test
    public void findPalindromesFromContainerStringValidInputText() {
        assertEquals(palindromes,
                palindromeFinderService.findPalindromesInIncomingText(incomingText).getBody());
    }

    @Test
    public void findPalindromesFromContainerStringExistingInputTextInDB() {
        when(palindromeRepository.findByClearedString(incomingText.getClearedString())).thenReturn(incomingText);
        assertEquals(incomingText.getPalindromes(), palindromeFinderService.findPalindromesInIncomingText(incomingText).getBody());
    }

}
