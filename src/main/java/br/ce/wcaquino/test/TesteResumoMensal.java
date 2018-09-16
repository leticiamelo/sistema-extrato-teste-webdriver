package br.ce.wcaquino.test;

import static br.ce.wcaquino.core.DriverFactory.getDriver;

import org.junit.Assert;
import org.junit.Test;

import br.ce.wcaquino.core.BaseTest;
import br.ce.wcaquino.page.MenuPage;
import br.ce.wcaquino.page.ResumoMensalPage;

public class TesteResumoMensal extends BaseTest{
	
	private MenuPage menuPage = new MenuPage();
	private ResumoMensalPage rmPage = new ResumoMensalPage();
	
	@Test
	public void consultarResumo(){
		menuPage.acessarTelaResumoMensal();
		rmPage.setMes("Setembro");
		rmPage.setAno("2018");
		rmPage.buscar();
	}
	
	@Test
	public void excluirMovimentacao(){
		menuPage.acessarTelaResumoMensal();
		rmPage.setMes("Setembro");
		rmPage.setAno("2018");
		rmPage.buscar();
		rmPage.excluirMovimentacao("Descrição", "teste Leticia");
		Assert.assertEquals("Movimentação removida com sucesso!", rmPage.obterMensagemSucesso());
	}
	
	@Test
	public void testResumoMensal(){
		menuPage.acessarTelaResumoMensal();
		Assert.assertEquals("Seu Barriga - Extrato", getDriver().getTitle());
	}
}
