package ikea.macacaikea;

import org.testng.annotations.Test;

public class TestVerify {
	@Test(priority = 1)
	public void setUp() throws Exception {
		Test01DeleteDBdata delete = new Test01DeleteDBdata();
		delete.FirstDelete();
		Logger.Output(LogType.LogTypeName.INFO, "==========删除数据库成功============");

	}

	@Test(priority = 2)
	public void Register() {
		Logger.Output(LogType.LogTypeName.INFO, "==========no register============");
	}

	@Test(priority = 3)
	public void update() throws Exception {
		Test03UpdateInfoCN update = new Test03UpdateInfoCN();
		update.IKEA_UpdateIEPage();
	}

	@Test(priority = 4)
	public void printtmpvcard() throws Exception {
		Test04PrintTempCard printtmpcard = new Test04PrintTempCard();
		printtmpcard.IKEA_PrintTempCardOnlyPage();
	}

	@Test(priority = 5)
	public void printecoupon() throws Exception {
		Test05PrintCoupon printcoupon = new Test05PrintCoupon();
		printcoupon.IKEA_PrintCoupon();
	}
}