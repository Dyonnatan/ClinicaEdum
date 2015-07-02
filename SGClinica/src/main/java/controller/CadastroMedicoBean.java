package controller;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import model.Convenio;
import model.Medico;
import model.Sexo;
import service.CadastroMedicoService;
import service.NegocioException;
import util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroMedicoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Medico medico;
	private List<Sexo> sexos;
	private List<String> telefones;
	private List<Convenio> convenios;
	private String telefoneSelecionado;
	@Inject
	private CadastroMedicoService cadastroMedicoService;

	@PostConstruct
	public void init() {
		this.limpar();
		sexos = Arrays.asList(Sexo.values());
	}

	public void salvar() {
		try {
			telefones.remove(1L);
			medico.setTelefones(telefones);
			this.cadastroMedicoService.salvar(medico);
			FacesUtil.addSuccessMessage("Médico salvo com sucesso!");
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
		this.medico = new Medico();
		telefones = new LinkedList<String>();

		convenios = new LinkedList<Convenio>();
		convenios.add(new Convenio());
	}

	public Medico getMedico() {
		return medico;
	}

	public void setMedico(Medico medico) {
		this.medico = medico;
	}

	public List<Sexo> getSexos() {
		return sexos;
	}

	public List<String> getTelefones() {
		return telefones;
	}
	
	public String getTelefoneSelecionado() {
		return telefoneSelecionado;
	}
	public void setTelefoneSelecionado(String telefoneSelecionado) {
		this.telefoneSelecionado = telefoneSelecionado;
	}
	public void addTelefone() {
		
		this.telefones.add(telefoneSelecionado);

	}

public void removeTelefone() {
	this.telefones.remove(telefoneSelecionado+0L);
}
	public List<Convenio> getConvenios() {
		return convenios;
	}

}
