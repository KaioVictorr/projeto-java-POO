package dao;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JOptionPane;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import model.Locacao;
import model.Pagamento;

public class AtendenteDAO {
	
	public void salvarLocacao(Locacao locacao) {
		try {
			//lê o arquivo de locacoes
			FileReader reader = new FileReader("locacoesAbertas.json");
			JsonArray arrayDeLocacoes = JsonParser.parseReader(reader).getAsJsonArray();
	        reader.close();
	        
	        //muda a chave do veiculo locado para true
	        VeiculoDAO veiculoDao = new VeiculoDAO();
	        veiculoDao.mudarChaveVeiculoTrue(locacao.getVeiculo().getModelo());
	        
	        //cria o objeto json e adiciona os atributos
	        JsonObject objeto = new JsonObject();
	        objeto.addProperty("cliente",locacao.getNomeCliente());
			objeto.addProperty("modelo veiculo",locacao.getVeiculo().getModelo());
			objeto.addProperty("tipo",locacao.getVeiculo().getTipo());
			objeto.addProperty("data retirada",locacao.getDataDeRetirada().toString());
			objeto.addProperty("data devolucao",locacao.getDataDeDevolucao().toString());
			
			//adiciona o objeto no array de objetos
			arrayDeLocacoes.add(objeto);
			
			//atualiza o arquivo json
			FileWriter writer = new FileWriter("locacoesAbertas.json");
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			writer.write(gson.toJson(arrayDeLocacoes));
			writer.flush();
			
			        	        
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void registrarPagamentoLocacao(Locacao locacao,Pagamento pagamento) {
		try {
			//abre o aquivo de registro de locacoes
		FileReader reader = new FileReader("registroLocacoes.json");
		JsonArray arrayDeRegistroLocacoes = JsonParser.parseReader(reader).getAsJsonArray();
        reader.close();
        
        //cria o objeto de registro de locacao com o registro de pagamento
        JsonObject objeto = new JsonObject();
        objeto.addProperty("cliente",locacao.getNomeCliente());
        objeto.addProperty("modelo veiculo",locacao.getVeiculo().getModelo());
        objeto.addProperty("tipo",locacao.getVeiculo().getTipo());
        objeto.addProperty("data retirada",locacao.getDataDeRetirada().toString());
        objeto.addProperty("data devolucao prevista",locacao.getDataDeDevolucao().toString());
        objeto.addProperty("data devolucao",pagamento.getDataPagamento().toString());
        objeto.add("registro pagamento",new JsonArray());
        
        JsonObject pagamentoObj = new JsonObject();
        pagamentoObj.addProperty("id pagamento",pagamento.getId());
        pagamentoObj.addProperty("valor pago", pagamento.getValorPago());
        pagamentoObj.addProperty("Data pagamento", pagamento.getDataPagamento().toString());
        pagamentoObj.addProperty("Metodo pagamento", pagamento.getMetodoPagamento());
        

        JsonArray registroArray = objeto.getAsJsonArray("registro pagamento");
        registroArray.add(pagamentoObj);
        
        arrayDeRegistroLocacoes.add(objeto);
        
        //atualiza o arquivo registro de locacoes
        FileWriter writer = new FileWriter("registroLocacoes.json");
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		writer.write(gson.toJson(arrayDeRegistroLocacoes));
		writer.flush();
		
		//exclui a locacao do aquivo de locacoes abertas
		LocacaoDAO locacaoDao = new LocacaoDAO();
		
		locacaoDao.excluirLocacao(locacao.getVeiculo().getModelo(),locacao.getNomeCliente());
		//muda a chave de "locado" do veiculo para false
		VeiculoDAO veiculoDao = new VeiculoDAO();
		veiculoDao.mudarChaveVeiculoFalse(locacao.getVeiculo().getModelo());
		
		JOptionPane.showMessageDialog(null,"Pagamento realizado","",JOptionPane.PLAIN_MESSAGE);
        
		
	}catch(IOException e) {
		e.printStackTrace();
	}
}
	}
