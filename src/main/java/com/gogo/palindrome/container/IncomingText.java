package com.gogo.palindrome.container;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity (name = "incoming_texts")
public class IncomingText {
    public static final String NON_SPECIAL_CHARACTER_REGEX = "[^a-zA-Z0-9/:]";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id = 0L;
    @Column(unique = true)
    private String clearedString;
    private String originalInputString;
    @OneToMany
    @Cascade(CascadeType.ALL)
    private Set<Palindrome> palindromes = new HashSet<>();

    public IncomingText(){

    }

    public IncomingText(String inputString){
        this.originalInputString = inputString;
        this.clearedString = clearOriginalStringFromSpecialCharacters(this.originalInputString);
    }

    /**
     * Clears the original string from special characters,
     * not including / and : and formatted in uppercase
     *
     * @param originalString
     * @return The cleared string
     */
    private String clearOriginalStringFromSpecialCharacters(String originalString){
        return originalString.replaceAll(NON_SPECIAL_CHARACTER_REGEX, "").toUpperCase();
    }

    public String getClearedString() {
        return clearedString;
    }

    public Set<Palindrome> getPalindromes() {
        return palindromes;
    }

    public void setPalindromes(Set<Palindrome> palindromes) {
        this.palindromes = palindromes;
    }
}
