<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<html>
<head>
<meta charset="UTF-8">
<title>Cadastro de EPI</title>
<s:head />
</head>
<body>
	<h2>Cadastro de Equipamento de Proteção Individual (EPI)</h2>
	
    <s:actionerror/>
	
	<s:form action="%{epiVO.id == null ? 'EPI' : 'salvarEdicaoEPI'}" method="post">
    <s:hidden name="epiVO.id" />

  
    	<s:textfield name="epiVO.nome" label="Nome"/>
    	<s:textfield name="epiVO.descricao" label="Descrição"/>
    	<s:select name="epiVO.classeRisco" label="Classe de Risco"
          list="#{'I':'Risco I', 'II':'Risco II', 'III':'Risco III'}"
          headerKey="" headerValue="Selecione uma classe" />
          
    	<s:textfield name="epiVO.certificadoAprovacao" label="CA" disabled="%{epiVO.id != null}" />
    	<s:textfield type="date" name="epiVO.validadeCAStr" 
         value="%{epiVO.validadeCAStr}" 
         label="Validade do CA" 
         disabled="%{epiVO.id != null}" />
    	<s:textfield type="date" name="epiVO.dataCadastroStr" 
         value="%{epiVO.dataCadastroStr}" 
         label="Data de Cadastro" 
         disabled="%{epiVO.id != null}" /> 
   		 <s:radio name="epiVO.status" label="Status" 
         list="#{'true':'Ativo', 'false':'Inativo'}" 
         disabled="%{epiVO.id != null}" />

    	<s:submit value="%{epiVO.id == null ? 'Cadastrar' : 'Atualizar'}" />
	</s:form>
	
</body>
</html>