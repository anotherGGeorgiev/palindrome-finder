package com.gogo.palindrome.container;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PalindromeRepository extends JpaRepository<IncomingText, Long> {

    IncomingText findByClearedString(String clearedString);

}
