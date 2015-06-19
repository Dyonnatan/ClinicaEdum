package controller;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import service.CadastroClienteService;
import service.NegocioException;
import util.jsf.FacesUtil;
import model.Cliente;

@Named
@ViewScoped
public class CadastroClienteBean {


	private Cliente cliente;
	
	@Inject
	private CadastroClienteService cadastroClienteService;

	@PostConstruct
	public void init() {
		this.limpar();
	}
	
	public void salvar() {
		try {
			this.cadastroClienteService.salvar(cliente);
			FacesUtil.addSuccessMessage("Cliente salvo com sucesso!");
		} catch (NegocioException e) {
			FacesUtil.addErrorMessage(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			FacesUtil.addErrorMessage("Erro desconhecido. Contatar o administrador");
		}
		
		this.limpar();
	}
	
	public void limpar() {
		this.cliente = new Cliente();
	}

	public Cliente getCliente() {
		return cliente;
	}
	
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

}
