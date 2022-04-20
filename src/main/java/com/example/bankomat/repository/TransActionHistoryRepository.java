package com.example.bankomat.repository;

import com.example.bankomat.entity.TransActionHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransActionHistoryRepository extends JpaRepository<TransActionHistory,Integer> {
}
