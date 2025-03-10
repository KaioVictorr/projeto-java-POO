package view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;
import controller.GerenteController;


public class TelaGerenteCadastrarCliente extends JFrame{
	
	private JTextField textFieldNomeCliente;
	private JFormattedTextField formatedTextCpf;
	private JFormattedTextField formatedTextTelefone;
	private JTextField textFieldEmail;
	
	
	public TelaGerenteCadastrarCliente() {
		setTitle("Cadastro de Cliente");
		setSize(400, 500);
		setLocationRelativeTo(null);
		setLayout(null);
		setResizable(false);
		labelNomeCliente();
		textFieldNomeCliente();
		labelCpfCliente();
		formatedTextFieldCpf();
		labelTelefone();
		formatedTextFieldTelefone();
		labelEmail();
		textFieldEmail ();
		buttonEnviarCliente();
		setVisible(true);
	}
	
	private void labelNomeCliente() {
		JLabel lbNomeCliente = new JLabel("Nome do Cliente:");
		lbNomeCliente.setFont(new Font("Arial",Font.BOLD,17));
		lbNomeCliente.setBounds(20,100,150,30);
		add(lbNomeCliente);
	}
	
	private void textFieldNomeCliente() {
		textFieldNomeCliente = new JTextField();
		textFieldNomeCliente.setBounds(170, 100, 200, 30);
		add(textFieldNomeCliente);
	}
	
	private void labelCpfCliente() {
		JLabel lbCpfCliente = new JLabel("CPF do Cliente:");
		lbCpfCliente.setFont(new Font("Arial",Font.BOLD,17));
		lbCpfCliente.setBounds(20,150,150,30);
		add(lbCpfCliente);
	}
	
	private void formatedTextFieldCpf() {
		try {
			MaskFormatter mascaraDeCpf = new MaskFormatter( "###.###.###-##");
			formatedTextCpf = new JFormattedTextField(mascaraDeCpf);
			formatedTextCpf.setBounds(170, 150, 200, 30);
			add(formatedTextCpf);
			}catch(ParseException e ) {
				e.printStackTrace();
			}
	}
	
	private void labelTelefone() {
		JLabel lbTelefoneCliente = new JLabel("Telefone do Cliente:");
		lbTelefoneCliente.setFont(new Font("Arial",Font.BOLD,17));
		lbTelefoneCliente.setBounds(20,200,170,30);
		add(lbTelefoneCliente);
	}
	
	private void formatedTextFieldTelefone() {
		try {
			MaskFormatter mascaraDeTelefone = new MaskFormatter( "(##)#####-####");
			formatedTextTelefone = new JFormattedTextField(mascaraDeTelefone);
			formatedTextTelefone.setBounds(180, 200, 190, 30);
			add(formatedTextTelefone);
			}catch(ParseException e ) {
				e.printStackTrace();
			}
	}
	
	private void labelEmail() {
		JLabel lbTeEmailCliente = new JLabel("Email do Cliente:");
		lbTeEmailCliente.setFont(new Font("Arial",Font.BOLD,17));
		lbTeEmailCliente.setBounds(20,250,170,30);
		add(lbTeEmailCliente);
	}
	
	private void textFieldEmail () {
		textFieldEmail = new JTextField();
		textFieldEmail.setBounds(170, 250, 200, 30);
		add(textFieldEmail);
	}
	
	private void buttonEnviarCliente() {
		JButton button = new JButton("Cadastrar Cliente");
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//Pega os dados do cliente
				String nomeCliente = getTextFieldNomeCliente().getText();
				String cpfCliente = getFormatedTextCpf().getText();
				String telefoneCliente = getFormatedTextTelefone().getText();
				String emailCliente = getTextFieldEmail().getText();
				
				//verifica se as entradas estão invalidas ou vazias
				if(nomeCliente.length() < 4 || cpfCliente.equals("   .   .   -  ") || telefoneCliente.equals("(  )     -    ") || emailCliente.length() < 5) {
					JOptionPane.showInternalMessageDialog(null,"Dados não permitidos","Erro", JOptionPane.WARNING_MESSAGE);
				}else {	
					
					GerenteController gerenteController = new GerenteController();
					
					//se cadastrar o cliente, volta pra tela inicial do gerente
					if(gerenteController.cadastrarCliente(nomeCliente, cpfCliente, telefoneCliente, emailCliente)) {
						dispose();
					}
				}
				
			}
		});
		button.setBounds(120, 350, 150, 40);
		add(button);
	}
	
	public JTextField getTextFieldNomeCliente() {
		return this.textFieldNomeCliente;
	}
	
	public JFormattedTextField getFormatedTextCpf() {
		return this.formatedTextCpf;
	}
	
	public JFormattedTextField getFormatedTextTelefone() {
		return this.formatedTextTelefone;
	}
	
	public JTextField getTextFieldEmail() {
		return this.textFieldEmail;
	}
}
