package br.com.caelum.agiletickets.acceptance;

import junit.framework.Assert;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import br.com.caelum.agiletickets.acceptance.page.EstabelecimentosPage;

public class Estabelecimento1Test {

	public static String BASE_URL = "http://localhost:8080";
	private static WebDriver browser;
	private EstabelecimentosPage estabelecimentos;


	@Test
	public void aoAdicionarUmEstabelecimentoDeveMostraEstacionamento() throws Exception {
		System.setProperty("webdriver.gecko.driver", "geckodriver");
		browser = new FirefoxDriver();
		browser.get("http://localhost:8080/estabelecimentos");
		WebElement campoNome = browser.findElement(By.name("estabelecimento.nome"));
		campoNome.sendKeys("Teste do Selenium");
		WebElement formulario = browser.findElement(By.id("addForm"));
		formulario.submit();
		WebDriverWait espera = new WebDriverWait(browser,10);
	
		WebElement erro = browser.findElement(By.id("errors"));
        Assert.assertEquals(0, erro.getSize());
	}
	@Test
	public void aoAdicionarUmEstabelecimentoSemNomeDeveMostrarErro() throws Exception {
		estabelecimentos.abreListagem();

		estabelecimentos.adicioneEstabelecimento("", "R. Vergueiro, 3185");

		estabelecimentos.deveMostrarErro("O nome não pode ser vazio");
	}

	@Test
	public void aoAdicionarUmEstabelecimentoSemEnderecoDeveMostrarErro() throws Exception {
		estabelecimentos.abreListagem();

		estabelecimentos.adicioneEstabelecimento("Caelum", "");

		estabelecimentos.deveMostrarErro("O endereco não pode ser vazio");
	}

	@Test
	public void mostraQueHaEstacionamentoQuandoCadastramosQueSim() throws Exception {
		estabelecimentos.abreListagem();

		estabelecimentos.adicioneEstabelecimentoComEstacionamento(true);

		estabelecimentos.ultimaLinhaDeveTerEstacionamento(true);
	}

	@Test
	public void mostraQueNaoHaEstacionamentoQuandoCadastramosQueNao() throws Exception {
		estabelecimentos.abreListagem();

		estabelecimentos.adicioneEstabelecimentoComEstacionamento(false);

		estabelecimentos.ultimaLinhaDeveTerEstacionamento(false);
	}
	
}
