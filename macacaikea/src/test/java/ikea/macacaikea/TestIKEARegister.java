package ikea.macacaikea;

import org.testng.annotations.Test;

import com.alibaba.fastjson.JSONObject;

import macaca.client.MacacaClient;
import macaca.client.commands.Element;

public class TestIKEARegister {

	MacacaClient driver = new MacacaClient();

	@Test(priority = 1)
	public void setUp() throws Exception {
		Test01DeleteDBdata delete = new Test01DeleteDBdata();
		delete.FirstDelete();
		Logger.Output(LogType.LogTypeName.INFO, "==========删除数据库成功============");

	}

	@Test(priority = 2)
	public void Register() throws Exception {

		JSONObject porps = new JSONObject();
		porps.put("browserName", "chrome");
		porps.put("platformName", "desktop");
		JSONObject desiredCapabilities = new JSONObject();
		desiredCapabilities.put("desiredCapabilities", porps);
		// desiredCapabilities.put("host", "127.0.0.1"); // custom remote host
		// desiredCapabilities.put("port", 3456); // custom remote port

		String url = Propertyikea.URL_IKEA;
		driver.initDriver(desiredCapabilities).setWindowSize(1080, 1280).get(url);
		driver.maximize();
		driver.takeScreenshot();
		driver.saveScreenshot("sscce");
		Logger.Output(LogType.LogTypeName.INFO, "==========此处应该有截屏============");
		driver.frame("frame1");
		Logger.Output(LogType.LogTypeName.INFO, "==========此处应该转化框架了============");

		driver.elementByXPath(Propertyikea.registerElement).click();
		Logger.Output(LogType.LogTypeName.INFO, "Click the button: " + "新会员注册");
		Logger.Output(LogType.LogTypeName.INFO, "Wait for  seconds.");
		driver.sleep(1000);
		driver.elementByLinkText("否").click();
		driver.sleep(3000);

		// String html = driver.source();
		// Assert.assertEquals(html, containsString("<html>"));

		driver.elementById("mz_cst_name").click();
		driver.elementById("mz_cst_name").clearText();
		driver.elementById("mz_cst_name").sendKeys(Propertyikea.username);

		driver.elementById("mz_cst_name").click();
		driver.elementById("mz_cst_name").clearText();
		driver.elementById("mz_cst_name").sendKeys(Propertyikea.username);
		Logger.Output(LogType.LogTypeName.INFO, "Type value is: " + Propertyikea.username + ".");

		driver.elementByXPath("(//input[@name='mz_cst_gender'])[2]").click();
		Logger.Output(LogType.LogTypeName.INFO, "Type value is: " + "选择性别女" + ".");
		// driver.elementByXPath(".//input[onclick='card_type_change('2')';").click();;
		Element cardtype = driver.elementById("xcs_idcardnum");
		cardtype.click();
		cardtype.clearText();
		cardtype.sendKeys("320683198912316046");
		Logger.Output(LogType.LogTypeName.INFO, "Type value is: " + "选择其他证件号" + ".");

		// JavascriptExecutor removeAttribute = (JavascriptExecutor) driver;
		// remove readonly attribute
		// removeAttribute.executeScript( "var
		// setDate=document.getElementById(\"mz_cst_birth\");setDate.removeAttribute('readonly');");
		// driver.elementById("mz_cst_birth").click();
		// driver.elementById("mz_cst_birth").clearText();
		// driver.elementById("mz_cst_birth").sendKeys("1999-12-01");
		Logger.Output(LogType.LogTypeName.INFO, "Type value is: " + "1999-12-01" + ".");

		driver.elementById("mz_cst_mobile").click();
		driver.elementById("mz_cst_mobile").clearText();
		driver.elementById("mz_cst_mobile").sendKeys(Propertyikea.mobilenum);

		driver.elementById("vercode").click();
		driver.elementById("vercode").clearText();
		driver.elementById("vercode").sendKeys(Propertyikea.validcode);

		// driver.elementById("work").click();
		// driver.elementById("RPROVINCE").clearText();
		/*
		 * new Select(driver.elementById("RPROVINCE")).selectByVisibleText("江苏省");
		 * 
		 * 
		 * driver.findElement(By.id("work")).click();
		 * driver.findElement(By.id("RPROVINCE")).click(); new
		 * Select(driver.findElement(By.id("RPROVINCE"))).selectByVisibleText("江苏省");
		 * driver.findElement(By.id("RPROVINCE")).click();
		 * driver.findElement(By.id("RCITY")).click(); new
		 * Select(driver.findElement(By.id("RCITY"))).selectByVisibleText("徐州市");
		 * driver.findElement(By.id("RCITY")).click();
		 * driver.findElement(By.id("RDISTRICT")).click(); new
		 * Select(driver.findElement(By.id("RDISTRICT"))).selectByVisibleText("丰县");
		 * 
		 * 
		 * driver.elementById("mz_address").click();
		 * driver.elementById("mz_address").clearText();
		 * driver.elementById("mz_address").sendKeys("dssfdsfadsfdasfdsdkjfkjdsjf");
		 */

		Element smsSend = driver.elementById("smsSend");
		smsSend.click();
		Thread.sleep(3000);
		MacacaClient alertCC = driver.acceptAlert();
		// Logger.Output(LogType.LogTypeName.INFO, "Type value is: " +
		// alertCC.alertText().toString());
		// alertCC.acceptAlert();

		driver.elementById("valid_code").click();
		driver.elementById("valid_code").clearText();
		driver.elementById("valid_code").sendKeys(Propertyikea.smscode);

		// driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		// Thread.sleep(3000);

		Element clickOnConfirm = driver.elementByXPath((".//a[@onclick='formSub();']/span"));
		clickOnConfirm.click();
		Thread.sleep(3000);
		driver.acceptAlert();

		// WebElement smsSend = driver.findElement(By.id("smsSend"));
		// smsSend.click();
		// driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		// Thread.sleep(3000);
		//
		//
		/*
		 * WebElement selector = driver.findElement(By.id("mz_cst_mngt_id")); Select
		 * select = new Select(selector); // select.selectByIndex(3);
		 * select.selectByValue("24");
		 * 
		 * WebElement selectorzhuangxiu = driver.findElement(By.id("xcs_rvt_id"));
		 * Select selectzz = new Select(selectorzhuangxiu); // select.selectByIndex(3);
		 * selectzz.selectByValue("4");
		 * 
		 * // WebElement childnum = //
		 * driver.findElement(By.xpath("(//input[@name='hasChild'])[0]")); //
		 * WebElements childnum = //
		 * driver.findElements(By.xpath("(//input[@name='hasChild'])")); //
		 * childnum.click(); //
		 * driver.findElement(By.xpath("(//input[@name='mz_cst_gender'])[2]")).click();
		 * 
		 * List<WebElement> childnum =
		 * driver.findElements(By.xpath("(//input[@name='hasChild'])"));
		 * 
		 * for (WebElement e : childnum) { e.click(); //
		 * Assert.assertTrue(e.isSelected()); Thread.sleep(1000); }
		 * 
		 * // WebElement checkbox1= //
		 * driver.findElement(By.xpath(".//input#id='agree'[@type='checkbox'][]"));
		 * WebElement checkbox1 = driver.findElement(By.id("agree")); checkbox1.click();
		 * Thread.sleep(1000); WebElement submitreg =
		 * driver.findElement(By.linkText("提交")); submitreg.click();
		 * 
		 * Alert alertsubmit = driver.switchTo().alert();
		 * Logger.Output(LogType.LogTypeName.INFO, "There popups a message: " +
		 * alertsubmit.getText().toString()); alertsubmit.accept();
		 */
		Thread.sleep(9000);

		Logger.Output(LogType.LogTypeName.INFO, ": " + "亲爱的注册会员成功" + ".");

		driver.setWaitElementTimeout(3);
		// driver.quit();

	}

	@Test(priority = 3)
	public void tearDown() throws Exception {
		driver.quit();
	}

}
