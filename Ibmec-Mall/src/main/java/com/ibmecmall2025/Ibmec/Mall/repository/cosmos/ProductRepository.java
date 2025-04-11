package com.ibmecmall2025.Ibmec.Mall.repository.cosmos;

import org.springframework.stereotype.Repository;

import com.azure.spring.data.cosmos.repository.CosmosRepository;
import com.ibmecmall2025.Ibmec.Mall.model.Product;

@Repository
public interface ProductRepository extends CosmosRepository<Product, String>{

}