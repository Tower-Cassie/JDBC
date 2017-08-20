package JDBC;

import java.io.IOException;
import java.sql.*;
/*
 * ��������Ҫ�ǣ��������ݿ��Լ��ر�һЩ��
 * 
 */
import java.util.Properties;

public class JDBCUtil {
	/*
	   //������Դ�ļ������������ݿ�
	   static Properties pros = null;//���԰�����ȡ�ʹ����ļ��е���Ϣ
	   static{//����JDBCUtil�����Ϣ
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
			//���������ࣨ���䣩
			Class.forName("com.mysql.jdbc.Driver");//����������Ҫ�Լ��������ز�������Դ��
			//�������ӣ����Ӷ����ڲ���ʵ������Socket������һ��Զ�̵����� + �ȽϺ�ʱ������Connection��������һ��Ҫ�㣩
			//���������У�Ϊ�����Ч�ʣ�����ʹ�����ӳ����������Ӷ���
			return DriverManager.getConnection("jdbc:mysql://localhost:3306/student","root","19950219");
		} catch (ClassNotFoundException | SQLException e) {
			return null;
		}
		
	}
	//�����ݴ�����֮�󣬹رո��ִ򿪵��ļ�
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
	//�����ݴ�����֮�󣬹رո��ִ򿪵��ļ�
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
	//�����ݴ�����֮�󣬹رո��ִ򿪵��ļ�
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
