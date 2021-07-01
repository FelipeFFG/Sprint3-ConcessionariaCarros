package com.example.projetogerenciamentocarros.controller;

import com.example.projetogerenciamentocarros.dto.CarroDto;
import com.example.projetogerenciamentocarros.form.CarroForm;
import com.example.projetogerenciamentocarros.model.Carro;
import com.example.projetogerenciamentocarros.repository.CarroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;


import javax.validation.Valid;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/api")
public class CarroController {

    @Autowired
    private CarroRepository carroRepository;

    @GetMapping("")
    public List<CarroDto> lista(){
        List<Carro> carros = carroRepository.findAll();
        CarroDto carroDto = new CarroDto();
        return  carroDto.converter(carros);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<CarroDto> cadastrar(@Valid @RequestBody CarroForm form, UriComponentsBuilder uriBuilder){
        Carro carro = form.coverter(carroRepository);
        if (carro !=null){
            carroRepository.save(carro);
            URI uri = uriBuilder.path("/api/cars/{id}").buildAndExpand(carro.getId()).toUri();
            return ResponseEntity.created(uri).body(new CarroDto(carro));
        }else
            System.out.println("ja existe um carro com esse chassi");
            return null;
    }



    @GetMapping("/cars")
    @Transactional
    public List<CarroDto> filtro(@RequestParam(value = "marca",required = false) String marca,    //pegar um parametro request da url
                                 @RequestParam(value = "nome",required = false) String nome,
                                 @RequestParam(value = "cor",required = false) String cor){

        if (marca==null & nome==null & cor==null){                          //se todos os parametros estiverem nulo, ele retorna todos os carros
            List<Carro> carro = carroRepository.findAll();
            CarroDto carroDtos = new CarroDto();
            return carroDtos.converter(carro);
        }else if(nome==null & cor==null) {                                  //MARCA
            List<Carro> carro = carroRepository.findByMarca(marca);
            if (carro != null){
                CarroDto carroDtos = new CarroDto();
                return carroDtos.converter(carro);
            }
        }else if(marca==null & cor==null){                                   //NOME
            List<Carro> carro = carroRepository.findByNome(nome);
            if (carro != null){
                CarroDto carroDtos = new CarroDto();
                return carroDtos.converter(carro);
            }
        }else if(marca==null & nome==null){                                  //COR
            List<Carro> carro = carroRepository.findByCor(cor);
            if (carro != null){
                CarroDto carroDtos = new CarroDto();
                return carroDtos.converter(carro);
            }
        }else if(nome==null){                                               //MARCA-COR    - COR-MARCA
            List<Carro> carro = carroRepository.findByMarcaAndCor(marca,cor);
            if (carro != null){
                CarroDto carroDtos = new CarroDto();
                return carroDtos.converter(carro);
            }
        }else if(cor ==null){                                                 //MARCA-NOME   - NOME-MARCA
            List<Carro> carro = carroRepository.findByMarcaAndNome(marca,nome);
            if (carro != null){
                CarroDto carroDtos = new CarroDto();
                return carroDtos.converter(carro);
            }
        }else if(marca==null){                                                 //NOME-COR   - COR-NOME
            List<Carro> carro = carroRepository.findByNomeAndCor(nome,cor);
            if (carro != null){
                CarroDto carroDtos = new CarroDto();
                return carroDtos.converter(carro);
            }
        }else if(marca!=null & nome!=null & cor!=null){                         //NOME-COR
            List<Carro> carro = carroRepository.findByMarcaAndValorAndCor(marca,nome,cor);
            if (carro != null){
                CarroDto carroDtos = new CarroDto();
                return carroDtos.converter(carro);
            }
        }
        return null;
    }



    private List<CarroDto> getCarroDtos(List<Carro> carro) {
        if (carro != null){
            CarroDto carroDtos = new CarroDto();
            List<CarroDto> carroMaisCaro= new ArrayList<>();
            carroMaisCaro.add(carroDtos.converter(carro).get(0));
            return carroMaisCaro;
        }
        return null;
    }


    //CARRO MAIS CARO
    @GetMapping("/cars/maiscaro")
    @Transactional
    public List<CarroDto> getMaisCaro(){
        List<Carro> carro = carroRepository.OrderByValorDesc();
        return getCarroDtos(carro);
    }

    //CARRO MAIS BARATO
    @GetMapping("/cars/maisbarato")
    @Transactional
    public List<CarroDto> getMaisBarato(){
        List<Carro> carro = carroRepository.OrderByValorAsc();
        return getCarroDtos(carro);
    }

    //ORDENCAO POR NOME
    @GetMapping("/cars/nomeasc")
    @Transactional
    public List<CarroDto> getOrderByNomeAsc(){
        List<Carro> carro = carroRepository.OrderByNomeAsc();
        CarroDto carroDtos = new CarroDto();
        return carroDtos.converter(carro);
    }

    @GetMapping("/cars/nomedesc")
    @Transactional
    public List<CarroDto> getOrderByNomeDesc(){
        List<Carro> carro = carroRepository.OrderByNomeDesc();
        CarroDto carroDtos = new CarroDto();
        return carroDtos.converter(carro);
    }


    //ORDENCAO POR VALOR
    @GetMapping("/cars/valorasc")
    @Transactional
    public List<CarroDto> getOrderByValorAsc(){
        List<Carro> carro = carroRepository.OrderByValorAsc();
        CarroDto carroDtos = new CarroDto();
        return carroDtos.converter(carro);
    }

    @GetMapping("/cars/valordesc")
    @Transactional
    public List<CarroDto> getOrderByValorDesc(){
        List<Carro> carro = carroRepository.OrderByValorDesc();
        CarroDto carroDtos = new CarroDto();
        return carroDtos.converter(carro);
    }



    //ORDENACAO POR ANO
    @GetMapping("/cars/anoasc")
    @Transactional
    public List<CarroDto> getOrderByAnosc(){
        List<Carro> carro = carroRepository.OrderByAnoAsc();
        CarroDto carroDtos = new CarroDto();
        return carroDtos.converter(carro);
    }

    @GetMapping("/cars/anodesc")
    @Transactional
    public List<CarroDto> getOrderByAnoDesc(){
        List<Carro> carro = carroRepository.OrderByAnoDesc();
        CarroDto carroDtos = new CarroDto();
        return carroDtos.converter(carro);
    }






}
