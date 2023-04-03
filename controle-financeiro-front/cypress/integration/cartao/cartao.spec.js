describe('Formulário do cartão', () => {

    beforeEach(() => {
      cy.visit('http://localhost:4200/entrar')
      cy.login();
      cy.contains('span', 'Usuário').should('be.visible');
    });

    it('Cadastro de cartões', () => {
      const cartoes = require('../../fixtures/cartao/cadastroCartao.json');
      cartoes.forEach(cartao => {
        cy.visit('http://localhost:4200/cartao/inserir')
        cy.addCartao(cartao.nome, cartao.bandeira, cartao.numero, cartao.limite);
        cy.contains('div', 'Cartão cadastrado com sucesso').should('be.visible');
      });
    });

    it('Mensagens de cartões inválidos', () => {
      const cartoes = require('../../fixtures/cartao/validacoesCartao.json');
      cartoes.forEach(cartao => {
        cy.visit('http://localhost:4200/cartao/inserir')
        cy.addCartao(cartao.nome, cartao.bandeira, cartao.numero, cartao.limite);
        cy.contains('mat-error', cartao.mensagem).should('be.visible');
      });
    });

})
