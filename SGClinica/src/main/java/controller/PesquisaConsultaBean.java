package controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import service.NegocioException;
import util.jsf.FacesUtil;
import model.Consulta;
import dao.GenericDAO;

@Named
@ViewScoped
public class PesquisaConsultaBean implements Serializable{

	private static final long serialVersionUID = 1L;

	@Inject
	private GenericDAO<Consulta> consultaDAO;

	private List<Consulta> consultas;
	private Consulta consultaSelecionada;

	@PostConstruct
	public void init() {
		this.consultas = this.consultaDAO.buscarTodos(Consulta.class);
	}

	public void excluir() {
		try {
			consultaDAO.excluir(Consulta.class, consultaSelecionada);
			consultas.remove(consultaSelecionada);
			FacesUtil.addSuccessMessage("Excluiu o modelo");
		} catch (NegocioException e) {
			FacesUtil.addErrorMessage(e.getMessage());
		}

	}

	public List<Consulta> getConsultas() {
		return consultas;
	}


	public Consulta getConsultaSelecionada() {
		return consultaSelecionada;
	}
	
	public void setConsultaSelecionada(Consulta consultaSelecionada) {
		this.consultaSelecionada = consultaSelecionada;
	}
	
	
}
