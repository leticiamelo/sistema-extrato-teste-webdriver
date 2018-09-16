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
		Assert.assertEquals("Movimentação adicionada com sucesso!", criarMovimentacaoPage.obterMensagemSucesso());
		
	}
	
	@Test
	public void testCamposObrigatorios(){
		menuPage.acessarTelaCriarMovimentacao();
		criarMovimentacaoPage.salvar();
		List<String> erros = criarMovimentacaoPage.obterErros();
//		Assert.assertEquals("Data da Movimentação é obrigatório", erros.get(0));
//		Assert.assertTrue(erros.contains("Data da Movimentação é obrigatório"));
		Assert.assertTrue(erros.containsAll(Arrays.asList(
				"Data da Movimentação é obrigatório",
				"Data do pagamento é obrigatório",
				"Descrição é obrigatório",
				"Interessado é obrigatório",
				"Valor é obrigatório",
				"Valor deve ser um número"
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
				"Data da Movimentação deve ser menor ou igual à data atual"
				));
		Assert.assertEquals(1, erros.size());
		
	}

}
