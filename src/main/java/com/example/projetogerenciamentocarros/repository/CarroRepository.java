package com.example.projetogerenciamentocarros.repository;




import com.example.projetogerenciamentocarros.model.Carro;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;



public interface CarroRepository  extends JpaRepository<Carro,Long> {



    Carro findByChassi(String chassi);


    //TRAZER TODOS OS CARROS
    List<Carro> findAll();



    //filtrar por todos os parametros
    List<Carro>findByMarcaAndValorAndCor(String marca,String nome,String cor);





    //ORDENACAO POR NOME
    List<Carro> OrderByNomeAsc();
    List<Carro> OrderByNomeDesc();

    //ORDENACAO POR VALOR
    List<Carro> OrderByValorAsc();
    List<Carro> OrderByValorDesc();

    //ORDENACAO POR ANO
    List<Carro> OrderByAnoAsc();
    List<Carro> OrderByAnoDesc();






    //NOME-COR
    List<Carro> findByNomeAndCor(String nome,String cor);

    //MARCA-NOME
    List<Carro> findByMarcaAndNome(String marca, String nome);

    //MARCA-COR
    List<Carro> findByMarcaAndCor(String marca, String cor);



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
