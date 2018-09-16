package br.ce.wcaquino.test;


import org.junit.Assert;
import org.junit.Test;

import br.ce.wcaquino.core.BaseTest;
import br.ce.wcaquino.page.ContasPage;
import br.ce.wcaquino.page.MenuPage;


public class ContaTest extends BaseTest{
	
	MenuPage menuPage = new MenuPage();
	ContasPage contasPage = new ContasPage();
	
	
	@Test
	public void testInserirConta(){
		
		menuPage.acessarTelaInserirContas();
		contasPage.setNome("Conta do Teste5");
		contasPage.salvar();
		Assert.assertEquals("Conta adicionada com sucesso!", contasPage.obterMensagemSucesso());
	}
	
	@Test
	public void testAlterarConta(){
		menuPage.acessarTelaListarContas();
				
		contasPage.clicarAlterarConta("Conta do Teste");
		contasPage.setNome("Conta  do  Teste alterada");
		contasPage.salvar();
		Assert.assertEquals("Conta alterada com sucesso!", contasPage.obterMensagemSucesso());
	}
	@Test
	public void testInserirContaMesmoNome(){
		menuPage.acessarTelaInserirContas();
		contasPage.setNome("Conta do Teste alterada");
		contasPage.salvar();
		Assert.assertEquals("Já existe uma conta com esse nome!", contasPage.obterMensagemErro());
	}
	@Test
	public void testExcluirContaComMovimentacao(){
		menuPage.acessarTelaListarContas();
		contasPage.clicarExcluirConta("fasdf");
		Assert.assertEquals("Conta em uso na movimentações", contasPage.obterMensagemErro());
	}
	
}
