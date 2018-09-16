package br.ce.wcaquino.page;

import org.openqa.selenium.By;

import br.ce.wcaquino.core.BasePage;

public class ResumoMensalPage extends BasePage{
	
	public void setMes(String mes){
		selecionarCombo("mes", mes);
	}
	
	public void setAno(String ano){
		selecionarCombo("ano", ano);
	}
	
	public void buscar(){
		clicarBotao(By.xpath("//input[@value='Buscar']"));
	}
	
	
	//span[@class='glyphicon glyphicon-remove-circle']
	
	public void excluirMovimentacao(String colunaBusca, String valor){
		clicarBotaoTabela(colunaBusca, valor, "Ações", "tabelaExtrato");
	}
	public String obterMensagemSucesso(){
		return obterTexto(By.xpath("//div[@class='alert alert-success']"));
	}
}
