# Wallet (CLI)

O wallet cli é um aplicação de linha de comando onde é possivel registra transações, seja ela de entrada ou saida, registrar categorias de transações, como, por exemplo: viagem, cinema, lazer. Também é possivel criar metas de valores alcançaveis para o usuário, permitindo um controle maior sobre alguns gasto e possibilidade de alerta.


## 1. Principais Funcionalidades

### 1.1. Transações

#### 1.1.1. Resumo

Neste modulo do sistema conseguimos registrar diversas transações tanto de entrada quanto de saida e isso vai alterando o saldo do usuario e o progresso das metas registradas. As transações possuem data e hora definidas pelo usuario, caso não inserida pegará a data e hora atual, existem também a opção de pacerlas de cada transcação, onde você pode indicar a parcela daquela transação especifica.

Exemplo de criação de uma transação:

```bash
./gradlew run --args="transaction add --type INPUT --amount 1.20 --dateTime '2005-07-14 10:00:30' --category teste"
```

#### 1.1.2. Estruturas GOF

Para executar os comandos foi utilizado o conceito do design pattern **Command**, onde cada subCommand  de transaction é um comand que implementamos onde cada comando executa uma tarefa em especifico.

Na estrutura de execução de cada comando é utilizado o **C****hain of responsability** como  um middleware para passar as entradas do usuário em fluxos de validações, assim como, também o comando que será executado e possiveis erros capturados no fluxo.

#### 1.1.3. Estruturas GRASP

Utilizamos o conceito de **Controller** em nossos services passando requisições que são geradas e executadas nos comandos. Ainda em services utilizamos interfaces para respeitar o principio de **Low Coupling**, reduzindo dependência de classes concretas.
