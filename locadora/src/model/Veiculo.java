package model;

import java.time.LocalDate;

public abstract class Veiculo {
	protected String placa;
	protected String modelo;
	protected String ano;
	protected boolean locado;
	
	public abstract String getTipo();
	
	public String getPlaca() {
		return this.placa;
	}
	
	public void setPlaca(String placa) {
		this.placa = placa;
	}
	
	public String getModelo() {
		return this.modelo;
	}
	
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	
	public String getAno() {
		return this.ano;
	}
	
	public void setAno(String ano) {
		this.ano = ano;
	}
	
	public boolean isLocado() {
		return locado;
	}
	
	public void setLocado(boolean locado) {
		this.locado = locado;
	}
	
	public abstract double calcularCustoLocacao(LocalDate dataRetirada,LocalDate dataDevolucaoPrevista,LocalDate dataDevolucao);
}
