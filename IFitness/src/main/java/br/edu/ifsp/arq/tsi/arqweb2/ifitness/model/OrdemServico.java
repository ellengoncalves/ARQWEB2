package br.edu.ifsp.arq.tsi.arqweb2.ifitness.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

public class OrdemServico implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Long codigo;
	private String descricao;
	private LocalDate dataEmissao;
	private LocalDate dataFinalizacao;
	private BigDecimal valor;
	private String observacao;
	
	public Long getCodigo() {
		return codigo;
	}
	
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public LocalDate getDataEmissao() {
		return dataEmissao;
	}
	
	public void setDataEmissao(LocalDate dataEmissao) {
		this.dataEmissao = dataEmissao;
	}
	
	public LocalDate getDataFinalizacao() {
		return dataFinalizacao;
	}
	
	public void setDataFinalizacao(LocalDate dataFinalizacao) {
		this.dataFinalizacao = dataFinalizacao;
	}
	
	public BigDecimal getValor() {
		return valor;
	}
	
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	
	public String getObservacao() {
		return observacao;
	}
	
	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
}
