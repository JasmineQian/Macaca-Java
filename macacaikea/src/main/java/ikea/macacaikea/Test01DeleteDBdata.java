package ikea.macacaikea;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Test01DeleteDBdata {

	// 这里可以设置数据库名称
	private final static String URL = "jdbc:sqlserver://NTGQASQL03:1433;DatabaseName=IKEA_MZN_QA";
	private static final String USER = "IKEA_MZN_QA";
	private static final String PASSWORD = "C935DD58";

	private static Connection conn = null;
	// 静态代码块（将加载驱动、连接数据库放入静态块中）
	static {
		try {
			// 1.加载驱动程序
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			// 2.获得数据库的连接
			conn = (Connection) DriverManager.getConnection(URL, USER, PASSWORD);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// 对外提供一个方法来获取数据库连接
	public static Connection getConnection() {
		return conn;
	}

	// 测试用例
	// public static void main(String[] args) throws Exception {
	public void FirstDelete() throws Exception {

		// 3.通过数据库的连接操作数据库，实现增删改查
		Statement stmt = conn.createStatement();
		Logger.Output(LogType.LogTypeName.INFO, "建立数据库链接 " );
		// ResultSet executeQuery(String sqlString)：执行查询数据库的SQL语句 ，返回一个结果集（ResultSet）对象。
		ResultSet rs = stmt
				.executeQuery("SELECT CST_ID,CST_NAME,CST_MOBILE FROM BCUSTOMER_MZN WHERE CST_DELETED_FLAG = 0 ");
		while (rs.next()) {// 如果对象中有数据，就会循环打印出来
			System.out.println(rs.getInt("CST_ID") + "," + rs.getString("CST_NAME") + "," + rs.getString("CST_MOBILE"));
		}

		String sql = "SELECT CST_ID INTO  #TEMPCARDKKK FROM BCUSTOMER_MZN where CST_mobile = '18962873440'\r\n" + "\r\n"
				+ "--SELECT CST_ID INTO  #TEMPCARDKKK FROM BCUSTOMER_MZN WHERE CST_ID BETWEEN 298 AND 314\r\n" + "\r\n"
				+ "--SELECT CST_ID INTO  #TEMPCARDKKK FROM BCUSTOMER_MZN where CST_ID = 247\r\n" + "\r\n" + "\r\n"
				+ "--SELECT CST_ID INTO  #TEMPCARDKKK FROM BCUSTOMER_MZN where CST_MOBILE in ('17321135211','18717805676','18817356097''18001701210''15618465639')\r\n"
				+ "--SELECT CST_ID INTO  #TEMPCARDKKK FROM BCUSTOMER_MZN where CST_MOBILE in ('17321135211','18001701210')\r\n"
				+ "--SELECT CST_ID INTO  #TEMPCARDKKK FROM BCUSTOMER_MZN where CST_MOBILE ='17721658628' and CST_DELETED_FLAG= 1\r\n"
				+ "--select * from #TEMPCARDKKK\r\n"
				+ "--SELECT CST_ID INTO  #TEMPCARDKKK FROM BCUSTOMER_MZN where CST_NAME like '%测试%'\r\n" + "\r\n"
				+ "--select * from BCUSTOMER_MZN where CST_DELETED_FLAG=1\r\n" + "\r\n"
				+ "DELETE FROM BCHILD_MZN WHERE CLD_XCS_CST_ID IN (SELECT CST_ID  FROM  #TEMPCARDKKK )\r\n"
				+ "DELETE FROM XCUSTOMER_MZN WHERE XCS_CST_ID IN (SELECT CST_ID FROM #TEMPCARDKKK )\r\n"
				+ "DELETE FROM BSMLACTIVESIGNIN where SAS_MMB_ID in  (SELECT MMB_ID FROM BMEMBERSHIP_MZN WHERE MMB_CST_ID IN (SELECT CST_ID FROM #TEMPCARDKKK ))\r\n"
				+ "DELETE FROM XMEMBERSHIP_MZN  WHERE XMB_MMB_ID IN (SELECT MMB_ID FROM BMEMBERSHIP_MZN WHERE MMB_CST_ID IN (SELECT CST_ID FROM #TEMPCARDKKK ))\r\n"
				+ "DELETE FROM HCOUPON_HISTORY WHERE HCP_MMB_ID IN (SELECT MMB_ID FROM BMEMBERSHIP_MZN WHERE MMB_CST_ID IN (SELECT CST_ID FROM #TEMPCARDKKK ))\r\n"
				+ "DELETE FROM RCOUPON_MEMBERSHIP WHERE CPM_MMB_ID IN (SELECT MMB_ID FROM BMEMBERSHIP_MZN WHERE MMB_CST_ID IN (SELECT CST_ID FROM #TEMPCARDKKK ))\r\n"
				+ "DELETE FROM BMEMBERSHIP_MZN WHERE MMB_CST_ID IN (SELECT CST_ID FROM #TEMPCARDKKK )\r\n"
				+ "DELETE FROM BCUSTOMER_MZN WHERE CST_ID IN (SELECT CST_ID FROM #TEMPCARDKKK) ";
		Statement stmtdelete = conn.createStatement();// 创建Statement对象
		stmtdelete.executeUpdate(sql);// 执行sql语句

		Logger.Output(LogType.LogTypeName.INFO, "指定会员已经删除 " );
		conn.close();
		System.out.println("数据库关闭成功");// 关闭数据库的连接
	}

}
