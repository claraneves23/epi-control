package com.epiadm.business;

import java.security.MessageDigest;
import java.util.Base64;

import com.epiadm.dao.EPIDAO;
import com.epiadm.dao.UserDAO;
import com.epiadm.vo.EpiVO;
import com.epiadm.vo.UserVO;

public class UserBusiness {
	
	private UserDAO userDAO = new UserDAO();
	
	
	public void validarUser(UserVO user) throws Exception{
		validarCamposObrigatorios(user);
		
		if (!validarLogin(user.getEmail(), user.getSenha())) {
	        throw new Exception("Email ou senha inválidos");
	    }
		
	}

	
	private boolean validarLogin(String email, String senhaDigitada) throws Exception {
		
		try {
				UserVO user = userDAO.buscarPorLogin(email);
				
					if(user == null) {
						return false;
					}
					
				String storedHash = user.getSenha();
				String storedSalt = user.getSalt();
				
				String inputHash = hashPassoword(senhaDigitada, Base64.getDecoder().decode(storedSalt));
				
				return storedHash.equals(inputHash);
			
		} catch (Exception e) {
				e.printStackTrace();
				return false;
		}
		
	}
	

	
	private String hashPassoword(String password, byte[] salt) {
		try {
			 MessageDigest md = MessageDigest.getInstance("SHA-256");
	            md.update(salt);
	            byte[] hashed = md.digest(password.getBytes());
	            return Base64.getEncoder().encodeToString(hashed);
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
	}
	
	


	public void validarCamposObrigatorios(UserVO user) throws Exception {
	    if (user.getEmail() == null || user.getEmail().trim().isEmpty()) {
	        throw new Exception("O campo 'Email' é obrigatório.");
	    }
	    
	    String regex = "^[\\w._%+-]+@[\\w.-]+\\.[a-zA-Z]{2,}$";
	    if (!user.getEmail().matches(regex)) {
	        throw new Exception("O email informado é inválido.");
	    }
	    
	    if (user.getSenha() == null || user.getSenha().trim().isEmpty()) {
	        throw new Exception("O campo 'Senha' é obrigatório.");
	    }
	}
}
