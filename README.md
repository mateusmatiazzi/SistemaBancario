# Desafio
Desafio para avaliação de candidato

### Instruções para o desafio
- O candidato deverá criar um fork no git e criar uma branch com o nome e sobrenome do canditado(ex: desafio_Jose_Silva) para o desenvolvimento da atividade;
- O projeto deverá ser divido em dois pacotes, um do front-end e um do back-end;
- O front deverá ser feito em Vue.js e o back deverá ser feito com spring boot;
- Fica a critério do candidato a implementar testes unitários;
- Histórico de commit será levado em consideração para acompanhamento da evolução do desafio, onde iremos avaliar o passo a passo desde a criação do projeto, criação dos pacotes back e front, criação dos serviços, criação das funcionalidades, etc. Portanto sugerimos que a cada passo realizado seja feito um commit com um comentário explicativo do que foi realizado, exemplo:
    - Commit 1- Criação da estrutura inicial do projeto;
    - Commit 2- Criação do pacote back-end;
    - Commit 3- Criação do pacote front-end;
    - Commit 4- Criação das entidades de persistência;
    - Commit x- Criação da funcionalidade Criar Licitação;
- Ao final do desafio, criar um documento explicando como configurar e subir a aplicação;


### Desafio
- Desenvolver uma aplicação web responsável por simular ações básicas de uma conta bancaria.
- Criar conta
    - Deve conter os atributos
        - número conta
        - responsável
        - saldo

- Motiventações
    - Realizar deposito
    - Realizar saque
    - Realizar transferência
	- Incluir

- Exibir extrato por (Dia/Mês/Intervalo)
    - deve detalhar as movimentações


# Dependencias
    -H2
    -JPA
    -Devtools

### Como inicializar a aplicação
    - back-end: Ir até o diretorio `back-end/src/main/java/com/desafio/backend` e executar a aplicação spring `BackEndApplication.java`

    - fornt-end: Ir até o diretorio `front-end` e executar o comando ```shell npm run serve```
    - A aplicação ficará disponível em `localhost:8081`

### Como utilizar
    -O pimeiro passo será a criação de contas bancárias, para isso existirá um campo na aplicação onde poderá ser criado uma nova conta. Na criação da conta o poderá ser definido o saldo inicial de cada conta, essa feature foi feita somente para facilitar a manipulação das contas, em uma aplicação real esse campo não existiria e todas as contas iriam começar com saldo zero.

    -Após criada uma conta ela irá aparece logo abaixo mostrando todos seus dados e quais operações pode realizar.

    -Para realizar uma operação basta clicar no botão de operação. Para as operações de deposito/saque/tranferência irão aparecer novos campos para a definição do valor a ser operado, e no caso de transferência irá aparecer mais um novo campo onde irá pedir a conta de destino da transferência. Após o preenchimento dos campos necessários você deverá clicar no botão da operação a ser feita novamente para sua conclusão.

    -Ao clicar no botão de extrato serão mostradas todas as operações que a conta realizou desde sua criação, para sair do estado de exibição do extrato, basta clicar novamente no botão extrato.

    -Ao deletar a conta um alerta irá aparecer confirmando a exclusão da mesma, e então ela será excluida do banco de dados.