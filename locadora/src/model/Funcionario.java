package model;

public abstract class Funcionario {
	
	protected String login;
	protected String senha;
	
	
	public abstract int getId();
		
	
	public String getLogin() {
		return login;
	}

	public void setLogin(String usuario) {
		this.login = usuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}




}
