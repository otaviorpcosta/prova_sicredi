#language: pt

@simulationsErrors
Funcionalidade: Cenários Alternativos na criação de nova simulação

  @createSimulationNoCPF
  Esquema do Cenário: Criar nova simulação quando não informar cpf

    Dado API POST informando os dados nome, email, valor, parcelas e seguro
    Quando realizar requisição para o serviço de "<requisicao>"
    Então o sistema retorna o status code igual a 400
    E exibe a lista de "<erro>" retornado do serviço informando "<mensagem de erro>"

    Exemplos:
      | requisicao | erro | mensagem de erro       |
      | simulacoes | cpf  | CPF não pode ser vazio |

  @createSimulationNoName
  Esquema do Cenário: Criar nova simulação quando não informar nome

    Dado API POST informando os dados cpf, email, valor, parcelas e seguro
    Quando realizar requisição para o serviço de "<requisicao>"
    Então o sistema retorna o status code igual a 400
    E exibe a lista de "<erro>" retornado do serviço informando "<mensagem de erro>"

    Exemplos:
      | requisicao | erro | mensagem de erro        |
      | simulacoes | nome | Nome não pode ser vazio |

  @createSimulationNoEmail
  Esquema do Cenário: Criar nova simulação quando não informar email

    Dado API POST informando os dados cpf, nome, valor, parcelas e seguro
    Quando realizar requisição para o serviço de "<requisicao>"
    Então o sistema retorna o status code igual a 400
    E exibe a lista de "<erro>" retornado do serviço informando "<mensagem de erro>"

    Exemplos:
      | requisicao | erro  | mensagem de erro          |
      | simulacoes | email | E-mail não deve ser vazio |

  @createSimulationEmailInvalid
  Esquema do Cenário: Criar nova simulação quando informar email invalido

    Dado API POST informando email inválido e demais dados válidos
    Quando realizar requisição para o serviço de "<requisicao>"
    Então o sistema retorna o status code igual a 400
    E exibe a lista de "<erro>" retornado do serviço informando que o "<mensagem de erro>" está inválido

    Exemplos:
      | requisicao | erro  | mensagem de erro                 |
      | simulacoes | email | E-mail deve ser um e-mail válido |

  @createSimulationNoValue
  Esquema do Cenário: Criar nova simulação quando não informar valor

    Dado API POST informando os dados cpf, nome, email, parcelas e seguro
    Quando realizar requisição para o serviço de "<requisicao>"
    Então o sistema retorna o status code igual a 400
    E exibe a lista de "<erro>" retornado do serviço informando "<mensagem de erro>"

    Exemplos:
      | requisicao | erro  | mensagem de erro         |
      | simulacoes | valor | Valor não pode ser vazio |

  @createSimulationValueInvalid
  Esquema do Cenário: Criar nova simulação quando informar valor maior que R$ 40.000

    Dado API POST informando valor maior que o permitido e demais dados válidos
    Quando realizar requisição para o serviço de "<requisicao>"
    Então o sistema retorna o status code igual a 400
    E exibe a lista de "<erro>" retornado do serviço informando "<mensagem de erro>"

    Exemplos:
      | requisicao | erro  | mensagem de erro                          |
      | simulacoes | valor | Valor deve ser menor ou igual a R$ 40.000 |

  @createSimulationNoPortion
  Esquema do Cenário: Criar nova simulação quando não informar parcelas

    Dado API POST informando os dados cpf, nome, email, valor e seguro
    Quando realizar requisição para o serviço de "<requisicao>"
    Então o sistema retorna o status code igual a 400
    E exibe a lista de "<erro>" retornado do serviço informando "<mensagem de erro>"

    Exemplos:
      | requisicao | erro     | mensagem de erro            |
      | simulacoes | parcelas | Parcelas não pode ser vazio |

  @createSimulationPortionSmallerThan
  Esquema do Cenário: Criar nova simulação quando informar parcela menor que 2

    Dado API POST informando parcela menor que o permitido e demais dados válidos
    Quando realizar requisição para o serviço de "<requisicao>"
    Então o sistema retorna o status code igual a 400
    E exibe a lista de "<erro>" retornado do serviço informando "<mensagem de erro>"

    Exemplos:
      | requisicao | erro     | mensagem de erro                       |
      | simulacoes | parcelas | Parcelas deve ser igual ou maior que 2 |

  @createSimulationCPFDuplicate
  Esquema do Cenário: Criar nova simulação quando informar cpf já existente

    Dado API POST informando cpf já existente e demais dados válidos
    Quando realizar requisição para o serviço de "<requisicao>"
    Então o sistema retorna o status code igual a 400
    E exibe a mensagem de "<erro>" retornado do serviço informando "<mensagem de erro>"

    Exemplos:
      | requisicao | erro     | mensagem de erro |
      | simulacoes | mensagem | CPF duplicado    |

  @updatedSimulationCPF
  Esquema do Cenário: Alterar simulação quando informar cpf que não possui simulação

    Dado API PUT informando cpf que não possui simulação cadastrada
    Quando realizar requisição de alteração para o serviço de "<requisicao>"
    Então o sistema retorna o status code igual a 404
    E exibe a mensagem de "<erro>" retornado do serviço informando "<mensagem de erro>"

    Exemplos:
      | requisicao | erro     | mensagem de erro               |
      | simulacoes | mensagem | CPF 47185729084 não encontrado |

  @searchSimulationCPF
  Esquema do Cenário: Buscar simulações pelo CPF

    Dado API GET do cpf desejado
    Quando realizar um requisição para o serviço de busca "<requisicao>" informando o cpf desejado
    Então o sistema retorna o status code igual a 200
    E os dados da simulação do cpf informado

    Exemplos:
      | requisicao |
      | simulacoes |

  @searchSimulationCPFNoValid
  Esquema do Cenário: Buscar simulações pelo CPF que não possui simulação

    Dado API GET do cpf desejado
    Quando realizar um requisição para o serviço de busca "<requisicao>" informando o cpf que não possui simulação
    Então o sistema retorna o status code igual a 404
    E exibe a mensagem de "<erro>" retornado do serviço informando "<mensagem de erro>"

    Exemplos:
      | requisicao | erro     | mensagem de erro               |
      | simulacoes | mensagem | CPF 65654754076 não encontrado |

#  @deleteSimulationNoValid
#  Esquema do Cenário: Deletar simulação com ID inexistente
#
#    Dado API DELETE com id inexistente
#    Quando realizar um requisição para o serviço "<requisicao>" informando id inexistente
#    Então o sistema retorna o status code igual a 404
#    E exibe a mensagem de "<erro>" retornado do serviço informando "<mensagem de erro>"
#
#    Exemplos:
#      | requisicao | erro     | mensagem de erro         |
#      | simulacoes | mensagem | Simulação não encontrada |