# Oriente - Sistema de Gestão de Projetos Kanban

![Licença](https://img.shields.io/badge/license-MIT-blue.svg)
![Java](https://img.shields.io/badge/Java-17%2B-orange.svg)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-green.svg)
![React](https://img.shields.io/badge/React-18%2B-blue.svg)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-15-lightgrey.svg)

Uma aplicação web Full-Stack para gerenciamento de projetos, desenvolvida como Trabalho de Conclusão de Curso. O "Oriente" visa equilibrar a simplicidade de um quadro Kanban visual com funcionalidades profissionais essenciais para equipes pequenas e médias.

---

## 📋 Tabela de Conteúdos

1.  [Sobre o Projeto](#sobre-o-projeto)
2.  [🚀 Principais Funcionalidades](#-principais-funcionalidades)
3.  [🛠️ Tecnologias Utilizadas](#️-tecnologias-utilizadas)
4.  [🏁 Como Começar](#-como-começar)
    * [Pré-requisitos](#pré-requisitos)
    * [Instalação do Back-end](#instalação-do-back-end)
    * [Instalação do Front-end](#instalação-do-front-end)
5.  [📄 Documentação da API](#-documentação-da-api)
6.  [⚖️ Licença](#️-licença)
7.  [👨‍💻 Autor](#-autor)

---

## <a name="sobre-o-projeto"></a> Sobre o Projeto

O "Oriente" nasceu da necessidade de uma ferramenta de gestão que não fosse nem tão básica a ponto de limitar a equipe, nem tão complexa a ponto de exigir uma curva de aprendizado íngreme. Este projeto implementa um sistema Kanban completo, com autenticação, colaboração em tempo real e relatórios básicos de produtividade.

![Screenshot do Projeto](URL_DA_SUA_IMAGEM_AQUI)
*(Substitua a URL acima por um print da tela principal da sua aplicação)*

---

## <a name="-principais-funcionalidades"></a> 🚀 Principais Funcionalidades

* **🔐 Autenticação e Gestão de Usuários:** Sistema seguro de registro e login com Tokens JWT e controle de acesso baseado em papéis (RBAC).
* **✨ Gestão de Projetos:** Crie, edite e organize múltiplos projetos, convidando membros para colaborar.
* **🎯 Quadro Kanban Interativo:** Organize tarefas em colunas personalizáveis com uma interface intuitiva de arrastar e soltar (drag-and-drop).
* **💬 Chat em Tempo Real:** Comunique-se com os membros do projeto instantaneamente sem sair da aplicação, graças à tecnologia WebSocket.
* **📊 Relatórios de Produtividade:** Visualize dados sobre o andamento das tarefas para tomar decisões mais informadas.

---

## <a name="️-tecnologias-utilizadas"></a> 🛠️ Tecnologias Utilizadas

O projeto segue uma arquitetura de 3 camadas (Three-Tier Architecture).

### **Back-end (Servidor)**
* **Java 17+**
* **Spring Boot 3+**
    * Spring Web (API REST)
    * Spring Security (Autenticação/Autorização com JWT)
    * Spring Data JPA (Persistência de Dados com Hibernate)
    * Spring WebSocket (Comunicação em Tempo Real)
* **Maven** (Gerenciador de Dependências)

### **Front-end (Cliente)**
* **React.js 18+**
* **React Router** (Roteamento de Páginas)
* **Axios** (Cliente HTTP para comunicação com a API)
* **StompJS & SockJS** (Clientes para WebSocket)
* **Styled Components** (Estilização CSS-in-JS)

### **Banco de Dados**
* **PostgreSQL 15**

---

## <a name="-como-começar"></a> 🏁 Como Começar

Siga estas instruções para configurar e executar o projeto em sua máquina local.

### Pré-requisitos

* **Java Development Kit (JDK)** - Versão 17 ou superior
* **Maven** - Versão 3.8 ou superior
* **Node.js e npm** - Versão 18 ou superior
* **PostgreSQL** - Versão 15 ou superior (com um banco de dados criado para o projeto)
* Um cliente de API como [Insomnia](https://insomnia.rest/) ou [Postman](https://www.postman.com/) para testes.

### Instalação do Back-end

1.  Clone o repositório:
    ```bash
    git clone URL_DO_SEU_REPOSITORIO_GIT
    ```

2.  Navegue até o diretório do back-end:
    ```bash
    cd oriente/backend
    ```

3.  Configure o banco de dados no arquivo `src/main/resources/application.properties`:
    ```properties
    spring.datasource.url=jdbc:postgresql://localhost:5432/nome_do_seu_banco
    spring.datasource.username=seu_usuario_postgres
    spring.datasource.password=sua_senha_postgres
    spring.jpa.hibernate.ddl-auto=update
    spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect

    # Configuração do JWT (use um valor secreto forte)
    jwt.secret=SEU_SEGREDO_SUPER_SECRETO_AQUI
    ```

4.  Instale as dependências e execute o servidor:
    ```bash
    mvn spring-boot:run
    ```
    O servidor estará rodando em `http://localhost:8080`.

### Instalação do Front-end

1.  Em um novo terminal, navegue até o diretório do front-end:
    ```bash
    cd oriente/frontend
    ```

2.  Instale as dependências do projeto:
    ```bash
    npm install
    ```

3.  Execute a aplicação React:
    ```bash
    npm