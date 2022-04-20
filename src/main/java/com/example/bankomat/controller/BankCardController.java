package com.example.bankomat.controller;

import com.example.bankomat.dto.ApiResponse;
import com.example.bankomat.dto.BankCardDto;
import com.example.bankomat.repository.BankCardRepository;
import com.example.bankomat.service.BankCardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping("/api/bankcard")
@RestController
@RequiredArgsConstructor
public class BankCardController {

    BankCardRepository bankCardRepository;
    BankCardService bankCardService;
    @GetMapping
    public HttpEntity<?> getAll() {
        return ResponseEntity.ok().body(bankCardRepository.findAll());
    }

    @PostMapping("/add")
    public HttpEntity<?> add(@Valid @RequestBody BankCardDto dto) {
        ApiResponse apiResponse=bankCardService.add(dto);
        return ResponseEntity.status(apiResponse.isSuccess()?201:409).body(apiResponse);
    }
    @PutMapping("/{id}")
    public HttpEntity<?> edit(@PathVariable Integer id,@Valid @RequestBody BankCardDto dto){
        ApiResponse apiResponse=bankCardService.edit(id,dto);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }

}
