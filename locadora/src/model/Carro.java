package model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Carro extends Veiculo{
	
	private String tipo = "Carro";
	
	public double calcularCustoLocacao(LocalDate dataRetirada,LocalDate dataDevolucaoPrevista,LocalDate dataDevolucao) {
		Double precoDiaLocacaoCarro = 4.0;
		
		Double precoLocacao = 0.0;
		
		//se a data de devolucao for após o periodo definido na locacao			
		if(dataDevolucao.isAfter(dataDevolucaoPrevista)) {
			//define o valor do dia com multa
			Double multaDiaLocacaoCarro = 8.0;
			
			//pega os dias que não tiveram multa
			long diasLocacaoSemMulta = ChronoUnit.DAYS.between(dataRetirada, dataDevolucaoPrevista);
			//define o preco da locacao
			precoLocacao = precoDiaLocacaoCarro * diasLocacaoSemMulta;
			//pega os dias de atraso na entrega				
			long diasAposDataDevolucaoPrevista = ChronoUnit.DAYS.between(dataDevolucaoPrevista, dataDevolucao);
			//define o preco da multa
			Double precoMulta = multaDiaLocacaoCarro * diasAposDataDevolucaoPrevista;
			//atualiza o preco da locacao com a multa
			precoLocacao = precoLocacao + precoMulta;
			//se a entrega for antes do periodo definido na locacao ou no dia previsto	
			
		}else if(dataDevolucao.isEqual(dataDevolucaoPrevista) || dataDevolucao.isBefore(dataDevolucaoPrevista)){
			//calcula os dias
			long diasLocacao = ChronoUnit.DAYS.between(dataRetirada, dataDevolucao) ;
			//define o preço
			precoLocacao = precoDiaLocacaoCarro * diasLocacao;
		}
		
		return precoLocacao;
	}
	
	public String getTipo() {
		return this.tipo;
	}
}
