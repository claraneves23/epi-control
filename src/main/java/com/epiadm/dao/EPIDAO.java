package com.epiadm.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.epiadm.factory.ConnectionFactory;
import com.epiadm.vo.EpiVO;

public class EPIDAO {
	
	
	public void salvar(EpiVO epi) throws SQLException {
		String sql = "INSERT INTO EPI(nome, descricao, certificado_aprovacao, validade_ca, classe_risco, data_cadastro,status)"
           + "VALUES (?, ?, ?, ?, ?, ?, ?)";
		
		try(Connection conn = ConnectionFactory.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setString(1, epi.getNome());
            stmt.setString(2, epi.getDescricao());
            stmt.setString(3, epi.getCertificadoAprovacao());
            stmt.setDate(4, Date.valueOf(epi.getValidadeCA()));
            stmt.setString(5, epi.getClasseRisco());
            stmt.setDate(6, Date.valueOf(epi.getDataCadastro()));
            stmt.setBoolean(7, epi.isStatus());

            stmt.executeUpdate();
            
            System.out.println("teste");
			
		}
		
	}
	
	public List<EpiVO> listarTodos() throws SQLException {
		List<EpiVO> lista = new ArrayList<>();
		String sql = "SELECT * FROM epi";
		
		try(Connection conn = ConnectionFactory.getConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery()){
		
			
			
			 while (rs.next()) {
	                EpiVO epi = new EpiVO();
	                epi.setId(rs.getLong("id"));
	                epi.setNome(rs.getString("nome"));
	                epi.setDescricao(rs.getString("descricao"));
	                epi.setCertificadoAprovacao(rs.getString("certificado_aprovacao"));
	                epi.setValidadeCA(rs.getDate("validade_ca").toLocalDate());
	                epi.setClasseRisco(rs.getString("classe_risco"));
	                epi.setDataCadastro(rs.getDate("data_cadastro").toLocalDate());
	                epi.setStatus(rs.getBoolean("status"));

	                lista.add(epi);
	            }
		}
		
		return lista;
	}
	
	public void excluir(Long id) throws SQLException {
		
		String sql = "DELETE FROM epi WHERE id = ?";
				
		try(Connection conn = ConnectionFactory.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql)){
			
			stmt.setLong(1, id);
			int rowsAffected = stmt.executeUpdate();
			
			 if(rowsAffected == 0) {
		            throw new SQLException("Nenhum EPI encontrado com o ID: " + id);
		        }
			
		} catch (SQLException e) {
			 System.err.println("Erro no DAO ao excluir EPI ID " + id + ": " + e.getMessage());
		        throw e; 
		}
				
	}
	
	public void editar(EpiVO epi) throws SQLException{
		
		if (epi == null || epi.getId() == null) {
	        throw new IllegalArgumentException("EPI ou ID não pode ser nulo.");
	    }
		
		String sql = "UPDATE epi SET nome = ?, descricao = ? , classe_risco = ? WHERE id = ?";
		
				
				try(Connection conn = ConnectionFactory.getConnection();
						PreparedStatement stmt = conn.prepareStatement(sql)) {
					stmt.setString(1, epi.getNome());
		            stmt.setString(2, epi.getDescricao());
		            stmt.setString(3, epi.getClasseRisco());
		            stmt.setLong(4, epi.getId());

		            
		            int rowsAffected = stmt.executeUpdate();
		            if (rowsAffected == 0) {
		                throw new SQLException("Nenhum registro foi atualizado. EPI com ID " + epi.getId() + " não encontrado.");
		            }
			}
	
	}

	public EpiVO buscarPorId(Long id) throws SQLException {
		
		String sql = "SELECT * FROM epi WHERE id = ?";
		EpiVO epi = null;
		
		try(Connection conn = ConnectionFactory.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql)){
			
			stmt.setLong(1, id);
			
			
			try (ResultSet rs = stmt.executeQuery()) {
				
				if (rs.next()) { 
	                epi = new EpiVO();
	                epi.setId(rs.getLong("id"));
	                epi.setNome(rs.getString("nome"));
	                epi.setDescricao(rs.getString("descricao"));
	                epi.setCertificadoAprovacao(rs.getString("certificado_aprovacao"));
	                epi.setValidadeCA(rs.getDate("validade_ca").toLocalDate());
	                epi.setClasseRisco(rs.getString("classe_risco"));
	                epi.setDataCadastro(rs.getDate("data_cadastro").toLocalDate());
	                epi.setStatus(rs.getBoolean("status"));
	            }
				
			}
		}
		
		
		return epi;
	}
	
	
	public List<EpiVO> buscarPorNome(String nomeBusca) throws SQLException{
		List<EpiVO> lista = new ArrayList<>();
		String sql = "SELECT * FROM epi";
		
		if (nomeBusca != null && !nomeBusca.trim().isEmpty()) {
	        sql += " WHERE nome LIKE ?";
	    }
		
		try(Connection conn = ConnectionFactory.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql)){
			
			 if (nomeBusca != null && !nomeBusca.trim().isEmpty()) {
		            stmt.setString(1, "%" + nomeBusca + "%");
		     }
			
			try (ResultSet rs = stmt.executeQuery()) {
				
				while (rs.next()) { 
					EpiVO epi = new EpiVO();
	                epi.setId(rs.getLong("id"));
	                epi.setNome(rs.getString("nome"));
	                epi.setDescricao(rs.getString("descricao"));
	                epi.setCertificadoAprovacao(rs.getString("certificado_aprovacao"));
	                epi.setValidadeCA(rs.getDate("validade_ca").toLocalDate());
	                epi.setClasseRisco(rs.getString("classe_risco"));
	                epi.setDataCadastro(rs.getDate("data_cadastro").toLocalDate());
	                epi.setStatus(rs.getBoolean("status"));
	                
	                lista.add(epi);
	            }
				
			}
		}
		
		return lista;
		
	}

	public EpiVO buscarPorCA(String certificadoAprovacao) throws SQLException {
		
		String sql = "SELECT * FROM epi WHERE certificado_aprovacao = ?";
		EpiVO epi = null;
		
		try(Connection conn = ConnectionFactory.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql)) {
					
			  stmt.setString(1, certificadoAprovacao);
		        
		        try (ResultSet rs = stmt.executeQuery()) {
		            if (rs.next()) {
		                epi = new EpiVO();
		                epi.setId(rs.getLong("id"));
		                epi.setNome(rs.getString("nome"));
		                epi.setCertificadoAprovacao(rs.getString("certificado_aprovacao"));
		        
		            	}
		        	}
				}
		
		return epi;
	}
	
	

	public int atualizarStatusEPIsVencidos() throws SQLException {
		
		String sql = "UPDATE epi SET status = false WHERE validade_ca < CURRENT_DATE AND status = true";
		EpiVO epi = null;
		
		try(Connection conn = ConnectionFactory.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql)) {
					
			  return stmt.executeUpdate();
		
		}
	
	}
	
}
