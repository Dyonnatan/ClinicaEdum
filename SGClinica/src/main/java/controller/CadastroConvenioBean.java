package controller;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

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
public class CadastroConvenioBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Convenio convenio;
	private String telefoneSelecionado;
	private List<String> telefones;

	@Inject
	private CadastroConvenioService cadastroConvenioService;

	@PostConstruct
	public void init() {
		this.limpar();
	}

	public void salvar() {
		try {
			convenio.setTelefones(telefones);
			this.cadastroConvenioService.salvar(convenio);
			FacesUtil.addSuccessMessage("Convenio salvo com sucesso!");
			this.limpar();
		} catch (NegocioException e) {
			FacesUtil.addErrorMessage(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			FacesUtil
					.addErrorMessage("Erro desconhecido. Contatar o administrador. ");
		}
	}

	public void limpar() {
		this.convenio = new Convenio();
		telefones = new LinkedList<String>();
	}

	public Convenio getConvenio() {
		return convenio;
	}

	public void setConvenio(Convenio convenio) {
		this.convenio = convenio;
	}

	public String getTelefoneSelecionado() {
		return telefoneSelecionado;
	}

	public void setTelefoneSelecionado(String telefoneSelecionado) {
		this.telefoneSelecionado = telefoneSelecionado;
	}

	public List<String> getTelefones() {
		return telefones;
	}

	public void addTelefone() {
		if (this.telefones.contains(telefoneSelecionado))
			return;
		this.telefones.add(telefoneSelecionado);
	}

	public void removeTelefone() {
		this.telefones.remove(telefoneSelecionado);
	}

}
