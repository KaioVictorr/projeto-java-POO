package model;

import java.time.LocalDate;
import java.util.Random;

public class Pagamento {
	
	private int id;
	private Double valorPago;
	private LocalDate dataPagemnto;
	private String metodoPagamento;
	
	public Pagamento(Double valorPago,LocalDate dataPagamento,String metodoPagamento) {
		Random random = new Random();
		this.id = random.nextInt(100000);
		this.valorPago = valorPago;
		this.dataPagemnto = dataPagamento;
		this.metodoPagamento = metodoPagamento;
	}

	public Double getValorPago() {
		return valorPago;
	}

	public LocalDate getDataPagamento() {
		return dataPagemnto;
	}
	
	public String getMetodoPagamento() {
		return metodoPagamento;
	}

	public int getId() {
		return id;
	}
	
	
}
