package controller;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import dao.GenericDAO;
import model.Cliente;
import model.Consulta;
import model.Convenio;
import model.Medico;
import model.Procedimento;
import service.CadastroConsultaService;
import service.NegocioException;
import util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroConsultaBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Medico medicoSel;
	private Cliente clienteSel;
	private Convenio convenioSel;
	private List<Convenio> convenios;
	private List<Medico> medicos;
	private List<Cliente> clientes;
	private Consulta consulta;
	private Collection<Procedimento> procedimentos;
	private Procedimento procedimentoSel;

	@Inject
	private CadastroConsultaService cadastroConsultaService;
	@Inject
	private GenericDAO<Cliente> cliDAO;
	@Inject
	private GenericDAO<Medico> medDAO;
	@Inject
	private GenericDAO<Convenio> convDAO;

	@PostConstruct
	public void init() {
		this.limpar();
		convenios = convDAO.buscarTodos(Convenio.class);
		medicos = medDAO.buscarTodos(Medico.class);
		clientes = cliDAO.buscarTodos(Cliente.class);
	}

	public void salvar() {
		try {
			consulta.setConvenio(convenioSel);
			consulta.setCliente(clienteSel);
			consulta.setMedico(medicoSel);
			consulta.setProcedimentos(procedimentos);
			this.cadastroConsultaService.salvar(consulta);
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
		this.consulta = new Consulta();
		convenioSel = new Convenio();
		medicoSel = new Medico();
		clienteSel = new Cliente();
	}

	public Medico getMedicoSel() {
		return medicoSel;
	}

	public void setMedicoSel(Medico medicoSel) {
		this.medicoSel = medicoSel;
	}

	public Cliente getClienteSel() {
		return clienteSel;
	}

	public void setClienteSel(Cliente clienteSel) {
		this.clienteSel = clienteSel;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}

	public Convenio getConvenioSel() {
		return convenioSel;
	}

	public void setConvenioSel(Convenio convenioSel) {
		this.convenioSel = convenioSel;
	}

	public Consulta getConsulta() {
		return consulta;
	}

	public void setConsulta(Consulta consulta) {
		this.consulta = consulta;
	}

	public List<Convenio> getConvenios() {
		return convenios;
	}

	public List<Medico> getMedicos() {
		return medicos;
	}

	public List<Cliente> getClientes() {
		return clientes;
	}

	public Collection<Procedimento> getProcedimentos() {
		return procedimentos;
	}

	public Procedimento getProcedimentoSel() {
		return procedimentoSel;
	}

	public void setProcedimentoSel(Procedimento procedimentoSel) {
		this.procedimentoSel = procedimentoSel;
	}

}
