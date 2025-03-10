package controller;

import javax.swing.JOptionPane;
import dao.LoginDAO;
import model.Funcionario;
import view.TelaAdministradorCadastroDeUsuarios;
import view.TelaAtendente;
import view.TelaGerente;
import view.TelaPrincipal;

public class LoginController {
	
	public void verificarLogin(String login,String senha,TelaPrincipal telaPrincipal) {
		LoginDAO loginDao = new LoginDAO();
		//cria um funcionario
		Funcionario funcionario;
		
		//pega o funcionario já com seu cargo
		funcionario = loginDao.getFuncionario(login, senha);
		
		
		//verifica se o funcionario existe
		if(funcionario == null) {
			JOptionPane.showMessageDialog(null, "Login não encontrado","Erro", JOptionPane.ERROR_MESSAGE);
		}else {
			//fecha a tela de login
			telaPrincipal.dispose();
			
			//Abre a tela conforme o cargo do funcionario
			if(funcionario.getId() == 0) {
				TelaAdministradorCadastroDeUsuarios tela = new TelaAdministradorCadastroDeUsuarios();
			}else if(funcionario.getId() == 1) {
				TelaGerente tela = new TelaGerente();
			}else {
				TelaAtendente tela = new TelaAtendente();
			}
		}
	}
}
