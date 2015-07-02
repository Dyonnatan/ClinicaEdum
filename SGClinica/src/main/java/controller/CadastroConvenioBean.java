package controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import model.Convenio;
import service.CadastroConvenioService;
import service.NegocioException;
import util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroConvenioBean implements Serializable{

	private static final long serialVersionUID = 1L;

	private Convenio convenio;
	@Inject
	private CadastroConvenioService cadastroConvenioService;

	@PostConstruct
	public void init() {
		this.limpar();
	}
	
	public void salvar() {
		try {
			this.cadastroConvenioService.salvar(convenio);
			FacesUtil.addSuccessMessage("Convenio salvo com sucesso!");
		} catch (NegocioException e) {
			FacesUtil.addErrorMessage(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			FacesUtil.addErrorMessage("Erro. Verifique se o convênio já existe");
		}
		
		this.limpar();
	}
	
	public void limpar() {
		this.convenio = new Convenio();	
	}

	public Convenio getConvenio() {
		return convenio;
	}
	
	public void setConvenio(Convenio convenio) {
		this.convenio = convenio;
	}
	
}
