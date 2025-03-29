package com.ibmecmall2025.Ibmec.Mall.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ibmecmall2025.Ibmec.Mall.model.Cartao;

@Repository
public interface CartaRepository extends JpaRepository<Cartao, Integer> {
    
}
