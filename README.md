# TODO List

Aplicação de lista de tarefas desenvolvida em **Java**, com o objetivo de praticar conceitos de programação orientada a objetos, organização em camadas, manipulação de arquivos e gerenciamento de tarefas.

---

## Sobre o projeto

O **TODO List** é uma aplicação de linha de comando (CLI) que permite cadastrar, visualizar, alterar, remover e organizar tarefas. As tarefas possuem informações como nome, descrição, data de término, prioridade, categoria e status. O projeto utiliza uma arquitetura dividida em **Model, Repository, Service e UI**, separando as responsabilidades de cada parte da aplicação.

---

## ✨ Funcionalidades

### Gerenciamento de tarefas

- Cadastrar tarefas;
- Listar todas as tarefas;
- Alterar tarefas;
- Remover tarefas;
- Consultar tarefas por categoria;
- Consultar tarefas por prioridade;
- Consultar tarefas por status;

### Status das tarefas

As tarefas podem possuir os seguintes status:

- `TODO` — tarefa ainda não iniciada
- `DOING` — tarefa em andamento
- `DONE` — tarefa concluída

Também é possível visualizar a quantidade de tarefas em cada status.

### Prioridade

Cada tarefa possui uma prioridade de `1` a `5`. As tarefas são reorganizadas de acordo com sua prioridade.

### Data de término

As tarefas possuem uma data de término no formato: ```dd/MM/yyyy```. A aplicação valida a data informada e não permite cadastrar uma data anterior ao dia atual.

### Categorias

As tarefas possuem categorias para facilitar sua organização e filtragem.

### Reordenação automática

A lista de tarefas é reorganizada automaticamente de acordo com o status e a prioridade. As tarefas ativas (TODO e DOING) são organizadas por prioridade. As tarefas DONE ficam no final da lista e são organizadas pela data de término.

### Persistência em arquivo

As tarefas são armazenadas em um arquivo: ```tasks.txt```. Ao iniciar a aplicação, as tarefas existentes são carregadas automaticamente. Ao encerrar o programa, as tarefas são salvas no arquivo.

## Tecnologias utilizadas

- Java 21
- Gradle
- Git
- GitHub
- Java NIO para manipulação de arquivos
- ArrayList para armazenamento das tarefas em memória
- Principais recursos do Java utilizados
- Programação Orientada a Objetos
- Classes e objetos
- Encapsulamento
- ArrayList
- List
- Scanner
- LocalDate
- DateTimeFormatter
- Tratamento de exceções com try/catch
- Manipulação de arquivos com Files
- Path
- Paths


## Arquitetura

O projeto utiliza uma separação em camadas:

- UI: responsável pela interação com o usuário através do terminal;
- Service: responsável pelas regras de negócio da aplicação, como gerenciamento das tarefas, filtros, contagem por status, alteração de status e reordenação das tarefas;
- Repository: responsável pelo armazenamento das tarefas e pela persistência em arquivo;
- Model: representa os dados de uma tarefa.

## Estrutura do projeto

```
TODO-list/
│
├── src/
│   └── main/
│       └── java/
│           └── todolist/
│               │
│               ├── Main.java
│               │
│               ├── model/
│               │   └── Task.java
│               │
│               ├── repository/
│               │   └── TaskRepository.java
│               │
│               ├── service/
│               │   └── TaskService.java
│               │
│               └── ui/
│                   └── Menu.java
│
├── build.gradle
├── settings.gradle
├── gradlew
├── gradlew.bat
└── README.md
```

## Arquivos principais

* Main.java: ponto de entrada da aplicação. Responsável por inicializar TaskRepository, TaskService, Menu e iniciar o sistema;
* Task.java: classe responsável por representar uma tarefa. Uma tarefa possui nome, descrição, data de término, prioridade, categoria e status;
* TaskRepository.java: responsável pelo armazenamento das tarefas. Principais responsabilidades adicionar tarefas, remover tarefas, listar tarefas, carregar tarefas do arquivo, salvar tarefas no arquivo;
* TaskService.java: contém as principais regras de negócio da aplicação. Entre suas responsabilidades estão: cadastro de tarefas, remoção, filtros, alteração de status, contagem de tarefas por status, reordenação das tarefas, comunicação com o Repository;
* Menu.java: Responsável pela interface de linha de comando. Apresenta as opções para o usuário e recebe as entradas através do Scanner.

## ```tasks.txt```

Arquivo utilizado para persistência das tarefas. Cada tarefa é armazenada em uma linha, utilizando | como separador:

```
nome|descrição|data|prioridade|categoria|status
```

## Como executar

### Pré-requisitos

Este tutorial foi feito para Ubuntu 24.04. É necessário possuir:

- Java 21;
- Git;
- Gradle.

Depois, clone o projeto:

```bash
git clone https://github.com/rafasavaris/TODO-list.git
```

Entre na pasta:

```bash
cd TODO-list
```

Executar utilizando o Gradle Wrapper:

```bash
./gradlew run
```
---