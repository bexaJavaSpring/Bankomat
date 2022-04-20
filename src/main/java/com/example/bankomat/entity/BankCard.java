package com.example.bankomat.entity;

import com.example.bankomat.entity.ebuns.CardStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class BankCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Long cardNumber;
    private int pinCode;
    @Enumerated(EnumType.STRING)
    private CardStatus status = CardStatus.UNBLOCK;
    @ManyToOne
    private User user;
    private Double balance;

}
