package br.edu.ifsp.arq.tsi.arqweb2.techcare.model.dto;

public class StatusByType {
	
	private String type;
	private Integer count;
	
	public StatusByType(String type, Integer count) {
		this.type = type;
		this.count = count;
	}
	
	public StatusByType() {
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public Integer getCount() {
		return count;
	}
	
	public void setCount(Integer count) {
		this.count = count;
	}
}
