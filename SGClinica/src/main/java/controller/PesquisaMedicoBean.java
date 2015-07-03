package controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import service.NegocioException;
import util.jsf.FacesUtil;
import model.Medico;
import dao.GenericDAO;

@Named
@ViewScoped
public class PesquisaMedicoBean implements Serializable{

	private static final long serialVersionUID = 1L;

	@Inject
	private GenericDAO<Medico> medicoDAO;

	private List<Medico> medicos;
	private Medico medicoSelecionado;

	@PostConstruct
	public void init() {
		this.medicos = this.medicoDAO.buscarTodos(Medico.class);
	}

	public void excluir() {
		try {
			medicoDAO.excluir(Medico.class, medicoSelecionado);
			medicos.remove(medicoSelecionado);
			FacesUtil.addSuccessMessage("Excluiu o modelo");
		} catch (NegocioException e) {
			FacesUtil.addErrorMessage(e.getMessage());
		}

	}

	public List<Medico> getMedicos() {
		return medicos;
	}
	
	public Medico getMedicoSelecionado() {
		return medicoSelecionado;
	}
	
	public void setMedicoSelecionado(Medico medicoSelecionado) {
		this.medicoSelecionado = medicoSelecionado;
	}
	
}
