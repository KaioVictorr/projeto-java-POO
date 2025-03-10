package model;

import java.time.LocalDate;
import java.util.Date;

public class Locacao {
	private String nomeCliente;
	private Veiculo veiculo;
	private LocalDate dataDeRetirada;
	private LocalDate dataDeDevolucao;
	
	public Locacao(String nomeCliente,Veiculo veiculo,LocalDate dataDeRetirada,LocalDate dataDeDevolucao) {
		this.nomeCliente = nomeCliente;
		this.veiculo = veiculo;
		this.dataDeRetirada = dataDeRetirada;
		this.dataDeDevolucao = dataDeDevolucao;
	}
	
	public Locacao() {
		
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	public Veiculo getVeiculo() {
		return veiculo;
	}

	public void setVeiculo(Veiculo veiculo) {
		this.veiculo = veiculo;
	}

	public LocalDate getDataDeRetirada() {
		return dataDeRetirada;
	}

	public void setDataDeRetirada(LocalDate dataDeRetirada) {
		this.dataDeRetirada = dataDeRetirada;
	}

	public LocalDate getDataDeDevolucao() {
		return dataDeDevolucao;
	}

	public void setDataDeDevolucao(LocalDate dataDeDevolucao) {
		this.dataDeDevolucao = dataDeDevolucao;
	}
	
	
}
