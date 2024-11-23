package br.edu.ifsp.arq.tsi.arqweb2.techcare.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class OrdemServico {

	protected Long codigo;
	private String descricao;
	private LocalDate dataEmissao;
	private LocalDate dataFinalizacao;
	private BigDecimal valor;
	private String observacao;
	private Cliente cliente;
	private Status status;
	private FormaPagamento formaPagamento;

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

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public FormaPagamento getFormaPagamento() {
		return formaPagamento;
	}

	public void setFormaPagamento(FormaPagamento formaPagamento) {
		this.formaPagamento = formaPagamento;
	}
}

/*
 * package br.edu.ifsp.arq.tsi.arqweb2.model;
 * 
 * import java.math.BigDecimal; import java.time.LocalDate;
 * 
 * public class OrdemServico { private long codigo; private String descricao;
 * private LocalDate dataEmissao; private LocalDate dataFinalizacao; private
 * BigDecimal valor; private String observacao; private Status status; private
 * FormaPagamento formaPagamento; private Cliente cliente;
 * 
 * public long getCodigo() { return codigo; }
 * 
 * public void setCodigo(long codigo) { this.codigo = codigo; }
 * 
 * public String getDescricao() { return descricao; }
 * 
 * public void setDescricao(String descricao) { this.descricao = descricao; }
 * 
 * public LocalDate getDataEmissao() { return dataEmissao; }
 * 
 * public void setDataEmissao(LocalDate dataEmissao) { this.dataEmissao =
 * dataEmissao; }
 * 
 * public LocalDate getDataFinalizacao() { return dataFinalizacao; }
 * 
 * public void setDataFinalizacao(LocalDate dataFinalizacao) {
 * this.dataFinalizacao = dataFinalizacao; }
 * 
 * public BigDecimal getValor() { return valor; }
 * 
 * public void setValor(BigDecimal valor) { this.valor = valor; }
 * 
 * public String getObservacao() { return observacao; }
 * 
 * public void setObservacao(String observacao) { this.observacao = observacao;
 * }
 * 
 * public Status getStatus() { return status; }
 * 
 * public void setStatus(Status status) { this.status = status; }
 * 
 * public FormaPagamento getFormaPagamento() { return formaPagamento; }
 * 
 * public void setFormaPagamento(FormaPagamento formaPagamento) {
 * this.formaPagamento = formaPagamento; }
 * 
 * public Cliente getCliente() { return cliente; }
 * 
 * public void setCliente(Cliente cliente) { this.cliente = cliente; } }
 */