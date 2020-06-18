package com.revature.daosproject;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
//import java.util.ArrayList;
import java.util.List;

import com.revature.modelsproject.User;
import com.revature.utilsproject.ConnectionFactory;

public class UserDAO implements DAO<User, Integer> {

	@Override
	public List<User> findAll() {
		//List<User> users = new ArrayList<User>();
		try(Connection conn = ConnectionFactory.getConnection()) {
			String sql = "SELECT * FROM user_table";
			Statement statement = conn.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);
			while(resultSet.next()) {
				User temp = new User();
				temp.setUserID(resultSet.getInt("user_id"));
				// so on
			}
		
		} catch(SQLException e) {
			
		}
		return null;
	}

	@Override
	public User findbyId(Integer id) {
		
		return null;
	}

	@Override
	public User create(User obj) {
		
		return null;
	}

	@Override
	public User update(User obj) {
		
		return null;
	}

	
	
}
