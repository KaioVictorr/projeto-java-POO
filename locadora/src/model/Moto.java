package model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Moto extends Veiculo{
	
	private String tipo = "Moto";
	
	public double calcularCustoLocacao(LocalDate dataRetirada,LocalDate dataDevolucaoPrevista,LocalDate dataDevolucao) {
		Double precoDiaLocacaoMoto = 2.0;
		
		Double precoLocacao = 0.0;
		
		//se a data de devolucao for após o periodo definido na locacao					
		if(dataDevolucao.isAfter(dataDevolucaoPrevista)) {
			//define o valor do dia com multa
			Double multaDiaLocacaoMoto = 4.0;
			
			//pega os dias que não tiveram multa
			long diasLocacaoSemMulta = ChronoUnit.DAYS.between(dataRetirada, dataDevolucaoPrevista);
			//define o preco da locacao
			precoLocacao = precoDiaLocacaoMoto * diasLocacaoSemMulta;
			//pega os dias de atraso na entrega				
			long diasAposDataDevolucaoPrevista = ChronoUnit.DAYS.between(dataDevolucaoPrevista, dataDevolucao);
			//define o preco da multa
			Double precoMulta = multaDiaLocacaoMoto * diasAposDataDevolucaoPrevista;
			//atualiza o preco da locacao com a multa
			precoLocacao = precoLocacao + precoMulta;
		//se a entrega for antes do periodo definido na locacao ou no dia previsto	
		}else if(dataDevolucao.isEqual(dataDevolucaoPrevista) || dataDevolucao.isBefore(dataDevolucaoPrevista)){
			//calcula os dias
			long diasLocacao = ChronoUnit.DAYS.between(dataRetirada, dataDevolucao) ;
			//define o preço
			precoLocacao = precoDiaLocacaoMoto * diasLocacao;
		}
		
		return precoLocacao;
	}
	
	public String getTipo() {
		return this.tipo;
	}
}
