package view;


import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import controller.AdministradorController;


public class TelaAdministradorCadastroDeUsuarios extends JFrame{
	
	private JComboBox combo;
	private JTextField textFieldLoginNovoUsuario;
	private JPasswordField passwordNovoUsuario;
	
	public TelaAdministradorCadastroDeUsuarios() {
		setTitle("Cadastro de Usuarios");
		setSize(400,500);
		setLocationRelativeTo(null);
		setLayout(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		adicionarLabelPrincipal();
		adicionarComboTipoUsuario();
		adicionarLabelUsuario();
		textFieldNovoLoginUsuario();
		adicionarLabelSenha();
		passwordFieldNovoUsuario();
		button();
		setVisible(true);
	}
	
	private void adicionarLabelPrincipal() {
		JLabel lbCombo = new JLabel("Tipo de Usuario:");
		lbCombo.setFont(new Font("Arial",Font.BOLD,17));
		lbCombo.setBounds(20,100,150,30);
		add(lbCombo);
	}
	private void adicionarComboTipoUsuario() {
		String[] tiposDeUsuario = {"Administrador","Gerente","Atendente"};
		combo = new JComboBox(tiposDeUsuario);
		combo.setBounds(170, 100, 200,30);
		add(combo);
	}
	
	private void adicionarLabelUsuario() {
		JLabel lbLoginNovoUsuario = new JLabel("Login do Usuario:");
		lbLoginNovoUsuario.setFont(new Font("Arial",Font.BOLD,17));
		lbLoginNovoUsuario.setBounds(20,170,150,30);
		add(lbLoginNovoUsuario);
	}
	
	private void textFieldNovoLoginUsuario() {
		textFieldLoginNovoUsuario = new JTextField();
		textFieldLoginNovoUsuario.setBounds(170, 170, 200, 30);
		add(textFieldLoginNovoUsuario);
	}
	
	private void adicionarLabelSenha() {
		JLabel lbSenhaNovoUsuario = new JLabel("Senha do Usuario:");
		lbSenhaNovoUsuario.setFont(new Font("Arial",Font.BOLD,17));
		lbSenhaNovoUsuario.setBounds(20,220,150,30);
		add(lbSenhaNovoUsuario);
	}
	
	private void passwordFieldNovoUsuario() {
		passwordNovoUsuario = new JPasswordField();
		passwordNovoUsuario.setBounds(170, 220, 200, 30);
		add(passwordNovoUsuario);			
	}
	
	private void button() {
		JButton button = new JButton("Cadastrar");
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(getPasswordNovoUsuario().getText() == "" || getPasswordNovoUsuario().getText().length() < 5 || getTextFieldLoginNovoUsuario().getText() == "" ) {
					JOptionPane.showInternalMessageDialog(null,"Dados não permitidos","Erro", JOptionPane.WARNING_MESSAGE);
					//Verifica se os campos estão vazios ou se a senha contem menos de 5 caracteres
				}else {
				
				// Pega os dados da tela
				String novoLogin = getTextFieldLoginNovoUsuario().getText();
				String novoPassword = getPasswordNovoUsuario().getText();
				String tipoUser = (String) getCombo().getSelectedItem();
					
				
				//Manda pro controller verificar 
				AdministradorController administradorController = new AdministradorController();	
				
				if(administradorController.adicionarUsuario(novoLogin, novoPassword, tipoUser)) {
					//fecha a tela de cadastro
					dispose();
					//abre a tela de login
					TelaPrincipal telaPrincipal = new TelaPrincipal();
				}
					
				}
				
			}
		});
		button.setBounds(150, 400, 100, 40);
		add(button);
	}
	
	public JTextField getTextFieldLoginNovoUsuario() {
		return this.textFieldLoginNovoUsuario;
	}
	
	public JPasswordField getPasswordNovoUsuario() {
		return this.passwordNovoUsuario;
	}
	
	public JComboBox<String> getCombo(){
		return combo;
	}
}
