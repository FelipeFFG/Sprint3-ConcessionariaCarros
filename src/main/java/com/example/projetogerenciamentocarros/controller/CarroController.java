package com.example.projetogerenciamentocarros.controller;

import com.example.projetogerenciamentocarros.dto.CarroDto;
import com.example.projetogerenciamentocarros.errors.ErrorToAddValues;
import com.example.projetogerenciamentocarros.errors.ErrorToFoundValues;
import com.example.projetogerenciamentocarros.form.CarroForm;
import com.example.projetogerenciamentocarros.model.Carro;
import com.example.projetogerenciamentocarros.repository.CarroRepository;
import com.example.projetogerenciamentocarros.specification.SpecificationCarro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/api")
public class CarroController {

    @Autowired
    private CarroRepository carroRepository;

    @PostMapping("/cars/individual")            //metodo post para adicionar apenas um carros.
    @Transactional
    public ResponseEntity<?> cadastrar(@Valid @RequestBody CarroForm form) {
        Carro carro = form.coverter(carroRepository);
        carroRepository.save(carro);
        List<Carro> carros = new ArrayList<>();
        carros.add(carro);
        return new ResponseEntity<>(CarroDto.converter(carros), HttpStatus.OK);

    }

    @PostMapping("/cars")                      //metodo post para adicionar uma lista de carros.
    @Transactional
    public ResponseEntity<?> cadastrar(@Valid @RequestBody List<CarroForm> form) {
        if (form.size() != 0) {
            List<Carro> carroList = new ArrayList<>();
            for (int i = 0; i < form.size(); i++) {
                Carro carro = form.get(i).coverter(carroRepository);
                carroRepository.save(carro);
                carroList.add(carro);
            }
            return new ResponseEntity<>(CarroDto.converter(carroList), HttpStatus.OK);
        }
        throw new ErrorToAddValues("Nao foi possivel adicionar o veiculo ao nosso banco de dados, por favor , certifique se os atributos passados estao certos.");
    }

    @GetMapping("/cars")
    @Transactional
    public ResponseEntity<?> filtro(@RequestParam(value = "marca", required = false) String marca, //Filtrar por marca.
                                    @RequestParam(value = "nome", required = false) String nome,   //Filtrar por nome.
                                    @RequestParam(value = "cor", required = false) String cor,     //Filtrar por cor.
                                    @RequestParam(value = "maiscaro",required = false)String maiscaro,
                                    @RequestParam(value = "maisbarato",required = false)String maisbarato,
                                    Pageable pageable) {
        if (marca == null & nome == null & cor == null      & maiscaro ==null & maisbarato==null) {    //MARCA NOME COR    -Paremetros nulos retornal todos os valores.
            Page<Carro> carro = carroRepository.findAll(pageable);
            return check(carro);
        } else if (marca != null & nome == null & cor == null      & maiscaro ==null & maisbarato==null) {  //MARCA    -filtra por marca
            Page<Carro> carro = carroRepository.findAll(Specification.where((SpecificationCarro.marca(marca))), pageable);
            return check(carro);
        } else if (marca != null & nome != null & cor == null      & maiscaro ==null & maisbarato==null) {  //MARCA & NOME
            Page<Carro> carro = carroRepository.findAll(Specification.where((SpecificationCarro.marca(marca))).and(SpecificationCarro.nome(nome)), pageable);
            return check(carro);
        } else if (marca == null & nome != null & cor == null      & maiscaro ==null & maisbarato==null) {  //NOME
            Page<Carro> carro = carroRepository.findAll(Specification.where((SpecificationCarro.nome(nome))), pageable);
            return check(carro);
        } else if (marca == null & nome != null & cor != null      & maiscaro ==null & maisbarato==null) {  //NOME COR
            Page<Carro> carro = carroRepository.findAll(Specification.where((SpecificationCarro.nome(nome))).and(SpecificationCarro.cor(cor)), pageable);
            return check(carro);
        } else if (marca == null & nome == null & cor != null      & maiscaro ==null & maisbarato==null) {  //COR
            Page<Carro> carro = carroRepository.findAll(Specification.where((SpecificationCarro.cor(cor))), pageable);
            return check(carro);
        } else if (marca != null & nome == null & cor != null      & maiscaro ==null & maisbarato==null) {  //COR & MARCA
            Page<Carro> carro = carroRepository.findAll(Specification.where((SpecificationCarro.marca(marca))).and(SpecificationCarro.cor(cor)), pageable);
            return check(carro);
        } else if (marca != null & nome != null & cor != null      & maiscaro ==null & maisbarato==null) {  //MARCA COR E NOME
            Page<Carro> carro = carroRepository.findAll(Specification.where((SpecificationCarro.marca(marca))).and(SpecificationCarro.nome(nome)).and(SpecificationCarro.cor(cor)), pageable);
            return check(carro);
        } else if (marca != null & nome != null & cor != null      & maiscaro ==null & maisbarato==null) {  //MARCA COR E NOME
            Page<Carro> carro = carroRepository.findAll(Specification.where((SpecificationCarro.marca(marca))).and(SpecificationCarro.nome(nome)).and(SpecificationCarro.cor(cor)), pageable);
            return check(carro);
        }
        else if (maiscaro!=null & marca == null & nome == null & cor == null & maisbarato==null) {  //MAIS CARO
            return convert(maiscaro());
        }
        else if (maiscaro==null & marca == null & nome == null & cor == null & maisbarato!=null) {  //MAIS BARATO
            return convert(maisbarato());
        }

        throw new ErrorToFoundValues("Nao foi possivel achar nenhum carro com esses filtro");
    }

    //realiza uma verificacao se a lista esta vazia ou nao, caso esteja ela, significa que nao conseguiu achar nenhum valor com aqueles paremetros e solta um exception com uma mensagem.
    public ResponseEntity<?> check(Page<Carro> carro) {
        if (buildCars(carro).size() > 0) {
            return new ResponseEntity<>(buildCars(carro), HttpStatus.OK);
        }
        throw new ErrorToFoundValues("Nao foi possivel achar nenhum carro com esses filtro");
    }

    //Funcao para transformar Page em List de carrosDto
    public List<CarroDto> buildCars(Page<Carro> carro) {
        List<Carro> carros = carro.toList();
        return CarroDto.converter(carros);
    }

    //PEGAR O CARRO MIAS CARO.
    public List<CarroDto> maiscaro() {
        List<Carro> carro = carroRepository.findByValor(carroRepository.max()); //FAZ UMA BUSCA POR CARROS QUE POSSUEM O VALOR MIN DA TABELA
        CarroDto carroDto = new CarroDto();
        return CarroDto.converter(carro);
    }

    //PEGAR O CARRO MAIS BARATO.
    public List<CarroDto> maisbarato() {
        List<Carro> carro = carroRepository.findByValor(carroRepository.min()); //FAZ UMA BUSCA POR CARROS QUE POSSUEM O VALOR MIN DA TABELA
        CarroDto carroDto = new CarroDto();
        return CarroDto.converter(carro);
    }

    public ResponseEntity<?> convert(List<CarroDto> carroDtos){
        return new ResponseEntity<>(carroDtos, HttpStatus.OK);
    }
}
