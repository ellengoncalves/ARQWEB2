package br.edu.ifsp.arq.tsi.arqweb2.techcare.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

public class OrdemServico implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	protected Long codigo;
	private String descricao;
	private LocalDate dataEmissao;
	private LocalDate dataFinalizacao;
	private BigDecimal valor;
	private String observacao;
	private Cliente cliente;
	private OrdemServicoStatus status;
	
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

	public OrdemServicoStatus getStatus() {
		return status;
	}

	public void setStatus(OrdemServicoStatus status) {
		this.status = status;
	}

	@Override
	public int hashCode() {
		return Objects.hash(codigo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrdemServico other = (OrdemServico) obj;
		return Objects.equals(codigo, other.codigo);
	}
}