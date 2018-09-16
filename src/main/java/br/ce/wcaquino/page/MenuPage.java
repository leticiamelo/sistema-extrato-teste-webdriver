package br.ce.wcaquino.page;

import br.ce.wcaquino.core.BasePage;

public class MenuPage extends BasePage{
	
	public void acessarTelaInserirContas(){
		clicarLink("Contas");
		clicarLink("Adicionar");
		
	}
	
	public void acessarTelaListarContas(){
		clicarLink("Contas");
		clicarLink("Listar");
	}
	public void acessarTelaCriarMovimentacao(){
		clicarLink("Criar Movimentação");
	}
	
	public void acessarTelaResumoMensal(){
		clicarLink("Resumo Mensal");
	}

}
