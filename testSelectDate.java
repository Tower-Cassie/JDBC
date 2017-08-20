package JDBC;

import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Random;

import com.mysql.jdbc.ResultSet;
/**
 * 测试利用SQL语句进行查询
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
public class testSelectDate {
	
	//将字符串转换为指定格式
	public static long strToDate(String datestr){
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		try {
			return format.parse(datestr).getTime();
		} catch (ParseException e) {
			return 0;
		}
	}

	public static void main(String[] args) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		java.sql.ResultSet res = null;
			try {
				//加载驱动类（反射）
				conn = JDBCUtil.getConnection();
				 /*
				//根据登记时间进行查询
				 ps = conn.prepareStatement("select * from class where reg_time > ? and reg_time < ? order by reg_time");//此处的?是占位符,可以防止statement的SQL注入
					Date start = new Date(strToDate("2017-08-18 16:59:20"));
					Date end = new Date(strToDate("2017-09-10 10:20:12"));
					
					//不用考虑对应变量的类型，直接进行赋值即可
					ps.setObject(1, start);
					ps.setObject(2, end);
					
					res = ps.executeQuery();
					while(res.next()){
						System.out.println(res.getInt("id")+"--"+res.getString("name") + "--" + res.getDate("reg_time"));
					}*/
				 //根据登陆时间进行查询
				 
				 ps = conn.prepareStatement("select * from class where login_time > ? and login_time < ? order by login_time");//此处的?是占位符,可以防止statement的SQL注入
					Date start = new Date(strToDate("2017-08-18 16:59:20"));
					Date end = new Date(strToDate("2017-09-10 10:20:12"));
					
					//不用考虑对应变量的类型，直接进行赋值即可
					ps.setObject(1, start);
					ps.setObject(2, end);
					
					res = ps.executeQuery();
					while(res.next()){
						System.out.println(res.getInt("id")+"--"+res.getString("name") + "--" + res.getTimestamp("login_time"));
					}
					
				
				
			} finally{
				JDBCUtil.CloseStream(conn, ps);
				
			}
	}

}
