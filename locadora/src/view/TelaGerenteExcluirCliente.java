package view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import controller.GerenteController;
import dao.ClienteDAO;

public class TelaGerenteExcluirCliente extends JFrame {
	
	private JComboBox comboListaClientes;
	
	public TelaGerenteExcluirCliente() {
		setTitle("Excluir Cliente");
		setSize(400,500);
		setLocationRelativeTo(null);
		setLayout(null);
		labelListarClientes();
		comboListarClientes();
		buttonExcluirCliente();
		setVisible(true);
	}
	
	private void labelListarClientes() {
		JLabel lbListarClientes = new JLabel("Excluir cliente: ");
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
	
	private void buttonExcluirCliente() {
		JButton button = new JButton("Excluir Cliente");
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//Pega o nome do cliente a ser excluido
				String ClienteRemover = (String) getComboListaClientes().getSelectedItem();
				
				//verifica se o valor é vazio
				if(ClienteRemover == null) {
					JOptionPane.showInternalMessageDialog(null,"Dados não permitidos","Erro", JOptionPane.WARNING_MESSAGE);
				}else {
					//exclui o cliente
					GerenteController gerenteController = new GerenteController();		
					
					gerenteController.excluirCliente(ClienteRemover);
					//fecha a tela e volta para a tela principal do gerente
					dispose();
				}				
			}
		});
		button.setBounds(120, 400, 150, 40);
		add(button);
	}
	
	
	public JComboBox getComboListaClientes(){
		return comboListaClientes;
	}
}
