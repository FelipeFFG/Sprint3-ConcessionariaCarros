package com.example.projetogerenciamentocarros.form;

import com.example.projetogerenciamentocarros.errors.ErrorToAddValues;
import com.example.projetogerenciamentocarros.model.Carro;
import com.example.projetogerenciamentocarros.repository.CarroRepository;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

public class CarroForm {      //Formulario para adiconar um carro no banco.

    @NotBlank(message = "chassi nao pode ser nulo ou vazio")
    private String chassi;

    @NotBlank(message = "nome nao pode ser nulo ou vazio")
    private String nome;

    @NotBlank(message = "nome nao pode ser nulo ou vazio")
    private String marca;

    @NotBlank(message = "cor nao pode ser nulo ou vazio")
    private String cor;

    private BigDecimal valor;
    private BigDecimal ano;

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

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public Carro coverter(CarroRepository carroRepository) {                //verifica se nao tem um carro com o mesmo chassi no banco de dados.
        Carro carro = carroRepository.findByChassi(this.chassi);
        if (carro == null) {
            return new Carro(this.chassi, this.nome, this.marca, this.cor, this.valor, this.ano);
        } else
            throw new ErrorToAddValues("Nao foi possivel adicionar o veiculo ao nosso banco de dados, por favor , certifique se os atributos passados estao certos.");
    }

}
