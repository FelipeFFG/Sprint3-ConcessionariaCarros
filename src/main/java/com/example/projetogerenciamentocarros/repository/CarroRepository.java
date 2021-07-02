package com.example.projetogerenciamentocarros.repository;




import com.example.projetogerenciamentocarros.model.Carro;
import org.hibernate.transform.ToListResultTransformer;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;



public interface CarroRepository  extends JpaRepository<Carro,Long> {
    Carro findByChassi(String chassi);

    //TRAZER TODOS OS CARROS
    List<Carro> findAll();
    //filtrar por todos os parametros
    List<Carro>findByMarcaAndValorAndCor(String marca,String nome,String cor);

    //MARCA
    List<Carro> findByMarca(String marca);
    List<Carro> findByMarcaOrderByNomeAsc(String marca);
    List<Carro> findByMarcaOrderByNomeDesc(String marca);
    List<Carro> findByMarcaOrderByValorAsc(String marca);
    List<Carro> findByMarcaOrderByValorDesc(String marca);
    List<Carro> findByMarcaOrderByAnoAsc(String marca);
    List<Carro> findByMarcaOrderByAnoDesc(String marca);
    List<Carro> findByMarcaOrderByValorDescNomeDesc(String marca);
    List<Carro> findByMarcaOrderByAnoDescNomeDesc(String marca);
    List<Carro> findByMarcaOrderByAnoDescValorDesc(String marca);

    //COR
    List<Carro> findByCor(String cor);
    List<Carro> findByCorOrderByNomeAsc(String cor);
    List<Carro> findByCorOrderByNomeDesc(String cor);
    List<Carro> findByCorOrderByValorAsc(String cor);
    List<Carro> findByCorOrderByValorDesc(String cor);
    List<Carro> findByCorOrderByAnoAsc(String cor);
    List<Carro> findByCorOrderByAnoDesc(String cor);
    List<Carro>findByCorOrderByValorDescNomeDesc(String cor);
    List<Carro>findByCorOrderByAnoDescNomeDesc(String cor);
    List<Carro> findByCorOrderByAnoDescValorDesc(String cor);


    //NOME
    List<Carro> findByNomeOrderByValorAsc(String nome);
    List<Carro> findByNomeOrderByValorDesc(String nome);
    List<Carro> findByNome(String nome);
    List<Carro> findByNomeOrderByAnoAsc(String nome);
    List<Carro> findByNomeOrderByAnoDesc(String nome);
    List<Carro>findByNomeOrderByAnoDescValorDesc(String nome);


    //ORDENACAO POR VALOR
    List<Carro> OrderByValorAsc();
    List<Carro> OrderByValorDesc();

    //NOME-COR
    List<Carro> findByNomeAndCor(String nome,String cor);
    List<Carro> findByNomeAndCorOrderByValorAsc(String nome,String cor);
    List<Carro> findByNomeAndCorOrderByValorDesc(String nome,String cor);
    List<Carro> findByNomeAndCorOrderByAnoAsc(String nome,String cor);
    List<Carro> findByNomeAndCorOrderByAnoDesc(String nome,String cor);
    List<Carro>findByNomeAndCorOrderByAnoDescValorDesc(String nome,String cor);


    //MARCA-NOME
    List<Carro> findByMarcaAndNome(String marca, String nome);
    List<Carro> findByMarcaAndNomeOrderByValorDesc(String marca,String nome);
    List<Carro> findByMarcaAndNomeOrderByValorAsc(String marca,String nome);
    List<Carro> findByMarcaAndNomeOrderByAnoAsc(String marca,String nome);
    List<Carro> findByMarcaAndNomeOrderByAnoDesc(String marca,String nome);
    List<Carro>findByMarcaAndNomeOrderByAnoDescValorDesc(String marca,String nome);




    //MARCA-COR
    List<Carro> findByMarcaAndCorOrderByNomeDesc(String marca,String cor);
    List<Carro> findByMarcaAndCorOrderByNomeAsc(String marca,String cor);
    List<Carro> findByMarcaAndCor(String marca, String cor);
    List<Carro> findByMarcaAndCorOrderByAnoAsc(String marca,String cor);
    List<Carro> findByMarcaAndCorOrderByAnoDesc(String marca,String cor);
    List<Carro> findByMarcaAndCorOrderByValorDesc(String marca,String cor);
    List<Carro> findByMarcaAndCorOrderByValorAsc(String marca,String cor);
    List<Carro>findByMarcaAndCorOrderByValorDescNomeDesc(String marca,String cor);
    List<Carro>findByMarcaAndCorOrderByAnoDescNomeDesc(String marca,String cor);
    List<Carro>findByMarcaAndCorOrderByAnoDescValorDesc(String marca,String cor);


    //FILTROS POR UM PARAMETRO

    //fultrar por valor
    List<Carro> findByValor(Integer valor);
    //filtra por ano
    List<Carro> findByAno(Integer ano);
    //filtra por cor



}
