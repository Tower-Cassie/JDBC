package JDBC;

import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Random;

import com.mysql.jdbc.ResultSet;
/**
 * ��������SQL�����в�ѯ
 * @author xiaohong
 *
 *���õ�Statement����
 *1.execute():������䣬�����Ƿ��н����
 *2.executeQuery():����select��䣬����ResultSet�����
 *3.executeUpdate():����insert/update/delete���������ظ��µ�����
 *
 *�ر����Ĳ�����resultset ----> statement ----> connection
 *
 */
public class testSelectDate {
	
	//���ַ���ת��Ϊָ����ʽ
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
				//���������ࣨ���䣩
				conn = JDBCUtil.getConnection();
				 /*
				//���ݵǼ�ʱ����в�ѯ
				 ps = conn.prepareStatement("select * from class where reg_time > ? and reg_time < ? order by reg_time");//�˴���?��ռλ��,���Է�ֹstatement��SQLע��
					Date start = new Date(strToDate("2017-08-18 16:59:20"));
					Date end = new Date(strToDate("2017-09-10 10:20:12"));
					
					//���ÿ��Ƕ�Ӧ���������ͣ�ֱ�ӽ��и�ֵ����
					ps.setObject(1, start);
					ps.setObject(2, end);
					
					res = ps.executeQuery();
					while(res.next()){
						System.out.println(res.getInt("id")+"--"+res.getString("name") + "--" + res.getDate("reg_time"));
					}*/
				 //���ݵ�½ʱ����в�ѯ
				 
				 ps = conn.prepareStatement("select * from class where login_time > ? and login_time < ? order by login_time");//�˴���?��ռλ��,���Է�ֹstatement��SQLע��
					Date start = new Date(strToDate("2017-08-18 16:59:20"));
					Date end = new Date(strToDate("2017-09-10 10:20:12"));
					
					//���ÿ��Ƕ�Ӧ���������ͣ�ֱ�ӽ��и�ֵ����
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
