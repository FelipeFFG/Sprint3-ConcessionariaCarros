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
                                 @RequestParam(value = "cor",required = false) String cor,
                                 @RequestParam(value = "valor",required = false)Integer valor,
                                 @RequestParam(value = "ano",required = false) Integer ano){

        if (marca==null & nome==null & cor==null & ano==null  & valor==null){              //se todos os parametros estiverem nulo, ele retorna todos os carros
            List<Carro> carro = carroRepository.findAll();
            CarroDto carroDtos = new CarroDto();
            return carroDtos.converter(carro);
        }else if(nome==null & cor==null & ano==null  & valor==null){                       //filtra por marca
            List<Carro> carro = carroRepository.findByMarca(marca);
            if (carro != null){
                CarroDto carroDtos = new CarroDto();
                return carroDtos.converter(carro);
            }
        }else if(marca==null & cor==null & ano==null  & valor==null){                     //filtra por nome
            List<Carro> carro = carroRepository.findByNome(nome);
            if (carro != null){
                CarroDto carroDtos = new CarroDto();
                return carroDtos.converter(carro);
            }
        }else if(marca==null & nome==null & ano==null  & valor==null){                    //filtra por  cor
            List<Carro> carro = carroRepository.findByCor(cor);
            if (carro != null){
                CarroDto carroDtos = new CarroDto();
                return carroDtos.converter(carro);
            }
        }else if(marca==null & nome==null & ano==null  & cor==null){                    //filtra por  valor
            List<Carro> carro = carroRepository.findByValor(valor);
            if (carro != null){
                CarroDto carroDtos = new CarroDto();
                return carroDtos.converter(carro);
            }
        }
        else if(marca==null & nome==null & cor==null){                                  //filtra por ano
            List<Carro> carro = carroRepository.findByAno(ano);
            if (carro != null){
                CarroDto carroDtos = new CarroDto();
                return carroDtos.converter(carro);
            }
        }else if(nome==null & cor==null  & valor==null){                                  //MARCA-ANO
            List<Carro> carro = carroRepository.findByMarcaAndAno(marca,ano);
            if (carro != null){
                CarroDto carroDtos = new CarroDto();
                return carroDtos.converter(carro);
            }
        }else if(nome==null & ano==null & cor==null){                                  //MARCA-VALOR
            List<Carro> carro = carroRepository.findByMarcaAndValor(marca,valor);
            if (carro != null){
                CarroDto carroDtos = new CarroDto();
                return carroDtos.converter(carro);
            }
        }else if(nome==null & ano==null  & valor==null){                                  //MARCA-COR
            List<Carro> carro = carroRepository.findByMarcaAndCor(marca,cor);
            if (carro != null){
                CarroDto carroDtos = new CarroDto();
                return carroDtos.converter(carro);
            }
        }else if(cor ==null & ano==null  & valor==null){                                  //MARCA-NOME
            List<Carro> carro = carroRepository.findByMarcaAndNome(marca,nome);
            if (carro != null){
                CarroDto carroDtos = new CarroDto();
                return carroDtos.converter(carro);
            }
        }





        return null;
    }







}
