# API Benefícios de Saúde Mongo Spring 

Aplicação desenvolvida com o intuito de melhorar o cadastro de Empresas, Funcionários e seus Benefícios de saúde. 
Utilizando de SpringBoot e MongoDB, a aplicação consegue realizar o cadastro de Funcionários, Clientes e Benefícios de forma simples utilizando requests http.
Existem Alguns tipos de benefícios diferentes que requerem dados diferentes de Funcionários para o cadastro, pensando nisso foi utilizado o mongodb que é um banco de documentos, diferente de um banco de dados relacional. 
Além disso, o SpringBoot junto do SpringData torna o código mais sucinto e padronizado quando se trata de requests http e uso de banco de dados. 

Estrutura de pastas da Aplicação

```
.
├── pom.xml
├── src
│   ├── main
│   │    └── java
│   │        ├── configuration
│   │        ├── constants
│   │        ├── controller
│   │        ├── model
│   │        ├── repository
│   │        ├── security
│   │        ├── service
│   │        ├── swagger
│   │        └── PipoApplication.java
│   └── resources
│      └──  application.yml
└── test
   └── src/test/java/br/com/gabrielreis/pipo/repository
       └── RepositoryTest.java

```
- `src` Arquivos de código fonte da aplicação
- `test` Arquivos de teste da aplicação;