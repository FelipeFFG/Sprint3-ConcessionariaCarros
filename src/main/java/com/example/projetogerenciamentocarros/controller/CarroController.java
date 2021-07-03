package com.example.projetogerenciamentocarros.controller;

import com.example.projetogerenciamentocarros.dto.CarroDto;
import com.example.projetogerenciamentocarros.form.CarroForm;
import com.example.projetogerenciamentocarros.model.Carro;
import com.example.projetogerenciamentocarros.repository.CarroRepository;
import com.example.projetogerenciamentocarros.specification.SpecificationCarro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;


import javax.validation.Valid;
import java.net.URI;
import java.util.List;


@RestController
@RequestMapping("/api")
public class CarroController {

    @Autowired
    private CarroRepository carroRepository;

    @PostMapping("/cars")
    @Transactional
    public ResponseEntity<CarroDto> cadastrar(@Valid @RequestBody CarroForm form, UriComponentsBuilder uriBuilder) {
        Carro carro = form.coverter(carroRepository);
        if (carro != null) {
            carroRepository.save(carro);
            URI uri = uriBuilder.path("/api/cars/{id}").buildAndExpand(carro.getId()).toUri();
            return ResponseEntity.created(uri).body(new CarroDto(carro));
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/cars")
    @Transactional
    public List<CarroDto> filtro(@RequestParam(value = "marca", required = false) String marca, //Filtrar por marca.
                                 @RequestParam(value = "nome", required = false) String nome,   //Filtrar por nome.
                                 @RequestParam(value = "cor", required = false) String cor,     //Filtrar por cor.
                                 Pageable pageable) {
        if (marca == null & nome == null & cor == null) {    //MARCA NOME COR    -Paremetros nulos retornal todos os valores.
            Page<Carro> carro = carroRepository.findAll(pageable);
            return buildCars(carro);
        } else if (marca != null & nome == null & cor == null) {  //MARCA    -filtra por marca
            Page<Carro> carro = carroRepository.findAll(Specification.where((SpecificationCarro.marca(marca))), pageable);
            return buildCars(carro);
        } else if (marca != null & nome != null & cor == null) {  //MARCA & NOME
            Page<Carro> carro = carroRepository.findAll(Specification.where((SpecificationCarro.marca(marca))).and(SpecificationCarro.nome(nome)), pageable);
            return buildCars(carro);
        } else if (marca == null & nome != null & cor == null) {  //NOME
            Page<Carro> carro = carroRepository.findAll(Specification.where((SpecificationCarro.nome(nome))), pageable);
            return buildCars(carro);
        } else if (marca == null & nome != null & cor != null) {  //NOME COR
            Page<Carro> carro = carroRepository.findAll(Specification.where((SpecificationCarro.nome(nome))).and(SpecificationCarro.cor(cor)), pageable);
            return buildCars(carro);
        } else if (marca == null & nome == null & cor != null) {  //COR
            Page<Carro> carro = carroRepository.findAll(Specification.where((SpecificationCarro.cor(cor))), pageable);
            return buildCars(carro);
        } else if (marca != null & nome == null & cor != null) {  //COR & MARCA
            Page<Carro> carro = carroRepository.findAll(Specification.where((SpecificationCarro.marca(marca))).and(SpecificationCarro.cor(cor)), pageable);
            return buildCars(carro);
        } else if (marca != null & nome != null & cor != null) {  //MARCA COR E NOME
            Page<Carro> carro = carroRepository.findAll(Specification.where((SpecificationCarro.marca(marca))).and(SpecificationCarro.nome(nome)).and(SpecificationCarro.cor(cor)), pageable);
            return buildCars(carro);
        }
        return null;
    }

    //Funcao para transformar Page em List de carrosDto
    public List<CarroDto> buildCars(Page<Carro> carro) {
        List<Carro> carros = carro.toList();
        return CarroDto.converter(carros);
    }

    //PEGAR O CARRO MIAS CARO.
    @GetMapping("/cars/maiscaro")
    public List<CarroDto> maiscaro() {
        List<Carro> carro = carroRepository.findByValor(carroRepository.max()); //FAZ UMA BUSCA POR CARROS QUE POSSUEM O VALOR MIN DA TABELA
        CarroDto carroDto = new CarroDto();
        return carroDto.converter(carro);
    }


    //PEGAR O CARRO MAIS BARATO.
    @GetMapping("/cars/maisbarato")
    public List<CarroDto> maisbarato(){
        List<Carro> carro = carroRepository.findByValor(carroRepository.min()); //FAZ UMA BUSCA POR CARROS QUE POSSUEM O VALOR MIN DA TABELA
        CarroDto carroDto = new CarroDto();
        return carroDto.converter(carro);
    }

}
