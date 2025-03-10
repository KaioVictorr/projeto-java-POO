package dao;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import model.Caminhao;
import model.Carro;
import model.Moto;
import model.Veiculo;

public class VeiculoDAO {
	
public boolean verificarPlaca(Veiculo veiculoo) {
		
		boolean resultPlacaExistente = false;
		
		try {
			//Lê o arquivo json de veiculos
			FileReader reader = new FileReader("veiculos.json");
			JsonArray arrayDeVeiculos = JsonParser.parseReader(reader).getAsJsonArray();
	        reader.close();
	        	
	        	//itera sobre os objetos do arquivo
	        	for(JsonElement veiculo : arrayDeVeiculos) {
	        	JsonObject veiculoObj = veiculo.getAsJsonObject();
	        	String placaJson = veiculoObj.get("placa").getAsString();
	        	
	        	//verifica se a placa do objeto json é igual a placa do veiculo informado por parametro
	        	if(placaJson.equals(veiculoo.getPlaca())){
	        		resultPlacaExistente = true;
	        		break;
	        	}
	        }
	        		       
			}catch(IOException e){
				e.printStackTrace();
			}
		
		return resultPlacaExistente; // retorna o resultado da busca
	}

	public List<JsonObject> listaVeiculos(){
		//cria um arraylist de todos os veiculos do json
		List<JsonObject> listaDeObjetos = new ArrayList<JsonObject>();
		
		try {
			FileReader reader = new FileReader("veiculos.json");
			JsonArray arrayDeVeiculos = JsonParser.parseReader(reader).getAsJsonArray();
	        reader.close();
	        
	        	for(JsonElement veiculo : arrayDeVeiculos) {
	        	JsonObject veiculoObj = veiculo.getAsJsonObject();
	        	//adiciona todos os veiculos no arraylist
	        	listaDeObjetos.add(veiculoObj);
	        }	        		       
			}catch(IOException e){
				e.printStackTrace();
			}
		
		return listaDeObjetos;//retorna a lista
}
	
public List<String> listaVeiculosDisponiveis(){
		//arraylist de veiculos que estão disponiveis para locacao
		List<String> listaDeVeiculosDisponiveis = new ArrayList<String>();
		
		try {
			FileReader reader = new FileReader("veiculos.json");
			JsonArray arrayDeVeiculos = JsonParser.parseReader(reader).getAsJsonArray();
	        reader.close();
	        	
	        //se a cheve do veiculo "locado" for false, adiciona o modelo do veiculo na lista
	        	for(JsonElement veiculo : arrayDeVeiculos) {
	        	JsonObject veiculoObj = veiculo.getAsJsonObject();
	        	if(veiculoObj.get("locado").getAsBoolean() == false) {
	        		listaDeVeiculosDisponiveis.add(veiculoObj.get("modelo").getAsString());
	        	}
	        }	        		       
			}catch(IOException e){
				e.printStackTrace();
			}
		
		return listaDeVeiculosDisponiveis;// retorna a lista dos veiculos disponiveis
}
public Veiculo getVeiculo(String modeloVeiculo) {
	
		Veiculo veiculoo = null;
		
		try {
			FileReader reader = new FileReader("veiculos.json");
			JsonArray arrayDeVeiculos = JsonParser.parseReader(reader).getAsJsonArray();
	        reader.close();
	        	
	        	// pega o tipo do veiculo e seta o modelo
	        	for(JsonElement veiculo : arrayDeVeiculos) {
	        	JsonObject veiculoObj = veiculo.getAsJsonObject();
	        	String modeloJson = veiculoObj.get("modelo").getAsString();
	        	if(modeloJson.equals(modeloVeiculo)){
	        		if(veiculoObj.get("tipo").getAsString().equals("Moto")) {
	        			Moto moto = new Moto();
	        			moto.setModelo(modeloVeiculo);
	        			veiculoo = moto;
	        		}else if(veiculoObj.get("tipo").getAsString().equals("Carro")) {
	        			Carro carro = new Carro();
	        			carro.setModelo(modeloVeiculo);
	        			veiculoo = carro;
	        		}else {
	        			Caminhao caminhao = new Caminhao();
	        			caminhao.setModelo(modeloVeiculo);
	        			veiculoo = caminhao;
	        		}
	        		break;
	        	}
	        }
	        		       
			}catch(IOException e){
				e.printStackTrace();
			}
		
		return veiculoo; // retorna o veiculo 
}

