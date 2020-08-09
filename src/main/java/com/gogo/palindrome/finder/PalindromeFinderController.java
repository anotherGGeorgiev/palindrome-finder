package com.gogo.palindrome.finder;

import com.gogo.palindrome.container.*;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class PalindromeFinderController {

    PalindromeFinderService palindromeFinderService;

    public PalindromeFinderController(PalindromeFinderService palindromeFinderService){
        this.palindromeFinderService = palindromeFinderService;
    }

    @PostMapping(value = "/palindrome-finder",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity postController(@RequestBody Object requestBody) {
        String inputText = (String) ((Map) requestBody).get("inputText");
        IncomingText incomingText = new IncomingText(inputText);
        return palindromeFinderService.findPalindromesInIncomingText(incomingText);

    }



}
