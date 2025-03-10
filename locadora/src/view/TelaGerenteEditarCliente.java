package view;


import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;
import controller.GerenteController;
import dao.ClienteDAO;


public class TelaGerenteEditarCliente extends JFrame{
	
	private JComboBox comboListaClientes;
	private JTextField textFieldNomeCliente;
	private JFormattedTextField formatedTextTelefone;
	private JTextField textFieldEmail;
	
	
	public TelaGerenteEditarCliente() {
		setTitle("Editar Cliente");
		setSize(400,500);
		setLocationRelativeTo(null);
		setLayout(null);
		labelListarClientes();
		comboListarClientes();	
		labelNomeCliente();
		textFieldNomeCliente();
		labelTelefone();
		formatedTextFieldTelefone();
		labelEmail();
		textFieldEmail ();
		buttonEditarCliente();
		setVisible(true);
	}
	
	private void labelListarClientes() {
		JLabel lbListarClientes = new JLabel("Editar cliente: ");
		lbListarClientes.setFont(new Font("Arial",Font.BOLD,16));
		lbListarClientes.setBounds(20,100,130,30);
		add(lbListarClientes);
	}
	
	private void comboListarClientes() {
		ClienteDAO clienteDao = new ClienteDAO();
		
		List<String>listaDeClientes = clienteDao.listarClientes();
		
		comboListaClientes = new JComboBox(listaDeClientes.toArray());
		comboListaClientes.setBounds(140, 100, 170,30);
		add(comboListaClientes);
	} 
	
	private void labelNomeCliente() {
		JLabel lbNomeCliente = new JLabel("Novo nome:");
		lbNomeCliente.setFont(new Font("Arial",Font.BOLD,17));
		lbNomeCliente.setBounds(20,150,150,30);
		add(lbNomeCliente);
	}
	
	private void textFieldNomeCliente() {
		textFieldNomeCliente = new JTextField();
		textFieldNomeCliente.setBounds(140, 150, 170, 30);
		add(textFieldNomeCliente);
	}
	
	
	private void labelTelefone() {
		JLabel lbTelefoneCliente = new JLabel("Novo telefone:");
		lbTelefoneCliente.setFont(new Font("Arial",Font.BOLD,17));
		lbTelefoneCliente.setBounds(20,200,170,30);
		add(lbTelefoneCliente);
	}
	
	private void formatedTextFieldTelefone() {
		try {
			MaskFormatter mascaraDeTelefone = new MaskFormatter( "(##)#####-####");
			formatedTextTelefone = new JFormattedTextField(mascaraDeTelefone);
			formatedTextTelefone.setBounds(140, 200, 170, 30);
			add(formatedTextTelefone);
			}catch(ParseException e ) {
				e.printStackTrace();
			}
	}
	
	private void labelEmail() {
		JLabel lbTeEmailCliente = new JLabel("Novo email:");
		lbTeEmailCliente.setFont(new Font("Arial",Font.BOLD,17));
		lbTeEmailCliente.setBounds(20,250,170,30);
		add(lbTeEmailCliente);
	}
	
	private void textFieldEmail () {
		textFieldEmail = new JTextField();
		textFieldEmail.setBounds(140, 250, 170, 30);
		add(textFieldEmail);
	}
	
	private void buttonEditarCliente() {
		JButton button = new JButton("Editar Cliente");
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//Pega o cliente a ser editado e os novos atributos dele
				String clienteEditado = (String) getComboListaClientes().getSelectedItem();			
				String novoNomeCliente = getTextFieldNomeCliente().getText();
				String novoTelefoneCliente = getFormatedTextTelefone().getText();
				String novoEmailCliente = getTextFieldEmail().getText();
				
				//verifica se os campos estão validos e não estão vazios
				if(novoNomeCliente.length() < 4 || novoTelefoneCliente.equals("(  )     -    ") || novoEmailCliente.length() < 5) {
					JOptionPane.showInternalMessageDialog(null,"Dados invalidos","Erro", JOptionPane.ERROR_MESSAGE);
				}else {
					
					//edita o cliente
					GerenteController gerenteController = new GerenteController();
					gerenteController.editarCliente(clienteEditado, novoNomeCliente, novoTelefoneCliente, novoEmailCliente);
					
					//fecha a tela de edicao do cliente e volta pra tela principal do gerente
					dispose();
					
				}
				
			}
		});
		button.setBounds(120, 400, 150, 40);
		add(button);
	}
	
	
	public JComboBox getComboListaClientes() {
		return this.comboListaClientes;
	}
	
	public JTextField getTextFieldNomeCliente() {
		return this.textFieldNomeCliente;
	}
	
	
	public JFormattedTextField getFormatedTextTelefone() {
		return this.formatedTextTelefone;
	}
	
	public JTextField getTextFieldEmail() {
		return this.textFieldEmail;
	}
	
}
