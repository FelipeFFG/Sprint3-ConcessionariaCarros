package com.example.projetogerenciamentocarros.repository;

import com.example.projetogerenciamentocarros.model.Carro;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;


public interface CarroRepository extends JpaSpecificationExecutor<Carro>, JpaRepository<Carro, Long> {

    Carro findByChassi(String chassi); //Usado para verificar se possuem algum veiculo com esse chassi na base


    @Query("select max(valor) from Carro")
    Carro maiscaro();

    @Query("select max(valor) from Carro")
    BigDecimal max();

    @Query("select min(valor) from Carro")
    BigDecimal min();


    List<Carro> findByValor(BigDecimal value);
}
