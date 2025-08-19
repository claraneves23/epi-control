package com.epiadm.business;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.logging.Logger;

import com.epiadm.dao.EPIDAO;
import com.epiadm.vo.EpiVO;

public class EPIBusiness {

	
	public void validarEPI(EpiVO epi) throws Exception{
		validarCamposObrigatorios(epi);
		validarDataValidade(epi.getValidadeCA());
		validarDataCadastro(epi.getDataCadastro());
		validarDuplicados(epi);
	}
	
	
	private void validarDataValidade(LocalDate dataValidade ) throws Exception {
		
		if(dataValidade.isBefore(LocalDate.now())) {
			throw new Exception("Data de validade não pode ser anterior à data atual");
		}
		
		if(dataValidade.isAfter(LocalDate.now().plusYears(5))) {
			throw new Exception("Validade não pode exceder 5 anos");
		}
	}
	
	private void validarDataCadastro(LocalDate dataCadastro ) throws Exception {
		
		if(dataCadastro.isAfter(LocalDate.now())) {
			throw new Exception("Data de cadastro não pode ser futura");
		}
	}
	
	private void validarDuplicados(EpiVO epiVO) throws Exception{
		EPIDAO dao = new EPIDAO();
		
		EpiVO existente = dao.buscarPorCA(epiVO.getCertificadoAprovacao());
		
		if (existente != null && !existente.getId().equals(epiVO.getId())) {
			
			throw new Exception("Já existe um EPI cadastrado com esse CA: " + epiVO.getCertificadoAprovacao());
			
		}
	}
	
	public int desativarEPIsVencidos() throws Exception {
		try {
			int registrosAfetados = new EPIDAO().atualizarStatusEPIsVencidos();
			
			return registrosAfetados;
			
		} catch(SQLException e) {
			throw new Exception("Falha ao desativar EPIs vencidos: " + e.getMessage());
		}
		
	}
	
	public void validarCamposObrigatorios(EpiVO epi) throws Exception {
	    if (epi.getNome() == null || epi.getNome().trim().isEmpty()) {
	        throw new Exception("O campo 'Nome' é obrigatório.");
	    }
	    if (epi.getDescricao() == null || epi.getDescricao().trim().isEmpty()) {
	        throw new Exception("O campo 'Descrição' é obrigatório.");
	    }
	    if (epi.getCertificadoAprovacao() == null || epi.getCertificadoAprovacao().trim().isEmpty()) {
	        throw new Exception("O campo 'Certificado de Aprovação' é obrigatório.");
	    }
	    if (epi.getValidadeCA() == null) {
	        throw new Exception("O campo 'Validade do CA' é obrigatório.");
	    }
	    if (epi.getClasseRisco() == null || epi.getClasseRisco().trim().isEmpty()) {
	        throw new Exception("O campo 'Classe de Risco' é obrigatório.");
	    }
	    if (epi.getDataCadastro() == null) {
	        throw new Exception("O campo 'Data de Cadastro' é obrigatório.");
	    }
	}

	
}
