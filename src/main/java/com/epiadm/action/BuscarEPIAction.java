package com.epiadm.action;

import java.util.ArrayList;
import java.util.List;

import com.epiadm.business.EPIBusiness;
import com.epiadm.dao.EPIDAO;
import com.epiadm.vo.EpiVO;
import com.opensymphony.xwork2.ActionSupport;

public class BuscarEPIAction extends ActionSupport{
	
	private String nomeBusca;
	private List<EpiVO> listaParaExibir;

	public String getNomeBusca() {
		return nomeBusca;
	}

	public void setNomeBusca(String nomeBusca) {
		this.nomeBusca = nomeBusca;
	}

	
	public String execute() {
		try {
			
			new EPIBusiness().desativarEPIsVencidos();
			if (nomeBusca != null && !nomeBusca.trim().isEmpty()) {
				listaParaExibir = new EPIDAO().buscarPorNome(nomeBusca);
			}
			
			else {
				listaParaExibir = new EPIDAO().listarTodos();
			}
			
			return SUCCESS;
			
		} catch (Exception e) {
			addActionError("Erro ao listar EPIs: " + e.getMessage());
			return ERROR;
		}
	}
	
	
	
	public List<EpiVO> getListaParaExibir() {
		return listaParaExibir;
	}

}
