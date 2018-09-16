package br.ce.wcaquino.test;

import static br.ce.wcaquino.utils.DataUtils.obterDataFormatada;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import br.ce.wcaquino.core.BaseTest;
import br.ce.wcaquino.page.CriarMovimentacaoPage;
import br.ce.wcaquino.page.MenuPage;
import br.ce.wcaquino.utils.DataUtils;

public class MovimentacaoTest extends BaseTest{
	
	private CriarMovimentacaoPage criarMovimentacaoPage = new CriarMovimentacaoPage();
	private MenuPage menuPage = new MenuPage();
	
	@Test
	public void testInserirMovimentacao(){
		menuPage.acessarTelaCriarMovimentacao();
		criarMovimentacaoPage.setTipodaMovimentacao("Receita");
		criarMovimentacaoPage.setDatadaMovimentacao(obterDataFormatada(new Date()));
		criarMovimentacaoPage.setDatadoPagamento(obterDataFormatada(new Date()));
		criarMovimentacaoPage.setDescricao("criar movimentacao");
		criarMovimentacaoPage.setInteressado("Leticia");
		criarMovimentacaoPage.setValor("100");
		criarMovimentacaoPage.setConta("Conta do Teste4");
		criarMovimentacaoPage.setSituacaoPago();
		criarMovimentacaoPage.salvar();
		Assert.assertEquals("Movimenta��o adicionada com sucesso!", criarMovimentacaoPage.obterMensagemSucesso());
		
	}
	
	@Test
	public void testCamposObrigatorios(){
		menuPage.acessarTelaCriarMovimentacao();
		criarMovimentacaoPage.salvar();
		List<String> erros = criarMovimentacaoPage.obterErros();
//		Assert.assertEquals("Data da Movimenta��o � obrigat�rio", erros.get(0));
//		Assert.assertTrue(erros.contains("Data da Movimenta��o � obrigat�rio"));
		Assert.assertTrue(erros.containsAll(Arrays.asList(
				"Data da Movimenta��o � obrigat�rio",
				"Data do pagamento � obrigat�rio",
				"Descri��o � obrigat�rio",
				"Interessado � obrigat�rio",
				"Valor � obrigat�rio",
				"Valor deve ser um n�mero"
				)));
		Assert.assertEquals(6, erros.size());
	}
	
	@Test
	public void testInserirMovimentacaoFutura(){
		menuPage.acessarTelaCriarMovimentacao();
		criarMovimentacaoPage.setTipodaMovimentacao("Receita");
		
		Date dataFutura = DataUtils.obterDataComDiferencaDias(5);
		
		criarMovimentacaoPage.setDatadaMovimentacao(obterDataFormatada(dataFutura));
		criarMovimentacaoPage.setDatadoPagamento(DataUtils.obterDataFormatada(dataFutura));
		criarMovimentacaoPage.setDescricao("criar movimentacao");
		criarMovimentacaoPage.setInteressado("Leticia");
		criarMovimentacaoPage.setValor("100");
		criarMovimentacaoPage.setConta("Conta do Teste4");
		criarMovimentacaoPage.setSituacaoPago();
		criarMovimentacaoPage.salvar();
		
		List<String> erros = criarMovimentacaoPage.obterErros();
		Assert.assertTrue(erros.contains(
				"Data da Movimenta��o deve ser menor ou igual � data atual"
				));
		Assert.assertEquals(1, erros.size());
		
	}

}
