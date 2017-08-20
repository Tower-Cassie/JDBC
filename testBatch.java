package JDBC;
import java.sql.*;
import java.util.Random;
/**
 * ����java.sql.Date,Time,Timestanp
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
public class testBatch {

	public static void main(String[] args) {
			Connection conn = null;
			PreparedStatement ps = null;
				
			for(int i = 1; i < 200; i++){//�����ݿ������200�м�¼
				try {
					//���������ࣨ���䣩
					conn = JDBCUtil.getConnection();
					//conn.setAutoCommit(false);//����Ϊ�Զ��ݽ�
					 ps = conn.prepareStatement("insert into class(name,age,reg_time,login_time)values(?,?,?,?)");//�˴���?��ռλ��,���Է�ֹstatement��SQLע��
							
					//���ÿ��Ƕ�Ӧ���������ͣ�ֱ�ӽ��и�ֵ����
					ps.setObject(1, "Lucy" + i);
			    	ps.setObject(2, 18);
							
			    	//���������
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
