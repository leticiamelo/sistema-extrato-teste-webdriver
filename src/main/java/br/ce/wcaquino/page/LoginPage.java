package br.ce.wcaquino.page;

import br.ce.wcaquino.core.BasePage;
import br.ce.wcaquino.core.DriverFactory;

public class LoginPage extends BasePage{
	
	public void acessarTelaInicial(){
		System.setProperty("webdriver.chrome.driver", "C:/Users/erica.castro/Documents/Pessoais/Curso de Selenium/Chromedriver/chromedriver_win32/chromedriver.exe");
		DriverFactory.getDriver().get("https://srbarriga.herokuapp.com/login");
	}

	public void setEmail(String email){
		escreve("email", email);
	}
	
	public void setSenha(String senha){
		escreve("senha", senha);
	}
	
	public void entrar (){
		clicarBotaoPorTexto("Entrar");
	}
	
	public void logar(String email, String senha){
		setEmail(email);
		setSenha(senha);
		entrar();
	}
	
}

