package mx.tec.lab;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlButton;
import com.gargoylesoftware.htmlunit.html.HtmlInput;
import com.gargoylesoftware.htmlunit.html.HtmlListItem;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlParagraph;
import com.gargoylesoftware.htmlunit.html.HtmlPasswordInput;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;

@SpringBootTest
class TestHtmlApplicationTests {
	private WebClient webClient;
	@BeforeEach
	public void init() throws Exception {
		webClient = new WebClient();
		
		}
	@AfterEach
	public void close() throws Exception {
			webClient.close();
			}
	@Test
	public void givenAClient_whenEnteringAutomationPractice_thenPageTitleIsCorrect()
			throws Exception {
		webClient.getOptions().setThrowExceptionOnScriptError(false);
		HtmlPage page = webClient.getPage("http://automationpractice.com/index.php");
		
		assertEquals("My Store", page.getTitleText());
		}
	
	@Test
	public void givenAClient_whenEnteringLoginCredentials_thenAccountPageIsDisplayed()
			throws Exception {
				webClient.getOptions().setThrowExceptionOnScriptError(false);
				HtmlPage page = webClient.getPage("http://automationpractice.com/index.php?controller=authentication&back=my-account");
				HtmlTextInput emailField = (HtmlTextInput) page.getElementById("email");
				emailField.setValueAttribute("A01566180@itesm.mx");
				HtmlPasswordInput passwordField = (HtmlPasswordInput) page.getElementById("passwd");
				passwordField.setValueAttribute("123456");
				HtmlButton submitButton = (HtmlButton) page.getElementById("SubmitLogin");
				HtmlPage resultPage = submitButton.click();
				
				assertEquals("My account - My Store", resultPage.getTitleText());
				
				}
	@Test
	public void givenAClient_whenEnteringWrongLoginCredentials_thenAuthenticationPageIsDisplayed()
			throws Exception {
				webClient.getOptions().setThrowExceptionOnScriptError(false);
			    HtmlPage page = webClient.getPage("http://automationpractice.com/index.php?controller=authentication&back=my-account");
			    HtmlTextInput emailField = (HtmlTextInput) page.getElementById("email");
			    emailField.setValueAttribute("A01566180@itesm.mx");
			    HtmlPasswordInput passwordField = (HtmlPasswordInput) page.getElementById("passwd");
			    passwordField.setValueAttribute("1234567");
			    HtmlButton submitButton = (HtmlButton) page.getElementById("SubmitLogin");
			    HtmlPage resultPage = submitButton.click();
		
			    assertEquals("Login - My Store", resultPage.getTitleText());
		//fail("Test not yet implemented!");
		}
	
	@Test
	public void givenAClient_whenEnteringWrongLoginCredentials_thenErrorMessageIsDisplayed()
			throws Exception {
				webClient.getOptions().setThrowExceptionOnScriptError(false);
			    HtmlPage page = webClient.getPage("http://automationpractice.com/index.php?controller=authentication&back=my-account");
			    HtmlTextInput emailField = (HtmlTextInput) page.getElementById("email");
			    emailField.setValueAttribute("A01566180@itesm.mx");
			    HtmlPasswordInput passwordField = (HtmlPasswordInput) page.getElementById("passwd");
			    passwordField.setValueAttribute("1234567");
			    HtmlButton submitButton = (HtmlButton) page.getElementById("SubmitLogin");
			    HtmlPage resultPage = submitButton.click();
			    HtmlListItem listItem = resultPage.getFirstByXPath("//div[@class='alert alert-danger']/ol/li");
		
			    assertEquals("Authentication failed.", listItem.getTextContent());
		//fail("Test not yet implemented!");
		}
	
	@Test
	public void givenAClient_whenSearchingNotExistingProduct_thenNoResultsDisplayed()
			throws Exception {
				webClient.getOptions().setThrowExceptionOnScriptError(false);
			    HtmlPage page = webClient.getPage("http://automationpractice.com/index.php?controller=authentication&back=my-account");
			    HtmlTextInput emailField = (HtmlTextInput) page.getElementById("email");
			    emailField.setValueAttribute("A01566180@itesm.mx");
			    HtmlPasswordInput passwordField = (HtmlPasswordInput) page.getElementById("passwd");
			    passwordField.setValueAttribute("123456");
			    HtmlButton submitButton = (HtmlButton) page.getElementById("SubmitLogin");
			    HtmlPage mainPage = submitButton.click();
				HtmlInput searchInput = (HtmlInput) mainPage.getElementById("search_query_top");
				searchInput.setValueAttribute("pantalon");
				HtmlButton searchButton = (HtmlButton) mainPage.getFirstByXPath("//button[@class='btn btn-default button-search']");
				HtmlPage searchResults = searchButton.click();
				HtmlParagraph noItemsFoundMessage = (HtmlParagraph) searchResults.getFirstByXPath("//p[@class='alert alert-warning']");
		
				assertEquals("No results were found for your search \"pantalon\"", noItemsFoundMessage.getVisibleText());
		//fail("Test not yet implemented!");
		}
	
	@Test
	public void givenAClient_whenSearchingEmptyString_thenPleaseEnterDisplayed()throws Exception {
			webClient.getOptions().setThrowExceptionOnScriptError(false);
		    HtmlPage page = webClient.getPage("http://automationpractice.com/index.php?controller=authentication&back=my-account");
		    HtmlTextInput emailField = (HtmlTextInput) page.getElementById("email");
		    emailField.setValueAttribute("A01566180@itesm.mx");
		    HtmlPasswordInput passwordField = (HtmlPasswordInput) page.getElementById("passwd");
		    passwordField.setValueAttribute("123456");
		    HtmlButton submitButton = (HtmlButton) page.getElementById("SubmitLogin");
		    HtmlPage mainPage = submitButton.click();
			HtmlButton searchButton = (HtmlButton) mainPage.getFirstByXPath("//button[@class='btn btn-default button-search']");
			HtmlPage searchResults = searchButton.click();
			HtmlParagraph noItemsFoundMessage = (HtmlParagraph) searchResults.getFirstByXPath("//p[@class='alert alert-warning']");
			
			assertEquals("Please enter a search keyword", noItemsFoundMessage.getTextContent().trim());
		//fail("Test not yet implemented!");
		
		}
	
	@Test
	public void givenAClient_whenSigningOut_thenAuthenticationPageIsDisplayed()
			throws Exception {
				webClient.getOptions().setThrowExceptionOnScriptError(false);
			    HtmlPage page = webClient.getPage("http://automationpractice.com/index.php?controller=authentication&back=my-account");
			    HtmlTextInput emailField = (HtmlTextInput) page.getElementById("email");
			    emailField.setValueAttribute("A01566180@itesm.mx");
			    HtmlPasswordInput passwordField = (HtmlPasswordInput) page.getElementById("passwd");
			    passwordField.setValueAttribute("123456");
			    HtmlButton submitButton = (HtmlButton) page.getElementById("SubmitLogin");
			    HtmlPage mainPage = submitButton.click();
				HtmlAnchor logoutButton = (HtmlAnchor) mainPage.getFirstByXPath("//a[@class='logout']");
				HtmlPage logoutResult = logoutButton.click();
				assertEquals("Login - My Store", logoutResult.getTitleText());
		//fail("Test not yet implemented!");
		}
	}
