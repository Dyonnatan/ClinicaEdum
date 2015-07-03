package controller;

import java.io.Serializable;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import dao.GenericDAO;
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
	private List<Convenio> conveniosExistente;
	private String telefoneSelecionado;
	private Convenio convenioSelecionado;

	@Inject
	private CadastroMedicoService cadastroMedicoService;
	@Inject
	private GenericDAO<Convenio> convDAO;

	@PostConstruct
	public void init() {
		this.limpar();
		conveniosExistente = convDAO.buscarTodos(Convenio.class);
		sexos = Arrays.asList(Sexo.values());
	}

	public void salvar() {
		try {
			medico.setTelefones(telefones);
			medico.setConvenios(convenios);
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
		convenioSelecionado = new Convenio();
		convenios = new LinkedList<Convenio>();
	}

	public Medico getMedico() {
		return medico;
	}

	public void setMedico(Medico medico) {
		if(medico!=null){
			convenios = medico.getConvenios();
			telefones = medico.getTelefones();
		}
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
		this.telefones.remove(telefoneSelecionado);
	}

	public List<Convenio> getConvenios() {
		return convenios;
	}

	public List<Convenio> getConveniosExistente() {
		return conveniosExistente;
	}

	public Convenio getConvenioSelecionado() {
		return convenioSelecionado;
	}

	public void setConvenioSelecionado(Convenio convenioSelecionado) {
		this.convenioSelecionado = convenioSelecionado;
	}

	public void addConvenio() {
		this.convenios.add(convenioSelecionado);
	}

	public void removeConvenio() {
		this.convenios.remove(convenioSelecionado);
	}

}
