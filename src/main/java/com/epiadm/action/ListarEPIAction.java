package com.epiadm.action;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

import com.epiadm.business.EPIBusiness;
import com.epiadm.dao.EPIDAO;
import com.epiadm.vo.EpiVO;
import com.opensymphony.xwork2.ActionSupport;

public class ListarEPIAction extends ActionSupport {
	
	
	private EpiVO epiVO = new EpiVO();
	private List<EpiVO> listaParaExibir;
	
	public String execute() {
		
		try {
			
			new EPIBusiness().desativarEPIsVencidos();
			listaParaExibir = new EPIDAO().listarTodos();
			
			return SUCCESS;
		} catch (Exception e) {
			addActionError("Erro ao listar EPIs: " + e.getMessage());
			return ERROR;
		}
	}
	
	

	public List<EpiVO> getListaParaExibir() {
		return listaParaExibir;
	}
	
	public EpiVO getEpiVO() {
		return epiVO;
	}

	public void setEpiVO(EpiVO epiVO) {
		this.epiVO = epiVO;
	}
	
	

	
	

}
