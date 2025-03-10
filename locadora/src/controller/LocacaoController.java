package controller;

import dao.LocacaoDAO;
import model.Locacao;

public class LocacaoController {
	public Locacao getLocacao(String modeloVeiculoLocado) {
		LocacaoDAO locacaoDao = new LocacaoDAO();	
		
		Locacao locacao = locacaoDao.getLocacao(modeloVeiculoLocado);
		
		return locacao;
	}
}
