package com.example.demo;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class FriendsRepository {
	Connection conn;
	Statement st;
	public FriendsRepository()
	{
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/friendsdb?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=IST","root", "");
			st=conn.createStatement();
					} catch (ClassNotFoundException | SQLException e) {
			System.out.println(e);
		}
	}
	
	public List<friends> getAllFriends()
	{	List<friends> result=new ArrayList<friends>();
		String sql="Select * from friendsinfo order by Id";
		ResultSet rs;
		try {
			rs = st.executeQuery(sql);
			while(rs.next())
			{
				friends f1=makeFriend(rs);
				result.add(f1);
			}
		} catch (SQLException e) {
			System.out.println(e);
		}
		return result;
	}
	public friends getOneFriend(int id)
	{
		String sql="Select * from friendsinfo where Id='"+id+"'";
		ResultSet rs;
		friends fResult=new friends();
		try {
			rs = st.executeQuery(sql);
			
			if(rs.next())
			{
				fResult=makeFriend(rs);
			}
			
			
		} catch (SQLException e) {
			System.out.println(e);;
		}
		return(fResult);

	}
	public void putFriends(friends f)
	{
		String sql="Insert into friendsinfo values (?,?,?,?)";
		
		try {
			PreparedStatement st1 = conn.prepareStatement(sql);
			st1.setInt(1, f.getId());
			st1.setString(2, f.getName());
			st1.setString(3, f.getCity());
			st1.setString(4, f.getBirthday());
			int row = st1.executeUpdate();
			System.out.println(row+" row is inserted!!");
		} catch (SQLException e) {
			System.out.println(e);
		}
		
		
	}
	public void updateFriend(int id,friends newFriend)
	{
		String sql="Update friendsinfo \n set Id=?, Name=?, City=?, Birthday=? \n Where Id="+id;
		PreparedStatement st1;
		try {
			st1 = conn.prepareStatement(sql);
			st1.setInt(1, newFriend.getId());
			st1.setString(2, newFriend.getName());
			st1.setString(3, newFriend.getCity());
			st1.setString(4, newFriend.getBirthday());
			int row = st1.executeUpdate();
			System.out.println(row+" row is updated!!");
		} catch (SQLException e) {
			System.out.println(e);
		}

	}
	public friends makeFriend(ResultSet rs)
	{
		friends f1=new friends();
	
		try {
			f1.setId(rs.getInt(1));
			f1.setName(rs.getString(2));
			f1.setCity(rs.getString(3));
			f1.setBirthday(rs.getString(4));
		} catch (SQLException e) {
			System.out.println(e);;
		}

		return(f1);
		
	}

	public void removeFriend(int id) {
		String sql="Delete From friendsinfo where id="+id;
		try {
			st.executeUpdate(sql);
		} catch (SQLException e) {
			System.out.println(e);
		}
	}
}
