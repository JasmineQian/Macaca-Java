package ikea.macacaikea;

import com.alibaba.fastjson.JSONObject;

import macaca.client.MacacaClient;
import macaca.client.commands.Element;

public class Test04PrintTempCard {

	public static String browserName;
	public static String serverURL;
	public static int waitfortime;
	public static MacacaClient driver = new MacacaClient();

	// public static void main(String arg[]) throws InterruptedException {
	public void IKEA_PrintTempCardOnlyPage() throws Exception {

		Logger.Output(LogType.LogTypeName.INFO, "测试用例04--打印会员临时会员卡");
		serverURL = Propertyikea.URL_IKEA;
		waitfortime = Propertyikea.waitfortime;
		Logger.Output(LogType.LogTypeName.INFO, "The test server URL is: " + serverURL);

		JSONObject porps = new JSONObject();
		porps.put("browserName", "chrome");
		porps.put("platformName", "desktop");
		JSONObject desiredCapabilities = new JSONObject();
		desiredCapabilities.put("desiredCapabilities", porps);
		// desiredCapabilities.put("host", "127.0.0.1"); // custom remote host
		// desiredCapabilities.put("port", 3456); // custom remote port

		driver.initDriver(desiredCapabilities).get(serverURL);
		driver.maximize();

		driver.frame("frame1");
		String forgetbringCardElement = Propertyikea.forgetbringCardElement;
		String smscode = Propertyikea.smscode;
		Logger.Output(LogType.LogTypeName.INFO, "----open update personal info Page---" + smscode);

		Element forgotcard = driver.elementByXPath(forgetbringCardElement);
		forgotcard.click();
		Logger.Output(LogType.LogTypeName.INFO, "----open update personal info Page---");
		Thread.sleep(1000);

		driver.elementById("search_dialog").click();
		Thread.sleep(1000);
		driver.acceptAlert();

		Element name = driver.elementById("name");
		name.click();
		name.clearText();
		name.sendKeys(Propertyikea.username);
		Logger.Output(LogType.LogTypeName.INFO, "输入用户姓名: " + Propertyikea.username + ".");

		driver.elementById("search_dialog").click();
		Thread.sleep(1000);
		Logger.Output(LogType.LogTypeName.INFO, "there popups a window: " + driver.alertText() + ".");
		driver.acceptAlert();

		Element mobile = driver.elementById("mobile");
		mobile.click();
		mobile.clearText();
		mobile.sendKeys(Propertyikea.mobilenum);
		Logger.Output(LogType.LogTypeName.INFO, "输入手机号码: " + Propertyikea.mobilenum + ".");

		driver.elementById("search_dialog").click();
		Thread.sleep(1000);
		driver.setWaitElementTimeout(3);

		Thread.sleep(1000);

		Element validcode = driver.elementById("valid_code");
		validcode.click();
		validcode.clearText();
		validcode.sendKeys(Propertyikea.validcode);
		Logger.Output(LogType.LogTypeName.INFO, "输入validcode: " + Propertyikea.validcode + ".");

		driver.elementByXPath(".//div[2]/div[3]/div/button[1]/span").click();
		driver.setWaitElementTimeout(3);
		Thread.sleep(1000);
		Logger.Output(LogType.LogTypeName.INFO, "Click button");

		Element submitreg = driver.elementByLinkText("提交");
		submitreg.click();
		Logger.Output(LogType.LogTypeName.INFO, "There popups a message: " + driver.acceptAlert().toString());
		driver.acceptAlert();

		Thread.sleep(9000);
		Element printbutton = driver.elementById("print");
		printbutton.click();
		;
		Logger.Output(LogType.LogTypeName.INFO, "请确认是否已经正确打印出优惠券 ");
		// driver.quit();
	}
}
