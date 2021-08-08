#language: pt

@Search
Funcionalidade: Busca de veículos

  @ct01
  Cenário: Validar a listagem das marcas cadastradas na webmotors

    Dado API GET
    Quando realizar um requisição para o serviço de "Make"
    Então a API irá retornar o status code igual a 200
    E exibe a listagem das marcas cadastradas

  @ct02
  Cenário: Validar a listagem dos modelos da Honda cadastrados na webmotors

    Dado API GET
    Quando realizar um requisição para o serviço de "Model" com o id da marca
    Então a API irá retornar o status code igual a 200
    E exibe a listagem dos modelos cadastrados

  @ct03
  Cenário: Validar a listagem das versões do Honda CRV cadastrados na webmotors

    Dado API GET
    Quando realizar um requisição para o serviço de "Version" com o id do modelo
    Então a API irá retornar o status code igual a 200
    E exibe a listagem das versões cadastradas

  @ct04
  Cenário: Validar a listagem dos veículos cadastrados na pagina 1

    Dado API GET
    Quando realizar um requisição para o serviço de "Vehicles" com o número da página desejada
    Então a API irá retornar o status code igual a 200
    E exibe a listagem dos veículos cadastrados