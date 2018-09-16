package br.ce.wcaquino.core;

import static br.ce.wcaquino.core.DriverFactory.killDriver;
import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TestName;

import br.ce.wcaquino.page.LoginPage;

public class BaseTest {
	private LoginPage page = new LoginPage();
	
	
	@Rule
	public TestName testName = new TestName();
	
	@Before
	public void inicializa(){
		page.acessarTelaInicial();
		page.setEmail("erica.lety@hotmail.com");
		page.setSenha("123");
		page.entrar();
		
	}
	
	@After
	public void finaliza() throws IOException{
		//TakesScreenshot ss = (TakesScreenshot) DriverFactory.getDriver();
		//File arquivo = ss.getScreenshotAs(OutputType.FILE);
		//tirar uma foto de cada teste e inserir o nome do teste.jpg. Al�m disso salvar� na pasta informada.
		//FileUtils.copyFile(arquivo, new File ("target" + File.separator + "screenshot" 
		//		+ File.separator + testName.getMethodName() + ".jpg"));
		
		//Utiliza a informa��o de propriedades para decidir se o browser vai fechar ao final de cada teste ou n�o.
		if(Propriedades.FECHAR_BROWSER){
			killDriver();
		}
		
	}

}
