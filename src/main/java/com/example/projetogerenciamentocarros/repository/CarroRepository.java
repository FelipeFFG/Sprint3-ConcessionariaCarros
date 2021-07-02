package com.example.projetogerenciamentocarros.repository;

import com.example.projetogerenciamentocarros.model.Carro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


public interface CarroRepository extends JpaSpecificationExecutor<Carro>, JpaRepository<Carro, Long> {

    Carro findByChassi(String chassi); //Usado para verificar se possuem algum veiculo com esse chassi na base

}
