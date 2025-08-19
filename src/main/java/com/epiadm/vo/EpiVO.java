package com.epiadm.vo;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;

public class EpiVO {
	
	private Long id;
	private String nome;
	private String descricao;
	private String certificadoAprovacao;
	private LocalDate validadeCA;
	private String classeRisco;
	private LocalDate dataCadastro;
	private boolean status;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getCertificadoAprovacao() {
		return certificadoAprovacao;
	}
	public void setCertificadoAprovacao(String certificadoAprovacao) {
		this.certificadoAprovacao = certificadoAprovacao;
	}
	public LocalDate getValidadeCA() {
		System.out.println("Validade CA (VO): " + this.validadeCA);
		return validadeCA;
	}
	
	 public void setValidadeCA(LocalDate validadeCA) {
	        this.validadeCA = validadeCA;
	 }
     
	public String getClasseRisco() {
		return classeRisco;
	}
	public void setClasseRisco(String classeRisco) {
		this.classeRisco = classeRisco;
	}
	public LocalDate getDataCadastro() {
		return dataCadastro;
	}
	
	public void setDataCadastro(LocalDate dataCadastro) {
        this.dataCadastro = dataCadastro;
    }
	
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public void setValidadeCA(String dateStr) {
	    if(dateStr != null && !dateStr.isEmpty()) {
	        this.validadeCA = LocalDate.parse(dateStr);
	    }
	}

	public void setDataCadastro(String dateStr) {
	    if(dateStr != null && !dateStr.isEmpty()) {
	        this.dataCadastro = LocalDate.parse(dateStr);
	    }
	}
	
	public String getValidadeCAStr() {
	    return validadeCA != null ? validadeCA.toString() : "";
	}

	public void setValidadeCAStr(String dateStr) {
	    if(dateStr != null && !dateStr.isEmpty()) {
	        this.validadeCA = LocalDate.parse(dateStr);
	    }
	}

	public String getDataCadastroStr() {
	    return dataCadastro != null ? dataCadastro.toString() : "";
	}

	public void setDataCadastroStr(String dateStr) {
	    if(dateStr != null && !dateStr.isEmpty()) {
	        this.dataCadastro = LocalDate.parse(dateStr);
	    }
	}
}
