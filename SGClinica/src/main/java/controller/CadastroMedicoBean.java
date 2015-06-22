package controller;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import model.Medico;
import model.Sexo;
import service.CadastroMedicoService;
import service.NegocioException;
import util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroMedicoBean implements Serializable{

	private static final long serialVersionUID = 1L;

	private Medico medico;
	private List<Sexo> sexos;
//	private LinkedList<Long> telefones;
	@Inject
	private CadastroMedicoService cadastroMedicoService;

	@PostConstruct
	public void init() {
		this.limpar();
		sexos = Arrays.asList(Sexo.values());
	}
	
	public void salvar() {
		try {
			this.cadastroMedicoService.salvar(medico);
			FacesUtil.addSuccessMessage("Médico salvo com sucesso!");
		} catch (NegocioException e) {
			FacesUtil.addErrorMessage(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			FacesUtil.addErrorMessage("Erro desconhecido. Contatar o administrador");
		}
		
		this.limpar();
	}
	
	public void limpar() {
		this.medico = new Medico();		
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
	

}
