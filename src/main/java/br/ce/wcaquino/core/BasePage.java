package br.ce.wcaquino.core;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class BasePage {
	
/**********************TextFiel e TextArea ****************************/
	
	public static void escreve(By by, String texto){
		DriverFactory.getDriver().findElement(by).clear();
		DriverFactory.getDriver().findElement(by).sendKeys(texto);
	}
	
	public static void escreve(String id_campo, String texto){
		
		escreve(By.id(id_campo), texto);
	}
	
	public String obterValorCampo(String id_campo){
					
		return DriverFactory.getDriver().findElement(By.id(id_campo)).getAttribute("value");
	}
		
	/***************** Radio e Check **************************/
	
	public void clicarRadio(By by){
		
		DriverFactory.getDriver().findElement(by).click();
	}
	
	public void clicarRadio(String id){
		
		clicarRadio(By.id(id));
	}
	
	public boolean isRadioMarcado(String id){
		
		return DriverFactory.getDriver().findElement(By.id(id)).isSelected();
	}
	
	public void clicarCheck(String id){
		
		DriverFactory.getDriver().findElement(By.id(id)).click();
	}
	
	public boolean isCheckMarcado(String id){
		
		return DriverFactory.getDriver().findElement(By.id(id)).isSelected();
	}
	
	/******************* Combo *********************/
	
	public void selecionarCombo (By by, String valor){
		
		WebElement element = DriverFactory.getDriver().findElement(by);
		Select combo = new Select (element);
		combo.selectByVisibleText(valor);
	}
	
	public void selecionarCombo (String id, String valor){
		selecionarCombo(By.id(id, valor));
		
	}
	
	public void deselecionarCombo (String id, String valor){
		
		WebElement element = DriverFactory.getDriver().findElement(By.id(id));
		Select combo = new Select (element);
		combo.selectByVisibleText(valor);
	}
	
	private By getDriver() {
		// TODO Auto-generated method stub
		return null;
	}

	public String obterValorCombo (String id){
		
		WebElement element = DriverFactory.getDriver().findElement(By.id(id));
		Select combo = new Select (element);
		return combo.getFirstSelectedOption().getText();
	}
	
	public List<String> obterValoresCombo (String id){
		
		WebElement element = DriverFactory.getDriver().findElement(By.id(id));
		Select combo = new Select (element);
		List<String> valores = new ArrayList<String>();
		for(WebElement opcao: allSelectedOptions){
			valores.add(opcao.getText());
		}
		return valores;
	}
	
	public int obterQuantidadeOpcoesCombo (String id){
		
		WebElement element = DriverFactory.getDriver().findElement(By.id(id));
		Select combo = new Select (element);
		List<WebElement> options = combo.getOptions();
		return options.size();

}
	
	public boolean verificarOpcaoCombo(String id, String opcao){
		
		WebElement element = DriverFactory.getDriver().findElement(By.id(id));
		Select combo = new Select (element);
		List<WebElement> options = combo.getOptions();
		for(WebElement option: options){
			if(option.getText().equals(opcao)){
				return true;
			}
		}
		return false;
	}
	
	public void selecionarComboPrime(String radical, String valor){
		clicarRadio(By.xpath("//td[.='Basic:']/..//div/div[3]/span"));
		clicarRadio(By.xpath(".//*[@id='" +radical+ "']"));
	}
	
	
	/********************** Botao ****************************/
	
	public static void clicarBotao (By by){
		
		DriverFactory.getDriver().findElement(by).click();
	}
	
	public static void clicarBotao (String botao_id){
		
		clicarBotao(By.id(botao_id));
	}
	
	public void clicarBotaoPorTexto(String texto){
		clicarBotao(By.xpath("//button[.='"+texto+"']"));
	}
	
	public String obterValueElemento(String id){
		
		return DriverFactory.getDriver().findElement(By.id(id)).getAttribute("value");
	}
	
	/*************** Link **********************/
	
	public void clicarLink (String link){
		
		DriverFactory.getDriver().findElement(By.linkText(link)).click();
	}
	
	/***************** Textos *****************/
	
	public static String obterTexto(By by){
		
		return DriverFactory.getDriver().findElement(by).getText();
	}
	
	public static void obterTexto(String id){
		
		obterTexto(By.id(id));
	}
	
	/************************* Alerts **********************/
	
	public String alertaObterTexto(){
		
		Alert alert = DriverFactory.getDriver().switchTo().alert();
		return alert.getText();
	}
	
	public String alertaObterTextoEAceita(){
		
		Alert alert = DriverFactory.getDriver().switchTo().alert();
		String valor = alert.getText();
		alert.accept();
		return valor;
	}
	
	public String alertaObterTextoENega(){
		
		Alert alert = DriverFactory.getDriver().switchTo().alert();
		String valor = alert.getText();
		alert.dismiss();
		return valor;
	}
	
	public void alertaEscrever(String valor){
		
		Alert alert = DriverFactory.getDriver().switchTo().alert();
		alert.sendKeys(valor);
		alert.accept();
	}
	
	/****************** Frames e Janelas *****************/
	
	public void entrarFrame(String id){
		
		DriverFactory.getDriver().switchTo().frame(id);
	}
	
	public void sairFrame(){
		
		DriverFactory.getDriver().switchTo().defaultContent();
	}
	
	public void trocarJanela(String id){
		
		DriverFactory.getDriver().switchTo().window(id);
	}
	
	/***************** JS ***********************/
	
	public Object executarJS(String cmd, Object... param){
		
		JavascriptExecutor js = (JavascriptExecutor) DriverFactory.getDriver();
		return js.executeScript(cmd, param);
	}
	
	/******************* Tabela**********************/
	
	public WebElement obterCelula(String colunaBusca, String valor, String colunaBotao, String idTabela){
		//procurar coluna do registro
		WebElement tabela = DriverFactory.getDriver().findElement(By.xpath("//*[@id='"+idTabela+"']"));
		//o uso do ponto indica que eu quero que ele busque n�o no site inteiro, mas no diretorio corrente que estou especificando. como a tabela:
		int idColuna = obterIndiceColuna(colunaBusca, tabela);
			
		//Encontrar a linha do registro
		int idLinha = obterIndiceLinha(valor, tabela, idColuna);
		
		//Procurar coluna do bot�o
		int idColunaBotao = obterIndiceColuna(colunaBotao, tabela);
		
		//clicar no botao da celula encontrada
		WebElement celula = tabela.findElement(By.xpath(".//tr["+idLinha+"]/td["+idColunaBotao+"]"));
		return celula;		
	}
	
	public void clicarBotaoTabela(String colunaBusca, String valor, String colunaBotao, String idTabela){
	
		WebElement celula = obterCelula(colunaBusca, valor, colunaBotao, idTabela);
		celula.findElement(By.xpath(".//input")).click();		
	}

	protected int obterIndiceLinha(String valor, WebElement tabela, int idColuna) {
		List<WebElement> linhas = tabela.findElements(By.xpath("./tbody/tr/td["+idColuna+"]"));
		
		int idLinha = -1;
		for (int i = 0; i < linhas.size(); i++){
			if (linhas.get(i).getText().equals(valor)){
				idLinha = i+1;
				break;
			}
		}
		return idLinha;
	}

	protected int obterIndiceColuna(String coluna, WebElement tabela) {
		List<WebElement> colunas = tabela.findElements(By.xpath(".//th"));
		int idColuna = -1;
		for (int i = 0; i < colunas.size(); i++){
			if (colunas.get(i).getText().equals(coluna)){
				idColuna = i+1;
				break;
			}
		}
		return idColuna;
	}
	
}
