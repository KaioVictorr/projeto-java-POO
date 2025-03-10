package view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.text.MaskFormatter;

import controller.AtendenteController;

import dao.ClienteDAO;
import dao.VeiculoDAO;

public class TelaAtendenteRegistrarLocacao extends JFrame{
	
	private JComboBox nomeClienteLocacao;
	private JComboBox nomeVeiculoLocacao;
	private JFormattedTextField dataRetirada;
	private JFormattedTextField dataDevolucao;
	
	public TelaAtendenteRegistrarLocacao() {
		setTitle("Locação");
		setSize(400, 500);
		setLocationRelativeTo(null);
		setLayout(null);
		setResizable(false);
		labelCliente();
		comboListarClientes();
		labelModeloVeiculo();
		comboListarVeiculosDisponiveis();
		labelDataRetirada();
		formatedTextFieldDataRetirada();
		labelDataDevolucao();
		formatedTextFieldDataDevolucao();
		buttonLocar();
		setVisible(true);
	}
	
	private void labelCliente() {
		JLabel lbListarClientes = new JLabel("Cliente: ");
		lbListarClientes.setFont(new Font("Arial",Font.BOLD,16));
		lbListarClientes.setBounds(20,100,130,30);
		add(lbListarClientes);
	}
	
	private void comboListarClientes() {
		ClienteDAO clienteDao = new ClienteDAO();
		
		List<String>listaDeClientes = clienteDao.listarClientes(); //pega o arraylist de clientes
		
		nomeClienteLocacao = new JComboBox(listaDeClientes.toArray()); // transforma o arraylist em um combobox
		nomeClienteLocacao.setBounds(100, 100, 170,30);
		add(nomeClienteLocacao);
	}
	
	private void labelModeloVeiculo() {
		JLabel lbListarVeiculosDisponiveis = new JLabel("Veiculo: ");
		lbListarVeiculosDisponiveis.setFont(new Font("Arial",Font.BOLD,16));
		lbListarVeiculosDisponiveis.setBounds(20,150,170,30);
		add(lbListarVeiculosDisponiveis);
	}
	
	private void comboListarVeiculosDisponiveis() {
		VeiculoDAO veiculoDao = new VeiculoDAO();
		
		List<String>listaDeVeiculosDisponiveis = veiculoDao.listaVeiculosDisponiveis(); //pega o arraylist de veiculos que não estão locados
		
		nomeVeiculoLocacao = new JComboBox(listaDeVeiculosDisponiveis.toArray());// transforma o arraylist em combobox
		nomeVeiculoLocacao.setBounds(100, 150, 170,30);
		add(nomeVeiculoLocacao);
	}
	
	
	private void labelDataRetirada() {
		JLabel lbDataRetirada = new JLabel("Data de Retirada: ");
		lbDataRetirada.setFont(new Font("Arial",Font.BOLD,16));
		lbDataRetirada.setBounds(20,200,170,30);
		add(lbDataRetirada);
	}
	
	private void formatedTextFieldDataRetirada() {
		try {
			MaskFormatter mascaraDeData = new MaskFormatter("##/##/####");
			dataRetirada = new JFormattedTextField(mascaraDeData);
			dataRetirada.setBounds(170,200,100,30);
			add(dataRetirada);
		}catch(ParseException e) {
			e.printStackTrace();
		}
	}
	
	private void labelDataDevolucao() {
		JLabel lbDataDevolucao = new JLabel("Data de Devolução: ");
		lbDataDevolucao.setFont(new Font("Arial",Font.BOLD,16));
		lbDataDevolucao.setBounds(20,250,170,30);
		add(lbDataDevolucao);
	}
	
	private void formatedTextFieldDataDevolucao() {
		try {
			MaskFormatter mascaraDeData = new MaskFormatter("##/##/####");
			dataDevolucao = new JFormattedTextField(mascaraDeData);
			dataDevolucao.setBounds(170,250,100,30);
			add(dataDevolucao);
		}catch(ParseException e) {
			e.printStackTrace();
		}
	}
	
	private void buttonLocar() {
		JButton button = new JButton("Locar");
		
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//verifica se a data não está vazia
				if(getDataRetirada().getText().equals("  /  /    ") || getDataDevolucao().getText().equals("  /  /    ") || getNomeClienteLocacao().getSelectedItem() == null || getNomeVeiculoLocacao().getSelectedItem() == null) {
					JOptionPane.showMessageDialog(null,"Dados Invalidos","Erro",JOptionPane.ERROR_MESSAGE);
				}else {
					try {
					//formatador da data
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
					
					//converte as datas de string para LocalDate
					LocalDate dataRetirada = LocalDate.parse(getDataRetirada().getText(), formatter);
					LocalDate dataDevolucao = LocalDate.parse(getDataDevolucao().getText(), formatter);
					//pega o nome do cliente e do veiculo
					String nomeClienteLocacao = (String) getNomeClienteLocacao().getSelectedItem();
					String nomeVeiculoLocacao = (String) getNomeVeiculoLocacao().getSelectedItem();
					
					AtendenteController atendenteController = new AtendenteController();
					
					//cadastra a locacao
					atendenteController.cadastrarLocacao(nomeClienteLocacao, nomeVeiculoLocacao, dataRetirada, dataDevolucao);
					//fecha a tela e volta pra tela de atendente
					setVisible(false);
					}catch(DateTimeParseException d) {
						JOptionPane.showMessageDialog(null,"Data invalida","Erro",JOptionPane.ERROR_MESSAGE);
					}
				
			}
				
			}
		});
		button.setBounds(120, 350, 150, 40);
		add(button);
	}
	
	public JComboBox<String> getNomeClienteLocacao(){
		return nomeClienteLocacao;
	}
	
	public JComboBox<String> getNomeVeiculoLocacao(){
		return nomeVeiculoLocacao;
	}
	
	public JFormattedTextField getDataRetirada() {
		return dataRetirada;
	}
	
	public JFormattedTextField getDataDevolucao() {
		return dataDevolucao;
	}
	
}
