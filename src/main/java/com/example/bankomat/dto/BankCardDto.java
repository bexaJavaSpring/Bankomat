package com.example.bankomat.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BankCardDto {
    private Long cardNumber;
    private int pinCode;
    private String status;
    private Integer userId;
    private Double balance;
}
