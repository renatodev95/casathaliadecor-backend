# API para gerenciamento de loja de móveis e decorações

Este é um projeto de backend em Java com SpringBoot para uma API que permite o gerenciamento de uma loja de móveis e decorações. A API permite o cadastro, consulta, atualização e exclusão de produtos, bem como o gerenciamento de pedidos e clientes.

## Funcionalidades

- Cadastro, login, consulta, atualização e exclusão de usuários;
- Cadastro, consulta, atualização e exclusão de produtos;
- Listagem de todos os usuários;
- Listagem de produtos por categoria;
- Cadastro, consulta e atualização de categorias;
- Listagem de todas as categorias;

## Tecnologias utilizadas

- Java 17
- SpringBoot 2.6.3
- MySQL
- Hibernate
- JWT
- Maven

## Como executar o projeto

Para executar o projeto, siga os passos abaixo:

1. Clone o repositório em sua máquina local;
2. Abra o projeto em sua IDE de preferência;
3. Configure as informações de conexão com o banco de dados no arquivo `application.properties`;
4. Execute o comando `mvn clean install` para instalar as dependências;
5. Execute a classe `ApiApplication` para iniciar a aplicação.

## Endpoints disponíveis

| Endpoint        | Método HTTP | Descrição                    |
|-----------------|-------------|------------------------------|
| /user/signup    | POST        | Cadastro de usuário          |
| /user/login     | POST        | Login de usuário             |
| /user/get       | GET         | Busca usuários               |
| /user/update    | POST        | Atualiza usuários            |
| /user/changePassword | POST        | Atualiza senha de usuário    |
| /user/forgotPassword | POST        | Função esqueceu senha        |
| /category/add   | POST        | Adicionar nova categoria     |
| /category/get   | POST        | Buscar categorias            |
| /category/update | POST        | Alterar categoria            |
| /product/add    | POST        | Adicionar produto            |
| /product/get    | GET         | Buscar todos os produtos     |
| /product/update | POST        | Alterar categoria            |
| /product/delete/{id}   | POST        | Remover produto por ID       |
| /product/updateStatus  | POST        | Atualizar Produto            |
| /product/getByCategory/{id}    | GET         | Buscar produtos por categoria |
| /product/getById/{id}    | GET         | Buscar produto por ID        |

## Finalidade

Este projeto foi feito exclusivamente para fins acadêmicos e tem como objetivo servir como exemplo de aplicação backend utilizando Java e SpringBoot.
