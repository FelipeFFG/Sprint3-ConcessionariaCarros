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
import java.util.stream.Collectors;


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
                                 @RequestParam(value = "ordenaNome",defaultValue = "null",required = false)String ordenaNome,
                                 @RequestParam(value = "ordenaValor",defaultValue = "null",required = false)String ordenaValor,
                                 @RequestParam(value = "ordenaAno",defaultValue = "null",required = false)String ordenaAno){

        if (marca==null & nome==null & cor==null & ordenaNome.equals("null") & ordenaValor.equals("null")){                 //se todos os parametros estiverem nulo, ele retorna todos os carros
            List<Carro> carro = carroRepository.findAll();
            return buildCarro(carro);
        }

 //-----------------------------------------------------------------------------------------------------------------------

        else if(nome==null & cor==null & ordenaNome.equals("null") & ordenaValor.equals("null")  & ordenaAno.equals("null")) {                          //MARCA
            List<Carro> carro = carroRepository.findByMarca(marca);
            return buildCarro(carro);
        }else if(nome==null & cor==null & ordenaNome.equals("asc")) {                          //MARCA  OrdenaNome-ASK
            List<Carro> carro = carroRepository.findByMarcaOrderByNomeAsc(marca);
            return buildCarro(carro);
        }else if(nome==null & cor==null & ordenaNome.equals("desc")) {                         //MARCA  OrdenaNome-Desc
            List<Carro> carro = carroRepository.findByMarcaOrderByNomeDesc(marca);
            return buildCarro(carro);
        }
        else if(nome==null & cor==null & ordenaValor.equals("asc")) {                          //MARCA  ordenaValor-ASK
            List<Carro> carro = carroRepository.findByMarcaOrderByValorAsc(marca);
            return buildCarro(carro);
        }else if(nome==null & cor==null & ordenaValor.equals("desc")) {                         //MARCA  ordenaValor-Desc
            List<Carro> carro = carroRepository.findByMarcaOrderByValorDesc(marca);
            return buildCarro(carro);
        }
        else if(nome==null & cor==null & ordenaAno.equals("asc") ) {                          //MARCA  odernaAno-ASK
            List<Carro> carro = carroRepository.findByMarcaOrderByAnoAsc(marca);
            return buildCarro(carro);
        }
        else if(nome==null & cor==null & ordenaAno.equals("desc")) {                         //MARCA  ordenaAno-Desc
            List<Carro> carro = carroRepository.findByMarcaOrderByAnoDesc(marca);
            return buildCarro(carro);
        }

//-----------------------------------------------------------------------------------------------------------------------

        else if(marca==null & cor==null  & ordenaNome.equals("null") & ordenaValor.equals("null") & ordenaAno.equals("null")){     //NOME
            List<Carro> carro = carroRepository.findByNome(nome);
            return buildCarro(carro);
        }

        else if(marca==null & cor==null & ordenaValor.equals("asc")) {                                   //NOME  ordenaValor-ASK
            List<Carro> carro = carroRepository.findByNomeOrderByValorAsc(nome);
            return buildCarro(carro);
        }
        else if(marca==null & cor==null & ordenaValor.equals("desc")) {                                  //NOME  ordenaValor-Desc
            List<Carro> carro = carroRepository.findByNomeOrderByValorDesc(nome);
            return buildCarro(carro);
        }


        else if(marca==null & cor==null & ordenaAno.equals("asc")) {                                   //NOME  ordenaAno-ASK
            List<Carro> carro = carroRepository.findByNomeOrderByAnoAsc(nome);
            return buildCarro(carro);
        }
        else if(marca==null & cor==null & ordenaAno.equals("desc")) {                                  //NOME  ordenaAno-Desc
            List<Carro> carro = carroRepository.findByNomeOrderByAnoDesc(nome);
            return buildCarro(carro);
        }

//-----------------------------------------------------------------------------------------------------------------------


        else if(nome==null & marca==null & ordenaNome.equals("null") & ordenaValor.equals("null") & ordenaAno.equals("null")){      //COR
            List<Carro> carro = carroRepository.findByCor(cor);
            return buildCarro(carro);
        }
        else if(nome==null & marca==null & ordenaNome.equals("asc")) {                          //COR-  OrdenaNome-ASK
            List<Carro> carro = carroRepository.findByCorOrderByNomeAsc(cor);
            return buildCarro(carro);
        }
        else if(nome==null & marca==null & ordenaNome.equals("desc")) {                         //COR-  OrdenaNome-Desc
            List<Carro> carro = carroRepository.findByCorOrderByNomeDesc(cor);
            return buildCarro(carro);
        }

        else if(nome==null & marca==null & ordenaValor.equals("asc")) {                          //COR  ordenaValor-ASK
            List<Carro> carro = carroRepository.findByCorOrderByValorAsc(cor);
            return buildCarro(carro);
        }
        else if(nome==null & marca==null & ordenaValor.equals("desc")) {                         //NOME  ordenaValor-Desc
            List<Carro> carro = carroRepository.findByCorOrderByValorDesc(cor);
            return buildCarro(carro);
        }


        else if(nome==null & marca==null & ordenaAno.equals("asc")) {                          //COR  ordenaAno-ASK
            List<Carro> carro = carroRepository.findByCorOrderByAnoAsc(cor);
            return buildCarro(carro);
        }
        else if(nome==null & marca==null & ordenaAno.equals("desc")) {                         //cor  ordenaAno-Desc
            List<Carro> carro = carroRepository.findByCorOrderByAnoDesc(cor);
            return buildCarro(carro);
        }


//-----------------------------------------------------------------------------------------------------------------------

        else if(nome==null & ordenaNome.equals("null") & ordenaValor.equals("null") & ordenaAno.equals("null")){                //MARCA-COR    - COR-MARCA
            List<Carro> carro = carroRepository.findByMarcaAndCor(marca,cor);
            return buildCarro(carro);
        }
        else if(nome==null & ordenaNome.equals("asc")) {                                            //MARCA-COR OrdenaNome-ASK
            List<Carro> carro = carroRepository.findByMarcaAndCorOrderByNomeAsc(marca, cor);
            return buildCarro(carro);
        }
        else if(nome==null & ordenaNome.equals("desc")) {                                           //MARCA-COR-OrdenaNome-Desc
            List<Carro> carro = carroRepository.findByMarcaAndCorOrderByNomeDesc( marca, cor);
            return buildCarro(carro);
        }

        else if(nome==null & ordenaValor.equals("asc")) {                                            //MARCA-COR OrdenaValor-ASK
            List<Carro> carro = carroRepository.findByMarcaAndCorOrderByValorAsc(marca, cor);
            return buildCarro(carro);
        }
        else if(nome==null & ordenaValor.equals("desc")) {                                           //MARCA-COR-OrdenaValor-Desc
            List<Carro> carro = carroRepository.findByMarcaAndCorOrderByValorDesc( marca, cor);
            return buildCarro(carro);
        }


        else if(nome==null & ordenaAno.equals("asc")) {                                            //MARCA-COR ordenaAno-ASK
            List<Carro> carro = carroRepository.findByMarcaAndCorOrderByAnoAsc(marca, cor);
            return buildCarro(carro);
        }
        else if(nome==null & ordenaAno.equals("desc")) {                                           //MARCA-COR-ordenaAno-Desc
            List<Carro> carro = carroRepository.findByMarcaAndCorOrderByAnoDesc( marca, cor);
            return buildCarro(carro);
        }

//-----------------------------------------------------------------------------------------------------------------------

        else if(cor ==null& ordenaNome.equals("null")& ordenaValor.equals("null") & ordenaAno.equals("null")){    //MARCA-NOME   - NOME-MARCA
            List<Carro> carro = carroRepository.findByMarcaAndNome(marca,nome);
            return buildCarro(carro);
        }


        else if(cor==null & ordenaValor.equals("asc")) {                                            //MARCA-NOME OrdenaValor-ASK
            List<Carro> carro = carroRepository.findByMarcaAndNomeOrderByValorAsc(marca, nome);
            return buildCarro(carro);
        }
        else if(cor==null & ordenaValor.equals("desc")) {                                           //MARCA-NOME  OrdenaValor-Desc
            List<Carro> carro = carroRepository.findByMarcaAndNomeOrderByValorDesc( marca, nome);
            return buildCarro(carro);
        }

        else if(cor==null & ordenaAno.equals("asc")) {                                            //MARCA-NOME ordenaAno-ASK
            List<Carro> carro = carroRepository.findByMarcaAndNomeOrderByAnoAsc(marca, nome);
            return buildCarro(carro);
        }
        else if(cor==null & ordenaAno.equals("desc")) {                                           //MARCA-NOME  ordenaAno-Desc
            List<Carro> carro = carroRepository.findByMarcaAndNomeOrderByAnoDesc( marca, nome);
            return buildCarro(carro);
        }

 //-----------------------------------------------------------------------------------------------------------------------


        else if(marca==null & ordenaValor.equals("null") & ordenaAno.equals("null")){             //NOME-COR   - COR-NOME
            List<Carro> carro = carroRepository.findByNomeAndCor(nome,cor);
            return buildCarro(carro);
        }

        else if(marca==null & ordenaValor.equals("asc")) {                                            //NOME-CR OrdenaValor-ASK
            List<Carro> carro = carroRepository.findByNomeAndCorOrderByValorAsc(nome, cor);
            return buildCarro(carro);
        }
        else if(marca==null & ordenaValor.equals("desc")) {                                           //NOME-COR  OrdenaValor-Desc
            List<Carro> carro = carroRepository.findByNomeAndCorOrderByValorDesc(nome, cor);
            return buildCarro(carro);
        }

        else if(marca==null & ordenaAno.equals("asc")) {                                            //NOME-CR ordenaAno-ASK
            List<Carro> carro = carroRepository.findByNomeAndCorOrderByAnoAsc(nome, cor);
            return buildCarro(carro);
        }
        else if(marca==null & ordenaAno.equals("desc")) {                                           //NOME-COR  ordenaAno-Desc
            List<Carro> carro = carroRepository.findByNomeAndCorOrderByAnoDesc(nome, cor);
            return buildCarro(carro);
        }

//-----------------------------------------------------------------------------------------------------------------------




        else if(marca!=null & nome!=null & cor!=null){                         //NOME-COR-MARCA
            List<Carro> carro = carroRepository.findByMarcaAndValorAndCor(marca,nome,cor);
            buildCarro(carro);
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



    public List<CarroDto> buildCarro(List<Carro> carro){
        if (carro != null){
            CarroDto carroDtos = new CarroDto();
            return carroDtos.converter(carro);
        }
        return null;
    }
}
