package view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import controller.AtendenteController;
import model.Locacao;
import model.Pagamento;

public class TelaAtendentePagamento extends JFrame{
	
	private TelaAtendenteRegistrarDevolucao tela;
	private Locacao locacao;
	private LocalDate dataDevolucaoVeiculoLocado;
	private Double valorLocacao;
	private JComboBox modoPagamento;
	
	public TelaAtendentePagamento(TelaAtendenteRegistrarDevolucao tela,Locacao locacao){
		this.tela = tela;
		this.locacao = locacao;
		setTitle("Pagamento");
		setSize(400, 500);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setLayout(null);
		setResizable(false);
		labelNomeCliente();
		labelModeloVeiculo();
		labelDataRetirada();
		labelDataDevolucaoPrevista();
		labelDataDevolucaoRealizada();
		labelValor();
		labelModoPagamento();
		comboModoPagamento();
		button();
		setVisible(true);
	}
	
	private void  labelNomeCliente() {
		JLabel lbNomeCliente = new JLabel("Cliente: " + locacao.getNomeCliente());
		lbNomeCliente.setFont(new Font("Arial",Font.BOLD,17));
		lbNomeCliente.setBounds(20,70,170,50);
		add(lbNomeCliente);
	}
	
	private void  labelModeloVeiculo() {
		JLabel lbModeloVeiculo = new JLabel("Modelo: " + locacao.getVeiculo().getModelo());
		lbModeloVeiculo.setFont(new Font("Arial",Font.BOLD,17));
		lbModeloVeiculo.setBounds(20,100,250,50);
		add(lbModeloVeiculo);
	}
	
	private void labelDataRetirada() {
		JLabel lbDatRetirada = new JLabel("Data retirada: " + locacao.getDataDeRetirada());
		lbDatRetirada.setFont(new Font("Arial",Font.BOLD,17));
		lbDatRetirada.setBounds(20,130,250,50);
		add(lbDatRetirada);
	}
	
	private void labelDataDevolucaoPrevista() {
		JLabel lbDataDevolucaoPrevista= new JLabel("Devolução prevista: " + locacao.getDataDeDevolucao());
		lbDataDevolucaoPrevista.setFont(new Font("Arial",Font.BOLD,17));
		lbDataDevolucaoPrevista.setBounds(20,160,250,50);
		add(lbDataDevolucaoPrevista);
	}
	
	private void labelDataDevolucaoRealizada() {
		JLabel lbDataDevolucaoRealizada= new JLabel("Devolução realizada: " + tela.getDataDevolucaoVeiculoLocadoLocalDate());
		lbDataDevolucaoRealizada.setFont(new Font("Arial",Font.BOLD,17));
		lbDataDevolucaoRealizada.setBounds(20,190,280,50);
		add(lbDataDevolucaoRealizada);
		
	}
	
	private void labelValor() {
		valorLocacao = locacao.getVeiculo().calcularCustoLocacao(locacao.getDataDeRetirada(), locacao.getDataDeDevolucao(), tela.getDataDevolucaoVeiculoLocadoLocalDate());
		JLabel lbValor = new JLabel("Valor: " + valorLocacao);
		lbValor.setFont(new Font("Arial",Font.BOLD,17));
		lbValor.setBounds(20,220,150,50);
		add(lbValor);	
	}
	
	private void  labelModoPagamento() {
		JLabel lbModoPagamento = new JLabel("Modo de Pagamento: ");
		lbModoPagamento.setFont(new Font("Arial",Font.BOLD,17));
		lbModoPagamento.setBounds(20,250,180,50);
		add(lbModoPagamento);
	}
	
	private void comboModoPagamento() {
		String[] tiposDeVeiculos = {"Dinheiro","Cartão","Pix"};
		modoPagamento = new JComboBox(tiposDeVeiculos);
		modoPagamento.setBounds(190, 260, 100,30);
		add(modoPagamento);
	}
	
	private void button() {
		JButton button = new JButton("Pagar");	
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//pega o metodo de pagamento selecionado
				String metodoPagamento = (String) getModoPagamento().getSelectedItem();
				
				//cria um pagamento
				Pagamento pagamento = new Pagamento(getValorLocacao(),tela.getDataDevolucaoVeiculoLocadoLocalDate(),metodoPagamento);
				
				AtendenteController atendenteController = new AtendenteController();
				
				//registra o pagamento
				atendenteController.registrarPagamentoLocacao(getLocacao(), pagamento);
				//fecha as telas e volta pra pagina do atendente
				dispose();
				tela.dispose();
				
			}
		});
		button.setBounds(150, 350, 100, 40);
		add(button);
	}
	
	public Locacao getLocacao() {
		return this.locacao;
	}
	
	public LocalDate getDataDevolucaoVeiculoLocado() {
		return this.dataDevolucaoVeiculoLocado;
	}
	
	public Double getValorLocacao() {
		return this.valorLocacao;
	}
	
	public JComboBox getModoPagamento() {
		return this.modoPagamento;
	}
	
}
