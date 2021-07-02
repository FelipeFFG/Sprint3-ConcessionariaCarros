package com.example.projetogerenciamentocarros.specification;

import com.example.projetogerenciamentocarros.model.Carro;
import org.springframework.data.jpa.domain.Specification;

public class SpecificationCarro {  //Esta classe é a especificação do carro, onde é utilizada para filtrar os carros pelos atributos.



    //NOME DO CARRO
    public static Specification<Carro> nome(String nome){
        return ((root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.like(root.get("nome"),"%"+nome+"%"));
    }

    //MARCA DO CARRO
    public static Specification<Carro> marca(String marca){
        return ((root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.like(root.get("marca"),"%"+marca+"%"));
    }
    //COR DO CARRO
    public static Specification<Carro> cor(String cor){
        return ((root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.like(root.get("cor"),"%"+cor+"%"));
    }
    //VALOR DO CARRO
    public static Specification<Carro> valor(Integer valor){
        return ((root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.like(root.get("valor"),"%"+valor+"%"));
    }

    //ANO DO CARRO
    public static Specification<Carro> ano(Integer ano){
        return ((root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.like(root.get("ano"),"%"+ano+"%"));
    }




}
