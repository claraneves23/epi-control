package com.epiadm.action;

import java.util.List;

import com.epiadm.dao.EPIDAO;
import com.epiadm.vo.EpiVO;
import com.opensymphony.xwork2.ActionSupport;

public class ExcluirEPIAction extends ActionSupport{
	
	private EpiVO epiVO = new EpiVO();

	public String execute() {
		
		System.out.println("DEBUG - ID a excluir: " + (epiVO != null ? epiVO.getId() : "epiVO nulo"));
		
		 if (epiVO == null || epiVO.getId() == null) {
			 	System.err.println("Falha: ID nulo recebido para exclusão");
		        addActionError("ID não fornecido para exclusão");
		        return ERROR;
		    }
		
		try {
			 System.out.println("Tentando excluir EPI com ID: " +  epiVO.getId()); 
			 
			 EPIDAO dao = new EPIDAO();
			 EpiVO epiExistente = dao.buscarPorId(epiVO.getId());
			 
			 if(epiExistente == null) {
				 addActionError("EPI não encontrado com o ID: " + epiVO.getId());
			        return ERROR;
			 }
			 
			 dao.excluir(epiVO.getId());
			 addActionMessage("EPI excluído com sucesso!");
			 return SUCCESS;
		} catch (Exception e) {
			String errorMsg = "Falha ao excluir EPI: " + 
                    (e.getMessage() != null ? e.getMessage() : "Erro desconhecido");
			System.err.println(errorMsg);
			addActionError(errorMsg);
			return ERROR;
		}
	}
	

	public EpiVO getEpiVO() {
		return epiVO;
	}

	public void setEpiVO(EpiVO epiVO) {
		this.epiVO = epiVO;
	}
	
	
}
