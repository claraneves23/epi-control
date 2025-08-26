package com.epiadm.action;


import com.epiadm.business.UserBusiness;
import com.epiadm.vo.UserVO;
import com.opensymphony.xwork2.ActionSupport;

public class UserLoginAction extends ActionSupport{
	
	private UserVO userVO = new UserVO();
	

	public String exibirFormulario() {
        return INPUT;
    }
	
	public String execute() {
			if (hasFieldErrors()) {
				return INPUT;
			}
		   try {
			   
			   	new UserBusiness().validarUser(userVO);
	            
	            return SUCCESS;
		   } catch (Exception e) {
			   addActionError(e.getMessage());
	           return INPUT;
		   }
		   
	 
	}
	
	public UserVO getUserVO() {
		return userVO;
	}

	public void setUserVO(UserVO userVO) {
		this.userVO = userVO;
	}
	

}
