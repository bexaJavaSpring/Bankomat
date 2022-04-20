package com.example.bankomat.service;

import com.example.bankomat.dto.ApiResponse;
import com.example.bankomat.dto.BankCardDto;
import com.example.bankomat.entity.BankCard;
import com.example.bankomat.entity.User;
import com.example.bankomat.entity.ebuns.CardStatus;
import com.example.bankomat.repository.BankCardRepository;
import com.example.bankomat.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BankCardService {

    final BankCardRepository bankCardRepository;
    final UserRepository userRepository;

    public ApiResponse add(BankCardDto dto) {
        if (bankCardRepository.existsByCardNumber(dto.getCardNumber())) {
            return new ApiResponse("Already exist",false);
        }
        BankCard bankCard=new BankCard();
        bankCard.setCardNumber(dto.getCardNumber());
        bankCard.setBalance(dto.getBalance());
        bankCard.setPinCode(dto.getPinCode());
        bankCard.setStatus(CardStatus.valueOf(dto.getStatus()));
        Optional<User> byId = userRepository.findById(dto.getUserId());
        bankCard.setUser(byId.get());
        bankCardRepository.save(bankCard);
        return new ApiResponse("Added",true);
    }

    public ApiResponse edit(Integer id, BankCardDto dto) {
        Optional<BankCard> byId = bankCardRepository.findById(id);
        if (!byId.isPresent()) {
            return new ApiResponse("Not found",false);
        }
        BankCard bankCard = byId.get();
        bankCard.setCardNumber(dto.getCardNumber());
        bankCard.setBalance(dto.getBalance());
        bankCard.setPinCode(dto.getPinCode());
        bankCard.setStatus(CardStatus.valueOf(dto.getStatus()));
        Optional<User> byId1 = userRepository.findById(dto.getUserId());
        bankCard.setUser(byId1.get());
        bankCardRepository.save(bankCard);
        return new ApiResponse("Edited",true);
    }
}
