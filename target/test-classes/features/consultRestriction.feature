#language: pt

@ConsultRestriction
Funcionalidade: Consulta de restrições

  @ct01
  Esquema do Cenário: Validar a consulta de CPF com restrição

    Dado API GET
    Quando realizar um requisição para o serviço de "<requisicao>" com o cpf com restrição "<cpf>"
    Então a API irá retornar o status code igual a 200
    E exibe a mensagem informando O CPF "<cpf>" possui restrição

    Exemplos:
      | requisicao | cpf         |
      | restricoes | 97093236014 |

  @ct02
  Esquema do Cenário: Validar a consulta de CPF sem restrição

    Dado API GET
    Quando realizar um requisição para o serviço de "<requisicao>" com o cpf com restrição "<cpf>"
    Então a API irá retornar o status code igual a 204

    Exemplos:
      | requisicao | cpf         |
      | restricoes | 10762549009 |