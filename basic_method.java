package JDBC;

import java.io.*;
import java.sql.*;
/**
 * ���õ�Statement����
 *1.execute():������䣬�����Ƿ��н����
 *2.executeQuery():����select��䣬����ResultSet�����
 *3.executeUpdate():����insert/update/delete���������ظ��µ�����
 *
 *�ر����Ĳ�����resultset ----> statement ----> connection
 */
public class basic_method {
	public static void main(String[] args){
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet res = null;
		try {
			//1.����
			conn = JDBCUtil.getConnection();
	     	ps = conn.prepareStatement("insert into class(name,age,reg_time,login_time,myInfo,headImg) values(?,?,?,?,?,?)");
			ps.setObject(1, "Tom");
			ps.setObject(2, 21);
			ps.setObject(3, new Date(System.currentTimeMillis()));//Date�����Ϣ
			ps.setObject(4, new Timestamp(System.currentTimeMillis()));//TimeStamp�����Ϣ
			
			//����CLOB�ı�������
			File file = new File("d:/a.txt");
			ps.setCharacterStream(5, new FileReader(file),(int)file.length());//���ı��ļ�����ֱ�����뵽���ݿ���
			
			/*
			//�ұ�����һ�仰�����������������䣬���ǵ�������������ʡ�����ҵ�����������long����ǿ��ת��Ϊint��
			ps.setCharacterStream(5, new FileReader(new File("d:/a.txt")));//���ı��ļ�����ֱ�����뵽���ݿ���
			
			//���������ַ�����Ȼ�﷨���ˣ����Ǳ��벻��ͨ��������ԭ��δ����
			//ps.setClob(5, new FileReader(new File("d:/a.txt")));//���ı��ļ�����ֱ�����뵽���ݿ���
			//ps.setClob(5, new BufferedReader(new InputStreamReader(new ByteArrayInputStream("sdhjdfkh".getBytes()))));
*/
			//����BLOB�����ƴ�����
			File file2 = new File("d:/icon.jpg");
			ps.setBinaryStream(6, new FileInputStream(file2),(int)file2.length());//�˴������÷�����setBlob()��������Ȼʹ�ô˷����﷨ͨ���ˣ����Ǳ���ͨ����
			ps.executeUpdate();
			
			//2.ɾ��
			ps = conn.prepareStatement("delete from class where id >= ? and id <= ?");
			ps.setObject(1, 13);
			ps.setObject(2, 14);
			ps.execute();
			//3.��ѯBLOB����
			ps = conn.prepareStatement("select * from class where id = ?");
			ps.setObject(1, 20);
			res = ps.executeQuery();
			InputStream is = null;//��BLOB�����Ϣ���д洢
			OutputStream os = null;//��BLOB�����Ϣ�����ָ�����ļ���
			while(res.next()){
				Blob blob = res.getBlob("headImg");
				is = blob.getBinaryStream();
				int temp = 0;
				os = new FileOutputStream("d:/pic.jpg");
				while((temp = is.read()) != -1)
					os.write(temp);
				 //System.out.print((char) temp);//�ڶ�Ӧ�Ŀ���̨��ӡ������������
			}
			//4.��ѯClob����
			ps = conn.prepareStatement("select * from class where id = ?");
			ps.setObject(1, 20);
			ResultSet res2 = ps.executeQuery();
			while(res2.next()){
				Clob clob = res2.getClob("myInfo");
				Reader r = clob.getCharacterStream();//��BLOB������һ��
				int temp = 0;
				while((temp = r.read()) != -1)
					System.out.print((char)temp);
			}
		} catch (SQLException  | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			JDBCUtil.CloseStream(conn, ps, res);
		}
		
	}

}
