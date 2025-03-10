package dao;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import model.Locacao;
import model.Veiculo;

public class LocacaoDAO {
	
	public List<String> getModeloVeiculoLocados(){ 
		
		List<String> veiculosLocados = new ArrayList<String>(); 
		
		try {
			FileReader reader = new FileReader("locacoesAbertas.json");
			JsonArray arrayDeVeiculosLocados = JsonParser.parseReader(reader).getAsJsonArray();
	        reader.close();
	        
	        //itera sobre o arquivo de locacoes abertas
	        for(JsonElement veiculoLocado : arrayDeVeiculosLocados) {
	        	JsonObject veiculoLocadoObj = veiculoLocado.getAsJsonObject();
	        	String modeloVeiculo = veiculoLocadoObj.get("modelo veiculo").getAsString();
	        	//adiciona todos os veiculos que estão locados no arraylist
	        	veiculosLocados.add(modeloVeiculo);
	        }
	        
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		
		return veiculosLocados; // retorna o arraylist com os modelos de veiculos locados
	}
	
	
	public Locacao getLocacao(String modeloVeiculoLocado) { // pega uma locacao aberta
		
		Locacao locacao = new Locacao();
		
		VeiculoDAO veiculoDao = new VeiculoDAO();
		Veiculo veiculo = veiculoDao.getVeiculoLocado(modeloVeiculoLocado); // pega o veiculo da locacao
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		
		try {
			FileReader reader = new FileReader("locacoesAbertas.json");
			JsonArray arrayDeVeiculosLocados = JsonParser.parseReader(reader).getAsJsonArray();
	        reader.close();
	        
	        //seta os atributos da locacao
	        for(JsonElement veiculoLocado : arrayDeVeiculosLocados) {
	        	JsonObject veiculoLocadoObj = veiculoLocado.getAsJsonObject();
	        	String modeloVeiculo = veiculoLocadoObj.get("modelo veiculo").getAsString();
	        	if(modeloVeiculo.equals(modeloVeiculoLocado)) {
	        		locacao.setNomeCliente(veiculoLocadoObj.get("cliente").getAsString());
	        		locacao.setVeiculo(veiculo);
	        		LocalDate dataRetirada = LocalDate.parse(veiculoLocadoObj.get("data retirada").getAsString());
	        		locacao.setDataDeRetirada(dataRetirada);
	        		LocalDate dataDevolucao = LocalDate.parse(veiculoLocadoObj.get("data devolucao").getAsString());
	        		locacao.setDataDeDevolucao(dataDevolucao);
	        	}
	        }
	        
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		return locacao; // retorna a locacao
	}
	
	public void excluirLocacao(String modeloVeiculo,String nomeCliente) {
		try {
			//lê o arquivo de locacoes abertas
			FileReader reader = new FileReader("locacoesAbertas.json");
			JsonArray arrayLocacoesAbertas = JsonParser.parseReader(reader).getAsJsonArray();
	        reader.close();
	        
	        // cria um iterator para percorrer todo o arquivo
	        Iterator<JsonElement> iterator = arrayLocacoesAbertas.iterator();
	        
	        //enquanto tiver locacao o while não para
	        while(iterator.hasNext()) {
	        	JsonObject locacao = iterator.next().getAsJsonObject();
	        	String modeloVeiculoObj = locacao.get("modelo veiculo").getAsString();
	        	String nomeClienteObj = locacao.get("cliente").getAsString();
	        	//se encontrar o modelo e o cliente passado por parametro,exclui a locacao
	        	if(modeloVeiculo.equals(modeloVeiculoObj) && nomeCliente.equals(nomeCliente)) {
        			iterator.remove();
        			}
	        }
	        
	        //atualiza o arquivo depois de excluir a locacao
	        FileWriter writer = new FileWriter("locacoesAbertas.json");
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			writer.write(gson.toJson(arrayLocacoesAbertas));
			writer.flush();
	        
	        
		}catch(IOException e) {
		e.printStackTrace();
		}
	
}
	
 public List<JsonObject> listaRegistroDeLocacoes(){ // Retorna um arrayList dos registros de locacoes
		
		List<JsonObject> listaDeObjetos = new ArrayList<JsonObject>(); // Cria o arraylist
		
		try {
			//Abre o arquivo
			FileReader reader = new FileReader("registroLocacoes.json");
			JsonArray arrayDeRegistroDeLocacoes = JsonParser.parseReader(reader).getAsJsonArray();
	        reader.close();
	        
	        //Itera sobre cada registro
	        for(JsonElement locacao : arrayDeRegistroDeLocacoes) {
	        	JsonObject locacaoObj = locacao.getAsJsonObject(); // pega o registro e adiciona ao arraylist
	        	listaDeObjetos.add(locacaoObj);
	        	}
	        
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		return listaDeObjetos;
		
	}
	}
