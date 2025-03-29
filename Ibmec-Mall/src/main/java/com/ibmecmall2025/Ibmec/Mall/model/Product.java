package com.ibmecmall2025.Ibmec.Mall.model;

import java.util.List;
import com.azure.spring.data.cosmos.core.mapping.Container;
import com.azure.spring.data.cosmos.core.mapping.PartitionKey;

import jakarta.persistence.Id;
import lombok.Data;

@Data
@Container(containerName = "products")
public class Product {
    @Id
    private String id;

    @PartitionKey
    private String productCategory;

    private String productName;

    private double price;

    private List<String> imageUrl;

    private String productDescription;



}
