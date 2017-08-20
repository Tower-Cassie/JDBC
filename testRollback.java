package JDBC;

import java.sql.*;
/**
 * 测试事务的基本概念和方法:要么全部提交成功，要么全部提交失败
 * @author xiaohong
 *
 *常用的Statement方法
 *1.execute():运行语句，返回是否有结果集
 *2.executeQuery():运行select语句，返回ResultSet结果集
 *3.executeUpdate():运行insert/update/delete操作，返回更新的行数
 *
 *关闭流的操作：resultset ----> statement ----> connection
 *
 */
public class testRollback {

	public static void main(String[] args) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		PreparedStatement ps2 = null;
		
		try {
			conn = JDBCUtil.getConnection();
			 
			 conn.setAutoCommit(false);//设置为自动递交
			 ps = conn.prepareStatement("insert into class(name,age)values(?,?)");//此处的?是占位符,可以防止statement的SQL注入
				
				//不用考虑对应变量的类型，直接进行赋值即可
				ps.setObject(1, "Lucy");
				ps.setObject(2, 18);
				ps.execute();
				System.out.println("成功的插入一行记录");
				
				
				ps2 = conn.prepareStatement("insert into class(name,age)values(?,?,?)");//此处的?是占位符,可以防止statement的SQL注入
				
				//不用考虑对应变量的类型，直接进行赋值即可
				ps2.setObject(1, "Raw");
				ps2.setObject(2, 48);
				ps2.execute();
				System.out.println("成功的插入第二行记录");
				
				conn.commit();
			
			
		} finally{
			JDBCUtil.CloseStream(conn, ps);
			
		}
		

	}

}
