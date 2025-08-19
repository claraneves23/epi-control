package com.epiadm.action;

import com.epiadm.business.EPIBusiness;
import com.epiadm.dao.EPIDAO;
import com.epiadm.vo.EpiVO;
import com.opensymphony.xwork2.ActionSupport;

public class EditarEPIAction extends ActionSupport {

	private EpiVO epiVO = new EpiVO();

	public String execute() {
		if (hasFieldErrors()) {
			return INPUT;
		}
		try {
			
			 new EPIBusiness().desativarEPIsVencidos();
			 EpiVO epiOriginal = new EPIDAO().buscarPorId(epiVO.getId());
			 
			 if (epiOriginal == null) {
		            throw new Exception("EPI não encontrado para edição.");
		     }
			 
		     epiVO.setCertificadoAprovacao(epiOriginal.getCertificadoAprovacao());
		     epiVO.setValidadeCA(epiOriginal.getValidadeCA());
		     epiVO.setStatus(epiOriginal.isStatus());
		     epiVO.setDataCadastro(epiOriginal.getDataCadastro());

		     new EPIBusiness().validarEPI(epiVO);
		     new EPIDAO().editar(epiVO);

			return SUCCESS;
		} catch (Exception e) {
			addActionError(e.getMessage());
			return INPUT;
		}

	}
	
	public String prepararEdicao() {
		try {
			EpiVO epiEncontrado = new EPIDAO().buscarPorId(epiVO.getId());
			
			if(epiEncontrado != null) {
				this.epiVO = epiEncontrado;
				return SUCCESS;
			}
			
			else {
				  addActionError("EPI não encontrado com o ID: " + epiVO.getId());
	                return ERROR;
			}
			
		} catch (Exception e) {
			addActionError(e.getMessage());
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
