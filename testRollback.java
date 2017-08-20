package JDBC;

import java.sql.*;
/**
 * ��������Ļ�������ͷ���:Ҫôȫ���ύ�ɹ���Ҫôȫ���ύʧ��
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
public class testRollback {

	public static void main(String[] args) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		PreparedStatement ps2 = null;
		
		try {
			conn = JDBCUtil.getConnection();
			 
			 conn.setAutoCommit(false);//����Ϊ�Զ��ݽ�
			 ps = conn.prepareStatement("insert into class(name,age)values(?,?)");//�˴���?��ռλ��,���Է�ֹstatement��SQLע��
				
				//���ÿ��Ƕ�Ӧ���������ͣ�ֱ�ӽ��и�ֵ����
				ps.setObject(1, "Lucy");
				ps.setObject(2, 18);
				ps.execute();
				System.out.println("�ɹ��Ĳ���һ�м�¼");
				
				
				ps2 = conn.prepareStatement("insert into class(name,age)values(?,?,?)");//�˴���?��ռλ��,���Է�ֹstatement��SQLע��
				
				//���ÿ��Ƕ�Ӧ���������ͣ�ֱ�ӽ��и�ֵ����
				ps2.setObject(1, "Raw");
				ps2.setObject(2, 48);
				ps2.execute();
				System.out.println("�ɹ��Ĳ���ڶ��м�¼");
				
				conn.commit();
			
			
		} finally{
			JDBCUtil.CloseStream(conn, ps);
			
		}
		

	}

}
