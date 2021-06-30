package com.example.projetogerenciamentocarros.dto;

import com.example.projetogerenciamentocarros.model.Carro;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

public class CarroDto {


    private String chassi;
    private String nome;
    private String cor;
    private String marca;
    private Integer valor;
    private Integer ano;

    public CarroDto() {
    }

    public CarroDto(Carro carro) {
        this.chassi = carro.getChassi();
        this.nome = carro.getNome();
        this.cor = carro.getCor();
        this.marca = carro.getMarca();
        this.valor = carro.getValor();
        this.ano = carro.getAno();
    }


    public static List<CarroDto> converter(List<Carro> carros){    //converte uma lista de carros em carrosDto
        return carros.stream().map(CarroDto::new).collect(Collectors.toList());
    }



    public String getChassi() {
        return chassi;
    }

    public String getNome() {
        return nome;
    }

    public String getCor() {
        return cor;
    }

    public Integer getValor() {
        return valor;
    }

    public Integer getAno() {
        return ano;
    }

    public String getMarca() {
        return marca;
    }
}
