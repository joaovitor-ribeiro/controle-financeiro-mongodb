describe('Formulário de ganho', () => {

  beforeEach(() => {
    cy.visit('http://localhost:4200/entrar')
    cy.login();
    cy.contains('span', 'Usuário').should('be.visible');
  });

  it('Cadastro de ganhos', () => {
    const categorias = require('../../fixtures/categoria/cadastroCategoria.json');
    categorias.forEach(() => {
      cy.visit('http://localhost:4200/ganho/inserir')
      cy.addGanho('Aula particular', 'Aula', '1500', '06/14/2022');
      cy.contains('div', 'Ganho cadastrado com sucesso').should('be.visible');
    });
  });

  it('Mensagens de ganhos inválidas', () => {
    const ganhos = require('../../fixtures/ganho/validacoesGanho.json');
    ganhos.forEach(ganhos => {
      cy.visit('http://localhost:4200/ganho/inserir')
      cy.addGanho(ganhos.descricao, ganhos.categoria, ganhos.valor, ganhos.data);
      cy.contains('mat-error', ganhos.mensagem).should('be.visible');
    });
  });

})
