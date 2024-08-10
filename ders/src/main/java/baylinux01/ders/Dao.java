package baylinux01.ders;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Dao {
	static String SqliteDBClassName="org.sqlite.JDBC";
	//static String SqliteDBEmptyUrl="jdbc:sqlite";
	static String SqliteDBUrl="jdbc:sqlite:Ders.sqlite";
	
	public void createDatabase() throws ClassNotFoundException, SQLException 
	{
		String query="create database if not exists Ders";
		
		Class.forName(SqliteDBClassName);
		Connection con=DriverManager.getConnection(SqliteDBUrl);
		PreparedStatement st=con.prepareStatement(query);
		st.executeUpdate();
		if(con!=null && !con.isClosed())
		{
			con.close();
		}
		
	}
	
	public void creatUserTable() throws ClassNotFoundException, SQLException 
	{
		String query="create table user ("
				+ "id integer primary key autoincrement,"
				+ "username text not null,"
				+ "password text not null"
				+ ");";
		
		Class.forName(SqliteDBClassName);
		Connection con=DriverManager.getConnection(SqliteDBUrl);
		PreparedStatement st=con.prepareStatement(query);
		st.executeUpdate();
		if(con!=null && !con.isClosed())
		{
			con.close();
		}
		
	}
	
	public int createUser(String username,String password) throws ClassNotFoundException, SQLException 
	{
		String query="insert into user (username,password) values (?,?)";
		
		Class.forName(SqliteDBClassName);
		Connection con=DriverManager.getConnection(SqliteDBUrl);
		PreparedStatement st=con.prepareStatement(query);
		st.setString(1, username);
		st.setString(2, password);
		int result=st.executeUpdate();
		if(con!=null && !con.isClosed())
		{
			con.close();
		}
		return result;
		
	}
	
	public List<User> getAllUsers() throws ClassNotFoundException, SQLException 
	{
		String query="select * from user";
		
		Class.forName(SqliteDBClassName);
		Connection con=DriverManager.getConnection(SqliteDBUrl);
		PreparedStatement st=con.prepareStatement(query);
		List<User> users=new ArrayList<User>();
		ResultSet rs=st.executeQuery();
		while(rs.next())
		{
			User user=new User();
			user.setId(rs.getInt("id"));
			user.setUsername(rs.getString("username"));
			user.setPassword(rs.getString("password"));
			users.add(user);
		}
		if(con!=null && !con.isClosed())
		{
			con.close();
		}
		return users;
		
	}
	public User getUserById(int id) throws ClassNotFoundException, SQLException 
	{
		String query="select * from user where id=?";
		
		Class.forName(SqliteDBClassName);
		Connection con=DriverManager.getConnection(SqliteDBUrl);
		PreparedStatement st=con.prepareStatement(query);
		List<User> users=new ArrayList<User>();
		st.setInt(1, id);
		ResultSet rs=st.executeQuery();
		while(rs.next())
		{
			User user=new User();
			user.setId(rs.getInt("id"));
			user.setUsername(rs.getString("username"));
			user.setPassword(rs.getString("password"));
			users.add(user);
		}
		if(con!=null && !con.isClosed())
		{
			con.close();
		}
		return users.get(0);
		
	}
	
	public void deleteUserById(int id) throws ClassNotFoundException, SQLException 
	{
		String query="delete from user where id=?";
		
		Class.forName(SqliteDBClassName);
		Connection con=DriverManager.getConnection(SqliteDBUrl);
		PreparedStatement st=con.prepareStatement(query);
		
		st.setInt(1, id);
		st.executeUpdate();
		
		if(con!=null && !con.isClosed())
		{
			con.close();
		}
		
		
	}
	public void deleteAllUsers() throws ClassNotFoundException, SQLException 
	{
		String query="delete from user";
		
		Class.forName(SqliteDBClassName);
		Connection con=DriverManager.getConnection(SqliteDBUrl);
		PreparedStatement st=con.prepareStatement(query);
		st.executeUpdate();
		
		if(con!=null && !con.isClosed())
		{
			con.close();
		}
		
		
	}
	public void UpdateUsernameById(int id,String newusername) throws ClassNotFoundException, SQLException 
	{
		String query="Update user set username=? where id=?";
		
		Class.forName(SqliteDBClassName);
		Connection con=DriverManager.getConnection(SqliteDBUrl);
		PreparedStatement st=con.prepareStatement(query);
		
		st.setString(1, newusername);
		st.setInt(2,id);
		st.executeUpdate();
		
		if(con!=null && !con.isClosed())
		{
			con.close();
		}
		
		
	}
	public void UpdatePasswordById(int id,String newpassword) throws ClassNotFoundException, SQLException 
	{
		String query="Update user set password=? where id=?";
		
		Class.forName(SqliteDBClassName);
		Connection con=DriverManager.getConnection(SqliteDBUrl);
		PreparedStatement st=con.prepareStatement(query);
		
		st.setString(1, newpassword);
		st.setInt(2,id);
		st.executeUpdate();
		
		if(con!=null && !con.isClosed())
		{
			con.close();
		}
		
		
	}
	public int UpdateUsernameAndPasswordById(int id,String newusername,String newpassword) throws ClassNotFoundException, SQLException 
	{
		String query="Update user set username=?, password=? where id=?";
		
		Class.forName(SqliteDBClassName);
		Connection con=DriverManager.getConnection(SqliteDBUrl);
		PreparedStatement st=con.prepareStatement(query);
		
		st.setString(1, newusername);
		st.setString(2,newpassword);
		st.setInt(3, id);
		int result=st.executeUpdate();
		
		if(con!=null && !con.isClosed())
		{
			con.close();
		}
		return result;
		
	}

}
