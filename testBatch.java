package JDBC;
import java.sql.*;
import java.util.Random;
/**
 * 测试java.sql.Date,Time,Timestanp
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
public class testBatch {

	public static void main(String[] args) {
			Connection conn = null;
			PreparedStatement ps = null;
				
			for(int i = 1; i < 200; i++){//往数据库中添加200行记录
				try {
					//加载驱动类（反射）
					conn = JDBCUtil.getConnection();
					//conn.setAutoCommit(false);//设置为自动递交
					 ps = conn.prepareStatement("insert into class(name,age,reg_time,login_time)values(?,?,?,?)");//此处的?是占位符,可以防止statement的SQL注入
							
					//不用考虑对应变量的类型，直接进行赋值即可
					ps.setObject(1, "Lucy" + i);
			    	ps.setObject(2, 18);
							
			    	//设置随机数
					int rand = 10000000 + new Random().nextInt(100000000);
					Date date = new Date(System.currentTimeMillis() + rand);
					ps.setObject(3, date);
							
				    Timestamp stamp = new Timestamp(System.currentTimeMillis() + rand);
					ps.setObject(4, stamp);
					ps.execute();					
						
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}finally{
						JDBCUtil.CloseStream(conn, ps);
					}	
				}
	}

}
