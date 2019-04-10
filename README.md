# Documentação para a execução da aplicação cobranca-mensal-app

# Frameworks e ferramentas utilizadas O projeto foi desenvolvido utilizando os seguintes tecnologias:

* Java 8
* Spring Boot
* Spring Data
* Tomcat embutido
* Swagger
* JPA
* Maven
* JUnit

# Arquitetura do projeto

Ambiente de execução embutido - Utilizado o Tomcat embutido, disponibilizado pelo Spring Boot.

Esta aplicação disponibiliza um serviço REST para disponibilizar recursos de cadastro de Clientes, Planos e Contratos, utilizado o banco de dados PostgreSQL.

# Pré requisitos
1. Criar o banco de dados com o nome **db_cobranca**
2. Executar a aplicação cobranca-mensal-app para disponibilizar os serviços de consumo e cadastro dos planos, clientes e contratos.
3. Java 8
4. Maven

# Configurações

Os arquivos de propriedades da aplicação se encontram no caminho: src/main/resources

* Application.properties : arquivo de propriedades do Spring


# Execução do projeto Maven: $ mvn clean package spring-boot:run

Utilizando uma IDE: /cobranca-mensal-app/src/main/java/br/com/cobrancamensal/CobrancaMensalAppApplication.java

No seu browser digite http://localhost:8080/swagger-ui.html por aqui você verá a documentação dos serviços da API.