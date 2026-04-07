# Wallet (CLI)

O wallet cli é uma aplicação de linha de comando onde é possível registrar transações, seja ela de entrada ou saída, registrar categorias de transações, como, por exemplo: viagem, cinema, lazer. Também é possível criar metas de valores alcançáveis para o usuário, permitindo um controle maior sobre alguns gastos e possibilidade de alerta.


## 1. Principais Funcionalidades

### 1.1. Transações

#### 1.1.1. Resumo

Neste módulo do sistema conseguimos registrar diversas transações tanto de entrada quanto de saída e isso vai alterando o saldo do usuário e o progresso das metas registradas. As transações possuem data e hora definidas pelo usuário, caso não inserida pegará a data e hora atual, existem também a opção de parcelas de cada transação, onde você pode indicar a parcela daquela transação específica.

Exemplo de criação de uma transação:
```bash
./gradlew run --args="transaction add --type INPUT --amount 1.20 --dateTime '2005-07-14 10:00:30' --category teste"
```

#### 1.1.2. Estruturas GOF

Para executar os comandos foi utilizado o conceito do design pattern **Command**, onde cada subCommand de transaction é um comando que implementamos onde cada comando executa uma tarefa em específico.

Na estrutura de execução de cada comando é utilizado o **Chain of Responsibility** como um middleware para passar as entradas do usuário em fluxos de validações, assim como, também o comando que será executado e possíveis erros capturados no fluxo.

#### 1.1.3. Estruturas GRASP

Utilizamos o conceito de **Controller** em nossos services passando requisições que são geradas e executadas nos comandos. Ainda em services utilizamos interfaces para respeitar o princípio de **Low Coupling**, reduzindo dependência de classes concretas.

---

### 1.2. Categorias

#### 1.2.1. Resumo

Neste módulo é possível gerenciar categorias que são associadas às transações, permitindo uma organização melhor dos gastos e entradas do usuário. Exemplos de categorias: viagem, cinema, lazer, alimentação.

Exemplos de uso:
```bash
./gradlew run --args="category add --title 'Alimentação' --description 'Gastos com comida'"
./gradlew run --args="category listAll"
./gradlew run --args="category listByName --name 'Alimentação'"
./gradlew run --args="category update --oldTitle 'Alimentação' --newTitle 'Comida'"
./gradlew run --args="category rm --name 'Comida'"
```

#### 1.2.2. Estruturas GOF

Assim como nas transações, cada subCommand de category implementa o padrão **Command**, executando uma tarefa específica como criar, listar, atualizar ou remover uma categoria.

#### 1.2.3. Estruturas GRASP

O **CategoryService** atua como **Controller**, centralizando a lógica de negócio do módulo. A dependência do repositório é feita via interface **ICategoryRepository**, respeitando o princípio de **Low Coupling**.

---

### 1.3. Metas

#### 1.3.1. Resumo

Neste módulo é possível criar e gerenciar metas financeiras, permitindo ao usuário definir um valor alvo a ser alcançado. Cada meta possui um título, descrição opcional, valor alvo e valor atual, sendo marcada automaticamente como concluída quando o valor atual atinge o valor alvo. O progresso das metas é atualizado automaticamente a cada transação de saída registrada no sistema.

Exemplos de uso:
```bash
./gradlew run --args="goal add --titulo 'Comprar notebook' --valorAlvo 3000"
./gradlew run --args="goal add --titulo 'Viagem' --descricao 'Viagem de férias' --valorAlvo 5000"
./gradlew run --args="goal listAll"
```

#### 1.3.2. Estruturas GOF

Cada subCommand de goal implementa o padrão **Command**, onde `AddMonthlyGoal` e `ListAllMonthlyGoal` executam tarefas específicas de criação e listagem de metas. A validação dos argumentos de entrada é centralizada no `MonthlyGoalValidator`.

#### 1.3.3. Estruturas GRASP

O **GoalService** atua como **Controller**, centralizando a lógica de negócio das metas. A dependência do repositório é feita via interface **IGoalRepository**, respeitando o princípio de **Low Coupling** e facilitando a substituição da implementação sem impacto nas camadas superiores.