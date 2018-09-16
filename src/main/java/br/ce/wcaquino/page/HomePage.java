package br.ce.wcaquino.page;

import br.ce.wcaquino.core.BasePage;

public class HomePage extends BasePage{
	
	public String obterSaldoConta(String nome){
		return obterCelula("Conta", nome, "Saldo", "tabelaSaldo").getText();
	}
}
