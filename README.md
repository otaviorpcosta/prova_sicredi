# SicredAPI
Testes de API

Ressalvas:

1 - Os testes foram criado utilizando o Cucumber, Rest Assured e o Junit. Os casos de teste estão escritos no modelo de BDD nos arquivos de feature, localizado no resources do projeto.

2 - Realizei os testes utilizando a IDE IntelliJ

3 - A validação de valor mínimo ("valor da simulação que deve ser igual ou maior que R$ 1.000") não está implementado no serviço disponibilizado, ou seja, quando eu cadastro uma simulação
com o valor menor que 1.000 o sistema permite o cadastro.

4 - A validação de parcelas máximas ("menor ou igual a 48") não está implementado no serviço disponibilizado, ou seja, quando eu cadastro uma simulação
com o parcelas maior que 48 o sistema permite o cadastro.

5 - Ao tentar deletar uma simulação com id que não existe, o sistem retorna statos code 200 OK, ou seja, o sistema deleta uma simulação que não existe.


Manual de execução de testes:

Para executar os testes primeiro deve acessar as features desejadas onde dividi por:

- Consulta Restrição (consultRestriction.feature)
- Criação, atualização, busca e exclusão de simulação com sucesso (createSimulation.feature)
- Cenários alternativos de simulações (simulationsAlternatives.feature)

Após abrir o .feature desejado existe duas formas de executar:

1) Executar a features inteira
Para executar uma feature inteira é só copiar a @tag que fica acima da funcionalidade, inserir a mesma no CucumberOption/tag do runner, e rodar o runner

2) Executar uma feature individual 
Para executar a feature individual é só copiar a @tag que fica acima do cenário desejado, inserir a mesma no CucumberOption/tag do runner, e rodar o runner



