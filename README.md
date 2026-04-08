# Wallet CLI

O **Wallet CLI** é uma aplicação de linha de comando desenvolvida em Java que permite ao usuário gerenciar suas finanças pessoais diretamente pelo terminal.

Com o sistema é possível:

- Registrar **transações financeiras** (entradas e saídas)
- Organizar gastos através de **categorias**
- Criar e acompanhar **metas financeiras**
- Monitorar o impacto das transações no **saldo e progresso das metas**

O projeto foi desenvolvido com foco em **organização de código, separação de responsabilidades e uso de padrões de projeto**, facilitando manutenção e evolução do sistema.

---

# Principais Funcionalidades

## Transações

O módulo de **transações** é responsável por registrar movimentações financeiras do usuário.

Uma transação pode representar:

- uma **entrada de dinheiro (INPUT)**
- uma **saída de dinheiro (OUTPUT)**

Cada transação possui:

- valor
- tipo
- categoria
- data e hora
- número de parcela (opcional)

Caso a data e hora não sejam informadas, o sistema utiliza automaticamente **a data e hora atual**.

As transações influenciam diretamente:

- o **saldo do usuário**
- o **progresso das metas financeiras**

### Exemplo de criação de transação

```bash
./gradlew run --args="transaction add --type INPUT --amount 1.20 --dateTime '2005-07-14 10:00:30' --category teste"
```

---

## Categorias

O módulo de **categorias** permite organizar as transações em grupos, facilitando o controle e análise dos gastos.

Exemplos de categorias:

- alimentação
- viagem
- cinema
- lazer

Cada categoria possui:

- título
- descrição (opcional)

### Exemplos de uso

```bash
./gradlew run --args="category add --title 'Alimentação' --description 'Gastos com comida'"
./gradlew run --args="category listAll"
./gradlew run --args="category listByName --name 'Alimentação'"
./gradlew run --args="category update --oldTitle 'Alimentação' --newTitle 'Comida'"
./gradlew run --args="category rm --name 'Comida'"
```

---

## Metas

O módulo de **metas financeiras** permite que o usuário defina objetivos de economia.

Cada meta possui:

- título
- descrição (opcional)
- valor alvo
- valor atual

Quando o valor atual atinge o valor alvo, a meta é automaticamente marcada como **concluída**.

O progresso das metas pode ser atualizado automaticamente conforme novas transações são registradas.

### Exemplos de uso

```bash
./gradlew run --args="goal add --titulo 'Comprar notebook' --valorAlvo 3000"
./gradlew run --args="goal add --titulo 'Viagem' --descricao 'Viagem de férias' --valorAlvo 5000"
./gradlew run --args="goal listAll"
```

---

# Arquitetura e Padrões de Projeto

O projeto foi estruturado para manter **separação clara entre responsabilidades**, facilitando manutenção e evolução do sistema.

A aplicação é organizada em camadas principais:

- **CLI (Presentation Layer)**  
  Responsável por interpretar os comandos digitados pelo usuário.

- **Application / Services**  
  Contém a lógica de negócio do sistema.

- **Domain / Repository**  
  Responsável pelo acesso e manipulação dos dados.

---

## Design Patterns Utilizados

### Command Pattern (GOF)

Cada comando da CLI é implementado como um **Command**.

Isso significa que cada ação do sistema é representada por uma classe responsável por executar uma única tarefa.

Exemplos:

- `AddTransaction`
- `RemoveTransaction`
- `ListAllTransactions`
- `AddCategory`
- `DeleteMonthlyGoal`

Essa abordagem facilita:

- adicionar novos comandos
- manter o código organizado
- separar responsabilidades

---

### Chain of Responsibility

A validação dos argumentos fornecidos pelo usuário segue um fluxo de **validações encadeadas**.

Cada regra de validação é executada em sequência antes da execução do comando.

Isso permite:

- reutilizar validações
- separar regras de validação da lógica principal
- manter o código mais modular

---

# Princípios GRASP Aplicados

## Controller

Os **Services** atuam como controladores da lógica de negócio.

Eles recebem requisições vindas da camada de comandos e coordenam a execução das regras da aplicação.

Exemplos:

- `TransactionService`
- `CategoryService`
- `GoalService`

---

## Low Coupling

O projeto utiliza **interfaces para os repositórios**, reduzindo o acoplamento entre as camadas.

Exemplos:

- `ICategoryRepository`
- `IGoalRepository`
- `ITransactionRepository`

Isso permite:

- trocar implementações facilmente
- melhorar testabilidade
- manter o sistema mais flexível

---

# Estrutura do Projeto

Exemplo simplificado da organização do projeto:

```
src
 ├─ application
 │   └─ services
 │
 ├─ domain
 │   ├─ entities
 │   └─ repositories
 │
 ├─ infrastructure
 │   └─ repositories
 │
 └─ presentation
     └─ cli
         ├─ commands
         ├─ validation
         └─ core
```

Essa separação ajuda a manter cada parte do sistema responsável por uma tarefa específica.

---

# Tecnologias Utilizadas

- **Java**
- **Gradle**
- **CLI Architecture**
- **Design Patterns (GOF)**
- **Princípios GRASP**