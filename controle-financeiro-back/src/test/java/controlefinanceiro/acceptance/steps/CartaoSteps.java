package controlefinanceiro.acceptance.steps;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import controlefinanceiro.acceptance.page.CartaoPage;
import io.cucumber.java.After;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;

public class CartaoSteps {
	
	CartaoPage cartaoPage = new CartaoPage();
	
	@After
	public void depois() {
		cartaoPage.sair();
	}
	
	@Dado("que estou logado na aplicação com o email {string} e a senha {string} para validar o cartao")
	public void estou_logado_na_aplicação_com_o_email_e_a_senha(String email, String senha) {
		cartaoPage.iniciarDriver();
		cartaoPage.realizarLogin(email, senha);
	}
	
	@Dado("que estou na página de cadastro de cartão")
	public void que_estou_na_página_de_cadastro_de_cartão() {
		cartaoPage.navegaParaCartaoListar();
		cartaoPage.clicoBotaoNovoCartao();
	}

	@Quando("preencho os campos nome {string} bandeira {string} numero {string} limite {string}")
	public void o_preencho_os_campos_nome_bandeira_numero_limite(String nome, String bandeira, String numero, String limite) {
		cartaoPage.preencherFormulario(nome, bandeira, numero, limite);
	}
	
	@Quando("aciono o botão salvar do cadastro de cartão")
	public void aciono_o_botao_salvar_do_cadastro_de_cartão() {
		cartaoPage.acionoBotaoSalvar();
	}
	
	@Entao("será exibido o alerta {string}")
	public void será_exibido_o_alerta(String mensagem) {
		cartaoPage.esperaAlert();
		assertTrue(cartaoPage.alertaDeSucesso(mensagem));
	}
	
	@Entao("o usuário será redirecionado para a grid do cartão")
	public void o_usuário_será_redirecionado_para_a_grid_do_cartão() {
		assertEquals("http://localhost:4200/cartao/listar", cartaoPage.paginaAtual());
	}
	
	@Entao("será exibido a mensagem {string} de erro")
	public void será_exibido_a_mensagem_de_erro(String mensagem) {
		assertEquals(mensagem, cartaoPage.mensagemDeErro());
	}

}
