package view;


import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import controller.GerenteController;


public class TelaGerenteCadastrarVeiculos extends JFrame{
	
	private JFormattedTextField formatedTextPlaca;
	private JFormattedTextField formatedTextAno;
	private JTextField textFieldModelo;
	private JComboBox combo;
	
	public TelaGerenteCadastrarVeiculos() {
		setTitle("Cadastro de Veiculos");
		setSize(400, 500);
		setLocationRelativeTo(null);
		setLayout(null);
		setResizable(false);
		labelPlaca();
		formatedTextFieldPlaca();
		labelModelo();
		textFieldModelo();
		labelAno();
		formatedTextFieldAno();
		labelTipoDeVeiculo();
		comboTipoVeiculo();
		buttonEnviarVeiculo();
		setVisible(true);
	}
	
	private void labelPlaca() {
		JLabel lbPlaca = new JLabel("Placa:");
		lbPlaca.setFont(new Font("Arial",Font.BOLD,16));
		lbPlaca.setBounds(40,100,50,30);
		add(lbPlaca);
	}
	
	private void formatedTextFieldPlaca() {
		try {
		MaskFormatter mascaraDePlaca = new MaskFormatter("UUU-####");
		formatedTextPlaca = new JFormattedTextField(mascaraDePlaca);
		formatedTextPlaca.setBounds(100, 100, 130, 30);
		add(formatedTextPlaca);
		}catch(ParseException e ) {
			e.printStackTrace();
		}	
	}
	
	private void labelModelo() {
		JLabel lbModelo = new JLabel("Modelo: ");
		lbModelo.setFont(new Font("Arial",Font.BOLD,16));
		lbModelo.setBounds(40, 150, 100, 30);;
		add(lbModelo);
	}
	
	private void textFieldModelo() {
		textFieldModelo = new JTextField();
		textFieldModelo.setBounds(100, 150, 130, 30);
		add(textFieldModelo);
	}
	
	private void labelAno() {
		JLabel lbAno = new JLabel("Ano: ");
		lbAno.setFont(new Font("Arial",Font.BOLD,16));
		lbAno.setBounds(40,200,100,30);
		add(lbAno);
	}
	
	private void formatedTextFieldAno() {
		try {
			MaskFormatter mascaraDeAno = new MaskFormatter("####");
			formatedTextAno = new JFormattedTextField(mascaraDeAno);
			formatedTextAno.setBounds(100, 200, 130, 30);
			add(formatedTextAno);
			}catch(ParseException e ) {
				e.printStackTrace();
			}	
	}
	
	private void labelTipoDeVeiculo() {
		JLabel lbCombo = new JLabel("Tipo de Veiculo:");
		lbCombo.setFont(new Font("Arial",Font.BOLD,17));
		lbCombo.setBounds(40,250,130,30);
		add(lbCombo);
	}
	
	private void comboTipoVeiculo() {
		String[] tiposDeVeiculos = {"Moto","Carro","Caminhão"};
		combo = new JComboBox(tiposDeVeiculos);
		combo.setBounds(180, 250, 130,30);
		add(combo);
	}
	
	
	private void buttonEnviarVeiculo() {
		JButton button = new JButton("Cadastrar Veiculo");
		
		button.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				//Pega os dados do veiculo
				
				String placa = getFormatedTextPlaca().getText();
				String modelo = getTextFieldModelo().getText();
				String ano = getFormatedTextAno().getText();
				String tipoVeiculo = (String) getComboVeiculos().getSelectedItem();
				
				//Verifica se as entradas são válidas ou estão vazias
				if(placa.equals("   -    ") || modelo.length() < 4 || ano.equals("    ")) {
					JOptionPane.showInternalMessageDialog(null,"Dados não permitidos","Erro", JOptionPane.WARNING_MESSAGE);
				}else {		
					GerenteController gerenteController = new GerenteController();
					
					//Se cadastrar o veiculo, volta pra tela do gerente
					if(gerenteController.cadastrarVeiculo(placa, modelo, ano, tipoVeiculo)) {
						dispose();
					}
				}			
			}
		});
		button.setBounds(120, 400, 150, 40);
		add(button);
	}
	
	public JFormattedTextField getFormatedTextPlaca() {
		return this.formatedTextPlaca;
	}
	
	public JFormattedTextField getFormatedTextAno() {
		return this.formatedTextAno;
	}
	
	public JTextField getTextFieldModelo() {
		return this.textFieldModelo;
	}
	
	public JComboBox<String> getComboVeiculos() {
		return this.combo;
	}
	
	
	}
