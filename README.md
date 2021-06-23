Se você quiser rodar apenas os testes de um classe específica, use o parâmetro ***it.test=NomeDaClasse***. Exemplo:

    mvn integration-test -Dit.test=ClientApiTest

O comando anterior roda todos os testes de integração somente da classe *ClientApiTest*. Se quiser rodar apenas um teste específico de um determinada classe, use ***#nomeMetodo*** logo após o nome da classe:

    mvn integration-test -Dit.test=ClientApiTest#shouldReturnAll

O comando anterior roda apenas o teste ***shouldReturnAll()*** da classe ***ClientApiTest***. Para acrescentar outros testes específicos, use **+** logo após o nome do teste anterior:

    mvn integration-test -Dit.test=ClientApiTest#shouldReturnAll+shouldReturnOne
