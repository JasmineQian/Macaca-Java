package ikea.macacaikea;

public class Propertyikea {
	
	// 10.215.70.209
		public static final String URL_IKEA = "https://hh";
		public static final String smscode = "1111"; 
		public static final String validcode = "1111"; 
		public static final String username = "1111"; 
		public static final String mobilenum = "1111"; 
		public static final int waitfortime = 2;

		// 注册新会员
		public static final String registerElement = ".//a[contains(@href,'/ikeaMemberZoneV2/jsp/IdCardConfirm.jsp')]";

		// 更新个人信息
		public static final String updateInfoElement = ".//a[contains(@href, '/ikeaMemberZoneV2/jsp/searchCustomer.jsp?jumpType=update')]";

		// 遗失会员卡补领
		public static final String lostMemberCardElement = ".//a[contains(@href, '/ikeaMemberZoneV2/jsp/searchCustomer.jsp?jumpType=lose')]";

		// 打印临时会员卡
		public static final String forgetbringCardElement = ".//a[contains(@href, '/ikeaMemberZoneV2/jsp/searchCustomer_forget.jsp?jumpType=forget')]";

		// 打印优惠券
		public static final String printtabElement = ".//a[contains(@href,'/ikeaMemberZoneV2/jsp/searchCustomer_couponPrint.jsp?jumpType=couponPrint')]";

}
