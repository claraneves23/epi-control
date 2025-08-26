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
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
<link href="${pageContext.request.contextPath}/css/form.css?v=2.5" rel="stylesheet">
<s:head theme="simple"/>
</head>
<body>
    <div class="form-wrapper">
        <h2>Cadastro de Equipamento de Proteção Individual (EPI)</h2>
        
        <s:actionerror cssClass="action-error"/>
        
        <s:form action="%{epiVO.id == null ? 'EPI' : 'salvarEdicaoEPI'}" method="post" theme="simple">
            <s:hidden name="epiVO.id" />
            
            <div class="form-row">
            <label for="epiVO.nome">Equipamento:</label>
                <s:textfield name="epiVO.nome" cssClass="form-input"/>
            </div>
            
            <div class="form-row">
            <label for="epiVO.descricao">Descrição:</label>
                <s:textfield name="epiVO.descricao" cssClass="form-input"/>
            </div>
            
            <div class="form-row">
            <label for="epiVO.classeRisco">Classe de Risco</label>
                <s:select name="epiVO.classeRisco"
                    list="#{'I':'Risco I', 'II':'Risco II', 'III':'Risco III'}"
                    headerKey="" headerValue="Selecione uma classe" 
                    cssClass="form-select"/>
            </div>
            
            <div class="form-row">
             <label for="epiVO.certificadoAprovacao">Certificado de Aprovação</label>
                <s:textfield name="epiVO.certificadoAprovacao" label="CA" 
                    cssClass="form-input" disabled="%{epiVO.id != null}"/>
            </div>
            
            <div class="form-row">
            <label for="epiVO.validadeCAStr">Validade do CA</label>
                <s:textfield type="date" name="epiVO.validadeCAStr"
                    value="%{epiVO.validadeCAStr}" 
                    cssClass="form-input" disabled="%{epiVO.id != null}"/>
            </div>
            
            <div class="form-row">
             <label for="epiVO.dataCadastroStr">Data de Cadastro</label>
                <s:textfield type="date" name="epiVO.dataCadastroStr"
                    value="%{epiVO.dataCadastroStr}" 
                    cssClass="form-input" disabled="%{epiVO.id != null}"/>
            </div>
            
           <div class="form-row">
    			<label>Status:</label>
    			<div class="radio-group">
        			<s:radio name="epiVO.status" list="#{'true':'Ativo', 'false':'Inativo'}" 
            		disabled="%{epiVO.id != null}"/>
    			</div>
			</div>
            
            <div class="form-buttons">
                <s:a href="listarEPI" cssClass="btn-cancelar">Cancelar</s:a>
                <s:submit value="%{epiVO.id == null ? 'Cadastrar' : 'Atualizar'}" cssClass="btn-salvar"/>
            </div>
        </s:form>
    </div>
</body>
</html>