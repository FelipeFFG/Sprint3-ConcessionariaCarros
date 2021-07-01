package com.example.projetogerenciamentocarros.repository;

import com.example.projetogerenciamentocarros.model.Carro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;



public interface CarroRepository  extends JpaRepository<Carro,Long> {


    Carro findByChassi(String chassi);


    //TRAZER TODOS OS CARROS
    List<Carro> findAll();



    //FILTRO MARCA




    //MARCA-NOME
    List<Carro> findByMarcaAndNome(String marca, String nome);

    //MARCA-COR
    List<Carro> findByMarcaAndCor(String marca, String cor);

    //MARCA-VALOR
    List<Carro> findByMarcaAndValor(String marca, Integer valor);

    //MARCA-ANO
    List<Carro> findByMarcaAndAno(String marca, Integer Ano);


    //FILTROS POR UM PARAMETRO


    //fultrar por valor
    List<Carro> findByValor(Integer valor);
    //filtra por ano
    List<Carro> findByAno(Integer ano);
    //filtra por cor
    List<Carro> findByCor(String cor);
    //filtra por nome
    List<Carro> findByNome(String nome);
    //filtra por marca
    List<Carro> findByMarca(String marca);
}
