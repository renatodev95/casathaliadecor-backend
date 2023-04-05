# API para gerenciamento de loja de móveis e decorações

Este é um projeto de backend em Java com SpringBoot para uma API que permite o gerenciamento de uma loja de móveis e decorações. A API permite o cadastro, consulta, atualização e exclusão de produtos, bem como o gerenciamento de pedidos e clientes.

## Funcionalidades

- Cadastro, consulta, atualização e exclusão de produtos;
- Listagem de todos os produtos;
- Listagem de produtos por categoria;
- Cadastro, consulta, atualização e exclusão de clientes;
- Listagem de todos os clientes;
- Cadastro, consulta, atualização e exclusão de pedidos;
- Listagem de todos os pedidos;
- Listagem de pedidos por cliente;
- Adição e remoção de produtos de um pedido.

## Tecnologias utilizadas

- Java 8
- SpringBoot 2.5.0
- MySQL
- Hibernate
- Maven

## Como executar o projeto

Para executar o projeto, siga os passos abaixo:

1. Clone o repositório em sua máquina local;
2. Abra o projeto em sua IDE de preferência;
3. Configure as informações de conexão com o banco de dados no arquivo `application.properties`;
4. Execute o comando `mvn clean install` para instalar as dependências;
5. Execute a classe `ApiApplication` para iniciar a aplicação.

## Endpoints disponíveis

| Endpoint | Método HTTP | Descrição |
| -------- | ----------- | --------- |
| /produtos | GET | Retorna todos os produtos |
| /produtos/{id} | GET | Retorna um produto específico |
| /produtos | POST | Cadastra um novo produto |
| /produtos/{id} | PUT | Atualiza um produto existente |
| /produtos/{id} | DELETE | Exclui um produto existente |
| /produtos/categoria/{categoria} | GET | Retorna todos os produtos de uma categoria específica |
| /clientes | GET | Retorna todos os clientes |
| /clientes/{id} | GET | Retorna um cliente específico |
| /clientes | POST | Cadastra um novo cliente |
| /clientes/{id} | PUT | Atualiza um cliente existente |
| /clientes/{id} | DELETE | Exclui um cliente existente |
| /pedidos | GET | Retorna todos os pedidos |
| /pedidos/{id} | GET | Retorna um pedido específico |
| /pedidos | POST | Cadastra um novo pedido |
| /pedidos/{id} | PUT | Atualiza um pedido existente |
| /pedidos/{id} | DELETE | Exclui um pedido existente |
| /pedidos/cliente/{id} | GET | Retorna todos os pedidos de um cliente específico |
| /pedidos/{id}/adicionar-produto/{produtoId} | PUT | Adiciona um produto a um pedido |
| /pedidos/{id}/remover-produto/{produtoId} | PUT | Remove um produto de um pedido |

## Documentação da API

A documentação da API está disponível no endpoint `/swagger-ui.html`.

## Autor

Este projeto foi desenvolvido por [seu nome aqui] e tem como objetivo servir como exemplo de aplicação backend utilizando Java e SpringBoot.
