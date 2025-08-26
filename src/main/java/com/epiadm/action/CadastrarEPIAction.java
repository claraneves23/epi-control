package com.epiadm.action;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;


import com.epiadm.business.EPIBusiness;
import com.epiadm.dao.EPIDAO;
import com.epiadm.vo.EpiVO;
import com.opensymphony.xwork2.ActionSupport;

public class CadastrarEPIAction extends ActionSupport {

	private EpiVO epiVO = new EpiVO();
	

	public String exibirFormulario() {
        return INPUT;
    }
	
	public String execute() {
			if (hasFieldErrors()) {
				return INPUT;
			}
		   try {
			   
			   	new EPIBusiness().validarEPI(epiVO);
			   	new EPIDAO().salvar(epiVO);
	            
	            return SUCCESS;
		   } catch (Exception e) {
			   addActionError(e.getMessage());
	           return INPUT;
		   }
		   
	 
	}
	

	public EpiVO getEpiVO() {
		return epiVO;
	}

	public void setEpiVO(EpiVO epiVO) {
		this.epiVO = epiVO;
	}


	
	 
	
}
