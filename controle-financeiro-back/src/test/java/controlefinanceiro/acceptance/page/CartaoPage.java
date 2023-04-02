package controlefinanceiro.acceptance.page;

import org.openqa.selenium.By;

public class CartaoPage extends PageObject {
	
	public void navegaParaCartaoListar() {
		browser.get("http://localhost:4200/cartao/listar");
	}
	
	public void clicoBotaoNovoCartao() {
		browser.findElement(By.id("novoCartao")).click();
	}
	
	public void preencherFormulario(String nome, String bandeira, String numero, String limite) {
		esperar();
		browser.findElement(By.cssSelector("input-field[formcontrolname=nome]" + complementoInput())).sendKeys(nome);
		browser.findElement(By.cssSelector("mat-select[formcontrolname=bandeira]")).sendKeys(bandeira);
		browser.findElement(By.cssSelector("input[formcontrolname=numero]")).sendKeys(numero);
		browser.findElement(By.cssSelector("number-field[formcontrolname=limite]" + complementoInput())).sendKeys(limite);
		esperar();
	}
	
}
