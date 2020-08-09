package com.gogo.palindrome.container;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

@Entity(name = "palindromes")
public class Palindrome {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id = 0L;
    private String palindrome;

    public Palindrome() {

    }

    @Override
    public boolean equals(Object objectToCompare) {
        if (this == objectToCompare) return true;
        if (!(objectToCompare instanceof Palindrome)) return false;
        Palindrome palindromeToCompare = (Palindrome) objectToCompare;
        return getPalindrome().equals(palindromeToCompare.getPalindrome());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPalindrome());
    }

    public Palindrome(String palindrome) {
        this.palindrome = palindrome;
    }

    public String getPalindrome() {
        return palindrome;
    }

    public void setPalindrome(String palindrome) {
        this.palindrome = palindrome;
    }
}
