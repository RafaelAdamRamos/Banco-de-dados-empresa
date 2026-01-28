# Banco de Dados Empresa

> 🔧 **Projeto aprimorável**  
> Este projeto apresenta uma estrutura de banco de dados relacional para uma empresa e pode ser expandido ou aprimorado conforme novos requisitos, estudos ou integrações com aplicações.

## Descrição

Este repositório contém um exemplo de **modelo de banco de dados relacional** para uma empresa, com tabelas como `FUNCIONARIO`, `DEPARTAMENTO`, `PROJETO`, `DEPENDENTE`, entre outras — estruturado para permitir operações básicas e servir como base para aplicações que façam CRUD.  
Ele pode ser usado como referência de estrutura de dados ou inicialização de bases de teste para projetos em Java, SQL ou outras linguagens que se conectem a banco de dados.

## Estrutura do Banco de Dados

As principais tabelas deste banco de dados incluem:

- **FUNCIONARIO** – registro de funcionários com atributos como nome, CPF, salário e relacionamentos com projetos e supervisores.
- **DEPARTAMENTO** – departamentos da empresa com gerente e datas de início.
- **LOCALIZACAO_DEP** – localização de departamentos.
- **PROJETO** – projetos com nome, número e departamento relacionado.
- **TRABALHA_EM** – tabela associativa entre funcionários e projetos.
- **DEPENDENTE** – dependentes de funcionários.

*(As tabelas são definidas no arquivo `banco.sql`.)*

## Arquivos

- `banco.sql` – contém os comandos SQL para criação das tabelas e estrutura do banco.

## Tecnologias Utilizadas

Este projeto envolve principalmente:
- **SQL** para modelagem de banco de dados relacional
- Pode ser integrado com aplicações em **Java** ou outras linguagens que se conectem a banco de dados usando JDBC ou bibliotecas similares

## Como usar

1. **Importar o script SQL**  
   - Abra seu SGBD (MySQL, PostgreSQL etc.)  
   - Crie um banco de dados (por exemplo `empresa`)  
   - Execute o conteúdo do arquivo `banco.sql` para criar as tabelas  

2. **Conectar com sua aplicação**  
   - Use JDBC ou ORM (por exemplo Hibernate) na sua aplicação Java  
   - Ajuste a configuração de conexão (URL, usuário, senha) conforme necessário

## Requisitos

- Servidor de banco de dados relacional (MySQL, PostgreSQL ou outro compatível)
- Ferramenta para executar scripts SQL (Workbench, DBeaver, PgAdmin etc.)
- Se for conectar com código Java: JDK instalado e driver JDBC configurado

## Observações

- Projeto voltado a **estudo de modelagem de banco de dados** e exemplos de estrutura relacional  
- Pode ser expandido com dados de exemplo, relacionamentos adicionais, views, índices, etc.
- Está em constante evolução conforme novos requisitos ou aplicações são integrados

