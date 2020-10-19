# Sistema de Hospedagem (Hotel) - API

Task List Project
*************************** Orientações ***************************

Este projeto fornece a implementação de uma api referenet à um sistema de hospedagem de hotel

* Instruções para execução:

    1 - Baixar e configurar o JDK 11.

    1 - baixar o projeto ou utilizar o git para fazer um clone no repositório: https://github.com/alanjhone/hotel.git

    2 -  Criar um banco de dados local do tipo postgres com o nome ‘hospedagem’ e, definir usuário e senha ‘postgres’.

        database: hospedagem
        usuario: postgres
        senha: postgres
        

    3 - Utilizar o eclipse ou o maven para compilar o projeto
      
      3.1 - Maven -> executar o comando abaixo na raiz do projeto para gerar o arquivo executável:
      
        mvn package
        
        Rodar aplicação:
        
        java -jar target/hotel-0.0.1-SNAPSHOT.jar
    
## Interface para descrever API - Swagger

    Endereço: http://localhost:8080/swagger-ui.html

## EXTRA (Deploy no Heroku)

    link: https://hotel-restapi.herokuapp.com/swagger-ui.html#/
