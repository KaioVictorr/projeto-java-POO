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

import model.Funcionario;
import dao.LoginDAO;


public class AdministradorDAO {
	
	public void salvarUsuario(Funcionario funcionario) {				
					try {
						//lê o arquivo json de usuarios
						FileReader reader = new FileReader("users.json");
						JsonArray arrayDeUsuarios = JsonParser.parseReader(reader).getAsJsonArray();
				        reader.close();
				        
				        //pega os atributos do funcionario e cria um objeto json
				        JsonObject objeto = new JsonObject();
				        objeto.addProperty("login",funcionario.getLogin());
						objeto.addProperty("senha",funcionario.getSenha());
						objeto.addProperty("id",funcionario.getId());
						
						//adiciona o objeto no arquivo json
						arrayDeUsuarios.add(objeto);
						
						//atualiza o arquivo de usuarios
						FileWriter writer = new FileWriter("users.json");
						Gson gson = new GsonBuilder().setPrettyPrinting().create();
						writer.write(gson.toJson(arrayDeUsuarios));
						writer.flush();
									        	        
					}catch(IOException e) {
						e.printStackTrace();
					}	
				}
}
				
				
		
	

