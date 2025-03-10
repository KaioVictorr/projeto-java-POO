package controller;

import java.time.LocalDate;

import javax.swing.JOptionPane;

import dao.AtendenteDAO;
import dao.VeiculoDAO;
import model.Locacao;
import model.Pagamento;
import model.Veiculo;

public class AtendenteController {
	
	public void cadastrarLocacao(String nomeClienteLocacao,String nomeVeiculoLocacao,LocalDate dataRetirada,LocalDate dataDevolucao) {
		//Cria um veiculo
		Veiculo veiculo;
		
		VeiculoDAO veiculoDao = new VeiculoDAO();
		
		//pega o veiculo
		veiculo = veiculoDao.getVeiculo(nomeVeiculoLocacao);
		
		
		//cria uma locacao
		Locacao locacao = new Locacao(nomeClienteLocacao,veiculo,dataRetirada,dataDevolucao);
		
		AtendenteDAO atendenteDao = new AtendenteDAO();
		
		//Salva a locacao no json
		atendenteDao.salvarLocacao(locacao);
		JOptionPane.showMessageDialog(null,"Locação cadastrada","Sucesso",JOptionPane.PLAIN_MESSAGE);
	}
	
	public void registrarPagamentoLocacao(Locacao locacao,Pagamento pagamento) {
		AtendenteDAO atendenteDao = new  AtendenteDAO();		
		atendenteDao.registrarPagamentoLocacao(locacao, pagamento);
	}
	
	
	
}
