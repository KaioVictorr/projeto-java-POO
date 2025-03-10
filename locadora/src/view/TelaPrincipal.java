package view;


import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import controller.LoginController;

public class TelaPrincipal extends JFrame{
	
	private JTextField textFieldLogin;
	private JPasswordField passwordField;
	
	public TelaPrincipal() {
		setTitle("Login");
		setSize(400,500);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setLayout(null);
		iconLogin();
		labelLogin();
		textFieldLogin();
		labelPassword();
		passwordField();
		button();
		setVisible(true);
	}
	
	private void iconLogin() {
		ImageIcon imgLogin = new ImageIcon("C:\\Users\\Kaio Victor\\OneDrive\\Desktop\\ProjetoPoo\\locadora\\src\\view\\img\\login-icon-3060.png");
		JLabel lbLogin = new JLabel(imgLogin);
		lbLogin.setBounds(50, 0, 300, 300);
		add(lbLogin);
	}
	
	private void labelLogin() {
		JLabel lbLogin = new JLabel("Login:");
		lbLogin.setFont(new Font("Arial",Font.BOLD,16));
		lbLogin.setBounds(40,300,50,30);
		add(lbLogin);
	}
	
	private void textFieldLogin() {
		textFieldLogin = new JTextField();
		textFieldLogin.setBounds(100, 300, 230, 30);
		add(textFieldLogin);
	}
	
	private void labelPassword() {
		JLabel lbPassword = new JLabel("Senha:");
		lbPassword.setFont(new Font("Arial",Font.BOLD,16));
		lbPassword.setBounds(40,350,70,30);
		add(lbPassword);
	}
	
	private void passwordField() {
		passwordField = new JPasswordField();
		passwordField.setBounds(100, 350, 230, 30);
		add(passwordField);
	}
	
	private void button() {
		
		JButton button = new JButton("Entrar");
		button.addActionListener( new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//Pega o login e senha da tela principal
				String login = getTextFieldLogin().getText();
				String password = getPasswordField().getText();
				
				LoginController loginController = new LoginController();
				
				//verifica se o login é existente e exibe a tela conforme o cargo
				loginController.verificarLogin(login, password,TelaPrincipal.this);
			}
		});
		
		button.setBounds(150, 400, 100, 40);
		add(button);
		
		
	}
	
	
	
	public JTextField getTextFieldLogin() {
		return textFieldLogin;
	}
	
	public JPasswordField getPasswordField() {
		return passwordField;
	}
}

