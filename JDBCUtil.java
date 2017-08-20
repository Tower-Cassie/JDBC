package JDBC;

import java.io.IOException;
import java.sql.*;
/*
 * 此类中主要是：连接数据库以及关闭一些流
 * 
 */
import java.util.Properties;

public class JDBCUtil {
	/*
	   //利用资源文件进行连接数据库
	   static Properties pros = null;//可以帮助获取和处理文件中的信息
	   static{//加载JDBCUtil类的信息
		pros = new Properties();
		try {
			pros.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("JDBCUtil.properties"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public static Connection getConnection(){
		try {
			Class.forName(pros.getProperty("mysqlDriver"));
			return DriverManager.getConnection(pros.getProperty("mysqlURL"), pros.getProperty("mysqlUser"),pros.getProperty( "mysqlPwd"));
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			return null;
		}
		
	}*/
	public static Connection getConnection(){
		try {
			//加载驱动类（反射）
			Class.forName("com.mysql.jdbc.Driver");//此驱动类需要自己进行下载并导入资源中
			//建立连接（连接对象内部其实包含了Socket对象，是一个远程的连接 + 比较耗时！这是Connection对象管理的一个要点）
			//真正开发中，为了提高效率，都会使用连接池来管理连接对象
			return DriverManager.getConnection("jdbc:mysql://localhost:3306/student","root","19950219");
		} catch (ClassNotFoundException | SQLException e) {
			return null;
		}
		
	}
	//当数据处理完之后，关闭各种打开的文件
	public static void CloseStream(Connection conn,PreparedStatement ps){
		try{
			if(conn != null)
				conn.close();
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try{
			if(ps != null)
				ps.close();
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//当数据处理完之后，关闭各种打开的文件
	public static void CloseStream(Connection conn,PreparedStatement ps,ResultSet rs){
		try{
			if(conn != null)
				conn.close();
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try{
			if(rs != null)
				rs.close();
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try{
			if(ps != null)
				ps.close();
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//当数据处理完之后，关闭各种打开的文件
	public static void CloseStream(Connection conn){
		try{
			if(conn != null)
				conn.close();
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
