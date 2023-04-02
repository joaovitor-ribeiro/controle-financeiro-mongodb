package controlefinanceiro.acceptance.page;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PageObject {
	
	WebDriver browser;
	
	WebDriverWait wait;
	
	public void iniciarDriver() {
		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--remote-allow-origins=*");
		browser = new ChromeDriver(options);
		browser.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
		browser.manage().window().maximize();
		wait = new WebDriverWait(browser, Duration.ofSeconds(40));
	}
	
	public void acionoBotaoSalvar() {
		browser.findElement(By.cssSelector("button[type=submit]")).click();
	}
	
	public String paginaAtual() {
		return browser.getCurrentUrl();
	}
	
	public void esperaAlert() {
		wait.until(ExpectedConditions.visibilityOf(browser.findElement(By.cssSelector("div[role=alert]"))));
	}
	
	public boolean alertaDeSucesso(String mensagem) {
		return browser.getPageSource().contains(mensagem);
	}
	
	public String mensagemDeErro() {
		return wait.until(ExpectedConditions.visibilityOf(browser.findElement(By.className("mat-error")))).getText();
	}
	
	public void realizarLogin(String email, String senha) {
		esperar();
		browser.get("http://localhost:4200/entrar");
		browser.findElement(By.cssSelector("input[formcontrolname=email]")).sendKeys(email);
		browser.findElement(By.cssSelector("input-field[formcontrolname=senha]" + complementoInput())).sendKeys(senha);
		browser.findElement(By.id("botaoEntrar")).click();
		wait.until(ExpectedConditions.visibilityOf(browser.findElement(By.cssSelector("div.divData > mat-form-field"))));
		esperar();
	}
	
	public String complementoInput() {
		return " > mat-form-field > div > div > div > input";
	}
	
	public void esperar() {
		try {
			Thread.sleep(300);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void sair() {
		if (browser != null) {
			browser.quit();
		}
	}

}
