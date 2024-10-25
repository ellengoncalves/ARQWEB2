package br.edu.ifsp.arq.tsi.arqweb2.techcare.model;

import java.io.Serializable;
import java.util.Objects;

public class FormaPagamento implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Long codigo;
	private String nome;
	private OrdemServico ordemServico;
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public OrdemServico getOrdemServico() {
		return ordemServico;
	}

	public void setOrdemServico(OrdemServico ordemServico) {
		this.ordemServico = ordemServico;
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

