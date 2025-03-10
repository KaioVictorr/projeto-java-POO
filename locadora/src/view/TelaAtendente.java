package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import com.lowagie.text.DocumentException;

public class TelaAtendente extends JFrame{
	
	public TelaAtendente() {
		setTitle("Atendente");
		setSize(400, 500);
		setLocationRelativeTo(null);
		setLayout(null);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		buttonListarVeiculos();
		buttonLocacao();
		buttonDevolucao();
		setVisible(true);
	}
	
	private void buttonListarVeiculos() {
			JButton button = new JButton("Listar Veículos");
			button.addActionListener(new ActionListener() {							
				public void actionPerformed(ActionEvent e) {
					try {
						TelaAtendenteListarVeiculos tela = new TelaAtendenteListarVeiculos();
					} catch (DocumentException d) {
						d.printStackTrace();
					}						
				}
			});
			button.setBounds(100, 100, 200, 40);
			add(button);		
	}
	
	private void buttonLocacao() {
		JButton button = new JButton("Registrar locação");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaAtendenteRegistrarLocacao tela = new TelaAtendenteRegistrarLocacao();			
			}
		});
		button.setBounds(100, 150, 200, 40);
		add(button);		
}
	
	private void buttonDevolucao() {
		JButton button = new JButton("Registrar Devolução");		
		button.addActionListener(new ActionListener() {						
			public void actionPerformed(ActionEvent e) {
				TelaAtendenteRegistrarDevolucao tela = new TelaAtendenteRegistrarDevolucao();
			}
		});
		button.setBounds(100, 200, 200, 40);
		add(button);		
}
	
	
	
	
	
}
