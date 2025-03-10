package view;

import java.util.List;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import com.google.gson.JsonObject;
import com.lowagie.text.DocumentException;

import dao.CriarPdf;
import dao.VeiculoDAO;

public class TelaAtendenteListarVeiculos extends JFrame{
	
	public TelaAtendenteListarVeiculos() throws DocumentException {
		setTitle("Veiculos");
		setSize(400, 500);
		setLocationRelativeTo(null);
		setLayout(null);
		setResizable(false);
		tabelaVeiculos();
		setVisible(true);
	}
	
	private void tabelaVeiculos() throws DocumentException {
		
		VeiculoDAO veiculoDao = new VeiculoDAO();
		
		DefaultTableModel modelo = new DefaultTableModel();
		
		//definindo as colunas
		modelo.addColumn("Placa");
		modelo.addColumn("Modelo");
		modelo.addColumn("Ano");
		modelo.addColumn("Tipo");
		modelo.addColumn("Locado");
		
		
		
		List<JsonObject> lista = veiculoDao.listaVeiculos();// pega o arraylist de veiculos
		
		
		//coloca cada chave em sua linha
		for(JsonObject veiculo : lista) {
			String[] linha = new String[5];
			JsonObject veiculoObj = veiculo.getAsJsonObject();
			linha[0] = veiculoObj.get("placa").getAsString();
			linha[1] = veiculoObj.get("modelo").getAsString();
			linha[2] = veiculoObj.get("ano").getAsString();
			linha[3] = veiculoObj.get("tipo").getAsString();
			linha[4] = veiculoObj.get("locado").getAsString();
			modelo.addRow(linha);
		}
		
		
		JTable tabela = new JTable(modelo);
		JScrollPane painelTabela = new JScrollPane(tabela);
		painelTabela.setBounds(10,20,360,400);
		add(painelTabela);
		
		
	}
}
