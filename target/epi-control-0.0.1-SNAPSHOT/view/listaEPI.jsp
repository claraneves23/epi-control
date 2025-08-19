<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>        
<html>
<head>
<meta charset="UTF-8">
<title>Lista de EPIs</title>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
<link href="${pageContext.request.contextPath}/css/styles.css?v=1.3" rel="stylesheet">
</head>
<body>
<header>
	<h2 class="logo">EPIs Cadastrados</h2>
</header>
	
<div class="search-container">
	<s:form action="buscarEPI" method="get">
	 <div class="search-field">
    	<s:textfield name="nomeBusca" label="Buscar por nome" />
     </div>
     <div class="btn-search">
    	<s:submit value="Buscar" />
      </div>
	</s:form>
</div>
	
	<table>
		<thead>
			<tr>
				<th>Nome</th>
				<th>Descrição</th>
				<th>CA</th>
				<th>Validade do CA</th>
				<th>Classe de Risco</th>
				<th>Data de Cadastro</th>
				<th>Status</th>
			</tr>
		</thead>
		<tbody>
			<s:iterator value="listaParaExibir" var="epi">
				<tr style="background-color: <s:property value="vencido ? '#ffcccc' : 'transparent'"/>;">
				    <td><s:hidden name="epiVO.id" value="%{#epi.id}" /></td>
					<td><s:property value="nome"/></td>
					<td><s:property value="descricao"/></td>
					<td><s:property value="certificadoAprovacao"/></td>
					
					<td><s:property value="validadeCAStr"/></td>
					<td><s:property value="classeRisco"/></td>
					<td><s:property value="dataCadastroStr"/></td>
					<td>
						<s:if test="status">Ativo</s:if>
						<s:else>Inativo</s:else>
					</td>
					 <td class="acoes">
                        <!-- Botão Editar -->
                        <s:url action="carregarEdicaoEPI" var="editarLink">
    						<s:param name="epiVO.id" value="id"/>
						</s:url>
                        <s:a href="%{editarLink}"><i class="fa fa-edit"></i></s:a>
                        
                        <!-- Botão Excluir -->
                        <s:url action="excluirEPI" var="excluirLink">
                            <s:param name="epiVO.id" value="id"/>
                        </s:url>
                       	<s:a href="%{excluirLink}" 
     						onclick="return confirm('Tem certeza que deseja excluir este EPI?')">
     						<i class="fa fa-trash"></i>
						</s:a>
                    </td>
				</tr>
			</s:iterator>
		</tbody>
	</table>
	
	
	
	 <s:url action="epiInputForm" var="epiInputLink" />
	 <div class="btn-cad">
	 	<p><s:a href="%{epiInputLink}">Cadastrar</s:a></p>
	 </div>
</body>
</html>