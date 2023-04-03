
Cypress.Commands.add('login', () => {
    cy.get('input[formcontrolname="email"]').type('usuario@gmail.com');
    cy.get('input-field[formcontrolname="senha"]').type('123456', {log: false});
    cy.get('button[id="botaoEntrar"]').click();
});

Cypress.Commands.add('addCartao', (nome, bandeira, numero, limite) => {
  if (nome && bandeira && numero && limite) {
    cy.get('input-field[formcontrolname="nome"]').type(nome );
    cy.get('mat-select[formcontrolname="bandeira"]').type(bandeira);
    cy.contains('span', bandeira).click();
    cy.get('input[formcontrolname="numero"]').type(numero);
    cy.get('number-field[formcontrolname="limite"]').type(limite);
  }
  cy.get('button[type="submit"]').click();
});

Cypress.Commands.add('addCategoria', (nome, tipo) => {
  if (nome && tipo) {
    cy.get('input-field[formcontrolname="nome"]').type(nome);
    cy.get('mat-select[formcontrolname="tipo"]').type(tipo);
    cy.contains('span', tipo === 'G' ? 'Ganho' : 'Despesa').click();
  }
  cy.get('button[type="submit"]').click();
});

Cypress.Commands.add('addDespesa', (cartao, descricao, categoria, valor, data) => {
  if (cartao && descricao && categoria && valor && data) {
    cy.get('mat-select[id="cartao"]').type(cartao);
    cy.contains('span', cartao).click();
    cy.get('input-field[formcontrolname="descricao"]').type(descricao);
    cy.get('mat-select[formcontrolname="categoria"]').type(categoria);
    cy.contains('span', categoria).click();
    cy.get('number-field[formcontrolname="valor"]').type(valor);
    cy.get('input[formcontrolname="data"]').type(data);
  }
  cy.get('button[type="submit"]').click();
});

Cypress.Commands.add('addGanho', (descricao, categoria, valor, data) => {
  if (descricao && categoria && valor && data) {
    cy.get('input-field[formcontrolname="descricao"]').type(descricao);
    cy.get('mat-select[formcontrolname="categoria"]').type(categoria);
    cy.contains('span', categoria).click();
    cy.get('number-field[formcontrolname="valor"]').type(valor);
    cy.get('input[formcontrolname="data"]').type(data);
  }
  cy.get('button[type="submit"]').click();
});
