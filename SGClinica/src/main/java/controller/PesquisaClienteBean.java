package controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import service.NegocioException;
import util.jsf.FacesUtil;
import model.Cliente;
import dao.GenericDAO;

@Named
@ViewScoped
public class PesquisaClienteBean implements Serializable{

	private static final long serialVersionUID = 1L;

	@Inject
	private GenericDAO<Cliente> clienteDAO;

	private List<Cliente> clientes;
//	private List<Long> telefones;
	private Cliente clienteSelecionado;

	@PostConstruct
	public void init() {
		this.clientes = this.clienteDAO.buscarTodos(Cliente.class);
//		telefones = this.clienteSelecionado.getTelefones();
	}

	public void excluir() {
		try {
			clienteDAO.excluir(Cliente.class, clienteSelecionado);
			clientes.remove(clienteSelecionado);
			FacesUtil.addSuccessMessage("Excluiu o modelo");
		} catch (NegocioException e) {
			FacesUtil.addErrorMessage(e.getMessage());
		}

	}

	public List<Cliente> getClientes() {
		return clientes;
	}

	public Cliente getClienteSelecionado() {
		return clienteSelecionado;
	}
	
	public void setClienteSelecionado(Cliente clienteSelecionado) {
		this.clienteSelecionado = clienteSelecionado;
	}
	
	public void buscarTelefones() {
		
	}
}
