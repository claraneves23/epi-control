package com.epiadm.util;

import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Base64;

public class PasswordHasher {
	  public static void main(String[] args) {
	        String password = "senha123"; // Senha que você quer testar
	        
	        // Gerar salt aleatório
	        byte[] salt = generateSalt();
	        String saltBase64 = Base64.getEncoder().encodeToString(salt);
	        
	        // Gerar hash da senha
	        String hashedPassword = hashPassword(password, salt);
	        
	        System.out.println("Senha: " + password);
	        System.out.println("Salt (Base64): " + saltBase64);
	        System.out.println("Hash (Base64): " + hashedPassword);
	        
	        // SQL para inserir no banco
	        System.out.println("\nSQL para inserir:");
	        System.out.println("INSERT INTO usuarios (email, senha, salt) VALUES (");
	        System.out.println("    'teste@email.com',");
	        System.out.println("    '" + hashedPassword + "',");
	        System.out.println("    '" + saltBase64 + "'");
	        System.out.println(");");
	    }
	    
	    public static byte[] generateSalt() {
	        byte[] salt = new byte[16];
	        new SecureRandom().nextBytes(salt);
	        return salt;
	    }
	    
	    public static String hashPassword(String password, byte[] salt) {
	        try {
	            MessageDigest md = MessageDigest.getInstance("SHA-256");
	            md.update(salt);
	            byte[] hashed = md.digest(password.getBytes());
	            return Base64.getEncoder().encodeToString(hashed);
	        } catch (Exception e) {
	            throw new RuntimeException(e);
	        }
	    }
}
