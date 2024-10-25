package br.edu.ifsp.arq.tsi.arqweb2.techcare.model;

public enum Status {
	EM_APROVACAO("Em aprovação"),
	APROVADA("Aprovada"),
	EM_ANDAMENTO("Em andamento"),
	FINALIZADA("Finalizada");
	
	private String descricao;
	
	private Status(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}
}
