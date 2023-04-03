describe('Formulário da categoria', () => {

  beforeEach(() => {
    cy.visit('http://localhost:4200/entrar')
    cy.login();
    cy.contains('span', 'Usuário').should('be.visible');
  });

  it('Cadastro de categorias', () => {
    const categorias = require('../../fixtures/categoria/cadastroCategoria.json');
    categorias.forEach(categoria => {
      cy.visit('http://localhost:4200/categoria/inserir')
      cy.addCategoria(categoria.nome, categoria.tipo);
      cy.contains('div', 'Categoria cadastrada com sucesso').should('be.visible');
    });
  });

  it('Mensagens de categorias inválidas', () => {
    const categorias = require('../../fixtures/categoria/validacoesCategoria.json');
    categorias.forEach(categoria => {
      cy.visit('http://localhost:4200/categoria/inserir')
      cy.addCategoria(categoria.nome, categoria.tipo);
      cy.contains('mat-error', categoria.mensagem).should('be.visible');
    });
  });

})
