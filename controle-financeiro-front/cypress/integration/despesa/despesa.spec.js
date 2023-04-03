describe('Formulário de despesa', () => {

  beforeEach(() => {
    cy.visit('http://localhost:4200/entrar')
    cy.login();
    cy.contains('span', 'Usuário').should('be.visible');
  });

  it('Cadastro de despesas', () => {
    const categorias = require('../../fixtures/categoria/cadastroCategoria.json');
    categorias.forEach(() => {
      cy.visit('http://localhost:4200/despesa/inserir')
      cy.addDespesa('5173 8634 0599 6183', 'Conta de Luz', 'Faculdade', '1500.00', '06/14/2022');
      cy.contains('div', 'Despesa cadastrada com sucesso').should('be.visible');
    });
  });

  it('Mensagens de despesas inválidas', () => {
    const despesas = require('../../fixtures/despesa/validacoesDespesa.json');
    despesas.forEach(despesas => {
      cy.visit('http://localhost:4200/despesa/inserir')
      cy.addDespesa(despesas.cartao, despesas.descricao, despesas.categoria, despesas.valor, despesas.data);
      cy.contains('mat-error', despesas.mensagem).should('be.visible');
    });
  });

})
