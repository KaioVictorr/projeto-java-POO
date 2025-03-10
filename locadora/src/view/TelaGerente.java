package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import controller.GerenteController;



public class TelaGerente extends JFrame{
	
	public TelaGerente() {
		setTitle("Gerente");
		setSize(400, 500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setLayout(null);
		setResizable(false);
		buttonCadastrarVeiculos();
		buttonCadastrarClientes();
		buttonEditarClientes();
		buttonExcluirClientes();
		buttonVisualizarRelatorios();
		setVisible(true);
	}
	
	private void buttonCadastrarVeiculos() {
		JButton button = new JButton("Cadastrar Veículo");
		button.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				TelaGerenteCadastrarVeiculos telaCadastrodeVeiculos = new TelaGerenteCadastrarVeiculos();				
			}
		});
		button.setBounds(100, 100, 200, 40);
		add(button);
	}
	
	private void buttonCadastrarClientes() {
		JButton button = new JButton("Cadastrar Cliente");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaGerenteCadastrarCliente tela = new TelaGerenteCadastrarCliente();				
			}
		});
		button.setBounds(100, 150, 200, 40);
		add(button);
		
	}
	
	private void buttonEditarClientes() {
		JButton button = new JButton("Editar Cliente");		
		button.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				TelaGerenteEditarCliente tela = new TelaGerenteEditarCliente();				
			}
		});
		button.setBounds(100, 200, 200, 40);
		add(button);
	}
	
	private void buttonExcluirClientes() {
		JButton button = new JButton("Excluir Cliente");
		button.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				TelaGerenteExcluirCliente tela = new TelaGerenteExcluirCliente();					
			}
		});
		button.setBounds(100, 250, 200, 40);
		add(button);
	}
	
	
	
	private void buttonVisualizarRelatorios() {
		JButton button = new JButton("Relatório de Locações");
		button.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				GerenteController gerenteController = new GerenteController();
				gerenteController.exibirRelatorioLocacoes();					
			}
		});
		button.setBounds(100, 300, 200, 40);
		add(button);
	}
}
