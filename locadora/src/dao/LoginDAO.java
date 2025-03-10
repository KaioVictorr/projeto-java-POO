package dao;

import java.io.FileReader;
import java.io.IOException;

import javax.swing.JOptionPane;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import model.Administrador;
import model.Atendente;
import model.Funcionario;
import model.Gerente;
import view.TelaAdministradorCadastroDeUsuarios;
import view.TelaAtendente;
import view.TelaGerente;
import view.TelaPrincipal;

public class LoginDAO {
	
	public Funcionario getFuncionario(String login,String senha) {
		//Cria um funcionario
		Funcionario funcionario = null;
		
		try {
			//lê o arquivo json de usuarios
			FileReader reader = new FileReader("users.json");
			JsonArray arrayDeUsuarios = JsonParser.parseReader(reader).getAsJsonArray();
	        reader.close();
	        
	        //percorre todo o arquivo
	        for(JsonElement funcionarioo : arrayDeUsuarios) {
	        	JsonObject usuarioObj = funcionarioo.getAsJsonObject();
	        	String loginJson = usuarioObj.get("login").getAsString();
	        	String passwordJson = usuarioObj.get("senha").getAsString();
	        	
	        	//verifica se o login e senha passados por parametro existem no json
	        	//caso existam pegam o id e definem o objeto Funcionario
	        	if(loginJson.equals(login) && passwordJson.equals(senha)) {
	        			if(usuarioObj.get("id").getAsInt() == 0) {
	        				Administrador adm = new Administrador();
	        				adm.setLogin(login);
	        				adm.setSenha(senha);
	        				funcionario = adm;
	       
	        			}else if(usuarioObj.get("id").getAsInt() == 1) {
	        				Gerente gerente = new Gerente();
	        				gerente.setLogin(login);
	        				gerente.setSenha(senha);
	        				funcionario = gerente;
	        				
	        			}else {
	        				Atendente atendente = new Atendente();
	        				atendente.setLogin(login);
	        				atendente.setSenha(senha);
	        				funcionario = atendente;
	        				
	        			}
	        		break;        		
	        	}
	        }
	        
		
		}catch(IOException e) {
			e.printStackTrace();
		}
		return funcionario; // Retorna o funcionario já com seu cargo
		}
		
		
	
	public boolean verificarLoginExistente(Funcionario func){
		
		boolean usuarioExistente = false;
		
		try {
			// lê o arquivo json de usuarios
			FileReader reader = new FileReader("users.json");
			JsonArray arrayDeUsuarios = JsonParser.parseReader(reader).getAsJsonArray();
	        reader.close();
	        
	        //itera sobre o todos os objetos json do arquivo
	        for(JsonElement funcionario : arrayDeUsuarios) {
	        	JsonObject usuarioObj = funcionario.getAsJsonObject(); // pega o objeto
	        	String loginJson = usuarioObj.get("login").getAsString(); // paga o login do objeto
	        	if(loginJson.equals(func.getLogin())){ // compara o login do usuario do parametro com o do objeto json
	        		usuarioExistente = true;	// se existir retorna true
	        		break;
	        	}
	        }
	       
			}catch(IOException e){
				e.printStackTrace();
			}
		return usuarioExistente; // retorna se existe login ou não
	}

	}
	
	
	



