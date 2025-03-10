package controller;

import javax.swing.JOptionPane;
import javax.swing.WindowConstants;

import dao.AdministradorDAO;
import dao.LoginDAO;
import model.Administrador;
import model.Atendente;
import model.Funcionario;
import model.Gerente;
import view.TelaAdministradorCadastroDeUsuarios;
import view.TelaPrincipal;

public class AdministradorController {
	
	public boolean adicionarUsuario(String novoLogin,String novoPassword,String tipoUsuario) {
		//Cria um funcionario
		Funcionario funcionario = null;
		
		boolean usuarioAdicionado = false;
		
		//Se o tipo for administrador cria um ADM
		if(tipoUsuario.equals("Administrador")) {
			Administrador adm = new Administrador();
			adm.setLogin(novoLogin);
			adm.setSenha(novoPassword);
			funcionario = adm;
		
		//Se o tipo for Gerente cria um gerente
		}else if(tipoUsuario.equals("Gerente")) {
			Gerente gerente = new Gerente();
			gerente.setLogin(novoLogin);
			gerente.setSenha(novoPassword);
			funcionario = gerente;
		//Se não for nenhum dos 2 cria um atendente
		}else {
			Atendente atendente = new Atendente();
			atendente.setLogin(novoLogin);
			atendente.setSenha(novoPassword);
			funcionario = atendente;
		}
		
		//Verifica se já existe um login igual
		LoginDAO loginDao = new LoginDAO();
		
		if(loginDao.verificarLoginExistente(funcionario)) {
			JOptionPane.showMessageDialog(null, "Usuario Já existente!");
		}else {
			//Se não existir salva o usuario no json
			AdministradorDAO administradorDao = new AdministradorDAO();			
			administradorDao.salvarUsuario(funcionario);
			JOptionPane.showMessageDialog(null,"Cadastro Realizado","Sucesso",JOptionPane.PLAIN_MESSAGE);
			usuarioAdicionado = true;
		}
		//retorna se o usuario foi salvo ou não
		return usuarioAdicionado;	
	}

}
