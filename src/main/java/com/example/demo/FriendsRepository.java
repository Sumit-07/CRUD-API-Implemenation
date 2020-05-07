package com.example.demo;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class FriendsRepository {
	Connection conn;
	Statement st;
	private static FriendsRepository friendsRepo;
	private final Logger logger;
	private FriendsRepository()
	{	this.logger=LoggerFactory.getLogger(this.getClass());
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/friendsdb?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=IST","root", "");
			st=conn.createStatement();
			
					} catch (ClassNotFoundException | SQLException e) {
						logger.error(e.toString());
		}
	}
	
	public static FriendsRepository getInstance()
	{
		if(friendsRepo==null)
		{
			friendsRepo=new FriendsRepository();
		}
		return friendsRepo;
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
			logger.error(e.toString());
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
			logger.error(e.toString());
		}
		return(fResult);

	}
	
	public List<friends> getFriendsBy(String condition,String category)
	{
		String sql="Select * from friendsinfo\n where "+ category+" like '"+condition+"'";
		List<friends> result= new ArrayList<>();
		ResultSet rs;
		friends fResult=new friends();
		try {
			rs = st.executeQuery(sql);
			
			while(rs.next())
			{
				fResult=makeFriend(rs);
				result.add(fResult);
				
			}
			
			
		} catch (SQLException e) {
			logger.error(e.toString());
		}
		return(result);

	}
	public void postFriends(friends f)
	{
		String sql="Insert into friendsinfo values (?,?,?,?,?)";
		try {
			PreparedStatement st1 = conn.prepareStatement(sql);
			st1.setInt(1, f.getId());
			st1.setString(2, f.getClassId());
			st1.setString(3, f.getName());
			st1.setString(4, f.getCity());
			st1.setString(5, f.getBirthday());
			int row = st1.executeUpdate();
			logger.info(row+" row is inserted");
			
		} catch (SQLException e) {
			logger.error(e.toString());
		
		}
		

	}
	public void updateFriend(friends newFriend)
	{
		String sql="Update friendsinfo \n set Id=?,Class=?, Name=?, City=?, Birthday=? \n Where Id="+newFriend.getId();
		PreparedStatement st1;
		try {
			st1 = conn.prepareStatement(sql);
			st1.setInt(1, newFriend.getId());
			st1.setString(2, newFriend.getClassId());
			st1.setString(3, newFriend.getName());
			st1.setString(4, newFriend.getCity());
			st1.setString(5, newFriend.getBirthday());
			int row = st1.executeUpdate();
			logger.info(row+" row is updated");
		} catch (SQLException e) {
			logger.error(e.toString());

		}

	}


	public void removeFriend(int id) {
		String sql="Delete From friendsinfo where Id="+id;
		try {
			st.executeUpdate(sql);
		} catch (SQLException e) {
			logger.error(e.toString());
		}
	}
	



	public List<Marksheet> getMarks() {
		List<Marksheet> result=new ArrayList<Marksheet>();
		String sql="Select id,class,marks_table.name,marks from friendsinfo,marks_table where friendsinfo.Name=marks_table.Name";
		try {
			ResultSet rs=st.executeQuery(sql);
			while(rs.next())
			{
				Marksheet m=makeMarksheet(rs);
				result.add(m);
			}
		} catch (SQLException e) {
			logger.error(e.toString());
		}
		
		return result;
	}
	
	

	public List<Marksheet> getMarksBy(String condition, String category) {
		List<Marksheet> result=new ArrayList<Marksheet>();
		String sql="Select id,class,marks_table.name,marks from friendsinfo,marks_table where friendsinfo.Name=marks_table.Name and friendsinfo."+category+" like '"+condition+"'";
		try {
			ResultSet rs=st.executeQuery(sql);
			while(rs.next())
			{
				Marksheet m=makeMarksheet(rs);
				result.add(m);
			}
		} catch (SQLException e) {
			
			logger.error(e.toString());
			
		}
		
		return result;		

	}
	

	public Marksheet getMarksById(int id) {
		Marksheet result=new Marksheet();
		String sql="Select id,class,marks_table.name,marks from friendsinfo,marks_table where friendsinfo.Name=marks_table.Name and id = "+id;
		try {
			ResultSet rs=st.executeQuery(sql);
			if(rs.next())
			{
				result=makeMarksheet(rs);

			}
		} catch (SQLException e) {
			
			logger.error(e.toString());

		}
		
		return result;	
	}
	
	public void postMarks(Marksheet m) {
		String sql="Insert into marks_table values (?,?)";
		try {
			PreparedStatement st1 = conn.prepareStatement(sql);
			st1.setString(1, m.getName());
			st1.setInt(2, m.getMarks());
			int row = st1.executeUpdate();
			System.out.println(row+" row is inserted!!");
		} catch (SQLException e) {
			logger.error(e.toString());

		
		}
		
		
	}
	
	
	private friends makeFriend(ResultSet rs)
	{
		friends f1=new friends();
	
		try {
			f1.setId(rs.getInt(1));
			f1.setClassId(rs.getString(2));
			f1.setName(rs.getString(3));
			f1.setCity(rs.getString(4));
			f1.setBirthday(rs.getString(5));
		} catch (SQLException e) {
			logger.error(e.toString());
		}

		return(f1);
		
	}
	
	private Marksheet makeMarksheet(ResultSet rs)
	{
		Marksheet m=new Marksheet();
		try {
			m.setId(rs.getInt(1));
			m.setClassId(rs.getString(2));
			m.setName(rs.getString(3));
			m.setMarks(rs.getInt(4));
		} catch (SQLException e) {
			logger.error(e.toString());
		}
		return m;
	}

	private int maxId() throws SQLException
	{
		int maxID = 0;
		st.execute("SELECT MAX(Id) FROM friendsinfo");    
		ResultSet rs = st.getResultSet(); // 
		if (rs.next())
		  maxID = rs.getInt(1);
		return(maxID);
	}
	public int getMaxId() throws SQLException
	{
		return(maxId());
	}

	public void updateMarks(Marksheet m) {
		String sql="Update marks_table \n set  Name=?, Marks=? \n Where Name='"+m.getName()+"'";
		PreparedStatement st1;
		try {
			st1 = conn.prepareStatement(sql);
			st1.setString(1, m.getName());
			st1.setInt(2, m.getMarks());
			int row = st1.executeUpdate();
			System.out.println(row+" row is updated!!");
		} catch (SQLException e) {
			logger.error(e.toString());
		}

		
	}

}
