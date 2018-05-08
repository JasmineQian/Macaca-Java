package ikea.macacaikea;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Test01DeleteDBdata {

	// 这里可以设置数据库名称
	private final static String URL = "jdbc:sqlserver:/hhh";
	private static final String USER = "jjj";
	private static final String PASSWORD = "xxxx";

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
			Statement stmtdelete = conn.createStatement();// 创建Statement对象
		stmtdelete.executeUpdate(sql);// 执行sql语句

		Logger.Output(LogType.LogTypeName.INFO, "指定会员已经删除 " );
		conn.close();
		System.out.println("数据库关闭成功");// 关闭数据库的连接
	}

}
