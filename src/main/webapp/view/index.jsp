 <!DOCTYPE html>
 <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>     
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
<link href="${pageContext.request.contextPath}/css/form.css?v=2.5" rel="stylesheet">
<s:head theme="simple"/>
</head>
<body>
	<div class="form-wrapper">
        <h2>Login</h2>
        
        <s:actionerror cssClass="action-error"/>
        
        <s:form action="login" method="post" theme="simple" novalidate="novalidate">
            
            <div class="form-row">
            <label for="userVO.email">Email:</label>
                <s:textfield name="userVO.email" cssClass="form-input"/>
            </div>
            
            <div class="form-row">
            <label for="userVO.senha">Senha:</label>
                <s:password name="userVO.senha" cssClass="form-input"/>
            </div>
            
            
            <div class="form-buttons">
                <s:submit value="Entrar" cssClass="btn-salvar"/>
            </div>
        </s:form>
    </div>
</body>
</html>