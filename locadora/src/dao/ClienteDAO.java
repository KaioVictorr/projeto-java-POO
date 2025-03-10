package dao;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import model.Cliente;

public class ClienteDAO {
	
public boolean verificarCpf(Cliente clientee) {
		
		boolean cpfExistente = false;
		
		try {
			FileReader reader = new FileReader("clientes.json");
			JsonArray arrayDeClientes = JsonParser.parseReader(reader).getAsJsonArray();
	        reader.close();
	        	
	        	//verifica se tem o cpf passado por parametro dentro do arquivo json
	        	for(JsonElement cliente : arrayDeClientes) {
	        	JsonObject clienteObj = cliente.getAsJsonObject();
	        	String cpfJson = clienteObj.get("cpf").getAsString();
	        	if(cpfJson.equals(clientee.getCpf())){
	        		//se tiver, muda a variavel e para o for
	        		cpfExistente = true;
	        		break;
	        	}
	        }
	        		       
			}catch(IOException e){
				e.printStackTrace();
			}		
		return cpfExistente;
	}

	public List<String> listarClientes(){
		
		List<String> listaDeClientes = new ArrayList<String>();
		
		try {
			//ler o arquivo json de clientes
			FileReader reader = new FileReader("clientes.json");
			JsonArray arrayDeClientes = JsonParser.parseReader(reader).getAsJsonArray();
	        reader.close();
	        	
	        	//adiciona todos os nomes dos clientes no arraylist
	        	for(JsonElement cliente : arrayDeClientes) {
	        	JsonObject clienteObj = cliente.getAsJsonObject();
	        	String nomeJson = clienteObj.get("nome").getAsString();
	        	listaDeClientes.add(nomeJson);
	        }	        		       
			}catch(IOException e){
				e.printStackTrace();
			}
		
		return listaDeClientes; // retorna a lista com todos os clientes do arquivo json
	}
	
	
	}
