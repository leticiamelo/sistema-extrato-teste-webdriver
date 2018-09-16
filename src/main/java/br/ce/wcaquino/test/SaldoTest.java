package br.ce.wcaquino.test;

import org.junit.Assert;
import org.junit.Test;

import br.ce.wcaquino.core.BaseTest;
import br.ce.wcaquino.page.HomePage;


public class SaldoTest extends BaseTest{
	
	 HomePage homePage = new HomePage();
	
	@Test
	public void testSaldoConta(){
		Assert.assertEquals("10.00", homePage.obterSaldoConta("fasdf"));
	}
}
