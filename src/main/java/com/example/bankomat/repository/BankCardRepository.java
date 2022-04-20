package com.example.bankomat.repository;

import com.example.bankomat.entity.BankCard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankCardRepository extends JpaRepository<BankCard,Integer> {

    boolean existsByCardNumber(Long cardNumber);
}
