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
import controller.LocacaoController;
import dao.LocacaoDAO;
import model.Locacao;

public class TelaAtendenteRegistrarDevolucao extends JFrame{
	
	private JComboBox modeloVeiculoLocado;
	private JFormattedTextField dataDevolucaoVeiculoLocado;
	private LocalDate dataDevolucaoVeiculoLocadoLocalDate;
	
	public TelaAtendenteRegistrarDevolucao() {
		setTitle("Devolução");
		setSize(400, 500);
		setLocationRelativeTo(null);
		setLayout(null);
		setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		labelModeloVeiculoDevolver();
		labelDataDevolucao();
		comboListarVeiculosLocados();
		formatedTextFieldDataDevolucao();
		buttonIrParaPagamento();
		setVisible(true);
		}
	
	private void labelModeloVeiculoDevolver() {
		JLabel lbModeloVeiculoDevolver = new JLabel("Modelo do veiculo: ");
		lbModeloVeiculoDevolver.setFont(new Font("Arial",Font.BOLD,16));
		lbModeloVeiculoDevolver.setBounds(20,150,150,30);
		add(lbModeloVeiculoDevolver);
	}
	
	private void comboListarVeiculosLocados() {
		LocacaoDAO locacaoDao = new LocacaoDAO();
		
		List<String>listaDeVeiculosLocados = locacaoDao.getModeloVeiculoLocados();
		
		modeloVeiculoLocado = new JComboBox(listaDeVeiculosLocados.toArray());
		modeloVeiculoLocado.setBounds(170, 150, 170,30);
		add(modeloVeiculoLocado);
	}
	
	private void labelDataDevolucao() {
		JLabel lbDataDevolucao = new JLabel("Data de Devolução: ");
		lbDataDevolucao.setFont(new Font("Arial",Font.BOLD,16));
		lbDataDevolucao.setBounds(20,200,160,30);
		add(lbDataDevolucao);
	}
	
	private void formatedTextFieldDataDevolucao() {
		try {
			MaskFormatter mascaraDeData = new MaskFormatter("##/##/####");
			dataDevolucaoVeiculoLocado = new JFormattedTextField(mascaraDeData);
			dataDevolucaoVeiculoLocado.setBounds(170,200,100,30);
			add(dataDevolucaoVeiculoLocado);
		}catch(ParseException e) {
			e.printStackTrace();
		}
	}
	
	private void buttonIrParaPagamento() {
		JButton button = new JButton("ir para pagamento");
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//pega o modelo do veiculo que está locado
				String modeloVeiculoLocado = (String) getModeloVeiculoLocado().getSelectedItem();
				//verifica se a data está vazia
				if(getDataDevolucaoVeiculoLocado().getText().equals("  /  /    ")) {
					JOptionPane.showInternalMessageDialog(null,"Dados não permitidos","Erro", JOptionPane.WARNING_MESSAGE);
				}else {
					try {
					//tranforma a data de string para localDate
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
					dataDevolucaoVeiculoLocadoLocalDate = LocalDate.parse(getDataDevolucaoVeiculoLocado().getText(),formatter);
					LocacaoController locacaoController = new LocacaoController();
					
					// cria uma locacao e pega a locacao do carro passado por parametro
					Locacao locacao;
					locacao = locacaoController.getLocacao(modeloVeiculoLocado);
						
					//abre a tela de pagamento
					TelaAtendentePagamento telaPagamento = new TelaAtendentePagamento(TelaAtendenteRegistrarDevolucao.this,locacao);
					}catch(DateTimeParseException d) {
						JOptionPane.showMessageDialog(null,"Data invalida","Erro",JOptionPane.ERROR_MESSAGE);
					}
				}
				
			}
		});
		button.setBounds(120, 250, 150, 40);
		add(button);
	}
	
	public JComboBox<String> getModeloVeiculoLocado(){
		return modeloVeiculoLocado;
	}
	
	public JFormattedTextField getDataDevolucaoVeiculoLocado() {
		return dataDevolucaoVeiculoLocado;
	}
	
	public LocalDate getDataDevolucaoVeiculoLocadoLocalDate() {
		return dataDevolucaoVeiculoLocadoLocalDate;
	}
	
}
