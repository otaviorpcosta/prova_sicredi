#language: pt

@simulationSucess
Funcionalidade: Criação de nova simulação

  @createSimulationSucess
  Esquema do Cenário: Criar nova simulação com sucesso

    Dado API POST informando os dados cpf, nome, email, valor, parcelas e seguro
    Quando realizar um requisição para o serviço de "<requisicao>"
    Então o retorno do status code igual a 201
    E exibe os dados inseridos como retorno

    Exemplos:
      | requisicao |
      | simulacoes |

  @alteraSimulationSucess
  Esquema do Cenário: Alterar simulação já existente

    Dado API PUT quando alterar o nome do cliente
    Quando realizar um requisição para o serviço de "<requisicao>" passando o cpf desejado
    Então o retorno do status code igual a 200
    E exibe os dados atualizados como retorno

    Exemplos:
      | requisicao |
      | simulacoes |

  @searchSimulationSucess
  Esquema do Cenário: Buscar simulações

    Dado API GET simulation
    Quando realizar um requisição para o serviço de busca "<requisicao>"
    Então o retorno do status code igual a 200
    E a lista das simulacoes cadastradas

    Exemplos:
      | requisicao |
      | simulacoes |

  @deleteSimulationSucess
  Esquema do Cenário: Deletar simulação com sucesso

    Dado API DELETE
    Quando realizar um requisição para o serviço de "<requisicao>" com o id da simulacao a ser excluida
    Então o retorno do status code igual a 200

    Exemplos:
      | requisicao |
      | simulacoes |