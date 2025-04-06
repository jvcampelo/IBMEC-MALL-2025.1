package com.ibmecmall2025.Ibmec.Mall.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;
@Data
@Entity(name = "cartao")
public class Cartao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    @JsonIgnore
    private Usuario usuario; // Relacionamento com o usuário

    @Column
    private String numero;

    @Column
    private LocalDateTime dtExpiracao;

    @Column
    private String cvv;

    @Column
    private Double saldo;
}
