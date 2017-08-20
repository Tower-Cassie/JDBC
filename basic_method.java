package JDBC;

import java.io.*;
import java.sql.*;
/**
 * 常用的Statement方法
 *1.execute():运行语句，返回是否有结果集
 *2.executeQuery():运行select语句，返回ResultSet结果集
 *3.executeUpdate():运行insert/update/delete操作，返回更新的行数
 *
 *关闭流的操作：resultset ----> statement ----> connection
 */
public class basic_method {
	public static void main(String[] args){
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet res = null;
		try {
			//1.插入
			conn = JDBCUtil.getConnection();
	     	ps = conn.prepareStatement("insert into class(name,age,reg_time,login_time,myInfo,headImg) values(?,?,?,?,?,?)");
			ps.setObject(1, "Tom");
			ps.setObject(2, 21);
			ps.setObject(3, new Date(System.currentTimeMillis()));//Date类的信息
			ps.setObject(4, new Timestamp(System.currentTimeMillis()));//TimeStamp类的信息
			
			//设置CLOB文本大数据
			File file = new File("d:/a.txt");
			ps.setCharacterStream(5, new FileReader(file),(int)file.length());//把文本文件内容直接输入到数据库中
			
			/*
			//我本想用一句话来代替上面的三句语句，但是第三个参数不能省，而且第三个参数的long必须强制转化为int型
			ps.setCharacterStream(5, new FileReader(new File("d:/a.txt")));//把文本文件内容直接输入到数据库中
			
			//用下面两种方法虽然语法过了，但是编译不会通过，具体原因还未修正
			//ps.setClob(5, new FileReader(new File("d:/a.txt")));//把文本文件内容直接输入到数据库中
			//ps.setClob(5, new BufferedReader(new InputStreamReader(new ByteArrayInputStream("sdhjdfkh".getBytes()))));
*/
			//设置BLOB二进制大数据
			File file2 = new File("d:/icon.jpg");
			ps.setBinaryStream(6, new FileInputStream(file2),(int)file2.length());//此处不能用方法，setBlob()方法，虽然使用此方法语法通过了，但是编译通不过
			ps.executeUpdate();
			
			//2.删除
			ps = conn.prepareStatement("delete from class where id >= ? and id <= ?");
			ps.setObject(1, 13);
			ps.setObject(2, 14);
			ps.execute();
			//3.查询BLOB对象
			ps = conn.prepareStatement("select * from class where id = ?");
			ps.setObject(1, 20);
			res = ps.executeQuery();
			InputStream is = null;//对BLOB类的信息进行存储
			OutputStream os = null;//将BLOB类的信息输出到指定的文件中
			while(res.next()){
				Blob blob = res.getBlob("headImg");
				is = blob.getBinaryStream();
				int temp = 0;
				os = new FileOutputStream("d:/pic.jpg");
				while((temp = is.read()) != -1)
					os.write(temp);
				 //System.out.print((char) temp);//在对应的控制台打印出来的是乱码
			}
			//4.查询Clob对象
			ps = conn.prepareStatement("select * from class where id = ?");
			ps.setObject(1, 20);
			ResultSet res2 = ps.executeQuery();
			while(res2.next()){
				Clob clob = res2.getClob("myInfo");
				Reader r = clob.getCharacterStream();//与BLOB的流不一样
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
