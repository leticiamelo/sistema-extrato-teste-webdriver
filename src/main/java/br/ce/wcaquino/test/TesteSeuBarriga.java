package br.ce.wcaquino.test;

import org.junit.Before;

import br.ce.aquino.page.CampoTreinamentoPage;
import br.ce.wcaquino.core.DSL;
import br.ce.wcaquino.core.DriverFactory;

public class TesteSeuBarriga extends BaseTest{

	
	@Before
	public void inicializa(){
		System.setProperty("webdriver.chrome.driver", "C:/Users/erica.castro/Documents/Pessoais/Curso de Selenium/Chromedriver/chromedriver_win32/chromedriver.exe");
		DriverFactory.getDriver().get("https://srbarriga.herokuapp.com/login");
		page = new CampoTreinamentoPage();
	}
}
