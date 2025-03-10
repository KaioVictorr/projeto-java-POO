package dao;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;

import javax.swing.JOptionPane;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import model.Cliente;
import model.Veiculo;

public class GerenteDAO {
	
	public void salvarVeiculo(Veiculo veiculo) {		
			try {
				//lê o arquivo dos veiculos
				FileReader reader = new FileReader("veiculos.json");
				JsonArray arrayDeVeiculos = JsonParser.parseReader(reader).getAsJsonArray();
		        reader.close();
		        
		        //pega os atributos do veiculo e coloca dentro de um objeto json
		        JsonObject objeto = new JsonObject();
		        objeto.addProperty("placa",veiculo.getPlaca());
				objeto.addProperty("modelo",veiculo.getModelo());
				objeto.addProperty("ano",veiculo.getAno());
				objeto.addProperty("tipo",veiculo.getTipo());
				objeto.addProperty("locado",veiculo.isLocado());
				
				//adiciona o objeto dentro do json que é um array de veiculos
				arrayDeVeiculos.add(objeto);
				
				//atualiza o arquivo json com o objeto já escrito
				FileWriter writer = new FileWriter("veiculos.json");
				Gson gson = new GsonBuilder().setPrettyPrinting().create();
				writer.write(gson.toJson(arrayDeVeiculos));
				writer.flush();
							        	        
			}catch(IOException e) {
				e.printStackTrace();
			}
			
		}
	
	
	public void salvarCliente(Cliente cliente) {
			try {
				//lê o arquivo json dos clientes
				FileReader reader = new FileReader("clientes.json");
				JsonArray arrayDeClientes = JsonParser.parseReader(reader).getAsJsonArray();
		        reader.close();
		        
		        //pega os atributos do cliente e coloca no objeto json
		        JsonObject objeto = new JsonObject();
		        objeto.addProperty("nome",cliente.getNome());
				objeto.addProperty("cpf",cliente.getCpf());
				objeto.addProperty("telefone",cliente.getTelefone());
				objeto.addProperty("email",cliente.getEmail());
				
				//adiciona o objeto json no arquivo de clientes
				arrayDeClientes.add(objeto);
				
				//atualiza o arquivo json de clientes
				FileWriter writer = new FileWriter("clientes.json");
				Gson gson = new GsonBuilder().setPrettyPrinting().create();
				writer.write(gson.toJson(arrayDeClientes));
				writer.flush();
											        	        
			}catch(IOException e) {
				e.printStackTrace();
			}
		}
	
	public void editarCliente(Cliente cliente,String nomeClienteSerEditado) {		
		try {
			//Lê o arquivo json dos clientes
			FileReader reader = new FileReader("clientes.json");
			JsonArray arrayDeClientes = JsonParser.parseReader(reader).getAsJsonArray();
	        reader.close();
	        	
	        	//faz um iterator para iterar sobre o arquivo de clientes
	        	Iterator<JsonElement> iterator = arrayDeClientes.iterator();
	        	
	        	//Objeto json do novo cliente
	        	JsonObject clienteNovo = new JsonObject();
	        	
	        	//enquanto tiver clientes no arquivo ele continua procurando
	        	while(iterator.hasNext()) {
	        		JsonObject clienteObj = iterator.next().getAsJsonObject();
	        		String clienteNome = clienteObj.get("nome").getAsString();
	        		
	        		//se achar o nome do cliente passado por parametro nos nomes do arquivo 
	        		//exclui o objeto 
	        		if(clienteNome.equals(nomeClienteSerEditado)) {
	        			//seta o cpf para nao o perder
	        			cliente.setCpf(clienteObj.get("cpf").getAsString());
	        			iterator.remove();
	        			
	        			//atualiza o arquivo com o objeto removido
	        			FileWriter writer = new FileWriter("clientes.json");
						Gson gson = new GsonBuilder().setPrettyPrinting().create();
						writer.write(gson.toJson(arrayDeClientes));
						writer.flush();
						
						//Adiciona um novo objeto com as informacoes atualizadas no arquivo
		        		clienteNovo.addProperty("nome", cliente.getNome());
		        		clienteNovo.addProperty("cpf", cliente.getCpf());
		        		clienteNovo.addProperty("telefone", cliente.getTelefone());
		        		clienteNovo.addProperty("email", cliente.getEmail());
		        		arrayDeClientes.add(clienteNovo);
		        		break;						
	        		}
	        	}
	        	//Atualiza já com o novo objeto	        	
        		FileWriter writer = new FileWriter("clientes.json");
				Gson gson = new GsonBuilder().setPrettyPrinting().create();
				writer.write(gson.toJson(arrayDeClientes));
				writer.flush();
			}catch(IOException e){
				e.printStackTrace();
			}		
	}
	
	public void excluirCliente(String nomeClienteExcluir) {		
		try {
			//Lê o arquivo json de clientes
			FileReader reader = new FileReader("clientes.json");
			JsonArray arrayDeClientes = JsonParser.parseReader(reader).getAsJsonArray();
	        reader.close();
	        
	        //faz um iterator para iterar sobre o arquivo de clientes
	        Iterator<JsonElement> iterator = arrayDeClientes.iterator();
	        
	        //enquanto tiver clientes no arquivo ele continua procurando
	        while(iterator.hasNext()) {
        		JsonObject clienteObj = iterator.next().getAsJsonObject();
        		String clienteNome = clienteObj.get("nome").getAsString();
        		
        		//se achar o nome do cliente passado por parametro nos nomes do arquivo 
        		//exclui o objeto 
        		if(nomeClienteExcluir.equals(clienteNome)) {
        			iterator.remove();
        		}	        
	        }
	        
	        //Atualiza o objeto sem o objeto excluido
	        FileWriter writer = new FileWriter("clientes.json");
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			writer.write(gson.toJson(arrayDeClientes));
			writer.flush();
	        
		}catch(IOException e) {
			e.printStackTrace();
		}		
	}
}
