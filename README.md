# 💰 FinanceFlow

> API REST para controle financeiro pessoal, desenvolvida em **Java + Spring Boot**, com autenticação JWT, regras de negócio, persistência em banco de dados e **testes unitários**.

---

## 🚀 Sobre o projeto

O **FinanceFlow** é uma API backend que permite:

* Criar e gerenciar usuários
* Criar contas
* Registrar transações de **entrada (INCOME)** e **saída (EXPENSE)**
* Atualizar automaticamente o saldo da conta
* Listar transações com filtros, ordenação e paginação
* Proteger rotas com autenticação **JWT**

O projeto foi desenvolvido com foco em:

* ✅ Boas práticas de arquitetura em camadas
* ✅ Separação de responsabilidades
* ✅ Regras de negócio no Service
* ✅ Testes unitários com JUnit e Mockito
* ✅ Padrões usados em sistemas reais

---

## 🧱 Arquitetura

O projeto segue a arquitetura em camadas:

* `controller` → Camada de entrada (REST API)
* `service` → Regras de negócio
* `repository` → Acesso ao banco de dados
* `dto` → Objetos de transferência de dados
* `model` → Entidades
* `security` → Autenticação e autorização (JWT)

---

## 🛠️ Tecnologias utilizadas

* Java 17
* Spring Boot
* Spring Security (JWT)
* Spring Data JPA
* Hibernate
* PostgreSQL (ou H2 para testes)
* Maven
* JUnit 5
* Mockito

---

## 🔐 Autenticação

A aplicação utiliza **JWT (JSON Web Token)** para autenticação.

Fluxo:

1. Usuário faz login
2. Recebe um token JWT
3. Envia o token no header `Authorization` nas próximas requisições

---

## 🧪 Testes

O projeto possui **testes unitários na camada de Service**, usando:

* JUnit 5
* Mockito

Exemplos de cenários testados:

* ✅ Criação de transação de entrada (INCOME) e atualização de saldo
* ✅ Criação de transação de saída (EXPENSE) e atualização de saldo
* ✅ Erro ao tentar criar transação para conta inexistente

---

## 📦 Como rodar o projeto

### Pré-requisitos

* Java 17+
* Maven
* PostgreSQL (ou usar H2 em memória)

### Passos

```bash
# Clonar o repositório
git clone https://github.com/prigarciaa/financeflow.git

# Entrar na pasta do projeto
cd financeflow

# Rodar a aplicação
mvn spring-boot:run
```

A aplicação irá subir em:

```
http://localhost:8080
```

---

## 📌 Exemplos de endpoints

* `POST /auth/register` → Registrar usuário
* `POST /auth/login` → Login
* `POST /accounts` → Criar conta
* `POST /transactions` → Criar transação
* `GET /transactions` → Listar transações

---

## 🎯 Objetivo do projeto

Projeto que simula um backend real de sistema financeiro para demonstrar:

* Conhecimento em backend Java
* Uso do Spring Boot em um projeto real
* Implementação de autenticação JWT
* Criação de regras de negócio
* Escrita de testes unitários
* Organização de código em arquitetura profissional

---

## 👩🏻‍💻 Desenvolvido por:

**Priscila Silva Garcia**,
Estudante de Análise e Desenvolvimento de Sistemas com
Foco em Backend Java

---

## ⭐ Se este projeto te ajudou

Deixe uma estrela no repositório! ⭐😄
