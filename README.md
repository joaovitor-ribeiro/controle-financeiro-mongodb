
# Controle financeiro 

Projeto de controle financeiro pessoal.

## Tecnologia

Java 

Spring boot 

MongoDB

Angular

Cypress

Selenium

## Pré requistos 

Conter o Angular CLI. Obter executando o comando

```bash
  npm i -g @angular/cli
```
Conter o Node.js, na versão V14.21.3. Obter o Node.js no site: https://nodejs.org/en

Conter o MongoDB instalado. Obter o MongoDB no site: https://www.mongodb.com/

Conter uma IDE de sua preferência.

Conter o Lombok de acordo com sua IDE. Obeter através do site: https://projectlombok.org/

## Instalação

Clonar o projeto

```bash
  git clone https://github.com/joaovitor-ribeiro/controle-financeiro-mongodb.git
```

Abrir o cmd e navegar até a pasta controle-financeiro-front, executar o comando 

```bash
    npm install
```

Importar o controle-financeiro-back como projeto maven na IDE de sua preferência.

Executar o maven update.

## Rodando os testes

Para rodar os testes unitários do front, rode o seguinte comando

```bash
  npm run test
```

Para rodar o teste de cypress no front, rode o seguinte comando

```bash
  npm run cy
```

Para rodar os testes E2E do selenium, execute a classe CucumberRunner.java com o junit.

Para rodar os testes unitários do back, execute o package tdd com junit.

Para rodar os testes de API do back, execute o package api com junit.
