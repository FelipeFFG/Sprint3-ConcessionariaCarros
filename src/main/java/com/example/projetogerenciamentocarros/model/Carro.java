package com.example.projetogerenciamentocarros.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.*;
import java.math.BigDecimal;

@Entity
public class Carro {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "chassi nao pode ser nulo ou vazio")
    private String chassi;

    @NotBlank(message = "nome nao pode ser nulo ou vazio")
    private String nome;

    @NotBlank(message = "marca nao pode ser nulo ou vazio")
    private String marca;

    @NotBlank(message = "cor nao pode ser nulo ou vazio")
    private String cor;


    private BigDecimal valor;
    private BigDecimal ano;


    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public Carro() {
    }

    public Carro(String chassi, String nome,String marca ,String cor, BigDecimal valor, BigDecimal ano) {
        this.chassi = chassi;
        this.nome = nome;
        this.cor = cor;
        this.valor = valor;
        this.ano = ano;
        this.marca =marca;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getChassi() {
        return chassi;
    }

    public void setChassi(String chassi) {
        this.chassi = chassi;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public BigDecimal getAno() {
        return ano;
    }

    public void setAno(BigDecimal ano) {
        this.ano = ano;
    }
}
