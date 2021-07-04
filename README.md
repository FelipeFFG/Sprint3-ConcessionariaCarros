# Sprint3-ConcessionariaCarros
Projeto Sprint3 Compasso Uol Conssionaria de Carros

# METODO POST FUNCIONAMENTO:
-O método post, funciona através de parâmetros passados no corpo da requisição,onde necessita ter o atributos preenchidos corretamente,para serem adicionados no banco de dados.  
-O método funciona através de um array, sendo possível adicionar mais de um objeto por vez.

# METODO GET FUNCIONAMENTO:
No método get, podemos realizar a filtragem e ordenção dos veículos.
### FILTRAR POR CARRO MARCA, COR E NOME:
-Para filtrar pelos atributos:NOME,COR E MARCA, basta inserir o nome que deseja filtrar referente aos atributos.
### FILTRAR POR CARRO MAIS CARO MAIS BARATO:
-Para buscar o carro mais caro ou o mais barato, basta inserir qualquer valor dentro do parâmetro.(importante que apenas só um desses,entre os demais atributos esteja preenchido)
### PARA REALIZAR A ORDENÇÃO:
-Para filtrar temos que colocar como parâmetro: "sort={atributo,formaDeOrdencao}" ex: http://localhost:8080/api/cars?marca=fiat&sort=valor,desc  
-Para juntar as ordenações, temos que inserir um sort para cada ordenação  ex: http://localhost:8080/api/cars?marca=fiat&sort=valor,desc&sort=ano,desc
