package controller;

import java.io.IOException;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.google.gson.JsonObject;
import com.lowagie.text.DocumentException;

import dao.ClienteDAO;
import dao.CriarPdf;
import dao.GerenteDAO;
import dao.LocacaoDAO;
import dao.VeiculoDAO;
import model.Caminhao;
import model.Carro;
import model.Cliente;
import model.Moto;
import model.Veiculo;

public class GerenteController {
	public boolean cadastrarVeiculo(String placa,String modelo,String ano,String tipoVeiculo) {
		
		//instancia um veiculo
		Veiculo veiculo;
		
		boolean veiculoCadastrado = false;
		
		//verifica qual tipo de veiculo,seta os atributos e define veiculo dependendo do tipo
		if(tipoVeiculo.equals("Moto")) {
			Moto moto = new Moto();
			moto.setPlaca(placa);
			moto.setModelo(modelo);
			moto.setAno(ano);
			veiculo = moto;
		}else if(tipoVeiculo.equals("Carro")) {
			Carro carro = new Carro();
			carro.setPlaca(placa);
			carro.setModelo(modelo);
			carro.setAno(ano);
			veiculo = carro;
		}else {
			Caminhao caminhao = new Caminhao();
			caminhao.setPlaca(placa);
			caminhao.setModelo(modelo);
			caminhao.setAno(ano);
			veiculo = caminhao;				
		}
		
		VeiculoDAO veiculoDao = new VeiculoDAO();
		
		//verifica se já tem placa do veiculo existente
		if(veiculoDao.verificarPlaca(veiculo)) {
			JOptionPane.showMessageDialog(null, "Placa já Existente!");
		}else {
			//salva o veiculo
			GerenteDAO gerenteDao = new GerenteDAO();
			gerenteDao.salvarVeiculo(veiculo);
			JOptionPane.showInternalMessageDialog(null,"Veiculo cadastrado com sucesso","Sucesso", JOptionPane.PLAIN_MESSAGE);
			veiculoCadastrado = true;
		}
		return veiculoCadastrado; // retorna se o veiculo foi cadastrado ou não
	}
	
	public boolean cadastrarCliente(String nome,String cpf,String telefone,String email) {
		boolean clienteCadastrado = false;
		
		//instancia um cliente
		Cliente cliente = new Cliente();
		
		//seta seus atributos
		cliente.setNome(nome);
		cliente.setCpf(cpf);
		cliente.setTelefone(telefone);
		cliente.setEmail(email);
		
		ClienteDAO clienteDao = new ClienteDAO();
		//verifica se tem cpf existente no json
		if(clienteDao.verificarCpf(cliente)) {
			JOptionPane.showMessageDialog(null, "CPF já Existente!","Erro",JOptionPane.ERROR_MESSAGE);
		}else {
			//salva o cliente
			GerenteDAO gerenteDao = new GerenteDAO();
			gerenteDao.salvarCliente(cliente);
			JOptionPane.showInternalMessageDialog(null,"Cliente cadastrado com sucesso","Sucesso", JOptionPane.PLAIN_MESSAGE);
			clienteCadastrado = true;
		}
	return clienteCadastrado; // retorna se o cliente foi cadastrado
	}
	
	
	public void editarCliente(String nomeClienteSerEditado,String novoNomeCliente,String novoTelefoneCliente,String novoEmailCliente) {
		//cria um cliente e atribui os valores
		Cliente cliente = new Cliente();
		
		cliente.setNome(novoNomeCliente);
		cliente.setTelefone(novoTelefoneCliente);
		cliente.setEmail(novoEmailCliente);
		
		GerenteDAO gerenteDao = new GerenteDAO();
		
		gerenteDao.editarCliente(cliente, nomeClienteSerEditado);
		JOptionPane.showInternalMessageDialog(null,"Cliente editado com sucesso","Sucesso", JOptionPane.PLAIN_MESSAGE);
	}
	


	public void excluirCliente(String nomeClienteExcluir) {
		GerenteDAO gerenteDao = new GerenteDAO();
		gerenteDao.excluirCliente(nomeClienteExcluir);
		JOptionPane.showInternalMessageDialog(null,"Cliente excluido com sucesso","Sucesso", JOptionPane.PLAIN_MESSAGE);
	}
	
	public void exibirRelatorioLocacoes() {
		
		LocacaoDAO locacaoDao = new LocacaoDAO();
		//criando modelo
		DefaultTableModel modelo = new DefaultTableModel();
		
		//definindo as colunas
		modelo.addColumn("Cliente");
		modelo.addColumn("Modelo");
		modelo.addColumn("Data retirada");
		modelo.addColumn("Data devolucao");
		modelo.addColumn("id pagamento");
		modelo.addColumn("valor");
		modelo.addColumn("data pagamento");
		modelo.addColumn("metodo pagamento");
		
		List<JsonObject> lista = locacaoDao.listaRegistroDeLocacoes(); // pega a listaDeRegistros e verifica se ela não está vazia
		if(lista.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Não há registros de locações", "Erro", JOptionPane.ERROR_MESSAGE);
		}else {
		for(JsonObject locacao : lista) {
			String[] linha = new String[8]; // array de colunas
			JsonObject locacaoObj = locacao.getAsJsonObject(); // pega a locacao do arquivo
			JsonObject pagamentosObj = locacaoObj.getAsJsonArray("registro pagamento").get(0).getAsJsonObject(); // pega o registro de pagamento da locacao
			
			//pega os valores da locacao e coloca em cada linha
			linha[0] = locacaoObj.get("cliente").getAsString();
			linha[1] = locacaoObj.get("modelo veiculo").getAsString();
			linha[2]  = locacaoObj.get("data retirada").getAsString();
			linha[3] = locacaoObj.get("data devolucao").getAsString();			
			linha[4] = pagamentosObj.get("id pagamento").getAsString() + "";
			linha[5] = pagamentosObj.get("valor pago").getAsString() + "";
			linha[6] = pagamentosObj.get("Data pagamento").getAsString();
			linha[7] = pagamentosObj.get("Metodo pagamento").getAsString();
			
			
					
			//adiciona todas as linhas no modelo		
			modelo.addRow(linha);
		}
		
		//cria a tabela passsando o modelo
		JTable tabela = new JTable(modelo);
		
		
		//cria o pdf
		CriarPdf criarPdf = new CriarPdf();
		try {
			criarPdf.criarPfd(tabela,"relatorioLocacoes.pdf");
		} catch (DocumentException e1) {			
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}
	}
	
}