	public void mudarChaveVeiculoTrue(String modeloVeiculo) { // muda a chave "locado" para true 
		try {
			FileReader reader = new FileReader("veiculos.json");
			JsonArray arrayDeVeiculos = JsonParser.parseReader(reader).getAsJsonArray();
	        reader.close();
	        	
	        	//pega o modelo por parametro e troca a chave para true
	        	for(JsonElement veiculo : arrayDeVeiculos) {
	        	JsonObject veiculoObj = veiculo.getAsJsonObject();
	        	String modeloJson = veiculoObj.get("modelo").getAsString();
	        	if(modeloJson.equals(modeloVeiculo)){
	        		veiculoObj.addProperty("locado", true);
	        		break;
	        	}
	        }
	        	//atualiza o arquivo e o veiculo com a chave "locado" true
	        	FileWriter writer = new FileWriter("veiculos.json");
				Gson gson = new GsonBuilder().setPrettyPrinting().create();
				writer.write(gson.toJson(arrayDeVeiculos));
				writer.flush();
	        		       
			}catch(IOException e){
				e.printStackTrace();
			}
	}
	

	public Veiculo getVeiculoLocado(String modeloVeiculo) {
		
		Veiculo veiculoo = null;
		
		try {
			// abre o arquivo json das locacoes abertas
			FileReader reader = new FileReader("locacoesAbertas.json");
			JsonArray arrayDeVeiculos = JsonParser.parseReader(reader).getAsJsonArray();
	        reader.close();
	        	
	        	//procura o veiculo passado por parametro
	        	for(JsonElement veiculo : arrayDeVeiculos) {
	        	JsonObject veiculoObj = veiculo.getAsJsonObject();
	        	String modeloJson = veiculoObj.get("modelo veiculo").getAsString();
	        	if(modeloJson.equals(modeloVeiculo)){
	        		if(veiculoObj.get("tipo").getAsString().equals("Moto")) {
	        			Moto moto = new Moto();
	        			moto.setModelo(modeloVeiculo);
	        			veiculoo = moto;
	        		}else if(veiculoObj.get("tipo").getAsString().equals("Carro")) {
	        			Carro carro = new Carro();
	        			carro.setModelo(modeloVeiculo);
	        			veiculoo = carro;
	        		}else {
	        			Caminhao caminhao = new Caminhao();
	        			caminhao.setModelo(modeloVeiculo);
	        			veiculoo = caminhao;
	        		}
	        		break;
	        	}
	        }
	        		       
			}catch(IOException e){
				e.printStackTrace();
			}
		
		return veiculoo;//retorna o veiculo das locacoes abertas
}
	
	public void mudarChaveVeiculoFalse(String modeloVeiculo) {// muda a chave "locado" do veiculo para false
		try {
			FileReader reader = new FileReader("veiculos.json");
			JsonArray arrayDeVeiculos = JsonParser.parseReader(reader).getAsJsonArray();
	        reader.close();
	        
	        	for(JsonElement veiculo : arrayDeVeiculos) {
	        	JsonObject veiculoObj = veiculo.getAsJsonObject();
	        	String modeloJson = veiculoObj.get("modelo").getAsString();
	        	if(modeloJson.equals(modeloVeiculo)){
	        		veiculoObj.addProperty("locado", false);
	        		break;
	        	}
	        }
	        	//atualiza o veiculo e o arquivo
	        	FileWriter writer = new FileWriter("veiculos.json");
				Gson gson = new GsonBuilder().setPrettyPrinting().create();
				writer.write(gson.toJson(arrayDeVeiculos));
				writer.flush();
	        		       
			}catch(IOException e){
				e.printStackTrace();
			}
	}
}
