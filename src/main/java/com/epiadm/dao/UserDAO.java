package com.epiadm.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.epiadm.factory.ConnectionFactory;
import com.epiadm.vo.UserVO;

public class UserDAO {
	
	public UserVO buscarPorLogin(String email) throws SQLException {
		
		String sql = "SELECT id, email, senha, salt FROM usuarios WHERE email = ?";
		UserVO user = null;
		
		try(Connection conn = ConnectionFactory.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql)) {
					
			  stmt.setString(1, email);
			
		        
		        try (ResultSet rs = stmt.executeQuery()) {
		            if (rs.next()) {
		            	user = new UserVO();
		                user.setId(rs.getLong("id"));
		                user.setEmail(rs.getString("email"));
		                user.setSenha(rs.getString("senha"));
		                user.setSalt(rs.getString("salt"));
		                
		            	}
		        	}
				}
		
		return user;
	}

}
