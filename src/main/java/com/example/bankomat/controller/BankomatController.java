package com.example.bankomat.controller;

import com.example.bankomat.dto.ApiResponse;
import com.example.bankomat.dto.BankDto;
import com.example.bankomat.entity.Bankomat;
import com.example.bankomat.repository.BankomatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RequestMapping("/api/bankomat")
@RestController
@RequiredArgsConstructor
public class BankomatController {

    final BankomatRepository bankomatRepository;

    @GetMapping
    public HttpEntity<?> getAll(){
        return ResponseEntity.ok().body(bankomatRepository.findAll());
    }
    @PostMapping("/add")
    public HttpEntity<?> add(@Valid @RequestBody BankDto dto){
        ApiResponse apiResponse=new ApiResponse();
        if(!bankomatRepository.existsByName(dto.getName())) {
            Bankomat bankomat = new Bankomat();
            bankomat.setName(dto.getName());
            bankomat.setCommissionFee(dto.getCommissionFee());
            bankomatRepository.save(bankomat);
            apiResponse.setMessage("Added");
            apiResponse.setSuccess(true);
        }
        apiResponse.setMessage("Already exist");
        apiResponse.setSuccess(false);
        return ResponseEntity.status(apiResponse.isSuccess()?201:409).body(apiResponse);
    }
    @PutMapping("/{id}")
    public HttpEntity<?> edit(@PathVariable Integer id,@Valid @RequestBody BankDto dto){
        ApiResponse apiResponse=new ApiResponse();
        Optional<Bankomat> byId = bankomatRepository.findById(id);
        if (!byId.isPresent()) {
            apiResponse.setSuccess(false);
            apiResponse.setMessage("Not found");
        }
        Bankomat bankomat = byId.get();
        bankomat.setName(dto.getName());
        bankomat.setCommissionFee(dto.getCommissionFee());
        bankomatRepository.save(bankomat);
        apiResponse.setMessage("Added");
        apiResponse.setSuccess(true);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }
}
