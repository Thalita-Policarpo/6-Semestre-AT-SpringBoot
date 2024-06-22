# Assessement Spring Boot

Este documento descreve o projeto **AssessementSpringBoot**, que consiste em um aplicativo web utilizando Spring Boot para desenvolvimento de serviços REST, integrando dois bancos de dados diferentes: MongoDB e H2.

### Regras de Negócio

O projeto abrange as seguintes regras de negócio:

- **Funcionários e Departamentos**: Gestão de funcionários e departamentos da empresa.
    
- **Usuários e Segurança**: Autenticação de usuários com diferentes papéis (roles).
    

### Configurações da Aplicação

#### Configurações de Segurança

A segurança é configurada usando Spring Security com as seguintes características:

- Autenticação baseada em usuário e senha criptografada.
    
- Autorização baseada em roles (usuário comum e administrador).
    

#### Configuração de Bancos de Dados

- **H2 Database**: Configuração para ambiente de desenvolvimento com dados em memória.
    
- **MongoDB**: Configuração para ambiente de produção ou teste com banco de dados NoSQL.
    

**Dependências do Projeto**  
O projeto utiliza as seguintes dependências Maven:

- **Spring Boot 3.3.1**: Framework para criação de aplicações Java baseadas em Spring.
    
- **Spring Data JPA**: Facilita a implementação de camadas de acesso a dados com Hibernate.
    
- **Spring Data MongoDB**: Permite integração com bancos de dados MongoDB.
    
- **Spring Security**: Para autenticação e controle de acesso.
    
- **H2 Database**: Banco de dados em memória para ambiente de desenvolvimento.
    
- **MongoDB**: Banco de dados NoSQL para armazenamento de dados.
    
- **Lombok**: Biblioteca Java que simplifica a criação de classes Java.
    
- **JUnit 5 e Mockito**: Para testes unitários e de integração.
    

### Endpoints da API

A API REST fornece os seguintes endpoints:

#### Departamentos

- **GET /api/public/departamentos**: Retorna todos os departamentos.
    
- **GET /api/public/departamentos/{id}**: Retorna um departamento pelo ID.
    
- **POST /api/public/departamentos**: Cria um novo departamento.
    
- **PUT /api/public/departamentos/{id}**: Atualiza um departamento existente pelo ID.
    
- **DELETE /api/public/departamentos/{id}**: Deleta um departamento pelo ID.
    

#### Funcionários

- **GET /api/public/funcionarios**: Retorna todos os funcionários.
    
- **GET /api/public/funcionarios/{id}**: Retorna um funcionário pelo ID.
    
- **POST /api/public/funcionarios**: Cria um novo funcionário.
    
- **PUT /api/public/funcionarios/{id}**: Atualiza um funcionário existente pelo ID.
    
- **DELETE /api/public/funcionarios/{id}**: Deleta um funcionário pelo ID.
    

#### Usuários

- **GET /api/public/usuarios**: Retorna todos os usuários.
    
- **POST /api/public/usuarios**: Cria um novo usuário com papel (role).
    
- **PUT /api/public/usuarios/{id}**: Atualiza um usuário existente pelo ID.
    
- **DELETE /api/public/usuarios/{id}**: Deleta um usuário pelo ID.
    

#### Outros

- **GET /api/public/oimundo**: Exemplo de endpoint público.
    
- **GET /api/private/oimundo**: Exemplo de endpoint privado (requer autenticação).
