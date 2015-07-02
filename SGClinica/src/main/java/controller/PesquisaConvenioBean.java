package controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import service.NegocioException;
import util.jsf.FacesUtil;
import model.Convenio;
import dao.GenericDAO;

@Named
@ViewScoped
public class PesquisaConvenioBean implements Serializable{

	private static final long serialVersionUID = 1L;

	@Inject
	private GenericDAO<Convenio> convenioDAO;

	private List<Convenio> convenios;
	private Convenio convenioSelecionado;

	@PostConstruct
	public void init() {
		this.convenios = this.convenioDAO.buscarTodos(Convenio.class);
	}

	public void excluir() {
		try {
			convenioDAO.excluir(Convenio.class, convenioSelecionado);
			convenios.remove(convenioSelecionado);
			FacesUtil.addSuccessMessage("Excluiu o modelo");
		} catch (NegocioException e) {
			FacesUtil.addErrorMessage(e.getMessage());
		}

	}

	public List<Convenio> getConvenios() {
		return convenios;
	}

	public void setConvenios(List<Convenio> convenios) {
		this.convenios = convenios;
	}
	
	public Convenio getConvenioSelecionado() {
		return convenioSelecionado;
	}
	
	public void setConvenioSelecionado(Convenio convenioSelecionado) {
		this.convenioSelecionado = convenioSelecionado;
	}
	
	
}
