package com.example.projetogerenciamentocarros.repository;

import com.example.projetogerenciamentocarros.model.Carro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;



public interface CarroRepository  extends JpaRepository<Carro,Long> {


    Carro findByChassi(String chassi);


    List<Carro> findAll();

    List<Carro> findByAno(Integer ano);
    List<Carro> findByCor(String cor);
    List<Carro> findByNome(String nome);
    List<Carro> findByMarca(String marca);
}
